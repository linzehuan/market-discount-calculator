package com.startone.principle;

import com.google.common.collect.Lists;
import com.startone.principle.discount.DefaultDiscountStrategy;
import com.startone.principle.discount.DiscountStrategy;
import com.startone.principle.discount.PercentDiscountStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 7:18 上午
 * 1. 入参的设计非常有讲究，这里将商品列表作为入参数，在不同的规则里面去循环判断 （如果有优先级的优惠规则怎么办）
 */
public class MarketDiscountCalculatorTest {
    private List<Goods> goodsList;
    private MarketDiscountCalculator marketDiscountCalculator;

    @BeforeEach
    public void init() {
        goodsList = Lists.newArrayList(
                Goods.getInstance("wine", Category.DRINK, 15, 1),
                Goods.getInstance("cola", Category.DRINK, 5, 2),
                Goods.getInstance("pork", Category.MEAT, 25, 2),
                Goods.getInstance("chicken", Category.MEAT, 10, 5),
                Goods.getInstance("light", Category.ELECTRONICS, 100, 1)
        );
        marketDiscountCalculator = new MarketDiscountCalculator();
    }


    @Test
    public void should_return_normal_amount() {
        DefaultDiscountStrategy defaultDiscountStrategy = new DefaultDiscountStrategy();
        marketDiscountCalculator.initDiscountStrategy(defaultDiscountStrategy);
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(amount, 225);
    }

    @Test
    public void should_return_80_percent_amount() {
        double discountPercent = 0.8;
        PercentDiscountStrategy percentDiscountStrategy = new PercentDiscountStrategy(discountPercent);
        marketDiscountCalculator.initDiscountStrategy(percentDiscountStrategy);
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(amount, 225 * discountPercent);
    }

    @Test
    public void should_return_full_reduce_amount() {
        DiscountStrategy fullReductionAmount = new FullFeductionAmount();
        marketDiscountCalculator.initDiscountStrategy(fullReductionAmount);
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(amount, 225 - 15);
    }
}