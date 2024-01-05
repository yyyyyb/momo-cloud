package com.momo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.momo.constants.WarframeMarketConstants;
import com.momo.dao.warframeMarket.*;
import com.momo.entity.warframeMarket.*;
import com.momo.service.WarframeMarketService;
import com.momo.utils.ChineseUtil;
import com.momo.utils.UrlConnectUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WarframeMarketServiceImpl extends SuperServiceImpl<WarframeMarketItemMapper, WarframeMarketItem> implements WarframeMarketService{
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private WarframeRelicMapper relicMapper;
    @Resource
    private WarframePrimeMapper warframePrimeMapper;

    @Resource
    private WarframeModMapper warframeModMapper;

    @Resource
    private WarframeWikiModMapper warframeWikiModMapper;

    /**
     * 获取所有的item
     */
    @Transactional(rollbackFor = Exception.class)
    public void getAllItem() {

        //获取warframe market所有的可交易物品
        List<MarketItem> marketItem = getMarketItem();

        assert marketItem != null;

        marketItem.forEach(t -> {
            WarframeMarketItem temp = new WarframeMarketItem();
            temp.setItemUrl(t.getUrlName());
            temp.setItemName(t.getItemName());
            temp.setItemId(t.getId());

            save(temp);
        });

        //获取 wiki 的mod
        String head = "https://warframe.huijiwiki.com/index.php?title=分类:MOD";
        boolean hasNext = true;
        String url = head;
        while (hasNext) {
            try {
                Connection connect = Jsoup.connect(url);
                Document document = connect.get();

                //获取 MOD
                Elements li = document.getElementsByTag("li");
                for (Element element : li) {
                    if(element.hasText()) {
                        String text = element.text();
                        if(text.length() < 10) {
                            WarframeWikiMod mod = new WarframeWikiMod();
                            if (text.contains("Prime")) {
                                text = text.replace("Prime", " Prime");
                            }
                            mod.setModName(text);
                            warframeWikiModMapper.insert(mod);
                        }
                    }
                }

                //获取下一页
                Elements a = document.getElementsByTag("a");
                boolean isNext = false;
                for (Element element : a) {
                    if (element.hasText()) {
                        if ("下一页".equals(element.text())) {
                            isNext = true;
                            String href = element.attr("href");
                            String[] split = href.split("&");
                            url = head + "&".concat(split[1]);
                            break;
                        }
                    }
                }

                if (!isNext) {
                    hasNext = false;
                }
            } catch (Exception e) {
                log.error("获取wiki mod 失败");
            }
        }
    }

    /**
     * 处理遗物item
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dealRelicItem() {
        LambdaQueryWrapper<WarframeMarketItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(WarframeMarketItem::getItemName, "遗物");
        List<WarframeMarketItem> warframeMarketItems = baseMapper.selectList(queryWrapper);

        assert warframeMarketItems != null;

        warframeMarketItems.forEach(t -> {
            WarframeRelic relic = new WarframeRelic();
            relic.setRelicName(t.getItemName());
            relic.setRelicUrl(t.getItemUrl());
            relic.setId(t.getId());
            relic.setRelicId(t.getItemId());

            //纪元
            String[] tierArray = t.getItemName().split(" ");
            relic.setRelicTier(tierArray[0]);

            //查询单词
            String replace = t.getItemName().replace(" ", "");
            String replace1 = replace.replace("纪", "").replace("遗物", "");

            if (StringUtils.equals(replace1, replace)) {
                relic.setRelicRemark(replace);
            } else {
                relic.setRelicRemark(replace + "," + replace1);
            }

            relicMapper.insert(relic);
        });

        //执行完成后删除表里数据
        baseMapper.deleteRelic();
    }


    /**
     * 处理遗物item
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dealPrime() {
        LambdaQueryWrapper<WarframeMarketItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(WarframeMarketItem::getItemName, "Prime");
        queryWrapper.notLikeRight(WarframeMarketItem::getItemUrl, "prime_");
        queryWrapper.orderByAsc(WarframeMarketItem::getItemId);
        List<WarframeMarketItem> warframeMarketItems = baseMapper.selectList(queryWrapper);

        //先新增所有的prime
        warframeMarketItems.forEach(t -> {
            WarframePrime warframePrime = new WarframePrime();
            warframePrime.setId(t.getId());

            String itemName = t.getItemName();
            String trim = itemName.trim();
            warframePrime.setPrimeName(trim);
            warframePrime.setPrimeUrl(t.getItemUrl());

            trim = trim.replace(" ", "").replace("·", "").replace("&", "");
            String replace = trim.replace("Prime", "p");
            warframePrime.setPrimeRemark(trim + "," + replace);
            warframePrime.setPrimeId(t.getItemId());

            warframePrimeMapper.insert(warframePrime);
        });

        //处理完成后删除数据
        baseMapper.deletePrime();
    }

    /**
     *
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dealModItem() {
        List<WarframeMarketItem> modItem = baseMapper.getModItem();

        assert modItem != null;

        modItem.forEach(t -> {
            WarframeMod mod = new WarframeMod();
            mod.setModName(t.getItemName());
            mod.setModUrl(t.getItemUrl());
            mod.setId(t.getId());
            mod.setModId(t.getItemId());

            String replace = t.getItemName().replace(" ", "");
            mod.setModRemark(replace);

            warframeModMapper.insert(mod);
        });

        baseMapper.deleteMod();

        //剩余的item
    }
    /**
     * 获取wm信息
     * @param query 查询物品
     * @return wm信息
     */
    @Override
    public String getWarframeMarketInfo(String query) {
        String replace = query.replace(" ", "");
        //模糊搜索
        List<WarframeMarketItem> warframeMarketItems = baseMapper.getWarframeMarketUrl(replace);


        StringBuilder result = new StringBuilder();
        if(!warframeMarketItems.isEmpty()) {

            if (warframeMarketItems.size() > 1) {
                result.append("- 可能想查询").append("\n");
                warframeMarketItems.forEach(t -> result.append("  - ").append(t.getItemName()).append("\n"));
            } else {
                WarframeMarketItem warframeMarketItem = warframeMarketItems.getFirst();
                String url = WarframeMarketConstants.WARFRAME_MARKET_ORDER_URL.replace("{0}", warframeMarketItem.getItemUrl());
                result.append(getWmMessage(url, warframeMarketItem.getItemName()));
            }
        }
        return result.toString();
    }

    /**
     * 设置请求头
     * @return 请求
     */
    public HttpEntity getHttpEntity() {
        //设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Language", "zh-hans");

        return new HttpEntity(httpHeaders);
    }


    /**
     * 获取market所有item
     */
    private List<MarketItem> getMarketItem() {
        try {
            List<MarketItem> result = new ArrayList<>();
            ResponseEntity<String> exchange = restTemplate.exchange(WarframeMarketConstants.WARFRAME_MARKET_ITEM_URL, HttpMethod.GET, getHttpEntity(), String.class);
            String itemStr = exchange.getBody();

            String jsonValue = ChineseUtil.convertToSimplifiedChinese(itemStr);
            JsonNode jsonNode = new ObjectMapper().readTree(jsonValue);
            JsonNode itemsNode = jsonNode.path("payload").path("items");
            for (JsonNode itemNode : itemsNode) {
                MarketItem t = new MarketItem();
                t.setItemName(itemNode.path("item_name").asText()); ;
                t.setThumb(itemNode.path("thumb").asText());
                t.setUrlName(itemNode.path("url_name").asText());
                t.setId(itemNode.path("id").asText());
                result.add(t);
            }
            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("获取warframe market item 失败");
        }
        return null;
    }

    /**
     * 访问wm
     * @param url 地址
     * @param itemName 部件
     * @return wm信息
     */
    private String getWmMessage(String url, String itemName) {
        StringBuilder resultMsg = new StringBuilder();
        Gson gson = new Gson();
        String wmData = UrlConnectUtil.getHttpUrlData(url);
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(wmData) && !wmData.contains("/error/404")) {
            //解析json串
            JsonObject jsonObject = JsonParser.parseString(wmData).getAsJsonObject();
            JsonObject payLoad = jsonObject.getAsJsonObject("payload");
            JsonArray orders = payLoad.getAsJsonArray("orders");

            List<Orders> sells = new ArrayList<>();
            List<Orders> buys = new ArrayList<>();

            for (JsonElement temp : orders) {
                Orders order = gson.fromJson(temp,new TypeToken<Orders>(){}.getType());
                //只获取在线的信息
                if ("ingame".equalsIgnoreCase(order.getUser().getStatus())) {
                    if ("sell".equals(order.getOrder_type())) {
                        sells.add(order);
                    } else if ("buy".equals(order.getOrder_type())) {
                        buys.add(order);
                    }
                }
            }

            //组装完成按照价格排序
            //出售物品价格升序
            sells.sort((Orders a, Orders b) -> (int) (a.getPlatinum() - b.getPlatinum()));
            //收购物品价格降序
            buys.sort((Orders a, Orders b) -> (int) (b.getPlatinum() - a.getPlatinum()));

            //取5位
            int sellSize = Math.min(sells.size(), 5);
            resultMsg.append("当前查询 【").append(itemName).append("】\n");
            resultMsg.append("①最低价").append(sellSize).append("位卖家:");
            resultMsg.append("\n");
            for (int i = 0; i < sellSize; i++) {
                resultMsg.append((i + 1)).append(". ").append("玩家：");
                resultMsg.append(sells.get(i).getUser().getIngame_name()).append("    ");
                resultMsg.append("白金： ");
                resultMsg.append((int)sells.get(i).getPlatinum());
                resultMsg.append("\n");
            }

            int buySize = Math.min(buys.size(), 5);
            resultMsg.append("②最高价").append(buySize).append("位买家:");
            resultMsg.append("\n");
            for (int i = 0; i < buySize; i++) {
                resultMsg.append((i + 1)).append(". ").append("玩家： ");
                resultMsg.append(buys.get(i).getUser().getIngame_name()).append("    ");
                resultMsg.append("白金： ");
                resultMsg.append((int)buys.get(i).getPlatinum());
                resultMsg.append("\n");
            }

        }
        return resultMsg.toString();
    }
}
