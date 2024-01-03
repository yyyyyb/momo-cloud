package com.momo.entity.waframe;

import lombok.Data;

/**
 * warframe 官方入侵信息json
 */
@Data
public class InvasionCountedItem {
    private Integer count;

    private String type;

    private String key;
}
