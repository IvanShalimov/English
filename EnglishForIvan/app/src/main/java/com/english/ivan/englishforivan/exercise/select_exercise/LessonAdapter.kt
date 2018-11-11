package com.english.ivan.englishforivan.exercise.select_exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.english.ivan.englishforivan.R
import kotlinx.android.synthetic.main.lesson_list_item.view.*

class LessonAdapter(var lessons:List<String>,
                    val callback: OnSelectLesson
): RecyclerView.Adapter<LessonItemViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LessonItemViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.lesson_list_item,p0,false)
        return LessonItemViewHolder(view, callback)
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

    override fun onBindViewHolder(p0: LessonItemViewHolder, p1: Int) {
        p0.bind(lessons[p1])
    }
}

interface OnSelectLesson{
    fun onSelect(lesson:String)
}

class LessonItemViewHolder(view: View,val callback: OnSelectLesson):RecyclerView.ViewHolder(view){
    fun bind(item:String){
        itemView.action_button.text = item
        itemView.action_button.setOnClickListener { callback.onSelect(item) }
    }
}