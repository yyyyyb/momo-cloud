package com.momo.entity.waframe;

import lombok.Data;

import java.util.List;

/**
 * warframe官方 赏金信息json实体
 */
@Data
public class SyndicateMission {
    private String id;
    private String activation;
    private String startString;
    private String expiry;
    private Boolean active;
    private String syndicate;
    private String syndicateKey;
    private String[] nodes;
    private List<Job> jobs;
    private String eta;
}
