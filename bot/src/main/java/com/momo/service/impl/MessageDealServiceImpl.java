package com.momo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.momo.dao.QueryMethodMapper;
import com.momo.dto.MessageDTO;
import com.momo.entity.QueryMethod;
import com.momo.enumeration.QueryType;
import com.momo.service.MessageDealService;
import com.momo.service.WarframeService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MessageDealServiceImpl implements MessageDealService {

    @Resource
    private QueryMethodMapper queryMethodMapper;

    @Resource
    private WarframeService warframeService;

    /**
     *
     * @param event 事件
     */
    @Override
    public void dealFriendMessage(FriendMessageEvent event) {
        //获取消息链
        MessageChain message = event.getMessage();

        //获取文本内容
        StringBuilder textSb = new StringBuilder();
        //获取发的图片id
        List<String> imageList = new ArrayList<>();

        //遍历获取消息类型
        for (SingleMessage singleMessage : message) {
            //如果包含图片，获取到图片id
            if (singleMessage instanceof Image) {
                imageList.add(((Image) singleMessage).getImageId());
            }

            if (singleMessage instanceof PlainText) {
                textSb.append(((PlainText) singleMessage).getContent());
            }
        }

        //获取完所有的消息后进行处理
        dealMessage(MessageDTO.builder().text(textSb.toString()).imageList(imageList).build(), event);
    }

    /**
     * 群组消息处理
     * @param event 事件
     */
    @Override
    public void dealGroupMessage(GroupMessageEvent event) {
        //获取到运行的bot
        Bot bot = event.getBot();

        //获取消息链
        MessageChain message = event.getMessage();

        //判断是否@机器人
        boolean atBot = false;
        List<Long> atList = new ArrayList<>();

        //获取文本内容
        StringBuilder textSb = new StringBuilder();

        //获取发的图片id
        List<String> imageList = new ArrayList<>();

        //遍历获取消息类型
        for (SingleMessage singleMessage : message) {
            //如果@人 ，获取被@的id
            if (singleMessage instanceof At) {
                long target = ((At) singleMessage).getTarget();
                if(target == bot.getId()) {
                    atBot = true;
                } else {
                    atList.add(target);
                }
            }

            //如果包含图片，获取到图片id
            if (singleMessage instanceof Image) {
                imageList.add(((Image) singleMessage).getImageId());
            }

            if (singleMessage instanceof PlainText) {
                textSb.append(((PlainText) singleMessage).getContent());
            }
        }

        //获取完所有的消息后进行处理
        MessageDTO build = MessageDTO.builder().atBot(atBot).text(textSb.toString()).imageList(imageList).atList(atList).build();
        dealMessage(build, event);
    }

    /**
     * 消息的正式处理
     * @param messageDTO 消息实体类
     * @param event 事件
     */
    private void dealMessage(MessageDTO messageDTO, MessageEvent event) {
        //先获取发送者
        //User user = event.getSender();

        String sendMessage = null;
        //1.获取个人信息
        //2.查询服务
        if (StringUtils.isNotBlank(messageDTO.getText())) {
            String query = messageDTO.getText();
            //1.wf查询
            if(query.startsWith("/wf") || query.startsWith("wf")) {
                String wf = query.toLowerCase().replace("wf", "").replace("/", "").trim();
                sendMessage = getInfoByWord(QueryType.WARFRAME.getCode(), wf);
            }
//            //2.wm查询
//            if(query.startsWith("/wm") || query.startsWith("wm")) {
//                String wm = query.toLowerCase().replace("wm", "").replace("/", "");
//            }
//            //3.紫卡查询
//            if(query.startsWith("/wmr") || query.startsWith("wmr")) {
//
//            }
//            //4.遗物查询
//            if(query.startsWith("/rel") || query.startsWith("rel")) {
//
//            }
        }
        //3.其他服务

        if (StringUtils.isNotBlank(sendMessage)) {
            event.getSubject().sendMessage(sendMessage);
        }
    }

    /**
     * 获取信息
     * @param type 查询类型
     * @param word 查询文本
     * @return 结果
     */
    public String getInfoByWord(String type, String word) {
        String result = null;
        //获取wf下的查询方式
        LambdaQueryWrapper<QueryMethod> methodLambdaQueryWrapper = new LambdaQueryWrapper<>();
        methodLambdaQueryWrapper.eq(QueryMethod::getQueryType, type);
        List<QueryMethod> queryMethods = queryMethodMapper.selectList(methodLambdaQueryWrapper);

        if (!queryMethods.isEmpty()) {
            String todoMethod = null;

            for (QueryMethod t : queryMethods) {
                String[] queryArray = t.getQueryWord().split(",");
                for (String s : queryArray) {
                    if (s.equals(word)) {
                        todoMethod = t.getQueryMethod();
                        break;
                    }
                }
            }

            if (StringUtils.isNotBlank(todoMethod)) {
                Class<WarframeServiceImpl> clazz = WarframeServiceImpl.class;
                try{
                    Method[] allMethod = clazz.getMethods();
                    for (Method tempMethod : allMethod) {
                        if(tempMethod.getName().equals(todoMethod)) {
                            Object o = tempMethod.invoke(warframeService);
                            result = o.toString();
                            break;
                        }
                    }
                } catch (Exception e) {
                    log.error("wf查询失败，错误信息={}", e.getMessage());
                }
            }
        }
        return result;
    }
}
