package com.momo.service;

import com.momo.entity.warframeMarket.WarframeMarketItem;
import org.springframework.transaction.annotation.Transactional;

/**
 * warframe查询Service
 */
public interface WarframeMarketService extends SuperService<WarframeMarketItem>{

    /**
     * 获取所有的item
     */
    void getAllItem();

    /**
     * item处理-遗物
     */
    void dealRelicItem();

    /**
     * item处理-prime
     */
    void dealPrime();

    /**
     * item处理-mod
     */
    void dealModItem();

    /**
     * 查询wm信息
     * @param query 查询词
     * @return wm信息
     */
    String getWarframeMarketInfo(String query);
}
