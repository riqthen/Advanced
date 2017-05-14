package com.riq.greendao;

import java.util.List;

import static com.riq.greendao.ShopBean.TYPE_LOVE;

/**
 * Created by 锐 on 2017/3/24.
 */

public class LoveDao {
    //增加或者替换
    public static void insertLove(ShopBean shopBean) {
        MyApplication.getDaoInstance().getShopBeanDao().insertOrReplace(shopBean);
    }

    //删除某数据
    public static void deleteLove(long id) {
        MyApplication.getDaoInstance().getShopBeanDao().deleteByKey(id);
    }

    //更新表
    public static void updateLove(ShopBean shopBean) {
        MyApplication.getDaoInstance().getShopBeanDao().update(shopBean);
    }

    //查询为TYPE_LOVE的数据
    public static List<ShopBean> queryLove() {
        return MyApplication.getDaoInstance().getShopBeanDao().queryBuilder()
                .where(ShopBeanDao.Properties.Type.eq(TYPE_LOVE)).list();
    }

    //查询全部数据
    public static List<ShopBean> queryAll() {
        return MyApplication.getDaoInstance().getShopBeanDao().loadAll();
    }

}
