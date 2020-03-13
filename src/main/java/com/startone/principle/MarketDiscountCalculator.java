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
        checkStrategy();
        List<Goods> remainGoodList = goodItemList;
        for (DiscountStrategy discountStrategy : discountStrategies) {
            totalAmount += discountStrategy.getAmount(remainGoodList);
            CalculateInfo amountInfo = discountStrategy.getAmountInfo();
            removeGoodList(remainGoodList, amountInfo.goodsList);
        }

        return totalAmount;
    }

    private void checkStrategy() {
        if (discountStrategies == null || discountStrategies.length == 0) {
            throw new RuntimeException("Need set default strategy");
        }
    }

    private void removeGoodList(List<Goods> remainGoodList, List<Goods> goodsList) {
        remainGoodList.removeAll(goodsList);
    }

    public void initDiscountStrategy(DiscountStrategy... discountStrategies) {
        this.discountStrategies = discountStrategies;
    }
}
