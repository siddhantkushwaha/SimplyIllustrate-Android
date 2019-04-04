package com.simplyillustrate.simplyillustrate.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.api.RestAPI
import com.simplyillustrate.simplyillustrate.entity.Question
import kotlinx.android.synthetic.main.activity_post_question.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostQuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_post_question)

        btn_submit.setOnClickListener {

            sendQuestion()
        }
    }

    private fun sendQuestion() {

        val question = Question()

        question.title = tv_title.text.toString().trim()
        question.difficulty = tv_difficulty.text.toString().trim()
        question.tags = arrayListOf("5c7c15892fda391295a2e78e")
                //arrayListOf(tv_title.text.toString().trim())
        question.description = tv_description.text.toString().trim()

        question.createdBy = "5c7c0f7d953d2810dae02a4f"

        RestAPI.addQuestion(question, object : Callback<JsonObject> {

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                t.printStackTrace()
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                if (response.isSuccessful) {
                    Toast.makeText(this@PostQuestionActivity, "Question Added", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}