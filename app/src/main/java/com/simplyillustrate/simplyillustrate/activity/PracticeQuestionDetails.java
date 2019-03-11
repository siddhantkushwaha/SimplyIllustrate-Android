package com.simplyillustrate.simplyillustrate.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.simplyillustrate.simplyillustrate.R;

public class PracticeQuestionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_question_details);
//        Bundle bundle = getIntent().getExtras();
//        PracticeQuestion question = bundle.getParcelable("practiceQuestion");
//        Log.d("QuestionDetails", question.getQuestion());
//        TextView title = findViewById(R.id.question_title);
//        TextView answer1 = findViewById(R.id.anwser_1);
//        TextView answer2 = findViewById(R.id.anwser_2);
//        TextView answer3 = findViewById(R.id.anwser_3);
//        TextView answer4 = findViewById(R.id.anwser_4);
//        ArrayList<String> answers = new ArrayList<>();
//        answers.addAll(question.getIncorrectAnswers());
//        answers.add(question.getCorrectAnswer());
//        Collections.shuffle(answers);
//        title.setText(getString(R.string.question) + question.getQuestion());
//        answer1.setText(getString(R.string.one) + answers.get(0));
//        answer2.setText(getString(R.string.two) + answers.get(1));
//        answer3.setText(getString(R.string.three) + answers.get(2));
//        answer4.setText(getString(R.string.four) + answers.get(3));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        System.out.println(bundle);

        if (bundle != null)
            System.out.println(bundle.getParcelable("question").toString());
    }
}
