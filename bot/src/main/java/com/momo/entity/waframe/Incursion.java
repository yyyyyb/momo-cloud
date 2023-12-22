package com.momo.entity.waframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * warframe官方钢铁奖励信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incursion implements Serializable {
    @Serial
    private static final long serialVersionUID = -1195752450002887647L;

    /**
     *
     */
    private String id;

    /**
     *
     */
    private String activation;

    /**
     *
     */
    private String expiry;
}
