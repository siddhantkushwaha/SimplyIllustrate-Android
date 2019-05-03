package com.simplyillustrate.simplyillustrate.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simplyillustrate.simplyillustrate.R
import com.simplyillustrate.simplyillustrate.entity.PracticeQuestion
import kotlinx.android.synthetic.main.activity_practice_question_details.*
import java.util.*

class PracticeQuestionDetails : AppCompatActivity() {

    var question: PracticeQuestion? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_question_details)

        val bundle = intent.extras
        if (bundle != null) {
            question = bundle.getParcelable<PracticeQuestion>("question")
            val answers = ArrayList<String>()
            answers.addAll(question?.incorrectAnswers!!)
            answers.add(question?.correctAnswer!!)
            answers.shuffle()
            question_title.text = getString(R.string.question) + question?.question!!

            answer1.text = getString(R.string.one) + answers[0]
            answer2.text = getString(R.string.two) + answers[1]
            answer3.text = getString(R.string.three) + answers[2]
            answer4.text = getString(R.string.four) + answers[3]
        }
    }

    fun submitAnswer(view: View) {

        val ans = (view as TextView).text.toString()
        Log.i("PracticeQuestionDetails", ans)
        Log.i("PracticeQuestionDetails", question?.correctAnswer.toString())

        var message = "Incorrect Answer."
        if (question?.correctAnswer == ans.substring(2)) {
            Log.i("PracticeQuestionDetails", "Correct Answer")
            message = "Correct Answer."

            view.setBackgroundColor(Color.GREEN)
        } else
            view.setBackgroundColor(Color.RED)

        Toast.makeText(this@PracticeQuestionDetails, message, Toast.LENGTH_SHORT).show()
    }
}