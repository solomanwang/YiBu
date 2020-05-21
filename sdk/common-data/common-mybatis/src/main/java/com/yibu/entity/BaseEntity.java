package com.yibu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wzq
 * @date 2020/5/20
 * @DESC
 */
@Data
public class BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    protected Long id;

    /**
    * @TableField(fill = FieldFill.INSERT) 自动填充数据
    */
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;
    /**
     *  @TableLogic 该注解表示逻辑删除
     * */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
