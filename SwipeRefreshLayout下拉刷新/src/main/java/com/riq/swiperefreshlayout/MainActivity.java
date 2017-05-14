package com.riq.swiperefreshlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.riq.swiperefreshlayout.utils.VolleyInterface;
import com.riq.swiperefreshlayout.utils.VolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下拉加载
 * 要点
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ListView lv;
    private SwipeRefreshLayout srl;
    private ArrayList<String> list = new ArrayList();
    private ArrayAdapter adapter;
    public static final String url = "http://route.showapi.com/341-1";
    private int page;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        srl.setOnRefreshListener(this);
        initData();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, list);
        srl.setColorSchemeColors(Color.GRAY);

    }

    //http://route.showapi.com/341-1?showapi_appid=35281&showapi_sign=669c476cb31c4db484496ef4d8955724
    private void initData() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        VolleyUtils volleyUtils = new VolleyUtils(list, this, lv, adapter);
////        Request request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
////            @Override
////            public void onResponse(String response) {
////                Log.e("============", response);
////                Gson gson = new Gson();
////                Joke joke = gson.fromJson(response, Joke.class);
////                List<Joke.ShowapiResBodyBean.ContentlistBean> contentlists = joke.getShowapi_res_body().getContentlist();
////                Log.e("==contentlists==", String.valueOf(contentlists));
////                Log.e("==contentlists.size()==", contentlists.size() + "");
////                for (int i = 0; i < contentlists.size(); i++) {
////                    list.add(contentlists.get(i).getText());
////                }
////                lv.setAdapter(adapter);
////                Log.e("==list==", String.valueOf(list));
////
////            }
////        }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////                Toast.makeText(MainActivity.this, "请求出错", Toast.LENGTH_SHORT).show();
////            }
////        }) {
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                Map<String, String> map = new HashMap<>();
////                map.put("showapi_appid", "35281");
////                map.put("showapi_sign", "669c476cb31c4db484496ef4d8955724");
////                map.put("page", "1");
////
////                return map;
////            }
////        };
//
//        Request request = volleyUtils.request;
//        queue.add(request);
        Map<String, String> map = new HashMap<>();
        map.put("showapi_appid", "35281");
        map.put("showapi_sign", "669c476cb31c4db484496ef4d8955724");
        map.put("page", page + "");
        VolleyRequest.RequestPost(url, "tag", map, new VolleyInterface() {
            @Override
            public void onMySuccess(String result) {
                Gson gson = new Gson();
                Joke joke = gson.fromJson(result, Joke.class);
                List<Joke.ShowapiResBodyBean.ContentlistBean> contentlists = joke.getShowapi_res_body().getContentlist();
//                Log.e("==contentlists==", String.valueOf(contentlists));
//                Log.e("==contentlists.size()==", contentlists.size() + "");
//                Log.e("=======", contentlists.get(1).getText());
                for (int i = 0; i < contentlists.size(); i++) {
                    list.add(contentlists.get(i).getText());
                }
                lv.setAdapter(adapter);

            }

            @Override
            public void onMyError(VolleyError error) {

            }
        });
    }

    @Override
    public void onRefresh() {
//        ArrayList<String> list2 = new ArrayList();
//        for (int i = 0; i < 5; i++) {
//            list2.add("i=" + i);
//        }
//
        // TODO: 2017/4/8 Handler携带数据
//        Message msg = new Message();
//        Bundle data = new Bundle();
////        data.putStringArrayList("list2", list2);
//        data.putInt("page", page);
//        msg.setData(data);
//        handler.sendMessage(msg);
        handler.sendEmptyMessage(0x1);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            page++;
//            Bundle data = msg.getData();
////            ArrayList<String> list2 = data.getStringArrayList("list2");
////            list.addAll(list2);
//            int page = data.getInt("page");
            adapter.notifyDataSetChanged();
            srl.setRefreshing(false);
        }
    };
}
