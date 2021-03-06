package com.startone.principle.discount;

import com.startone.principle.CalculateInfo;
import com.startone.principle.Goods;
import com.startone.principle.rule.IRule;

import java.util.List;

/**
 * @author Lin Zehuan
 * @date 2020/3/13 7:53 上午
 */
public class DefaultDiscountStrategy implements DiscountStrategy {

    private CalculateInfo amountInfo = new CalculateInfo();

    @Override
    public void setLimitRule(IRule rule) {
    }

    @Override
    public double getAmount(List<Goods> goodsList) {
        amountInfo.totalAmount = goodsList.stream().mapToDouble(goods -> goods.price * goods.quality).sum();
        amountInfo.goodsList = goodsList;
        return amountInfo.totalAmount - amountInfo.discount;
    }

    @Override
    public CalculateInfo getAmountInfo() {
        return amountInfo;
    }
}
