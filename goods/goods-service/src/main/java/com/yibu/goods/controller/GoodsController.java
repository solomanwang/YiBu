package com.yibu.goods.controller;

import com.yibu.goods.enums.GoodsStatusEnum;
import com.yibu.goods.pojo.Goods;
import com.yibu.goods.service.GoodsService;
import com.yibu.web.dto.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author wzq
 * Date      2020/5/11 23:56
 * Describe:
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping
    public String get(){
        return "成功";
    }

    @GetMapping("save")
    public HttpResult<Goods> saveGoods(){
        Goods goods = new Goods();
        goods.setName("测试机");
        goods.setPrice(new BigDecimal(12.5));
        goods.setStatusEnum(GoodsStatusEnum.OFF_SHELF);
        boolean save = goodsService.save(goods);
        if (save){
            return HttpResult.success(goods);
        }
        return HttpResult.error("保存失败");
    }
}
