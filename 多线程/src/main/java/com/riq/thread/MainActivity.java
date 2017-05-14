package com.riq.thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    AThread aThread = new AThread();
    CThread cThread = new CThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//操作上不使用，因为一般都假设线程在任何时候都可能被剥夺CPU的使用权
//TODO设置线程的优先级 1,5,10三个等级   线程调度器使高优先级的线程始终运行
//        aThread.setPriority(Thread.NORM_PRIORITY);
//        bThread.setPriority(Thread.MIN_PRIORITY);
        aThread.start();
        bThread.start();
        cThread.run();


        new Thread(cThread).start();
    }

    //创建线程的三种方法（准确来说不包含第三种，第三种即第二种方法，new Thread(new CThread)）
    //TODO ①子类继承Thread
    private class AThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10; i++) {
                Log.e("=========", "--------线程A");
            }
        }

    }

    //TODO ②使用Thread类直接创建线程对象
    Thread bThread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                Log.e("=========", "--------线程B");
            }
        }
    });

    //实现了接口的类可以将它的实例赋予接口实例，从而赋予接口实例具体接口中的方法
    //TODO ③实现Runnable接口
    private class CThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                    Log.e("====休眠====", "--------线程C休眠");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
