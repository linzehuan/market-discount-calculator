package com.startone.principle.discount;

import com.startone.principle.Goods;

import java.util.List;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 7:43 上午
 */
public class PercentDiscountStrategy implements DiscountStrategy {

    private double percent;

    public PercentDiscountStrategy(double percent) {
        this.percent = percent;
    }

    @Override
    public double getAmount(List<Goods> goodsList) {
        return goodsList.stream().mapToDouble(goods->goods.price * goods.quality).sum() * percent;
    }
}
