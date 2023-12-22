package com.momo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.momo.R;
import com.momo.mapper.SuperMapper;
import com.momo.service.SuperService;
import org.springframework.transaction.annotation.Transactional;


public class SuperServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements SuperService<T> {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(T model) {
        R<Boolean> result = handlerSave(model);

        return super.save(model);
    }

    /**
     * 处理新增相关处理
     *
     * @param model 实体
     * @return 是否成功
     */
    protected R<Boolean> handlerSave(T model) {
        return R.successDef();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(T model) {
        R<Boolean> result = handlerUpdateById(model);
        return result.getData();
    }


    /**
     * 处理修改相关处理
     *
     * @param model 实体
     * @return 是否成功
     */
    protected R<Boolean> handlerUpdateById(T model) {
        return R.successDef();
    }
}

