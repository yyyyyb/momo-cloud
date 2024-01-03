package com.momo.entity.waframe;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * warframe 官方入侵信息json
 */
@Data
public class Invasions {
    private String id;

    private String activation;

    private String startString;

    private String node;

    private String nodeKey;

    private String desc;

    private InvasionReward attackerReward;

    private String attackingFaction;

    private InvasionObject attacker;

    private InvasionReward defenderReward;

    private String defendingFaction;

    private InvasionObject defender;

    private Boolean vsInfestation;

    private String count;

    private Long requiredRuns;

    private String completion;

    private Boolean completed;

    private String eta;

    private String[] rewardTypes;

    /**
     * 组装入侵文本
     * @return 入侵信息
     */
    public String generateStr() {
        StringBuilder sb = new StringBuilder();
        sb.append(" - 地点:").append(node).append("\n");
        if (vsInfestation) {
            sb.append(" - I系怪物入侵").append("\n");
        } else {
            sb.append(" - 攻击:").append(attackingFaction).append("\n");
            sb.append("   - 奖励:").append(attackerReward.getAsString()).append("\n");
        }
        sb.append(" - 防守:").append(defendingFaction).append("\n");
        sb.append("   - 奖励:").append(defenderReward.getAsString()).append("\n");
        return sb.toString();
    }
}
