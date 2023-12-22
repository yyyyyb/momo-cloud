package com.momo.service;

import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.springframework.stereotype.Service;

public interface MessageDealService {

    /**
     * 处理好友信息
     * @param event 事件
     */
    void dealFriendMessage(FriendMessageEvent event);

    /**
     * 处理群内消息
     * @param event 事件
     */
    void dealGroupMessage(GroupMessageEvent event);

    String getInfoByWord(String type, String word);
}
