package com.yibu.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yibu.entity.BaseEntity;
import lombok.Data;

/**
 * @author wzq
 * @date 2020/5/
 * @DESC 角色
 */
@Data
@TableName
public class Role extends BaseEntity {

    /**
     * 角色名
     */
    private String name;
    /**
     * 说明
     */
    @TableField("desc")
    private String description;
}
