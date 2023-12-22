package com.momo.enumeration;


/**
 * warframe 裂缝类型
 */
public enum FissureType {

    NORMAL("01", "普通裂缝"),
    HARD("02", "钢铁裂缝"),
    STORM("03", "九重天裂缝"),
    AN_HUN("04", "安魂裂缝"),
    ;

    private String code;

    private String desc;

    FissureType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
}
