package com.simplyillustrate.simplyillustrate.activity

import android.os.Bundle
import android.util.Log
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.adapters.AnswerAdapter
import com.simplyillustrate.simplyillustrate.api.RestAPI
import com.simplyillustrate.simplyillustrate.entity.Answer
import com.simplyillustrate.simplyillustrate.entity.Question
import kotlinx.android.synthetic.main.activity_question_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionDetails : AppCompatActivity() {

    private var question: Question? = null
    private var answers: ArrayList<Answer> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_details)

        val bundle = intent.extras
        question = bundle?.getParcelable("question")

        if (question?.id == null) {
            finish()
        }

        question_title.text = question?.title
        question_created_by.text = question?.createdBy
        question_description.text = question?.description
        question_difficulty.text = question?.difficulty

        val adapter = AnswerAdapter(answers)
        listview_answers.layoutManager = LinearLayoutManager(this@QuestionDetails)
        listview_answers.adapter = adapter



        RestAPI.getAnswersByQuestionId(question?.id!!, object : Callback<ArrayList<Answer>> {
            override fun onFailure(call: Call<ArrayList<Answer>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ArrayList<Answer>>, response: Response<ArrayList<Answer>>
            ) {
                if (response.isSuccessful) {
                    answers.clear()
                    response.body()?.forEach {
                        answers.add(it)
                        Log.i("ANSWERS", "${it.timestamp}")
                        Log.i("ANSWERS", "${it.id}")
                    }
                    adapter.notifyDataSetChanged()
                }
            }

        })
    }
}
