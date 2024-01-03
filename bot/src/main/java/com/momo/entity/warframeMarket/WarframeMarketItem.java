package com.momo.entity.warframeMarket;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momo.SuperEntity;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("b_warframe_market_all_item")
@AllArgsConstructor
@Builder
public class WarframeMarketItem extends SuperEntity<Long> {

    /**
     * 查询单词
     */
    @TableField(value = "item_name")
    private String itemName;

    /**
     * 查询单词
     */
    @TableField(value = "item_id")
    private String itemId;

    /**
     * 对应方法
     */
    @TableField(value = "item_url")
    private String itemUrl;
}
