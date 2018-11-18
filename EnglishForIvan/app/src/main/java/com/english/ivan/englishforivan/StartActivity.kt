package com.english.ivan.englishforivan

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.english.ivan.englishforivan.exercise.countable.CountableFragment
import com.english.ivan.englishforivan.exercise.plural_nouns.PluralNounsFragment
import com.english.ivan.englishforivan.exercise.present_simple.PresentSimpleFragment
import com.english.ivan.englishforivan.exercise.questions.QuestionsFragment
import com.english.ivan.englishforivan.exercise.select_exercise.SelectExerciseFragment
import com.english.ivan.englishforivan.exercise.time.TimeFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router

class StartActivity : AppCompatActivity(), SelectExerciseFragment.OnFragmentInteractionListener {

    lateinit var  router: Router
    private val naviganor: Navigator = Navigator {

    }

    override fun onNewScreenOpen(screen: String) {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        val fragment: Fragment
        when (screen) {
            "Plural nouns" -> {
                fragment = PluralNounsFragment()
            }
            "What's the time?" -> {
                fragment = TimeFragment()
            }
            "Countable and Uncountable nouns" -> {
                fragment = CountableFragment()
            }
            "Present Simple" -> {
                fragment = PresentSimpleFragment()
            }
            "Questions" -> {
                fragment = QuestionsFragment()
            }
            else -> {
                fragment = PluralNounsFragment()
            }

        }
        supportFragmentManager?.let {
            it.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.fragment_host, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        router = EnglishApplication.getRouter()

        supportFragmentManager?.let {
            it.beginTransaction()
                .add(
                    R.id.fragment_host,
                    SelectExerciseFragment()
                )
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        EnglishApplication.getNavigatorHolder().setNavigator(naviganor)
    }

    override fun onPause() {
        super.onPause()
        EnglishApplication.getNavigatorHolder().removeNavigator()
    }

    override fun onBackPressed() {

        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportActionBar?.let {
                it.setDisplayHomeAsUpEnabled(false)
                it.setHomeButtonEnabled(false)
            }

            supportFragmentManager.popBackStack()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
