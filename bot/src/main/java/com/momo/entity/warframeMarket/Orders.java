package com.momo.entity.warframeMarket;

import lombok.Data;

@Data
public class Orders {
    private int quantity;   //数量
    private String order_type;
    private double platinum;
    private WarframeUser user;
    private String platform;
    private String region;
    private String creation_date;
    private String last_update;
    private String visible;
    private String id;
}
