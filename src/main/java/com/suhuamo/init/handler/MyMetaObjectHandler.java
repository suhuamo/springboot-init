package com.suhuamo.init.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.suhuamo.init.constant.MysqlConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author suhuamo
 * @date 2023-12-31
 * mybatis插入数据库的数据处理handler
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     *  插入时的填充策略
     * @param metaObject
     * @return void
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        this.setFieldValByName(MysqlConstant.CREATE_TIME_FILED, LocalDateTime.now(), metaObject);
        // 更新时间
        this.setFieldValByName(MysqlConstant.UPDATE_TIME_FILED, LocalDateTime.now(), metaObject);
    }

    /**
     *  更新时的时的填充策略
     * @param metaObject
     * @return void
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        this.setFieldValByName(MysqlConstant.UPDATE_TIME_FILED, LocalDateTime.now(), metaObject);
    }
}