package com.riq.swiperefreshlayout;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.riq.swiperefreshlayout.MainActivity.url;

/**
 * Created by 锐 on 2017/4/9.
 */

public class VolleyUtils {
    List<String> list;
    Context context;
    ListView lv;
    ArrayAdapter adapter;

    public VolleyUtils(List<String> list, Context context, ListView lv, ArrayAdapter adapter) {
        this.list = list;
        this.context = context;
        this.lv = lv;
        this.adapter = adapter;
    }

    //    RequestQueue queue = Volley.newRequestQueue(context);
    Request request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.e("============", response);
            Gson gson = new Gson();
            Joke joke = gson.fromJson(response, Joke.class);
            List<Joke.ShowapiResBodyBean.ContentlistBean> contentlists = joke.getShowapi_res_body().getContentlist();
            Log.e("==contentlists==", String.valueOf(contentlists));
            Log.e("==contentlists.size()==", contentlists.size() + "");
            for (int i = 0; i < contentlists.size(); i++) {
                list.add(contentlists.get(i).getText());
            }
            lv.setAdapter(adapter);
            Log.e("==list==", String.valueOf(list));

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(context, "请求出错", Toast.LENGTH_SHORT).show();
            Log.e("======error=======", String.valueOf(error));
        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> map = new HashMap<>();
            map.put("showapi_appid", "35281");
            map.put("showapi_sign", "669c476cb31c4db484496ef4d8955724");
            map.put("page", "1");

            return map;
        }
    };

//        queue.add(request);
}
