package com.simplyillustrate.simplyillustrate.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.entity.PracticeQuestion
import kotlinx.android.synthetic.main.activity_practice_question_details.*
import java.util.*

class PracticeQuestionDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_question_details)

        val bundle = intent.extras
        if (bundle != null) {
            val question = bundle.getParcelable<PracticeQuestion>("question")
            val answers = ArrayList<String>()
            answers.addAll(question?.incorrectAnswers!!)
            answers.add(question.correctAnswer!!)
            answers.shuffle()
            question_title.text = getString(R.string.question) + question.question!!

            answer1.text = getString(R.string.one) + answers[0]
            answer2.text = getString(R.string.two) + answers[1]
            answer3.text = getString(R.string.three) + answers[2]
            answer4.text = getString(R.string.four) + answers[3]
        }
    }
}