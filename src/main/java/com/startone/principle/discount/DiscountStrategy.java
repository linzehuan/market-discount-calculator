package com.startone.principle.discount;

import com.startone.principle.CalculateInfo;
import com.startone.principle.Goods;
import com.startone.principle.rule.IRule;

import java.util.List;

/**
 * @author Lin Zehuan
 * @date 2020/3/13 7:40 上午
 */
public interface DiscountStrategy {

    void setLimitRule(IRule rule);

    double getAmount(List<Goods> goodsList);

    CalculateInfo getAmountInfo();
}
