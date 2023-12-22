package com.momo.entity.waframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Choices implements Serializable {

    @Serial
    private static final long serialVersionUID = -765152566557863527L;

    private String category;

    private String categoryKey;

    private List<String> choices;
}
