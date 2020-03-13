package com.startone.principle.discount;

import com.startone.principle.AmountInfo;
import com.startone.principle.Goods;
import com.startone.principle.rule.IRule;

import java.util.List;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 7:53 上午
 */
public class DefaultDiscountStrategy implements DiscountStrategy {

    private AmountInfo amountInfo = new AmountInfo();

    @Override
    public void setLimitRule(IRule rule) {
        return;
    }

    @Override
    public double getAmount(List<Goods> goodsList) {
        amountInfo.totalAmount = goodsList.stream().mapToDouble(goods -> goods.price * goods.quality).sum();
        amountInfo.goodsList = goodsList;
        return amountInfo.totalAmount - amountInfo.discount;
    }

    @Override
    public AmountInfo getAmountInfo() {
        return amountInfo;
    }
}
