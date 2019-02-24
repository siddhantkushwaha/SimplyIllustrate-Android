package com.simplyillustrate.simplyillustrate.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.simplyillustrate.simplyillustrate.R

class QuestionsFragment : Fragment() {

    companion object {
        private val tag = QuestionsFragment::class.java.toString()
        fun getNewInstance(): QuestionsFragment {
            return QuestionsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.i(tag, "Fragment Opened")

        return inflater.inflate(R.layout.questions_fragment, container, false)
    }
}