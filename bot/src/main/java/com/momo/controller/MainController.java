package com.momo.controller;

import com.momo.R;
import com.momo.cache.CacheKeyDefinition;
import com.momo.entity.User;
import com.momo.service.UserService;
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

    /**
     * 测试
     */
    @GetMapping("/test")
    public String test(String qq) {
        redisUtil.get(CacheKeyDefinition.WARFRAME_SORTIE);
       return userService.getUserInfo(qq);
    }
}
