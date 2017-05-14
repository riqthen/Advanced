package com.riq.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

/**
 * DevOpenHelper：创建SQLite数据库的SQLiteOpenHelper的具体实现
 * DaoMaster：GreenDao的顶级对象，作为数据库对象、用于创建表和删除表
 * DaoSession：管理所有的Dao对象，Dao对象中存在着增删改查等API
 */

public class MyApplication extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setDatabase();
    }

    private void setDatabase() {
        //创建数据库shop.db
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();

    }

    public static DaoSession getDaoInstance() {
        return daoSession;
    }


}
