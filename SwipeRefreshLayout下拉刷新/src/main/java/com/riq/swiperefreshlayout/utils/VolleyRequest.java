package com.riq.swiperefreshlayout.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by 锐 on 2017/4/9.
 */

public class VolleyRequest {
    public static StringRequest stringRequest;

    public static void RequestGet(String url, String tag, VolleyInterface vi) {
        stringRequest = new StringRequest(Request.Method.GET, url, vi.loadingListener(), vi.errorListener());
        stringRequest.setTag(tag);
        MyApp.getInstance().getHttpQueues().add(stringRequest);
    }

    public static void RequestPost(String url, String tag, final Map<String, String> map , VolleyInterface vi) {
        stringRequest = new StringRequest(Request.Method.POST, url, vi.loadingListener(), vi.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        stringRequest.setTag(tag);
        MyApp.getInstance().getHttpQueues().add(stringRequest);
    }


}
