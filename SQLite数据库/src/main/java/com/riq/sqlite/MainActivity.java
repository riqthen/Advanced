package com.riq.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * CREATE TABLE person (personid integer primary key autoincrement, name varchar(20))
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    private EditText etTitle;
    private EditText etContent;
    private Button btnCreate;
    private Button btnInsert;
    private String title;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        etTitle = (EditText) findViewById(R.id.et_title);
        etContent = (EditText) findViewById(R.id.et_content);
        btnCreate = (Button) findViewById(R.id.btn_create);
        btnInsert = (Button) findViewById(R.id.btn_insert);

        btnCreate.setOnClickListener(this);
        btnInsert.setOnClickListener(this);

        submit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                Log.e("============", getFilesDir().toString());    ///data/data/com.riq.sqlite/files
                //TODO 创建或打开数据库
                db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().toString() + "/sqlite.db", null);
                break;
            case R.id.btn_insert:
                insertData(db, etTitle, etContent);
                break;
        }
    }

    private void insertData(SQLiteDatabase db, EditText etTitle, EditText etContent) {
        //TODO 插入语句
        db.execSQL("insert into person values(null, ?, ?)", new String[]{title, content});
    }

    private void submit() {
        // validate
        title = etTitle.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "title", Toast.LENGTH_SHORT).show();
            return;
        }

        content = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "content", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
