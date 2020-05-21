package com.yibu.goods.controller;

import com.yibu.goods.enums.GoodsStatusEnum;
import com.yibu.goods.pojo.Goods;
import com.yibu.goods.service.GoodsService;
import com.yibu.web.dto.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public HttpResult<Goods> getGoodsById(long id){
        return HttpResult.success(goodsService.getById(id));
    }

    @PostMapping
    public HttpResult<Goods> saveGoods(@RequestBody Goods goods){
//        goods.setStatusEnum(GoodsStatusEnum.OFF_SHELF);
        boolean save = goodsService.save(goods);
        if (save){
            return HttpResult.success(goods);
        }
        return HttpResult.error("保存失败");
    }
    @DeleteMapping
    public HttpResult deleteGoodsById(Long id){
        goodsService.removeById(id);
        return HttpResult.success();
    }

    @PutMapping
    public HttpResult<Goods> updateGoods(@RequestBody Goods goods){
        boolean save = goodsService.updateById(goods);
        if (save){
            return HttpResult.success(goods);
        }
        return HttpResult.error("保存失败");
    }
}
