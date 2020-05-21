package com.yibu.user.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.exception.ExceptionKey;
import com.wang.exception.WErrorException;
import com.yibu.user.mapper.UserMapper;
import com.yibu.user.pojo.User;
import com.yibu.web.dto.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author wzq
 * @date 2020/5/20
 * @DESC
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    /**
     * @Author wzq
     * @Description: 根据电话、密码查找用户
     * @Date 2020/5/21 15:19
     * @Param
     * @return
     **/
    public User queryUserByPhone(String phone, String password) {
        User user = this.getOne(new QueryWrapper<User>().eq("mobile", phone));
        if (ObjectUtil.isEmpty(user)) {
            throw new WErrorException(ExceptionKey.NOT_FOUND);
        }
        if (!StringUtils.equals(password, user.getPassword())) {
            throw new WErrorException(ExceptionKey.WRONG_PASSWORD);
        }
        return user;
    }
}

