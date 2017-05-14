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
import java.net.Socket;

//客户端
public class TCPClientActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    Socket socket;
    DataInputStream is;
    DataOutputStream os;
    String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpclient);
        editText = (EditText) findViewById(R.id.et);
        textView = (TextView) findViewById(R.id.tv);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("客户收到服务器的回答" + s1);
        }
    };

    public void send(View view) {
        String s = editText.getText().toString();
        try {
            socket = new Socket("127.0.0.1", 2022);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            os.writeUTF(s);
            s1 = is.readUTF();
            handler.sendEmptyMessage(0x1);
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("服务器已经断开", "===============" + e);
        }
    }
}
