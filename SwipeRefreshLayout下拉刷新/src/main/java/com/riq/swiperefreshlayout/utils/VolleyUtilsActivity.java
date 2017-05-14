package com.riq.swiperefreshlayout.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.riq.swiperefreshlayout.MainActivity;
import com.riq.swiperefreshlayout.R;

public class VolleyUtilsActivity extends AppCompatActivity {
    String url = "http://route.showapi.com/341-1";
    String page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volleyutils);

    }

    //Get请求
    public void get(View view) {
        VolleyRequest.RequestGet(url, "join",
                new VolleyInterface() {
                    @Override
                    public void onMySuccess(String result) {
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                        Log.e("===========", result);
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "请求错误", Toast.LENGTH_SHORT).show();
                        Log.e("===========", String.valueOf(error));
                    }
                }

        );
    }

    //Post请求
    public void post(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Object tag = VolleyRequest.stringRequest.getTag();
        RequestQueue queues = MyApp.getInstance().getHttpQueues();
        queues.cancelAll(tag);
    }
}
