package com.riq.sockettcpserver;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpserver);
        editText = (EditText) findViewById(R.id.et);
        textView = (TextView) findViewById(R.id.tv);
    }

    Socket socket;
    ServerSocket serverSocket;
    DataInputStream is;
    DataOutputStream os;
    String s1;

    public void send(View view) {
        String s = editText.getText().toString();
        try {
            serverSocket = new ServerSocket(2022);
            socket = serverSocket.accept();
            os = new DataOutputStream(socket.getOutputStream());
            is = new DataInputStream(socket.getInputStream());
            s1 = is.readUTF();
            handler.sendEmptyMessage(0x1);
            os.writeUTF(s);
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("客户已经断开", "===============" + e);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("服务器收到客户的消息" + s1);

        }
    };
}
