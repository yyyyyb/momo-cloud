package com.momo.entity.warframeMarket;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momo.SuperEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("b_warframe_wiki_mod")
@AllArgsConstructor
@Builder
public class WarframeWikiMod extends SuperEntity<Long> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4548013751643092085L;

    /**
     * 黑话
     */
    @TableField(value = "mod_name")
    private String modName;
}
