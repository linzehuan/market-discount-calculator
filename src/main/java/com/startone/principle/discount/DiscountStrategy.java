package com.startone.principle.discount;

import com.startone.principle.Goods;

import java.util.List;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 7:40 上午
 */
public interface DiscountStrategy {

    double getAmount(List<Goods> goodsList);
}
