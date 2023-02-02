package com.yibu.admin.contorller;

import com.yibu.web.dto.HttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : AdminUserContorller
 * @Description :
 * @Author : wzq
 * @Date: 2023-02-02 16:11
 */
@RestController
public class AdminUserController {

    @GetMapping("userinfo")
    public HttpResult<String> test(@RequestParam("username")String username){
        return HttpResult.success(username);
    }
}
