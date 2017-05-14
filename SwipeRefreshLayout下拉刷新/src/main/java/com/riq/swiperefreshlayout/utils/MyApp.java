package com.riq.swiperefreshlayout.utils;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 锐 on 2017/4/9.
 */

public class MyApp extends Application {
    private static MyApp myApp;
    RequestQueue queue;

    public static MyApp getInstance() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        queue = Volley.newRequestQueue(getApplicationContext());

    }


    public RequestQueue getHttpQueues() {
        return queue;
    }
}
