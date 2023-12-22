package com.momo.listener;

import com.momo.service.MessageDealService;
import com.momo.utils.SpringUtil;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public class BotEventListener extends SimpleListenerHost {
    private MessageDealService messageDealService;

    public BotEventListener() {
        if (messageDealService == null) {
            messageDealService = SpringUtil.getBean(MessageDealService.class);
        }
    }
    /**
     * 好友消息事件
     */
    @EventHandler
    public void handleFriendMessage(FriendMessageEvent event) {
        messageDealService.dealFriendMessage(event);
    }

    /**
     * 群组消息事件
     */
    @EventHandler
    public void handleGroupMessage(GroupMessageEvent event) {
        messageDealService.dealGroupMessage(event);
    }
}
