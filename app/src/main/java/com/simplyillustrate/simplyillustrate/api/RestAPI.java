package com.simplyillustrate.simplyillustrate.api;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.simplyillustrate.simplyillustrate.pojo.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.io.IOException;

public class RestAPI {

    private static final String BASE_URL = "http://192.168.43.140:3000/";
    private static final String URL_WRITE_USER = "writeUser";

    private static final Gson gson = new GsonBuilder().setLenient().create();
    private static final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

    //almost each api should have a synchronous and an asynchronous call
    private interface WriteUserApi {

        @Headers("Content-Type: application/json")
        @POST(URL_WRITE_USER)
        Call<JsonObject> writeUser(@Body User user);
    }

    //synchronous call
    public static Response<JsonObject> requestWriteUser(@NonNull User user) throws IOException {

        WriteUserApi writeUserApi = retrofit.create(WriteUserApi.class);
        Call<JsonObject> call = writeUserApi.writeUser(user);
        return call.execute();
    }

    //asynchronous call
    public static void requestWriteUser(@NonNull User user, @NonNull Callback<JsonObject> callback) {

        WriteUserApi writeUserApi = retrofit.create(WriteUserApi.class);
        Call<JsonObject> call = writeUserApi.writeUser(user);
        call.enqueue(callback);
    }
}
