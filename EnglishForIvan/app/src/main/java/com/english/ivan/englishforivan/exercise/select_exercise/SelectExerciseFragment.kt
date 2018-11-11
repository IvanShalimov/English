package com.english.ivan.englishforivan.exercise.select_exercise

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.english.ivan.englishforivan.R
import kotlinx.android.synthetic.main.fragment_select_exercise.view.*
import androidx.recyclerview.widget.DividerItemDecoration




class SelectExerciseFragment : Fragment(), OnSelectLesson {
    override fun onSelect(lesson: String) {
        callback.onNewScreenOpen(lesson)
    }

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_exercise, container, false)
        view.list_lesson.layoutManager = LinearLayoutManager(context)
        //Добавляем разделитель для нашей ориентации(В данном случае для вертикальной)
        val dividerItemDecoration = DividerItemDecoration(
            view.list_lesson.context,
            (view.list_lesson.layoutManager as LinearLayoutManager).orientation
        )
        view.list_lesson.addItemDecoration(dividerItemDecoration)

        view.list_lesson.adapter = LessonAdapter(
            listOf(
                "Plural nouns",
                "What's the time?",
                "Countable and Uncountable nouns",
                "Present Simple"
            ), this
        )
        return view
    }

    lateinit var callback: OnFragmentInteractionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            callback = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onNewScreenOpen(screen: String)
    }

}
