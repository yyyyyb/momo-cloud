package com.momo.entity.waframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * warframe官方午夜电波
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NightWave implements Serializable {

    @Serial
    private static final long serialVersionUID = -2833717817082423183L;

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
     * 数量
     */
    private String season;

    /**
     * 任务类型
     */
    private String tag;

    /**
     * ...?
     */
    private Long phase;

    /**
     * ...?
     */
    private Object params;

    /**
     * ...?
     */
    private List<String> possibleChallenges;

    /**
     * 午夜电波挑战
     */
    private List<ActiveChallenge> activeChallenges;

    /**
     * 奖励类型
     */
    private List<String> rewardTypes;

    /**
     * 组装午夜电波信息
     * @return 午夜电波信息
     */
    public String generateStr() {
        if (getActiveChallenges().isEmpty()) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("当前查询:【午夜电波】\n");
        for (int i = 0; i < getActiveChallenges().size(); i++) {
            ActiveChallenge activeChallenge = getActiveChallenges().get(i);
            sb.append((i + 1)).append(". ").append(activeChallenge.getDesc()).append(" ").append(activeChallenge.getReputation()).append("经验");
            sb.append("\n");
        }
        return sb.toString();
    }
}
