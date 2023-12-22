package com.momo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -848701845313594180L;

    /**
     * 文本内容
     */
    private String text;

    /**
     * 是否At机器人
     */
    private Boolean atBot;

    /**
     * At人的集合
     */
    private List<Long> atList;

    /**
     * 图片集合
     */
    private List<String> imageList;
}
