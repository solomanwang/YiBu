package com.yibu.user.api;

import com.yibu.user.pojo.User;
import com.yibu.web.dto.HttpResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
public interface UserApi {
    @PostMapping("user/app/login")
    HttpResult<User> login(@RequestParam("mobile")String mobile, @RequestParam("password")String password);

    @PostMapping("user/app/registered")
    HttpResult<User> registered(@RequestBody @Valid User user);

    @GetMapping("user/api/mobile/{mobile}")
    HttpResult<User> queryUserByMobile(@PathVariable("mobile")String mobile);
}
