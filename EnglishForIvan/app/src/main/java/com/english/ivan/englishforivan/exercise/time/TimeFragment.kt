package com.english.ivan.englishforivan.exercise.time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.english.ivan.englishforivan.R
import kotlinx.android.synthetic.main.fragment_time.view.*
import java.util.*

class TimeFragment : Fragment() {

    lateinit var root:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_time, container, false)

        root.generate_button.setOnClickListener { root.clock_image.setImageResource(generate()) }
        return root
    }


    private fun generate():Int{
        return when(Random().nextInt(12)){
            0 -> return R.drawable.clock1
            1 -> return R.drawable.clock2
            2 -> return R.drawable.clock3
            3 -> return R.drawable.clock4
            4 -> return R.drawable.clock5
            5 -> return R.drawable.clock6
            6 -> return R.drawable.clock7
            7 -> return R.drawable.clock8
            8 -> return R.drawable.clock9
            9 -> return R.drawable.clock10
            10 -> return R.drawable.clock11
            11 -> return R.drawable.clock12

            else -> 0
        }
    }
}
