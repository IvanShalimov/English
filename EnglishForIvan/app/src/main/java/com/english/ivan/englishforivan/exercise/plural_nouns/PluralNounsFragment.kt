package com.english.ivan.englishforivan.exercise.plural_nouns

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.english.ivan.englishforivan.R
import kotlinx.android.synthetic.main.fragment_plural_nouns.view.*
import java.util.*

class PluralNounsFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var root:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_plural_nouns, container, false)

        root.generate_button.setOnClickListener { generateWord() }

        root.check_button.setOnClickListener { checkWord() }
        generateWord()
        return root
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    val array = arrayOf("car","box","day","bus","class","dish","watch","potato","family","baby","country","toy",
        "valley","wife","loaf","scarf","man","woman","child","person","foot","tooth","penny","fish","sheep"
        ,"clothes","jeans","shorts","scissors","trousers","glasses")

    private val arrayAnswer = arrayOf("cars","boxes","days","buses","classes","dishes","watches",
        "potatoes","families","babies","countries","toys","valleys","wives","loaves","scarves"
        ,"men","women","children","people","feet","teeth","pence","fish","sheep"
        ,"clothes","jeans","shorts","scissors","trousers","glasses")

    var currentSelect:Int = 0

    private fun generateWord(){
        currentSelect = Random().nextInt(array.size)
        root.example_word.text = array[currentSelect]
    }


    private fun checkWord(){
        if(root.editText.text.toString().toLowerCase().equals(arrayAnswer[currentSelect])){
            root.example_word.text = "Right!"
        } else {
            root.example_word.text = "Wrong!!!"
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

}
