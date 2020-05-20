package com.yibu.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wzq
 * Date      2020/5/20 23:30
 * Describe: mybatis-plus 自动填充数据处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
    * 插入的填充策略
    */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    /**
     * 更行的填充策略
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
