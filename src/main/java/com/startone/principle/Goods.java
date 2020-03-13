package com.startone.principle;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 7:13 上午
 */
public class Goods {


    public int getQuality() {
        return quality;
    }

    public static Goods getInstance(String name, Category category, double price, int quality) {
        return new Goods(name, category, price, quality);
    }
    public Goods(String name, Category category, double price, int quality) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public String name;

    public Category category;

    public double price;

    public int quality;
}
