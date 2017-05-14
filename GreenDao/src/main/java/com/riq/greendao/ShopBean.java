package com.riq.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/*
 * @Entity：告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
 * @Id：对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
 * @Property：可以自定义字段名，注意外键不能使用该属性
 * @NotNull：属性不能为空
 * @Transient：使用该注释的属性不会被存入数据库的字段中
 * @Unique：该属性值必须在数据库中是唯一值
 * @Generated：编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改
 */
@Entity /*告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作*/
public class ShopBean {
    //购物车列表
    public static final int TYPE_CART = 0x1;
    //收藏列表
    public static final int TYPE_LOVE = 0x2;

    @Id(autoincrement = true)   /*对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值*/
    private Long id;        //必须是long类型

    @Unique     /*该属性值必须在数据库中是唯一值*/
    private String name;

    @Property(nameInDb = "price")   /*可以自定义字段名，注意外键不能使用该属性*/
    private String price;


    private int num;

    private String imgUrl;

    private String address;

    private int type;

    @Generated(hash = 1668894816)
    public ShopBean(Long id, String name, String price, int num, String imgUrl, String address, int type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.num = num;
        this.imgUrl = imgUrl;
        this.address = address;
        this.type = type;
    }

    @Generated(hash = 748345971)
    public ShopBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
