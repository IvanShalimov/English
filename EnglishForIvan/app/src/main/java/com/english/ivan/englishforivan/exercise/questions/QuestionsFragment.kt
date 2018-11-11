package com.english.ivan.englishforivan.exercise.questions


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager

import com.english.ivan.englishforivan.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_questions.*

class QuestionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_questions, container, false)
        activity?.let {
            val fragmentAdapter  = SectionPageAdapter(it.supportFragmentManager)
            val pager:ViewPager = root.findViewById(R.id.pager)
            pager.adapter = fragmentAdapter

            val tabs:TabLayout = root.findViewById(R.id.tabs)
            tabs.setupWithViewPager(pager)
        }

        return root
    }

}
