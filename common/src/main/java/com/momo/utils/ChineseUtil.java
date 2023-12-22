package com.momo.utils;

import com.github.stuxuhai.jpinyin.ChineseHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * 语言工具类
 */
@Slf4j
public class ChineseUtil {

    /**
     * 简体转换为繁体
     * @param pinYinStr 要转换的字符串
     * @return
     */
    public static String convertToTraditionalChinese(String pinYinStr) {
        String tempStr;
        try {
            tempStr = ChineseHelper.convertToTraditionalChinese(pinYinStr);
        } catch (Exception e) {
            tempStr = pinYinStr;
            log.error("转换繁体失败, 简体内容={}", pinYinStr);
        }
        return tempStr;
    }

    /**
     * 繁体转换为简体
     * @param pinYinSt 要转换的字符串
     * @return
     */
    public static String convertToSimplifiedChinese(String pinYinSt) {
        String tempStr;
        try {
            tempStr = ChineseHelper.convertToSimplifiedChinese(pinYinSt);
        } catch (Exception e) {
            tempStr = pinYinSt;
            log.error("转换简体失败, 繁体内容={}", pinYinSt);
        }

        return tempStr;
    }
}
