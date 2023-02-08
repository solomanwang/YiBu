package com.yibu.user.controller.api;

import com.wang.bean.UserInfo;
import com.wang.util.JwtUtils;
import com.yibu.user.pojo.User;
import com.yibu.user.service.UserService;
import com.yibu.web.dto.HttpResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wzq
 * Date      2020/5/12 0:17
 * Describe:
 */
@RestController
@RequestMapping("/api")
public class UserApiController {

    @Resource
    private UserService userService;

    @GetMapping("mobile/{mobile}")
    public HttpResult<User> queryUserByMobile(@PathVariable("mobile")String mobile){
        return HttpResult.success(userService.queryUserByPhone(mobile));
    }

    @PostMapping("role/uid")
    public HttpResult<List<String>> selectedRoleByUid(@RequestBody User user){
        return HttpResult.success(userService.selectedRoleByUid(user.getId()));
    }
    @GetMapping("userInfo")
    public HttpResult<User> selectUserInfo(HttpServletRequest request){
        UserInfo userInfo = JwtUtils.getUserInfo(request);
        return HttpResult.success(new User());
    }

}
