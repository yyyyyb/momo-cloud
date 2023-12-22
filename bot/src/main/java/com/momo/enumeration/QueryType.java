package com.momo.enumeration;

import lombok.Getter;

@Getter
public enum QueryType {

    WARFRAME("warframe","wf查询"),

    WARFRAME_MARKET("warframeMarket", "wm查询");

    private final String code;

    private final String desc;
    QueryType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
