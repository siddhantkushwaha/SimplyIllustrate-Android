package com.simplyillustrate.simplyillustrate.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager

import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.SimplyIllustrate
import com.simplyillustrate.simplyillustrate.activity.QuestionDetails
import com.simplyillustrate.simplyillustrate.adapters.QuestionsFeedAdapter
import com.simplyillustrate.simplyillustrate.api.RestAPI
import com.simplyillustrate.simplyillustrate.entity.Question
import com.simplyillustrate.simplyillustrate.entity.Tag

import kotlinx.android.synthetic.main.questions_fragment.view.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionsFragment : Fragment() {

    private lateinit var rootView: View

    private val questionsFeed = ArrayList<Question>()
    private val tagNames = ArrayList<String>()

    companion object {
        private val TAG = QuestionsFragment::class.java.toString()
        fun getNewInstance(): QuestionsFragment {
            return QuestionsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i(TAG, "Fragment Opened")

        rootView = inflater.inflate(R.layout.questions_fragment, container, false)

        initViews()

        val tagAdapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_dropdown_item,
            tagNames
        )
        rootView.tag_spinner.adapter = tagAdapter
        rootView.tag_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                adapter: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                println(SimplyIllustrate.tags[position]?.name)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        /* get tags and add to spinner */
        RestAPI.getAllTags(object : Callback<ArrayList<Tag>> {
            override fun onFailure(call: Call<ArrayList<Tag>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ArrayList<Tag>>,
                response: Response<ArrayList<Tag>>
            ) {
                /* update global variables */
                SimplyIllustrate.tags.clear()


                val tag = Tag()
                tag.id = "all"
                tag.name = "All"
                SimplyIllustrate.tags.add(tag)

                response.body()?.forEach {
                    SimplyIllustrate.tags.add(it)
                }

                tagNames.clear()
                tagNames.addAll(SimplyIllustrate.tags.map { it.name ?: "null" })
                tagAdapter.notifyDataSetChanged()
            }

        })

        return rootView
    }

    private fun initViews() {
        rootView.rv_questions.itemAnimator = DefaultItemAnimator()
        rootView.rv_questions.adapter = QuestionsFeedAdapter(questionsFeed,
            QuestionsFeedAdapter.ListItemClickListener { _, clickedItemIndex ->
                Toast.makeText(
                    context,
                    "Position $clickedItemIndex",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(context, QuestionDetails::class.java)
                val bundle = Bundle()
                bundle.putParcelable("question", questionsFeed[clickedItemIndex])
                intent.putExtras(bundle)

                println(questionsFeed[clickedItemIndex])

                startActivity(intent)
            })
        rootView.rv_questions.layoutManager = LinearLayoutManager(activity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        RestAPI.getAllQuestions(object : Callback<ArrayList<Question>> {
            override fun onFailure(call: Call<ArrayList<Question>>, t: Throwable) {

                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ArrayList<Question>>,
                response: Response<ArrayList<Question>>
            ) {

                Log.i(TAG, response.body().toString())

                questionsFeed.clear()
                response.body()?.forEach {
                    it.title = it.title?.trim { ch -> ch == '\"' }
                    questionsFeed.add(it)
                }

                rootView.rv_questions.adapter?.notifyDataSetChanged()
            }
        })
    }
}

