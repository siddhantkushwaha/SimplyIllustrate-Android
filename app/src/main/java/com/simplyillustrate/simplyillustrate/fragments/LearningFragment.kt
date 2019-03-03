package com.simplyillustrate.simplyillustrate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.adapters.QuestionsAdapter
import com.simplyillustrate.simplyillustrate.api.RestAPI
import com.simplyillustrate.simplyillustrate.models.PracticeQuestion
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.learning_fragment.view.*
import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set

class LearningFragment : Fragment() {

    private val practiceQuestions = ArrayList<PracticeQuestion>()

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.learning_fragment, container, false)

        initViews(rootView)

        getQuestions(rootView)

        return rootView
    }

    private fun getQuestions(rootView: View) {

        val map = HashMap<String, String>()
        map["tags"] = "5c742bab10a0e463cc1460a3"

        compositeDisposable =
            RestAPI.subscribeToPracticeQuestions(map, object : DisposableObserver<PracticeQuestion>() {
                override fun onComplete() {
                    // pass
                }

                override fun onNext(t: PracticeQuestion) {
                    practiceQuestions.add(t)

                    (rootView.rv_questions.adapter as? QuestionsAdapter)?.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })
    }

    private fun initViews(rootView: View) {

        rootView.rv_questions.itemAnimator = DefaultItemAnimator()
        rootView.rv_questions.adapter =
            QuestionsAdapter(practiceQuestions,
                QuestionsAdapter.ListItemClickListener { view, clickedItemIndex ->
                    Toast.makeText(
                        context,
                        "Position $clickedItemIndex",
                        Toast.LENGTH_SHORT
                    ).show()
                })
        rootView.rv_questions.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroy() {
        super.onDestroy()

        if (::compositeDisposable.isInitialized) {
            compositeDisposable.clear()
        }
    }
}