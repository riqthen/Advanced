package com.riq.okhttp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnGet;
    private Button btnPost;
    private Button btnUpload;
    private Button btnDownload;
    private Button btnMultipartUpload;
    private TextView tv;

    //在上传文件中，定义上传文件类型
    public static final MediaType MEDIA_FILE = MediaType.parse("text/x-markdown; charset=utf-8");
    public static final MediaType MEDIA_IMAGE = MediaType.parse("image/png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnGet = (Button) findViewById(R.id.btn_get);
        btnPost = (Button) findViewById(R.id.btn_post);
        btnUpload = (Button) findViewById(R.id.btn_upload);
        btnDownload = (Button) findViewById(R.id.btn_download);
        btnMultipartUpload = (Button) findViewById(R.id.btn_multipart_upload);
        tv = (TextView) findViewById(R.id.tv);

        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        btnDownload.setOnClickListener(this);
        btnMultipartUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //TODO 异步GET请求
            case R.id.btn_get:
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder().url("https://www.baidu.com");
                builder.method("GET", null);    //默认get请求，所以可以省略
                Request request = builder.build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("====请求失败====", String.valueOf(call) + "      " + e);
                        setTv("请求失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.cacheResponse() != null) {
                            String s = response.cacheResponse().toString();
                            Log.e("====请求成功 not null ====", s);
                            setTv("请求成功 not null：" + s);
                        } else {
//                            String string = response.body().string();
                            String s2 = response.networkResponse().toString();
                            Log.e("====请求成功 null ====", "network" + s2);
                            setTv("请求成功 null：" + s2);
                        }
                        showToast("请求成功");
                    }
                });
                break;

            //TODO 异步POST请求
            case R.id.btn_post:
                OkHttpClient okHttpClient1 = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("size", "10")
                        .build();
                Request request1 = new Request.Builder()
                        .url("http://api.1-blog.com/biz/bizserver/article/list.do")
                        .post(body)
                        .build();
                Call call1 = okHttpClient1.newCall(request1);
                call1.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("====请求失败====", String.valueOf(call) + "      " + e);
                        setTv("请求失败" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s = response.body().string();
                        Log.e("====请求成功====", s);
                        setTv("请求成功：" + s);
                        showToast("请求成功");
                    }
                });
                break;

            //TODO 异步上传文件（上传文件也是一个POST请求）
            //将/mnt/sdcard/aaa.txt上传到服务器
            case R.id.btn_upload:
                OkHttpClient okHttpClient2 = new OkHttpClient();
                File file = new File("/mnt/sdcard/aaa.txt");
                RequestBody body2 = RequestBody.create(MEDIA_FILE, file);
                Request request2 = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(body2)
                        .build();
                Call call2 = okHttpClient2.newCall(request2);
                call2.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("====上传失败====", String.valueOf(call) + "      " + e);
                        setTv("上传失败" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s = response.body().string();
                        Log.e("====上传成功====", s);
                        setTv("上传成功：" + s);
                        showToast("上传成功");
                    }
                });
                break;

            //TODO 异步下载文件
            //下载图片
            case R.id.btn_download:
                OkHttpClient okHttpClient3 = new OkHttpClient();
                Request request3 = new Request.Builder()
                        .url("http://tva1.sinaimg.cn/crop.0.0.640.640.50/6d38ee54jw8fbatuej8xvj20hs0hswez.jpg")
                        .build();
                Call call3 = okHttpClient3.newCall(request3);
                call3.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("====下载失败====", String.valueOf(call) + "      " + e);
                        setTv("下载失败" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream is = response.body().byteStream();
                        FileOutputStream fos;
                        try {
                            fos = new FileOutputStream("/mnt/sdcard/zaq.png");
                            byte[] buf = new byte[1024];
                            int len = 0;
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                            }
                            fos.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setTv("图片下载成功");
                        showToast("图片下载成功");
                    }
                });

                break;

            //TODO 异步上传Multipart文件
            //上传文件同时还需要传其他类型的字段
            case R.id.btn_multipart_upload:
                OkHttpClient okHttpClient4 = new OkHttpClient();
                RequestBody body3 = RequestBody.create(MEDIA_IMAGE, "/mnt/sdcard/2628.jpg");
                RequestBody body1 = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("title", "标题")
                        .addFormDataPart("iamge", "2628.jpg", body3)
                        .build();
                Request request4 = new Request.Builder()
                        .header("Authorization", "Client-ID" + "...")
                        .url("https://api.imgur.com/3/image")
                        .post(body1)
                        .build();
                Call call4 = okHttpClient4.newCall(request4);
                call4.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("====上传失败====", String.valueOf(call) + "      " + e);
                        setTv("上传失败" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        setTv(response.body().string());
                    }
                });

                break;
        }
    }

    public void setTv(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(s);
            }
        });
    }

    public void showToast(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
