package com.momo.entity.warframeMarket;

import lombok.Data;

@Data
public class WarframeUser {
    private int reputation;
    private String region;
    private String last_seen;
    private String ingame_name;
    private String status;
    private String id;
    private String avatar;
}
