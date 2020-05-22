package com.yibu.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yibu.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@Data
@TableName
public class User extends BaseEntity {
    /**
     * 会员等级id
     */
    private Long levelId;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 盐
     */
    private String salt;
    /**
     * 头像
     */
    private String header;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 生日
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
}
