package com.momo.entity.waframe;

import com.momo.utils.DateUtil;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DailyDeals {
    private String id;
    /**
     * 物品名称
     */
    private String item;

    private String uniqueName;
    /**
     * 结束时间
     */
    private String expiry;

    /**
     *
     */
    private String activation;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 打折价格
     */
    private BigDecimal salePrice;

    /**
     * 出售数量
     */
    private Integer total;

    /**
     * 已出售数量
     */
    private Integer sold;

    /**
     * 剩余时间
     */
    private String eta;

    private Integer discount;

    @Override
    public String toString() {
        return "当前出售 : ".concat(item).concat("\n")
                .concat("现价 : ").concat(salePrice.toString()).concat("  原价 : ").concat(originalPrice.toString())
                .concat("\n").concat("剩余数量 :").concat(String.valueOf(total - sold)).concat("\n")
                .concat("剩余时间 : ").concat(DateUtil.dateReplace(eta));
    }
}
