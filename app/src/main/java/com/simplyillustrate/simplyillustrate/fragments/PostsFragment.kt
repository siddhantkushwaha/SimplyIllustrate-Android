package com.simplyillustrate.simplyillustrate.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.activity.PostQuestionActivity
import kotlinx.android.synthetic.main.posts_fragment.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addPost.setOnClickListener {
            //Snackbar.make(it,"Click to create new post",Snackbar.LENGTH_SHORT).show()
            startActivity(Intent(activity, PostQuestionActivity::class.java))
        }
    }
}