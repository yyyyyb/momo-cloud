package com.momo.entity.waframe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward implements Serializable {

    @Serial
    private static final long serialVersionUID = 4654634956331762565L;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer cost;

    @Override
    public String toString() {
        return name + " 花费：" + cost;
    }
}
