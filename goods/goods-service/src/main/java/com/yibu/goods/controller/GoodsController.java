package com.yibu.goods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzq
 * Date      2020/5/11 23:56
 * Describe:
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @GetMapping
    public String get(){
        return "成功";
    }
}
