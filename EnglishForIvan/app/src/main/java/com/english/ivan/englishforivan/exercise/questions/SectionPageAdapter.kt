package com.english.ivan.englishforivan.exercise.questions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.english.ivan.englishforivan.exercise.questions.section.BeFragment
import com.english.ivan.englishforivan.exercise.questions.section.DoFragment
import com.english.ivan.englishforivan.exercise.questions.section.HaveGotFragment

class SectionPageAdapter(fragmentManager:FragmentManager):FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
           0 -> {
                DoFragment()
           }
           1 -> {
               BeFragment()
           }
           else -> {
               HaveGotFragment()
           }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Do/Does"
            1 -> "To Be"
            else -> "Have/Has got"
        }
    }
}