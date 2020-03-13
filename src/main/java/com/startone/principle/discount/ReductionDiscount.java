package com.startone.principle.discount;

import com.startone.principle.CalculateInfo;
import com.startone.principle.Goods;
import com.startone.principle.rule.IRule;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 8:30 上午
 */
public class ReductionDiscount implements DiscountStrategy {

    private CalculateInfo amountInfo = new CalculateInfo();
    private ReductionSetting[] reductionSettings;
    private IRule rule;

    public ReductionDiscount(ReductionSetting... reductionSettings){
        this.reductionSettings = reductionSettings;
    }


    @Override
    public void setLimitRule(IRule rule) {
        this.rule = rule;
    }

    @Override
    public double getAmount(List<Goods> goodsList) {
        amountInfo.goodsList = goodsList.stream().filter(this::filterCondition).collect(Collectors.toList());
        amountInfo.totalAmount = amountInfo.goodsList .stream().mapToDouble(goods -> goods.price * goods.quality).sum();

        Optional<ReductionSetting> reductionSetting = Arrays.stream(reductionSettings)
                .sorted(Comparator.comparingDouble(ReductionSetting::getReductionAmount).reversed())
                .filter(reductionSettings -> amountInfo.totalAmount > reductionSettings.getReductionAmount())
                .findFirst();

        reductionSetting.ifPresent(s -> amountInfo.discount = s.getDiscount());

        return amountInfo.totalAmount - amountInfo.discount;
    }

    @Override
    public CalculateInfo getAmountInfo() {
        return amountInfo;
    }

    private boolean filterCondition(Goods goods) {
        if (rule == null) {
            return true;
        }
        return rule.filterCondition(goods);
    }

}
