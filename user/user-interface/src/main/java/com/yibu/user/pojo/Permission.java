package com.yibu.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yibu.entity.BaseEntity;
import lombok.Data;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@Data
@TableName
public class Permission extends BaseEntity {
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限类型 1为菜单 2为功能 3为API
     */
    private Integer type;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限描述
     */
    private String description;

    private String pid;

    //可见状态
    private String enVisible;
}
