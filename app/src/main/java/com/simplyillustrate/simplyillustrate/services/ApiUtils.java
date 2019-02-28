package com.simplyillustrate.simplyillustrate.services;

public class ApiUtils {

    private ApiUtils() {

    }

    public static final String BASE_URL = "http://172.20.10.3:3000/";

    public static ApiService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
