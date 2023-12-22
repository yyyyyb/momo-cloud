package com.momo.entity.waframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * warframe官方执行官信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission implements Serializable {
    @Serial
    private static final long serialVersionUID = 6044320495190046737L;

    private String node;

    private String nodeKey;

    private String type;

    private String typeKey;

    private Boolean nightmare;

    private Boolean archwingRequired;

    private Boolean isSharkwing;

    private Object advancedSpawners;

    private Object requiredItems;

    private Object levelAuras;
}
