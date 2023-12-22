package com.momo.entity.waframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * warframe官方午夜电波
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveChallenge implements Serializable {

    @Serial
    private static final long serialVersionUID = -8517365085596490251L;
    /**
     * 数据id
     */
    private String id;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 任务总长时间
     */
    private String startString;

    /**
     * 过期时间
     */
    private String expiry;

    /**
     * 是否有效
     */
    private Boolean active;

    /**
     *
     */
    private Boolean isDaily;

    /**
     *
     */
    private Boolean isElite;

    /**
     * 任务描述
     */
    private String desc;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务奖励点数
     */
    private Long reputation;

    /**
     * 是否永久
     */
    private Boolean isPermanent;
}
