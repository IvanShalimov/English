package com.english.ivan.englishforivan.custom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Navigator
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.commands.*
import java.util.*
import androidx.fragment.app.FragmentTransaction
import com.english.ivan.englishforivan.R

class SupportAppNavigatorX(
    var activity: AppCompatActivity,
    var fragmentManager: FragmentManager,
    var containerId: Int
) : Navigator {
    var localStackCopy: LinkedList<String>? = null


    constructor(activity: AppCompatActivity, containerId: Int) : this(
        activity,
        activity.supportFragmentManager,
        containerId
    )

    override fun applyCommands(commands: Array<out Command>?) {
        fragmentManager.executePendingTransactions()

        //copy stack before apply commands
        commands?.forEach {
            applyCommand(it)
        }
    }

    private fun copyStackToLocal(){
        localStackCopy = LinkedList<String>()

        val stackSize = fragmentManager.backStackEntryCount
        var i = 0
        while (i< stackSize){
            localStackCopy!!.add(fragmentManager.getBackStackEntryAt(i).name!!)
            i++
        }
    }

    /**
     * Perform transition described by the navigation command
     *
     * @param command the navigation command to apply
     */
    protected fun applyCommand(command: Command) {
        when (command) {
            is Forward -> activityForward(command)
            is Replace -> activityReplace(command)
            is BackTo -> backTo(command)
            is Back -> fragmentBack()
        }
    }

    protected fun activityForward(command: Forward) {
        val screen = command.screen as SupportAppScreenX
        val activityIntent = screen.getActivityIntent(activity)

        // Start activity
        if (activityIntent != null) {
            val options = createStartActivityOptions(command, activityIntent)
            checkAndStartActivity(screen, activityIntent, options!!)
        } else {
            fragmentForward(command)
        }
    }

    protected fun fragmentForward(command:Forward ){
        val screen = command.screen as SupportAppScreenX

        val fragment = createFragment(screen)

        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        setupFragmentTransaction(
            command,
            fragmentManager.findFragmentById(containerId)!!,
            fragment,
            transaction
        )

        fragment?.let {
            transaction
                .replace(containerId, it)
                .addToBackStack(screen.screenKey)
                .commit()
        }


        localStackCopy!!.add(screen.screenKey)
    }

    protected fun fragmentBack() {
        if (localStackCopy!!.size > 0) {
            fragmentManager.popBackStack()
            localStackCopy!!.removeLast()
        } else {
            activityBack()
        }
    }

    protected fun activityBack() {
        activity.finish()
    }

    protected fun activityReplace(command: Replace) {
        val screen = command.screen as SupportAppScreenX
        val activityIntent = screen.getActivityIntent(activity)

        // Replace activity
        if (activityIntent != null) {
            val options = createStartActivityOptions(command, activityIntent)
            checkAndStartActivity(screen, activityIntent, options)
            activity.finish()
        } else {
            fragmentReplace(command)
        }
    }

    protected fun fragmentReplace(command: Replace) {
        val screen = command.screen as SupportAppScreenX
        val fragment = createFragment(screen)

        if (localStackCopy!!.size > 0) {
            fragmentManager.popBackStack()
            localStackCopy!!.removeLast()

            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )

            fragment?.let {
                fragmentTransaction
                    .replace(containerId, it)
                    .addToBackStack(screen.screenKey)
                    .commit()
                localStackCopy!!.add(screen.screenKey)

            }

        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )

            fragment?.let {
                fragmentTransaction
                    .replace(containerId, it)
                    .commit()
            }

        }
    }

    /**
     * Performs [BackTo] command transition
     */
    protected fun backTo(command: BackTo) {
        if (command.screen == null) {
            backToRoot()
        } else {
            val key = command.screen.screenKey
            val index = localStackCopy!!.indexOf(key)
            val size = localStackCopy!!.size

            if (index != -1) {
                for (i in 1 until size - index) {
                    localStackCopy!!.removeLast()
                }
                fragmentManager.popBackStack(key, 0)
            } else {
                backToUnexisting(command.screen as SupportAppScreenX)
            }
        }
    }

    private fun backToRoot() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        localStackCopy!!.clear()
    }

    /**
     * Override this method to setup fragment transaction [FragmentTransaction].
     * For example: setCustomAnimations(...), addSharedElement(...) or setReorderingAllowed(...)
     *
     * @param command             current navigation command. Will be only [Forward] or [Replace]
     * @param currentFragment     current fragment in container
     * (for [Replace] command it will be screen previous in new chain, NOT replaced screen)
     * @param nextFragment        next screen fragment
     * @param fragmentTransaction fragment transaction
     */
    protected fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
    }

    /**
     * Override this method to create option for start activity
     *
     * @param command        current navigation command. Will be only [Forward] or [Replace]
     * @param activityIntent activity intent
     * @return transition options
     */
    protected fun createStartActivityOptions(command: Command, activityIntent: Intent): Bundle? {
        return null
    }

    private fun checkAndStartActivity(screen: SupportAppScreenX, activityIntent: Intent, options: Bundle?) {
        // Check if we can start activity
        if (activityIntent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(activityIntent, options)
        } else {
            unexistingActivity(screen, activityIntent)
        }
    }

    /**
     * Called when there is no activity to open `screenKey`.
     *
     * @param screen         screen
     * @param activityIntent intent passed to start Activity for the `screenKey`
     */
    protected fun unexistingActivity(screen: SupportAppScreenX, activityIntent: Intent) {
        // Do nothing by default
    }

    /**
     * Creates Fragment matching `screenKey`.
     *
     * @param screen screen
     * @return instantiated fragment for the passed screen
     */
    protected fun createFragment(screen: SupportAppScreenX): Fragment? {
        val fragment = screen.getFragment()

        if (fragment == null) {
            errorWhileCreatingScreen(screen)
        }
        return fragment
    }

    /**
     * Called when we tried to fragmentBack to some specific screen (via [BackTo] command),
     * but didn't found it.
     *
     * @param screen screen
     */
    protected fun backToUnexisting(screen: SupportAppScreenX) {
        backToRoot()
    }

    protected fun errorWhileCreatingScreen(screen: SupportAppScreenX) {
        throw RuntimeException("Can't create a screen: " + screen.screenKey)
    }

}