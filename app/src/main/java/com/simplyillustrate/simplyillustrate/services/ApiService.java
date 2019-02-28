package com.simplyillustrate.simplyillustrate.services;

import com.simplyillustrate.simplyillustrate.models.PracticeQuestion;
import com.simplyillustrate.simplyillustrate.models.Question;
import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ApiService {

    @POST("/addQuestion")
    @FormUrlEncoded
    Observable<Question> saveQuestion(@Field("user") String user,
                                      @Field("title") String title,
                                      @Field("difficulty") String difficulty,
                                      @Field("tags") ArrayList<String> tags,
                                      @Field("description") String description);

    @GET("/Learning")
    Observable<List<PracticeQuestion>> getPracticeQuestions(@QueryMap Map<String, String> queryValues);
}
