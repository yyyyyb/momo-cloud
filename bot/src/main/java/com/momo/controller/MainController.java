package com.momo.controller;

import com.momo.R;
import com.momo.cache.CacheKeyDefinition;
import com.momo.entity.User;
import com.momo.service.UserService;
import com.momo.service.WarframeMarketService;
import com.momo.service.WarframeService;
import com.momo.utils.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MainController {
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;

    @Resource
    private WarframeMarketService warframeMarketService;

    /**
     * 测试
     */
    @GetMapping("/test")
    public String test(String qq) {
        redisUtil.get(CacheKeyDefinition.WARFRAME_SORTIE);
       return userService.getUserInfo(qq);
    }

    /**
     * 测试
     */
    @GetMapping("/test2")
    public void test2() {
        warframeMarketService.getAllItem();
    }

    /**
     * 测试
     */
    @GetMapping("/test3")
    public void test3() {
        warframeMarketService.dealRelicItem();
    }


    /**
     * 测试
     */
    @GetMapping("/test4")
    public void test4() {
        warframeMarketService.dealPrime();
    }


    /**
     * 测试
     */
    @GetMapping("/test5")
    public void test5() {
        warframeMarketService.dealModItem();
    }

    /**
     * 测试
     */
    @GetMapping("/test6")
    public String test6(String query) {
        return warframeMarketService.getWarframeMarketInfo(query);
    }
}
