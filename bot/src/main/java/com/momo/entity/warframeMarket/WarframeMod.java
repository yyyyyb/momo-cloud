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
@TableName("b_warframe_mod")
@AllArgsConstructor
@Builder
public class WarframeMod extends SuperEntity<Long> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4548013751643092085L;

    /**
     * mod名称
     */
    @TableField(value = "mod_name")
    private String modName;

    /**
     * mod url
     */
    @TableField(value = "mod_url")
    private String modUrl;

    /**
     * mod搜索词
     */
    @TableField(value = "mod_remark")
    private String modRemark;

    /**
     *
     */
    @TableField(value = "mod_id")
    private String modId;
}
