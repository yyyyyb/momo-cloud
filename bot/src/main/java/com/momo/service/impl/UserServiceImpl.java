package com.momo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.momo.dao.UserMapper;
import com.momo.entity.User;
import com.momo.enumeration.QueryType;
import com.momo.service.MessageDealService;
import com.momo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private MessageDealService messageDealService;

    public String getUserInfo(String qq) {
        return messageDealService.getInfoByWord(QueryType.WARFRAME.getCode(), qq);
    }
}
