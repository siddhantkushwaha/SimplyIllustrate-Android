package com.simplyillustrate.simplyillustrate.api;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.simplyillustrate.simplyillustrate.entity.PracticeQuestion;
import com.simplyillustrate.simplyillustrate.entity.Question;
import com.simplyillustrate.simplyillustrate.entity.User;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestAPI {

    private static final String BASE_URL = "http://10.0.32.66:3000/";

    private static final Gson gson = new GsonBuilder().setLenient().create();
    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson));
    private static final Retrofit retrofit = retrofitBuilder.build();
    private static final Retrofit retrofitRx = retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();


    /* APIs and functions begin from here */


    /* Api and function(s) to write a user */

    private interface WriteUserApi {

        @Headers("Content-Type: application/json")
        @POST("writeUser")
        Call<JsonObject> writeUser(@Body User user);
    }

    public static void requestWriteUser(@NonNull User user, @NonNull Callback<JsonObject> callback) {

        WriteUserApi writeUserApi = retrofit.create(WriteUserApi.class);
        Call<JsonObject> call = writeUserApi.writeUser(user);
        call.enqueue(callback);
    }


    /* Api and function(s) to add a new question */

    private interface AddQuestionApi {

        @Headers("Content-Type: application/json")
        @POST("addQuestion")
        Call<JsonObject> addQuestion(@Body Question question);
    }

    public static void addQuestion(@NonNull Question question, @NonNull Callback<JsonObject> callback) {

        AddQuestionApi addQuestionApi = retrofit.create(AddQuestionApi.class);
        Call<JsonObject> call = addQuestionApi.addQuestion(question);
        call.enqueue(callback);
    }


    /* Api and function(s) get practice questions */

    private interface GetPracticeQuestionsApi {

        @Headers("Content-Type: application/json")
        @GET("/learning")
        Observable<List<PracticeQuestion>> getPracticeQuestions(@QueryMap Map<String, String> queryValues);
    }

    public static CompositeDisposable subscribeToPracticeQuestions(HashMap<String, String> queryData, DisposableObserver<PracticeQuestion> disposableObserver) {


        GetPracticeQuestionsApi getPracticeQuestionsApi = retrofitRx.create(GetPracticeQuestionsApi.class);
        Observable<List<PracticeQuestion>> practiceQuestionObservable = getPracticeQuestionsApi.getPracticeQuestions(queryData);

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(practiceQuestionObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<List<PracticeQuestion>, Observable<PracticeQuestion>>) Observable::fromIterable).subscribeWith(disposableObserver));

        return compositeDisposable;
    }
}