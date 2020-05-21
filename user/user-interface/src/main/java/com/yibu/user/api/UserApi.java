package com.yibu.user.api;

import com.yibu.user.pojo.User;
import com.yibu.web.dto.HttpResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
public interface UserApi {
    @PostMapping("user/app/login")
    HttpResult<User> login(@RequestParam("mobile")String mobile, @RequestParam("password")String password);
}
