package com.startone.principle;

import com.startone.principle.discount.DiscountStrategy;

import java.util.List;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 8:30 上午
 */
public class FullFeductionAmount implements DiscountStrategy {
    @Override
    public double getAmount(List<Goods> goodsList) {
        double amount = goodsList.stream().mapToDouble(goods -> goods.price * goods.quality).sum();
        double discount = 0;
        if (amount > 100) {
            discount = 15;
        } else if (amount > 15) {
            discount = 5;
        }
        return amount - discount;
    }
}
