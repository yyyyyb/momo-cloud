package com.momo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.momo.mapper.SuperMapper;

import java.util.List;

public interface SuperService<T> extends IService<T> {

    /**
     * 批量保存数据
     * <p>
     * 注意：该方法仅仅测试过mysql
     *
     * @param entityList
     * @return
     */
    default boolean saveBatchSomeColumn(List<T> entityList) throws Exception{
        if (entityList.isEmpty()) {
            return true;
        }

        if (entityList.size() > 5000) {
            throw new Exception("批量新增数据过多");
        }

        return SqlHelper.retBool(((SuperMapper) getBaseMapper()).insertBatchSomeColumn(entityList));
    }
}
