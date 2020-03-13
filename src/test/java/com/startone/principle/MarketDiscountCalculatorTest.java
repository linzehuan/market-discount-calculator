package com.startone.principle;

import com.google.common.collect.Lists;
import com.startone.principle.discount.*;
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
    DefaultDiscountStrategy defaultDiscountStrategy = new DefaultDiscountStrategy();

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
        marketDiscountCalculator.initDiscountStrategy(defaultDiscountStrategy);
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(225, amount);
    }

    @Test
    public void should_return_80_percent_amount() {
        double discountPercent = 0.8;
        DiscountStrategy percentDiscountStrategy = new PercentDiscountStrategy(discountPercent);
        marketDiscountCalculator.initDiscountStrategy(percentDiscountStrategy, defaultDiscountStrategy);
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(225 * discountPercent, amount);
    }

    @Test
    public void should_return_full_reduce_amount() {
        DiscountStrategy reductionDiscount = new ReductionDiscount(
                ReductionSetting.createInstance(100, 15),
                ReductionSetting.createInstance(59, 5)
        );
        marketDiscountCalculator.initDiscountStrategy(reductionDiscount, defaultDiscountStrategy);
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(225 - 15, amount);
    }

    @Test
    public void should_return_drink_80_percent_amount() {
        double discountPercent = 0.8;
        DiscountStrategy percentDiscountStrategy = new PercentDiscountStrategy(discountPercent);
        percentDiscountStrategy.setLimitRule(LimitRule.createInstance(Category.DRINK));
        marketDiscountCalculator.initDiscountStrategy(percentDiscountStrategy, defaultDiscountStrategy);
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(225 - 5, amount);
    }

    @Test
    public void should_return_drink_80_percent_and_meat_reduction_amount() {
        double discountPercent = 0.8;
        DiscountStrategy percentDiscountStrategy = new PercentDiscountStrategy(discountPercent);
        percentDiscountStrategy.setLimitRule(LimitRule.createInstance(Category.DRINK));

        DiscountStrategy reductionDiscount = new ReductionDiscount(
                ReductionSetting.createInstance(20, 2),
                ReductionSetting.createInstance(60, 8)
        );
        marketDiscountCalculator.initDiscountStrategy(
                percentDiscountStrategy,
                reductionDiscount,
                defaultDiscountStrategy
        );
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(225 - (5 + 8), amount);
    }


    @Test
    public void should_return_drink_80_percent_and_meat_reduction_except_pork_amount() {
        double discountPercent = 0.8;
        DiscountStrategy percentDiscountStrategy = new PercentDiscountStrategy(discountPercent);
        percentDiscountStrategy.setLimitRule(LimitRule.createInstance(Category.DRINK));

        DiscountStrategy reductionDiscount = new ReductionDiscount(
                ReductionSetting.createInstance(20, 2),
                ReductionSetting.createInstance(60, 8)
        );
        reductionDiscount.setLimitRule(LimitRule.createInstance(Category.MEAT, "pork"));

        marketDiscountCalculator.initDiscountStrategy(
                percentDiscountStrategy,
                reductionDiscount,
                defaultDiscountStrategy
        );

        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(225 - (5 + 2), amount);
    }

    @Test
    public void should_return_drink_second_half_percent_and_meat_reduction_except_pork_amount() {
        DiscountStrategy reductionDiscount = new ReductionDiscount(
                ReductionSetting.createInstance(20, 2),
                ReductionSetting.createInstance(60, 8)
        );
        reductionDiscount.setLimitRule(LimitRule.createInstance(Category.MEAT, "pork"));

        HalfDiscountStrategy halfDiscountStrategy = new HalfDiscountStrategy();
        halfDiscountStrategy.setLimitRule(LimitRule.createInstance(Category.DRINK));

        marketDiscountCalculator.initDiscountStrategy(reductionDiscount, halfDiscountStrategy, defaultDiscountStrategy);
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(225 - (2.5 + 2), amount);
    }

    @Test
    public void should_return_drink_second_half_percent_and_meat_reduction_except_pork_and_electronic_80_percent_amount() {
        double discountPercent = 0.8;
        DiscountStrategy percentDiscountStrategy = new PercentDiscountStrategy(discountPercent);
        percentDiscountStrategy.setLimitRule(LimitRule.createInstance(Category.ELECTRONICS));

        DiscountStrategy reductionDiscount = new ReductionDiscount(
                ReductionSetting.createInstance(20, 2),
                ReductionSetting.createInstance(60, 8)
        );
        reductionDiscount.setLimitRule(LimitRule.createInstance(Category.MEAT, "pork"));

        HalfDiscountStrategy halfDiscountStrategy = new HalfDiscountStrategy();
        halfDiscountStrategy.setLimitRule(LimitRule.createInstance(Category.DRINK));

        marketDiscountCalculator.initDiscountStrategy(percentDiscountStrategy, reductionDiscount, halfDiscountStrategy, defaultDiscountStrategy);
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(225 - (2.5 + 2 + 20), amount);
    }
}