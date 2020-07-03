package com.yibu.user.controller.api;

import com.yibu.user.pojo.User;
import com.yibu.user.service.UserService;
import com.yibu.web.dto.HttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wzq
 * Date      2020/5/12 0:17
 * Describe:
 */
@RestController
@RequestMapping("/user/api")
public class UserApiController {

    @Resource
    private UserService userService;

    @GetMapping("mobile/{mobile}")
    public HttpResult<User> queryUserByMobile(@PathVariable("mobile")String mobile){
        return HttpResult.success(userService.queryUserByPhone(mobile));
    }
}
