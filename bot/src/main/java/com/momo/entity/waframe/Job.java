package com.momo.entity.waframe;

import lombok.Data;

import java.util.List;

/**
 * warframe官方 赏金信息json实体
 */
@Data
public class Job {
    private String id;
    private String[] rewardPool;
    private String type;
    private Integer[] enemyLevels;
    private Integer[] standingStages;
    private Integer minMR;
    private String expiry;
    private String timeBound;
    private Boolean isVault;
    private String locationTag;
    private String timeBoound;
}
