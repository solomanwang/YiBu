package com.yibu.goods.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.bean.UserInfo;
import com.yibu.goods.interceptors.LoginInterceptor;
import com.yibu.goods.mapper.GoodsMapper;
import com.yibu.goods.pojo.Goods;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzq
 * @date 2020/5/20
 * @DESC
 */
@Service
public class GoodsService extends ServiceImpl<GoodsMapper,Goods> {

    public List<Goods> queryGoodsByUserId() {
        UserInfo userInfo = LoginInterceptor.getUserInfo();
        System.out.println(userInfo.toString());
        return new ArrayList<>();
    }
}
