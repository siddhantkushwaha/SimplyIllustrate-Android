package com.simplyillustrate.simplyillustrate.activity;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.simplyillustrate.simplyillustrate.R;
import com.simplyillustrate.simplyillustrate.models.Question;
import com.simplyillustrate.simplyillustrate.services.ApiService;
import com.simplyillustrate.simplyillustrate.services.ApiUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.HashMap;

public class PostQuestionActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextInputEditText title;
    @BindView(R.id.tv_difficulty)
    TextInputEditText difficulty;
    @BindView(R.id.tv_tags)
    TextInputEditText tags;
    @BindView(R.id.tv_description)
    TextInputEditText description;
    @BindView(R.id.btn_submit)
    MaterialButton sbtButton;
    @BindView(R.id.tv_response)
    TextInputEditText response;

    private ApiService mApiService;
    HashMap<String, String> mapTags = new HashMap<>();

    private Observable<Question> questionObservable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);
        ButterKnife.bind(this);
        mApiService = ApiUtils.getApiService();
        mapTags.put("Computer Science", "5c6e63642875e201dc4d7dae");
        mapTags.put("mathematics", "5c6e63f505c62401dc334110");
        sbtButton.setOnClickListener(v -> {
            String Title = title.getText().toString().trim();
            String Difficulty = difficulty.getText().toString().trim();
            String Tags = tags.getText().toString().trim();
            String Description = description.getText().toString().trim();
            sendPost(Title, Difficulty, Tags, Description);
        });
    }

    @SuppressLint("CheckResult")
    public void sendPost(String title, String difficulty, String tags, String description) {
        String user = "5c6a86d92984710fd5e8403a";
        ArrayList<String> tagKeys = new ArrayList<>();
        tagKeys.add(mapTags.get(tags));

        questionObservable = mApiService.saveQuestion(user, title, difficulty, tagKeys, description);
        compositeDisposable.add(questionObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Question>() {
                    @Override
                    public void onNext(Question question) {
                        showResponse("New Question successfully added at "+question.getCreatedAt());
                    }

                    @Override
                    public void onError(Throwable e) {
                        showResponse("Unable to add question at the moment");
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public void showResponse(String res) {
        response.setText(res);
    }
}
