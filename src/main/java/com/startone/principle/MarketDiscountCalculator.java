package com.startone.principle;

import java.util.List;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 7:17 上午
 */
public class MarketDiscountCalculator {

    public double calculate(List<Goods> goodItemList) {
        return goodItemList.stream().mapToDouble(goods -> goods.price * goods.quality).sum();
    }
}
