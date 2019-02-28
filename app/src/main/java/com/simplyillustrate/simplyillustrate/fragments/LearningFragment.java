package com.simplyillustrate.simplyillustrate.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.simplyillustrate.simplyillustrate.R;
import com.simplyillustrate.simplyillustrate.activity.HomeActivity;
import com.simplyillustrate.simplyillustrate.activity.PostQuestionActivity;
import com.simplyillustrate.simplyillustrate.activity.PracticeQuestionDetails;
import com.simplyillustrate.simplyillustrate.adapters.QuestionsAdapter;
import com.simplyillustrate.simplyillustrate.models.PracticeQuestion;
import com.simplyillustrate.simplyillustrate.services.ApiService;
import com.simplyillustrate.simplyillustrate.services.ApiUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LearningFragment extends Fragment {

    private ArrayList<PracticeQuestion> practiceQuestions;
    private RecyclerView questionsList;
    private Toast mToast;
    private ApiService mApiService;
    private Observable<List<PracticeQuestion>> practiceQuestionObservable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.learning_fragment, container, false);
        practiceQuestions = new ArrayList<>();
        getQuestions(rootView);
        return rootView;
    }

    private void getQuestions(View rootView) {
        HashMap<String, String> queryData = new HashMap<>();
        queryData.put("tags", "5c742bab10a0e463cc1460a3");
        mApiService = ApiUtils.getApiService();
        practiceQuestionObservable = mApiService.getPracticeQuestions(queryData);
        compositeDisposable.add(practiceQuestionObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<List<PracticeQuestion>, Observable<PracticeQuestion>>)
                        practiceQuestions -> Observable.fromIterable(practiceQuestions))
                .subscribeWith(new DisposableObserver<PracticeQuestion>() {
                    @Override
                    public void onNext(PracticeQuestion practiceQuestion) {
                        practiceQuestions.add(practiceQuestion);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        initViews(rootView);
                    }
                }));
    }

    public void initViews(View rootView) {
        questionsList = rootView.findViewById(R.id.rv_questions);
        questionsList.setItemAnimator(new DefaultItemAnimator());
        QuestionsAdapter.ListItemClickListener listItemClickListener = (view,position) ->{
            Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
            /*
            Intent intent = new Intent(getActivity().getBaseContext(), PracticeQuestionDetails.class);
            Bundle args = new Bundle();
            args.putParcelable("practiceQuestion", practiceQuestions.get(position));
            intent.putExtras(args);
            startActivity(intent);
            */
        };
        questionsList.setAdapter(new QuestionsAdapter(practiceQuestions, listItemClickListener));
        questionsList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }
}
