package com.riq.thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

//举例 让两个线程操作同一个对象，例如让猫和狗同时喝一盆水
public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WaterThread waterThread = new WaterThread(10);
        Thread catThread = new Thread(waterThread);
        catThread.setName("猫");
        Thread dogThread = new Thread(waterThread);
        dogThread.setName("狗");
        catThread.start();
        dogThread.start();
    }


    private class WaterThread implements Runnable {
        int water;

        WaterThread(int water) {
            this.water = water;
        }


        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (true) {
                if (name.equals("猫")) {
                    Log.e("============", name + "喝水");
                    drinkWater();   //water -= 1;
                } else if (Thread.currentThread().getName().equals("狗")) {
                    Log.e("============", name + "喝水");
                    drinkWater();   //water -= 2;
                }
                Log.e("============", "还剩下" + water);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (water <= 0) {
                    return;
                }
            }
        }

        synchronized void drinkWater() {
            if (Thread.currentThread().getName().equals("猫")) {
                while (true) {
                    water -= 1;
                }
            } else if (Thread.currentThread().getName().equals("狗")) {
                while (true) {
                    water -= 2;
                }
            }
        }
    }
}
