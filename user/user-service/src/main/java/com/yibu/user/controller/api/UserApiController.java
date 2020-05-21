package com.yibu.user.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzq
 * Date      2020/5/12 0:17
 * Describe:
 */
@RestController
@RequestMapping("/user/api")
public class UserApiController {

    @Value("${server.port}")
    private int port;

    @GetMapping
    public int get(){
        return port;
    }
}
