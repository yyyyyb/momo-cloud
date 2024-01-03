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
@TableName("b_warframe_prime")
@AllArgsConstructor
@Builder
public class WarframePrime extends SuperEntity<Long> implements Serializable {
    @Serial
    private static final long serialVersionUID = 8498275750173231015L;

    /**
     * 遗物名称
     */
    @TableField(value = "prime_name")
    private String primeName;

    /**
     * 遗物纪元
     */
    @TableField(value = "prime_item")
    private String primeItem;

    /**
     * 遗物url
     */
    @TableField(value = "prime_url")
    private String primeUrl;

    /**
     * 查询单词
     */
    @TableField(value = "prime_remark")
    private String primeRemark;

    /**
     * 查询单词
     */
    @TableField(value = "prime_id")
    private String primeId;
}
