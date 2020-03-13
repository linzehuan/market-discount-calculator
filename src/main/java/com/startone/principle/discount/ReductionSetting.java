package com.startone.principle.discount;

/**
 * @author Lin Zehuan
 * @description
 * @email lzh@kapark.cn
 * @date 2020/3/13 9:46 下午
 */
public class ReductionSetting {

    public double getReductionAmount() {
        return reductionAmount;
    }

    public double reductionAmount;

    public double getDiscount() {
        return discount;
    }

    public double discount;

    public static ReductionSetting createInstance(double reductionAmount, double discount){
        return new ReductionSetting(reductionAmount,discount);
    }
    public ReductionSetting(double reductionAmount, double discount){

        this.reductionAmount = reductionAmount;
        this.discount = discount;
    }
    
}
