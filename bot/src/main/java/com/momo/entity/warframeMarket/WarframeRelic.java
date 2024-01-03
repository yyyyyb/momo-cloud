package com.momo.entity.warframeMarket;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momo.SuperEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("b_warframe_relic")
@AllArgsConstructor
@Builder
public class WarframeRelic extends SuperEntity<Long> implements Serializable {
    @Serial
    private static final long serialVersionUID = 8498275750173231015L;

    /**
     * 遗物名称
     */
    @TableField(value = "relic_name")
    private String relicName;

    /**
     * 遗物纪元
     */
    @TableField(value = "relic_tier")
    private String relicTier;

    /**
     * 遗物url
     */
    @TableField(value = "relic_url")
    private String relicUrl;

    /**
     * 查询单词
     */
    @TableField(value = "relic_remark")
    private String relicRemark;

    /**
     * 查询单词
     */
    @TableField(value = "relic_id")
    private String relicId;
}
