package com.momo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface SuperMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入所有字段
     * <p>
     * 只测试过MySQL！只测试过MySQL！只测试过MySQL！
     *
     * @param entityList 实体集合
     * @return 插入数量
     */
    int insertBatchSomeColumn(List<T> entityList);
}
