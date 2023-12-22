package com.momo.entity;

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
@TableName("b_query_method")
@AllArgsConstructor
@Builder
public class QueryMethod extends SuperEntity<Long> {
    /**
     * 查询类型
     */
    @TableField(value = "query_type")
    private String queryType;

    /**
     * 查询单词
     */
    @TableField(value = "query_word")
    private String queryWord;

    /**
     * 对应方法
     */
    @TableField(value = "query_method")
    private String queryMethod;
}
