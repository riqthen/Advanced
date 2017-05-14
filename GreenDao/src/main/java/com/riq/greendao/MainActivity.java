package com.riq.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int[] type = {ShopBean.TYPE_CART, ShopBean.TYPE_LOVE};

    //增
    public void insert(View view) {
        for (int i = 0; i < 10; i++) {
            ShopBean shopBean = new ShopBean((long) i, "张" + i, 100 + i + "", i + 10, "https://www.baidu.com/img/baidu_jgylogo3.gif", "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=%E5%AE%89%E5%8D%93%E6%80%8E%E4%B9%88%E8%8E%B7%E5%8F%96%E6%95%B0%E6%8D%AE%E5%BA%93%E7%9A%84%E6%95%B0%E6%8D%AE%E5%BA%93&oq=greendao&rsv_pq=f7a8a73400025a90&rsv_t=b509eYYh8nGUeDFm46VnhB72diX4AVTk%2BSDa9pDJvg%2BHOaUYbZLkzyb%2FhEI&rqlang=cn&rsv_enter=1&inputT=5859&rsv_sug3=40&rsv_sug1=17&rsv_sug7=101&bs=greendao", type[i % 2]);
            LoveDao.insertLove(shopBean);
        }
    }

    //删
    public void delete(View view) {
        LoveDao.deleteLove(1);
    }

    public void update(View view) {
        ShopBean shopBean = new ShopBean();
        LoveDao.updateLove(shopBean);
    }

    boolean single = true;

    public void query(View view) {
        if (single) {
            List<ShopBean> shopBeanList = LoveDao.queryAll();
            for (ShopBean shopBean : shopBeanList) {
                String name = shopBean.getName();
                Log.e("==========", name);
            }
            single = false;
        } else {
            List<ShopBean> shopBeanList = LoveDao.queryLove();
            for (ShopBean shopBean : shopBeanList) {
                String name = shopBean.getName();
                Log.e("==========", name);
            }
        }
    }
}
