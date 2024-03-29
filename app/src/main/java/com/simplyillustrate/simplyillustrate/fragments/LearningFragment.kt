package com.simplyillustrate.simplyillustrate.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.activity.PracticeQuestionDetails
import com.simplyillustrate.simplyillustrate.adapters.QuestionsAdapter
import com.simplyillustrate.simplyillustrate.api.RestAPI
import com.simplyillustrate.simplyillustrate.entity.PracticeQuestion
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.learning_fragment.view.*
import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set

class LearningFragment : Fragment() {

    private lateinit var rootView: View

    private val practiceQuestions = ArrayList<PracticeQuestion>()

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.learning_fragment, container, false)

        initViews()

        getQuestions()

        return rootView
    }

    private fun getQuestions() {

        val map = HashMap<String, String>()
        map["tags"] = "5c6e63642875e201dc4d7dae"

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

    private fun initViews() {

        rootView.rv_questions.itemAnimator = DefaultItemAnimator()
        rootView.rv_questions.adapter =
            QuestionsAdapter(practiceQuestions,
                QuestionsAdapter.ListItemClickListener { _, clickedItemIndex ->
                    Toast.makeText(
                        context,
                        "Position $clickedItemIndex",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(context, PracticeQuestionDetails::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable("question", practiceQuestions[clickedItemIndex])
                    intent.putExtras(bundle)

                    println(practiceQuestions[clickedItemIndex])

                    startActivity(intent)
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