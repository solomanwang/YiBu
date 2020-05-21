package com.yibu.user.controller.app;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.exception.ExceptionKey;
import com.wang.exception.WErrorException;
import com.yibu.user.pojo.User;
import com.yibu.user.service.UserService;
import com.yibu.web.dto.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@RequestMapping("user/app/")
@RestController
public class UserAppController {

    @Autowired
    private UserService userService;

    @PostMapping("registered")
    public HttpResult<User> registered(@RequestBody User user){
        boolean save = userService.save(user);
        if (save){
            return HttpResult.success(user);
        }
        return HttpResult.error("失败");
    }

    @GetMapping
    public HttpResult<User> getById(Long id){
        return HttpResult.success(userService.getById(id));
    }

    @PostMapping("login")
    public HttpResult<User> login(@RequestParam("mobile")String mobile,@RequestParam("password")String password){
        User user = userService.queryUserByPhone( mobile,password);
        return HttpResult.success(user);
    }
}
