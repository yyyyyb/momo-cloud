package com.momo.entity.waframe;

import lombok.Data;

import java.util.List;

/**
 * warframe 官方入侵信息json
 */
@Data
public class InvasionReward {
    private String[] items;

    private List<InvasionCountedItem> countedItems;

    private Integer credits;

    private String asString;

    private String itemString;

    private String thumbnail;

    private Long color;
}
