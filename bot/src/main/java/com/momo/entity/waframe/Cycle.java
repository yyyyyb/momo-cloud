package com.momo.entity.waframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


/**
 * warframe官方平原信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cycle implements Serializable {
    @Serial
    private static final long serialVersionUID = -3352958208076938826L;

    /**
     *
     */
    private String id;

    private String expiry;

    private String activation;

    private Boolean isDay;

    private String state;

    private String timeLeft;

    private Boolean isCetus;

    private Boolean isCorpus;

    private Boolean isWarm;

    private String shortString;

    private String bountiesEndDate;

    private String active;

    //王境
    private Choices[] choices;
}
