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
@TableName("b_warframe_remark")
@AllArgsConstructor
@Builder
public class WarframeRemark extends SuperEntity<Long> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4548013751643092085L;

    /**
     * 黑话
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 真实名称
     */
    @TableField(value = "name")
    private String name;
}
