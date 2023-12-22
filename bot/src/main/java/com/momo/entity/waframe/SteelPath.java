package com.momo.entity.waframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * warframe官方钢铁奖励信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SteelPath implements Serializable {

    @Serial
    private static final long serialVersionUID = -1871091593925823129L;

    /**
     *
     */
    private Reward currentReward;

    /**
     *
     */
    private String activation;

    /**
     *
     */
    private String expiry;

    /**
     *
     */
    private String remaining;

    /**
     *
     */
    private List<Reward> rotation;

    /**
     *
     */
    private List<Reward> evergreens;

    /**
     *
     */
    private Incursion incursions;
}
