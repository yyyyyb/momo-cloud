package com.momo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.momo.cache.CacheKeyDefinition;
import com.momo.constants.WarframeConstants;
import com.momo.entity.waframe.*;
import com.momo.service.WarframeService;
import com.momo.utils.ChineseUtil;
import com.momo.utils.DateUtil;
import com.momo.utils.RedisUtil;
import com.momo.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * warframe查询Service
 */
@Service
@Slf4j
public class WarframeServiceImpl implements WarframeService {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 普通裂缝信息
     * @return 普通裂缝
     */
    @Override
    public String getFissures(){
        var fissures = getFissure();
        assert fissures != null;
        List<Fissure> result = fissures.stream()
                .filter(t -> !t.getIsStorm() && !t.getIsHard() && !"5".equals(t.getTierNum())).toList();

        return generateFissureStr(result.stream().sorted(Comparator.comparing(Fissure::getTierNum)).toList());
    }

    /**
     * 钢铁裂缝信息
     * @return 钢铁裂缝
     */
    @Override
    public String getHardFissures(){
        var fissures = getFissure();
        assert fissures != null;
        List<Fissure> result = fissures.stream()
                .filter(Fissure::getIsHard).toList();

        return generateFissureStr(result.stream().sorted(Comparator.comparing(Fissure::getTierNum)).toList());
    }

    /**
     * 九重天裂缝信息
     * @return 九重天裂缝
     */
    @Override
    public String getStormFissures(){
        var fissures = getFissure();
        assert fissures != null;
        List<Fissure> result = fissures.stream()
                .filter(Fissure::getIsStorm).toList();
        return generateFissureStr(result.stream().sorted(Comparator.comparing(Fissure::getTierNum)).toList());
    }
    /**
     * 安魂裂缝信息
     * @return 安魂裂缝
     */
    @Override
    public String getTierNumFissures(){
        var fissures = getFissure();
        assert fissures != null;
        List<Fissure> result = fissures.stream()
                .filter(t -> "5".equals(t.getTierNum())).toList();
        return generateFissureStr(result.stream().sorted(Comparator.comparing(Fissure::getTierNum)).toList());
    }

    /**
     * 获取所有的裂缝信息
     * @return 裂缝信息
     */
    private List<Fissure> getFissure() {
        try {
            initRestTemplate();
            String fissureStr = restTemplate.getForObject(WarframeConstants.FISSURE_URL, String.class);

            TypeReference<List<Fissure>> typeRef = new TypeReference<>() {};
            return getListFormat(fissureStr, typeRef);
        } catch (Exception e) {
            log.error("查询裂缝信息出错,错误信息={}", e.getMessage());
        }
        return null;
    }

    /**
     * 拼接裂缝信息
     * @param fissures 裂缝信息集合
     * @return 裂缝信息Str
     */
    private String generateFissureStr(List<Fissure> fissures) {
        StringBuilder fissureSb = new StringBuilder();
        if(!CollectionUtils.isEmpty(fissures)) {
            String tier = null;
            for (Fissure fissure : fissures) {
                if (!StringUtils.equals(tier, fissure.getTier())) {
                    tier = fissure.getTier();
                    fissureSb.append(" - ").append(tier).append("\n");
                }
                String text = fissure.generateFissure();
                if (StringUtils.isNotBlank(text)) {
                    fissureSb.append(text);
                    fissureSb.append("\n");
                }
            }
        }
        return fissureSb.toString();
    }

    /**
     * 获取突击信息
     * @return 突击信息
     */
    @Override
    public String getSortie(){
        initRestTemplate();

        //查询缓存是否有突击信息
        if (redisUtil.get(CacheKeyDefinition.WARFRAME_SORTIE) != null) {
            return (String) redisUtil.get(CacheKeyDefinition.WARFRAME_SORTIE);
        }

        //未从缓存获取到重新查询
        String sortieStr = restTemplate.getForObject(WarframeConstants.SORTIE_URL, String.class);
        Sortie sortie = getObjectFormat(sortieStr, Sortie.class);

        assert sortie != null;

        String result = sortie.generateSortie();
        if (StringUtils.isNotBlank(result)) {
            redisUtil.set(CacheKeyDefinition.WARFRAME_SORTIE, result, DateUtil.getTodayRemainSecond());
        }
        return result;
    }

    /**
     * 获取午夜电波信息
     * @return 午夜电波信息
     */
    @Override
    public String getNightWave() {
        initRestTemplate();

        String nightWaveStr = restTemplate.getForObject(WarframeConstants.NIGHT_WAVE_URL, String.class);
        NightWave nightWave = getObjectFormat(nightWaveStr, NightWave.class);

        assert nightWave != null;

        return nightWave.generateStr();
    }

    /**
     * 获取虚空商人信息
     * @return 虚空商人
     */
    @Override
    public String getVoidTrader() {
        initRestTemplate();
        String voidTraderStr = restTemplate.getForObject(WarframeConstants.TRADER_URL, String.class);
        VoidTrader voidTrader = getObjectFormat(voidTraderStr, VoidTrader.class);
        assert voidTrader != null;
        StringBuilder result = new StringBuilder();

        if (voidTrader.getActive()) {
            result.append("虚空商人信息").append("\n");
            result.append("  此次着陆将在").append(DateUtil.dateReplace(voidTrader.getEndString())).append("结束").append("\n");
            result.append("  携带的物品如下 : ").append("\n");
            List<Inventory> inventory = voidTrader.getInventory();
            if (!inventory.isEmpty()) {
                for (int i = 0; i < inventory.size(); i++) {
                    Inventory inventoryVO = inventory.get(i);
                    result.append((i + 1)).append(". ").append(inventoryVO.getItem()).append("  ");
                    result.append(inventoryVO.getDucats()).append("杜卡德  ");
                    result.append(inventoryVO.getCredits()).append("星币").append("\n");
                }
            }
        } else {
            result.append("- 距离").append(voidTrader.getCharacter()).append("着陆");
            result.append("\n").append("   还有: ").append(DateUtil.dateReplace(voidTrader.getStartString()));
            result.append("\n").append("   地点: ").append(voidTrader.getLocation());
        }

        return result.toString();
    }

    /**
     * 获取入侵信息
     * @return 入侵信息
     */
    @Override
    public String getInvasion() {
        return null;
    }

    /**
     * 获取执行官突击任务
     * @return 执行官突击
     */
    @Override
    public String getArchonHunt() {
        initRestTemplate();

        //从缓存获取
        if (redisUtil.get(CacheKeyDefinition.WARFRAME_ARCHON) != null) {
            return (String) redisUtil.get(CacheKeyDefinition.WARFRAME_ARCHON);
        }

        //缓存没的话重新获取
        String archonStr = restTemplate.getForObject(WarframeConstants.ARCHON_HUNT_URL, String.class);
        Archon archon = getObjectFormat(archonStr, Archon.class);

        assert archon != null;

        return archon.generateStr();
    }

    /**
     * 获取地球平原信息
     * @return 地球平原
     */
    @Override
    public String getEarthCycle(){
        initRestTemplate();
        //地球
        String earthStr = restTemplate.getForObject(WarframeConstants.EARTH_CYCLE_URL, String.class);
        Cycle earth = getObjectFormat(earthStr, Cycle.class);
        assert earth != null;

        return "- 地球" + "\n" +
                " 状态: " + (earth.getIsDay() ? "白天" : "夜晚") + " " + "剩余: " + DateUtil.dateReplace(earth.getTimeLeft());
    }

    /**
     * 获取希图斯平原信息
     * @return 希图斯平原
     */
    @Override
    public String getCetusCycle(){
        initRestTemplate();
        //希图斯
        String cetusStr = restTemplate.getForObject(WarframeConstants.CETUS_CYCLE_URL, String.class);
        Cycle cetus = getObjectFormat(cetusStr, Cycle.class);
        assert cetus != null;

        return "- 希图斯" + "\n" +
                " 状态: " + (cetus.getIsDay() ? "白天" : "夜晚") + " " + "剩余: " + DateUtil.dateReplace(cetus.getTimeLeft());
    }

    /**
     * 获取火卫2平原信息
     * @return 火卫2平原
     */
    @Override
    public String getCambionCycle(){
        initRestTemplate();
        //火卫二
        String cambionStr = restTemplate.getForObject(WarframeConstants.CAMBION_CYCLE_URL, String.class);
        Cycle cambion = getObjectFormat(cambionStr, Cycle.class);
        assert cambion != null;

        return "- 火卫二" + "\n" +
                " 状态: " + cambion.getActive() + " " + "剩余: " + DateUtil.dateReplace(cambion.getTimeLeft());
    }

    /**
     * 获取扎里曼平原信息
     * @return 扎里曼平原
     */
    @Override
    public String getZarimanCycle(){
        initRestTemplate();
        //扎里曼
        String zarimanStr = restTemplate.getForObject(WarframeConstants.ZARIMAN_CYCLE_URL, String.class);
        Cycle zariman = getObjectFormat(zarimanStr, Cycle.class);
        assert zariman != null;

        return "- 扎里曼" + "\n" +
                " 状态: " + (StringUtils.equals("corpus", zariman.getState()) ? zariman.getState() : "grineer") + " " + "剩余: " + DateUtil.dateReplace(zariman.getTimeLeft());
    }

    /**
     * 获取福尔图娜平原信息
     * @return 福尔图娜平原
     */
    @Override
    public String getVallisCycle(){
        initRestTemplate();
        //福尔图娜
        String vallisStr = restTemplate.getForObject(WarframeConstants.VALLIS_CYCLE_URL, String.class);
        Cycle vallis = getObjectFormat(vallisStr, Cycle.class);
        assert vallis != null;

        return "- 福尔图娜" + "\n" +
                " 状态: " + (vallis.getIsWarm() ? "温暖" : "寒冷") + " " + "剩余: " + DateUtil.dateReplace(vallis.getTimeLeft());
    }

    /**
     * 获取地球平原信息
     * @return 地球平原
     */
    @Override
    public String getDuviriCycle(){
        initRestTemplate();
        //王境
        String duviriStr = restTemplate.getForObject(WarframeConstants.DUVIRI_CYCLE_URL, String.class);
        Cycle duviri = getObjectFormat(duviriStr, Cycle.class);
        assert duviri != null;

        return "- 王境 " + "\n" +
                " 状态: " + duviri.getState();
    }

    /**
     * 获取所有的平原信息
     * <p>
     * @return 所有的平原信息
     */
    @Override
    public String getAllCycle() {
        //地球
        return getEarthCycle() + "\n" +
                //希图斯
                getCetusCycle() + "\n" +
                //金星
                getVallisCycle() + "\n" +
                //火卫2
                getCambionCycle() + "\n" +
                //扎里曼
                getZarimanCycle() + "\n" +
                //王境
                getDuviriCycle();
    }

    /**
     * 获取回廊奖励
     * @return 回廊奖励
     */
    @Override
    public String getDuviriChoices() {
        initRestTemplate();
        //王境
        String duviriStr = restTemplate.getForObject(WarframeConstants.DUVIRI_CYCLE_URL, String.class);
        Cycle duviri = getObjectFormat(duviriStr, Cycle.class);
        assert duviri != null;

        Choices[] choices = duviri.getChoices();

        StringBuilder sb = new StringBuilder();
        sb.append("- 回廊奖励:").append("\n");
        for (Choices choice : choices) {
            sb.append(" ").append("等级:").append(choice.getCategory()).append("\n");
            sb.append("  ").append("  ").append("奖励:");
            for (String temp : choice.getChoices()) {
                sb.append(temp).append("、");
            }
            sb.deleteCharAt(sb.lastIndexOf("、"));
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 获取仲裁信息
     * @return 仲裁
     */
    @Override
    public String getArbitration() {
        initRestTemplate();
        String arbitrationStr = restTemplate.getForObject(WarframeConstants.ARBITRATION_URL, String.class);
        Arbitration arbitration = getObjectFormat(arbitrationStr, Arbitration.class);

        assert arbitration != null;
        if (arbitration.getExpired()) {
            return arbitration.generateStr();
        }
        return null;
    }

    /**
     * 钢铁奖励查询
     * @return 钢铁奖励
     */
    @Override
        public String getSteelPath() {
        initRestTemplate();
        //钢铁奖励
        String steelPathStr = restTemplate.getForObject(WarframeConstants.STEEL_PATH_URL, String.class);
        SteelPath steelPath = getObjectFormat(steelPathStr, SteelPath.class);

        assert steelPath != null;
        StringBuilder result = new StringBuilder();
        result.append("当前奖励")
                .append("\n")
                .append("  ").append(steelPath.getCurrentReward().toString()).append("\n")
                .append("  ").append("剩余").append(DateUtil.dateReplace(steelPath.getRemaining())).append("\n")
                .append("轮换奖励预览:").append("\n");

        //获取后面的奖励
        List<Reward> rotation = steelPath.getRotation();
        int i = rotation.indexOf(steelPath.getCurrentReward());
        List<Reward> rewards = rotation.subList(i, rotation.size() - 1);
        for (Reward reward : rewards) {
            result.append("  ").append(reward.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * 每日特惠查询
     * @return 每日特惠
     */
    @Override
    public String getDailyDeals() {
        initRestTemplate();
        String dailyDealsStr = restTemplate.getForObject(WarframeConstants.DAILY_DEALS_URL, String.class);

        TypeReference<List<DailyDeals>> typeRef = new TypeReference<>() {};
        List<DailyDeals> dailyDeals = getListFormat(dailyDealsStr, typeRef);

        assert dailyDeals != null;

        return "Darvo的特卖商品:\n" +
                dailyDeals;
    }

    /**
     * json文字转换
     * @param jsonValue 待转换内容
     * @param t 泛型
     * @return 转换后的内容
     */
    private <T> T getObjectFormat(String jsonValue, Class<T> t) {
        try {
            jsonValue = ChineseUtil.convertToSimplifiedChinese(jsonValue);
            return new ObjectMapper().readValue(jsonValue, t);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("json转换失败, json={}", jsonValue);
        }

        return null;
    }

    /**
     * json文字转换
     * @param jsonValue 待转换内容
     * @param typeReference 泛型
     * @return 转换后的内容
     */
    private <T> T getListFormat(String jsonValue, TypeReference<T> typeReference) {
        try {
            jsonValue = ChineseUtil.convertToSimplifiedChinese(jsonValue);
            return new ObjectMapper().readValue(jsonValue, typeReference);
        } catch (Exception e) {
            log.error("json转换失败, json={}", jsonValue);
            log.error(e.getMessage());
        }

        return null;
    }

    /**
     * 初始化restTemplate
     */
    private void initRestTemplate() {
        //反射调用时，@Resource未注入
        if (restTemplate == null) {
            restTemplate = SpringUtil.getBean(RestTemplate.class);
        }
        if (redisUtil == null) {
            redisUtil = SpringUtil.getBean(RedisUtil.class);
        }
    }
}