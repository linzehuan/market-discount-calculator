package com.startone.principle;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 7:18 上午
 */
public class MarketDiscountCalculatorTest {
    private List<Goods> goodsList;
    private MarketDiscountCalculator marketDiscountCalculator = new MarketDiscountCalculator();

    @BeforeEach
    public void init() {
        goodsList = Lists.newArrayList(
                Goods.getInstance("wine", Category.DRINK, 15, 1),
                Goods.getInstance("cola", Category.DRINK, 5, 2),
                Goods.getInstance("pork", Category.MEAT, 25, 2),
                Goods.getInstance("chicken", Category.MEAT, 10, 5),
                Goods.getInstance("light", Category.ELECTRONICS, 100, 1)
        );
    }


    @Test
    public void should_return_normal_amount() {
        double amount = marketDiscountCalculator.calculate(goodsList);
        assertEquals(amount,225);
    }
}