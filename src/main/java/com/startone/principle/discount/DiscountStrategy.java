package com.startone.principle.discount;

import com.startone.principle.AmountInfo;
import com.startone.principle.Goods;
import com.startone.principle.rule.IRule;

import java.util.List;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 7:40 上午
 */
public interface DiscountStrategy {

    void setLimitRule(IRule rule);

    double getAmount(List<Goods> goodsList);

    AmountInfo getAmountInfo();
}
