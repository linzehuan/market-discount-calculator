package com.startone.principle.rule;

import com.startone.principle.Goods;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 11:12 下午
 */
public interface IRule {

    boolean filterCondition(Goods goods);
}
