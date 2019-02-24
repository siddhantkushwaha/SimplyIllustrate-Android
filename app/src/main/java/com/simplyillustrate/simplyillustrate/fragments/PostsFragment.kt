package com.simplyillustrate.simplyillustrate.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.simplyillustrate.simplyillustrate.R

class PostsFragment : Fragment() {

    companion object {
        private val tag = PostsFragment::class.java.toString()
        fun getNewInstance(): PostsFragment {
            return PostsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.i(tag, "Fragment Opened")

        return inflater.inflate(R.layout.posts_fragment, container, false)
    }
}