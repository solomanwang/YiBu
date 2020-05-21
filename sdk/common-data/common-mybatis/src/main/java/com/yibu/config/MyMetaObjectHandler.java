package com.yibu.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wzq
 * Date      2020/5/20 23:30
 * Describe: mybatis-plus 自动填充数据处理器
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
    * 插入的填充策略
    */
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        //  自动填充数据，metaObject , 字段名 ， 字段值类型， 插入值
        this.strictInsertFill(metaObject,"createTime",LocalDateTime.class,now);
        this.strictInsertFill(metaObject,"updateTime",LocalDateTime.class,now);
        this.strictInsertFill(metaObject,"deleted",Integer.class,0);
    }

    /**
     * 更行的填充策略
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
    }
}
