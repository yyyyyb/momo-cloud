package com.momo.service;

import com.momo.entity.User;

public interface UserService extends SuperService<User> {
    String getUserInfo(String qq);


    //机器人启动的时候获取所有的群组+成员

}
