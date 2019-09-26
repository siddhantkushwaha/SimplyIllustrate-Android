package com.simplyillustrate.simplyillustrate;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.simplyillustrate.simplyillustrate.entity.Tag;

import java.util.ArrayList;
import java.util.HashMap;

import io.fabric.sdk.android.Fabric;

public class SimplyIllustrate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }

    /* global variables */
    public static String userId;

    public static ArrayList<Tag> tags = new ArrayList<>();
    public static HashMap<String, String> tagsMap = new HashMap<>();
}
