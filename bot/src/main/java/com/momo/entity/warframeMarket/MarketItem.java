package com.momo.entity.warframeMarket;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class MarketItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 8147314590772166331L;

    private String itemName;

    private String thumb;

    private String urlName;

    private String id;
}
