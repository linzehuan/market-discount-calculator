package com.startone.principle.rule;

import com.startone.principle.Category;
import com.startone.principle.Goods;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 11:08 下午
 */
public class LimitRule implements IRule {

    private Category category = Category.ALL;


    private String exceptedGoodName;

    public static LimitRule createInstance(Category category) {
        return new LimitRule(category,"");
    }
    public static LimitRule createInstance(Category category, String exceptedGoodName){
          return new LimitRule(category,exceptedGoodName);
    }

    public LimitRule(Category category, String exceptedGoodName) {
        this.category = category;
        this.exceptedGoodName = exceptedGoodName;
    }

    public LimitRule(Category category) {
        this.category = category;
    }

    private boolean isExceptedGood(Goods goods) {
        if ("ALL".equals(exceptedGoodName)) {
            return false;
        }
        return goods.name.equals(exceptedGoodName);
    }

    @Override
    public boolean filterCondition(Goods goods) {
       return isSelectedCategory(goods)&&!isExceptedGood(goods);
    }


    private boolean isSelectedCategory(Goods goods) {
        if (this.category == Category.ALL) {
            return true;
        }
        return this.category == goods.category;
    }
}
