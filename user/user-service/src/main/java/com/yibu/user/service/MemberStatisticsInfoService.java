package com.yibu.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yibu.entity.PageUtils;
import com.yibu.user.pojo.MemberStatisticsInfoEntity;

import java.util.Map;

/**
 * 会员统计信息
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:47:05
 */
public interface MemberStatisticsInfoService extends IService<MemberStatisticsInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

