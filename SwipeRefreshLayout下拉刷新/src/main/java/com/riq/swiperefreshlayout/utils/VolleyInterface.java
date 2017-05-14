package com.riq.swiperefreshlayout.utils;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by 锐 on 2017/4/9.
 */

public abstract class VolleyInterface {
    public static Response.Listener<String> listener;
    public static Response.ErrorListener errorListener;

    public abstract void onMySuccess(String result);

    public abstract void onMyError(VolleyError error);

    public Response.Listener<String> loadingListener() {
        listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onMySuccess(response);
            }
        };
        return listener;
    }

    public Response.ErrorListener errorListener() {
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onMyError(error);
            }
        };
        return errorListener;
    }
}
