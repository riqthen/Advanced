package com.riq.sockettcpserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TODO TCP服务端
    public void TCPServer(View view) {
        startActivity(new Intent(this, TCPServerActivity.class));
    }

    //TODO TCP客户端
    public void TCPClient(View view) {
        startActivity(new Intent(this, TCPClientActivity.class));
    }

    //TODO UDP服务端
    public void UDPServer(View view) {

    }

    //TODO UDP客户端
    public void UDPClient(View view) {

    }
}
