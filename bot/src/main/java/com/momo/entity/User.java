package com.momo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momo.SuperEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_user")
@AllArgsConstructor
@Builder
public class User extends SuperEntity<Long> {
    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * qq号
     */
    @TableField(value = "qq")
    private String qq;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 群号
     */
    @TableField(value = "group_no")
    private String groupNo;

    /**
     * 群内备注
     */
    @TableField(value = "group_remark")
    private String groupRemark;
}
