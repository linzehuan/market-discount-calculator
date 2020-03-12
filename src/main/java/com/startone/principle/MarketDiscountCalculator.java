package com.startone.principle;

import com.startone.principle.discount.DiscountStrategy;

import java.util.List;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 7:17 上午
 */
public class MarketDiscountCalculator {

    private double totalAmount = 0;
    private DiscountStrategy[] discountStrategies;

    public double calculate(List<Goods> goodItemList) {

        for (DiscountStrategy discountStrategy : discountStrategies) {
            totalAmount+= discountStrategy.getAmount(goodItemList);
        }
        return totalAmount;
    }

    public void initDiscountStrategy(DiscountStrategy... discountStrategies) {
        this.discountStrategies = discountStrategies;
    }
}
