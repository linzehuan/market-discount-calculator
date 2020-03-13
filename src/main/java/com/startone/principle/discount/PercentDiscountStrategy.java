package com.startone.principle.discount;

import com.startone.principle.CalculateInfo;
import com.startone.principle.Goods;
import com.startone.principle.rule.IRule;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lin Zehuan
 * @date 2020/3/13 7:43 上午
 */
public class PercentDiscountStrategy implements DiscountStrategy {

    private double percent;

    private CalculateInfo amountInfo = new CalculateInfo();

    private IRule rule;

    @Override
    public void setLimitRule(IRule rule) {
        this.rule = rule;
    }

    public PercentDiscountStrategy(double percent) {
        this.percent = percent;
    }

    @Override
    public double getAmount(List<Goods> goodsList) {
        amountInfo.goodsList = goodsList.stream().filter(this::filterCondition).collect(Collectors.toList());
        amountInfo.totalAmount = amountInfo.goodsList.stream().mapToDouble(goods -> goods.price * goods.quality).sum();
        amountInfo.discount = amountInfo.goodsList.stream().mapToDouble(goods -> goods.price * goods.quality * (1 - percent)).sum();
        return amountInfo.totalAmount - amountInfo.discount;
    }

    @Override
    public CalculateInfo getAmountInfo() {
        return amountInfo;
    }

    private boolean filterCondition(Goods goods) {
        if (rule == null) {
            return true;
        }
        return rule.filterCondition(goods);
    }

}
