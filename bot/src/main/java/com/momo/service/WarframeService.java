package com.momo.service;

/**
 * warframe查询Service
 */
public interface WarframeService {

    /**
     * 普通裂缝信息
     * @return 普通裂缝
     */
    String getFissures();

    /**
     * 钢铁裂缝信息
     * @return 钢铁裂缝
     */
    String getHardFissures();

    /**
     * 九重天裂缝信息
     * @return 九重天裂缝
     */
    String getStormFissures();

    /**
     * 安魂裂缝信息
     * @return 安魂裂缝
     */
    String getTierNumFissures();

    /**
     * 获取地球平原信息
     * @return 地球平原
     */
    String getEarthCycle();

    /**
     * 获取希图斯平原信息
     * @return 希图斯
     */
    String getCetusCycle();

    /**
     * 获取扎里曼平原信息
     * @return 扎里曼
     */
    String getCambionCycle();

    /**
     * 获取地球平原信息
     * @return 地球平原
     */
    String getZarimanCycle();

    /**
     * 获取福尔图娜平原信息
     * @return 福尔图娜
     */
    String getVallisCycle();

    /**
     * 获取王境信息
     * @return 王境
     */
    String getDuviriCycle();

    /**
     * 获取全部平原信息
     * @return 平原
     */
    String getAllCycle();

    /**
     * 获取突击信息
     * @return 突击信息
     */
    String getSortie();

    /**
     * 获取午夜电波信息
     * @return 午夜电波
     */
    public String getNightWave();

    /**
     * 获取回廊奖励
     * @return 回廊奖励
     */
    String getDuviriChoices();

    /**
     * 获取仲裁信息
     * @return 仲裁
     */
    String getArbitration();

    /**
     * 获取虚空商人信息
     * @return 虚空商人
     */
    String getVoidTrader();

    /**
     * 入侵信息
     * @return 入侵信息
     */
    String getInvasion();

    /**
     * 获取钢铁奖励信息
     * @return 钢铁奖励
     */
    String getSteelPath();

    /**
     * 获取每日特惠信息
     * @return 每日特惠
     */
    String getDailyDeals();

    /**
     * 执行官任务
     */
    String getArchonHunt();

    /**
     * 获取英择谛赏金
     * @return 英择谛赏金
     */
    String getEntratiSyndicate();

    /**
     * 希图斯赏金
     * @return 希图斯赏金
     */
    String getOstronsSyndicate();

    /**
     * 获取索拉里联盟赏金
     * @return 索拉里联盟赏金
     */
    String getSolarisUnitedSyndicate();

    /**
     * 获取扎里曼赏金
     * @return 扎里曼赏金
     */
    String getZarimanSyndicate();
}
