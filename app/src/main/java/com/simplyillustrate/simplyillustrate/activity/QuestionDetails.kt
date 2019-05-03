package com.simplyillustrate.simplyillustrate.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.entity.Question
import kotlinx.android.synthetic.main.activity_question_details.*

class QuestionDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_details)

        val bundle = intent.extras
        if (bundle != null) {
            val question = bundle.getParcelable<Question>("question")
            question_title.text = question.title
            question_created_by.text = question.createdBy
            question_description.text = question.description
            question_difficulty.text = question.difficulty
        }
    }
}
