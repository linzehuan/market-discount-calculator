package com.startone.principle.discount;

import com.startone.principle.Category;
import com.startone.principle.Goods;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 10:36 下午
 */
public class HalfDiscountStrategy implements DiscountStrategy {

    private AmountInfo amountInfo = new AmountInfo();

    private IRule rule;

    @Override
    public void setLimitRule(IRule rule) {
        this.rule = rule;
    }

    @Override
    public double getAmount(List<Goods> goodsList) {
        amountInfo.goodsList = goodsList.stream().filter(this::filterCondition).collect(Collectors.toList());
        amountInfo.totalAmount = goodsList.stream().filter(this::filterCondition).mapToDouble(goods -> goods.price * goods.quality).sum();
        Map<String, List<Goods>> collect = amountInfo.goodsList.stream().collect(groupingBy(Goods::getName));
        amountInfo.discount = collect.values().stream().mapToDouble(goods -> (goods.stream().mapToInt(Goods::getQuality).sum() / 2) * goods.get(0).price / 2).sum();
        return amountInfo.totalAmount - amountInfo.discount;
    }

    @Override
    public AmountInfo getAmountInfo() {
        return amountInfo;
    }

    private boolean filterCondition(Goods goods) {
        if (rule == null) {
            return true;
        }
        return rule.filterCondition(goods);
    }
}
