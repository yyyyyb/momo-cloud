package com.momo.entity.waframe;

import com.momo.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * warframe官方仲裁信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arbitration implements Serializable {

    @Serial
    private static final long serialVersionUID = 3537051316603832988L;

    /**
     * 数据id
     */
    private String id;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 过期时间
     */
    private String expiry;

    /**
     * 敌人
     */
    private String enemy;

    /**
     * 任务类型
     */
    private String type;


    /**
     * 任务类型
     */
    private String typeKey;

    /**
     * 是否archWing
     */
    private Boolean archwing;

    /**
     * 是否archWing
     */
    private Boolean sharkwing;

    /**
     * 任务地点
     */
    private String node;

    /**
     * 任务地点
     */
    private String nodeKey;

    /**
     * 是否有效
     */
    private Boolean expired;

    public String generateStr() {
        if(getActivation() == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        //获取当前小时
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        try {
            String newDate = DateUtil.dateChangeStrS(getActivation());
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newDate);
            String arbitrationHour = new SimpleDateFormat("HH:mm").format(date);
            int oldHour = Integer.parseInt(arbitrationHour.substring(0,2));
            if (hour == oldHour) {
                sb.append("当前查询:【仲裁】\n");
                sb.append(" - 敌人: ").append(getEnemy()).append("\n");
                sb.append(" - 地点: ").append(getNode()).append("\n");
                sb.append(" - 任务: ").append(getType());
            } else {
                sb.append("还未刷新，刷新时间(每小时的第6分钟)");
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString();
    }
}
