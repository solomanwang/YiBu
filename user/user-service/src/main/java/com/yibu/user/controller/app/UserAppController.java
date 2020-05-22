package com.yibu.user.controller.app;

import com.yibu.user.pojo.User;
import com.yibu.user.service.UserService;
import com.yibu.web.dto.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public HttpResult<User> registered(@RequestBody @Valid User user){
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

    @GetMapping("all")
    public HttpResult<List<User>> selectAllUser(){
        List<User> list = userService.list();
        return HttpResult.success(list);
    }
}
