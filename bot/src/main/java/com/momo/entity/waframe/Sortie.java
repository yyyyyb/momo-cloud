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
 * warframe官方突击信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sortie implements Serializable {

    @Serial
    private static final long serialVersionUID = -7413567793616900473L;

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
    private List<String> missions;

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

    public String generateSortie() {
        if (!active) {
            return "突击还未刷新，刷新时间(0点7分)";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("当前查询:【突击】" + "\n");
        sb.append(" - Boss: ").append(getBoss()).append("\n");
        //任务
        for (int i = 0; i < variants.size(); i++) {
            Variant variant = variants.get(i);
            sb.append(" - 任务").append(i + 1).append("\n");
            sb.append("   - 地点: ").append(variant.getNode()).append("\n");
            sb.append("   - 类型: ").append(variant.getMissionType()).append("\n");
            sb.append("   - 备注: ").append(variant.getModifier()).append("\n");
        }
        return sb.toString();
    }
}
