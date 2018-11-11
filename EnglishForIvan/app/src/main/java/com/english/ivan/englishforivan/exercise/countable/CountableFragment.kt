package com.english.ivan.englishforivan.exercise.countable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.english.ivan.englishforivan.R
import kotlinx.android.synthetic.main.fragment_countable.view.*
import java.util.*

class CountableFragment : Fragment(), View.OnClickListener {

    lateinit var root:View

    val variant = arrayOf("market","carrot","apples","onion","potato","bank","chair",
        "man","loaf of bread","a bottle of oil","steaks","oil","bread","cheese","meat","tea","coffee","sugar"
        ,"metal","wood","plastic","paper","history","art","music","English","Russian"
        ,"advice","love","time","education","information","chairs","tables","sofas"
        ,"suitcase","bags","euros","pounds","dollars")

    val arrayCountable = arrayOf("market","carrot","apples","onion","potato","bank","chair",
        "man","loaf of bread","a bottle of oil","steaks")

    val arrayUncountable = arrayOf("oil","bread","cheese","meat","tea","coffee","sugar"
        ,"metal","wood","plastic","paper","history","art","music","English","Russian"
        ,"advice","love","time","education","information","chairs","tables","sofas"
        ,"suitcase","bags","euros","pounds","dollars")

    var currentSelect:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_countable, container, false)


        root.countable_button.setOnClickListener(this)
        root.uncountable_button.setOnClickListener(this)

        generateWord()
        return root
    }

    private fun generateWord(){
        currentSelect = Random().nextInt(variant.size)
        root.nouns_exercise_second.text = variant[currentSelect]
    }

    private fun checkResult(v: View?):Boolean{
        val text = root.nouns_exercise_second.text.toString()
        when (v?.id){
            R.id.countable_button -> {
                return arrayCountable.contains(text)
            }
            R.id.uncountable_button -> {
                return arrayUncountable.contains(text)
            }
        }
        return false
    }

    override fun onClick(v: View?) {
        if(checkResult(v)) {
            root.result_image.setImageResource(R.drawable.ic_done_black_24dp)
        } else {
            root.result_image.setImageResource(R.drawable.ic_warning_black_24dp)
        }

        generateWord()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
