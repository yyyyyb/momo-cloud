package com.momo.entity.waframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * warframe官方虚空商人信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoidTrader implements Serializable {

    @Serial
    private static final long serialVersionUID = 7781002896026700487L;

    /**
     * 数据id
     */
    private String id;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 距离开始时间
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
     * 角色对象
     */
    private String character;

    /**
     * 地点
     */
    private String location;

    /**
     *
     */
    private List<Inventory> inventory;

    /**
     *
     */
    private String psId;

    /**
     * 结束时间
     */
    private String endString;

    /**
     * 任务奖励点数
     */
    private String initialStart;


    /**
     * 任务奖励点数
     */
    private List<String> schedule;
}
