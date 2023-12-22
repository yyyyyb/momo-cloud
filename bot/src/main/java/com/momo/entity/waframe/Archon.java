package com.momo.entity.waframe;

import com.momo.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * warframe官方执行官信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Archon implements Serializable {

    @Serial
    private static final long serialVersionUID = 1079648583116221003L;

    /**
     * 数据id
     */
    private String id;

    /**
     * 开始时间
     */
    private String activation;

    /**
     * 任务总长时间
     */
    private String startString;

    /**
     * 过期时间
     */
    private String expiry;

    /**
     * 是否有效
     */
    private Boolean active;

    /**
     * 奖池
     */
    private String rewardPool;

    /**
     * 任务类型
     */
    private List<Variant> variants;

    /**
     * 任务类型
     */
    private List<Mission> missions;

    /**
     * 头目
     */
    private String boss;

    /**
     * 敌人派系
     */
    private String faction;

    /**
     * 地点
     */
    private Boolean expired;

    /**
     * 已过时间
     */
    private String eta;

    /**
     * 组装执行官突击任务
     * @return 执行官突击
     */
    public String generateStr() {
        if (!active) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("当前查询:【执行官突击】" + "\n");
        sb.append(" - Boss: ").append(getBoss()).append("\n");
        sb.append(" - 派系: ").append(getFaction()).append("\n");
        //任务
        for (int i = 0; i < missions.size(); i++) {
            Mission mission = missions.get(i);
            sb.append(" - 任务").append(i + 1).append("\n");
            sb.append("   - 地点: ").append(mission.getNode()).append("\n");
            sb.append("   - 类型: ").append(mission.getType()).append("\n");
        }
        sb.append(" - 剩余: ").append(DateUtil.dateReplace(getEta()));
        return sb.toString();
    }
}
