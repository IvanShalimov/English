package com.english.ivan.englishforivan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.english.ivan.englishforivan.exercise.countable.CountableFragment
import com.english.ivan.englishforivan.exercise.plural_nouns.PluralNounsFragment
import com.english.ivan.englishforivan.exercise.present_simple.PresentSimpleFragment
import com.english.ivan.englishforivan.exercise.select_exercise.SelectExerciseFragment
import com.english.ivan.englishforivan.exercise.time.TimeFragment

class StartActivity : AppCompatActivity(), SelectExerciseFragment.OnFragmentInteractionListener {
    override fun onNewScreenOpen(screen: String) {

        val fragment: Fragment
        when(screen){
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
