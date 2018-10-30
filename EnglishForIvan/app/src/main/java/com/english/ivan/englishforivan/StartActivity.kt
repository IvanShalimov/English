package com.english.ivan.englishforivan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.english.ivan.englishforivan.exercise.plural_nouns.PluralNounsFragment
import com.english.ivan.englishforivan.exercise.select_exercise.SelectExerciseFragment
import com.english.ivan.englishforivan.exercise.time.TimeFragment

class StartActivity : AppCompatActivity(), SelectExerciseFragment.OnFragmentInteractionListener {
    override fun onNewScreenOpen(screen: String) {

        val fragment:Fragment
        when(screen){
            "Plural nouns" -> {
                fragment = PluralNounsFragment()
            }
            "What's the time?" -> {
                fragment = TimeFragment()
            }
            "Countable and Uncountable nouns" -> {
                fragment = PluralNounsFragment()
            }
            "Present Simple" -> {
                fragment = PluralNounsFragment()
            }
            else -> {
                fragment = PluralNounsFragment()
            }

        }
        supportFragmentManager?.let {
            it.beginTransaction()
                .replace(R.id.fragment_host,fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        supportFragmentManager?.let {
            it.beginTransaction()
                .add(R.id.fragment_host,
                    SelectExerciseFragment()
                )
                .commit()
        }
    }

    override fun onBackPressed() {

        val count =supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }

    }
}