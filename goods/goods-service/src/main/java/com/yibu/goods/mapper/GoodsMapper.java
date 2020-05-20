package com.yibu.goods.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yibu.goods.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author wzq
 * @date 2020/5/20
 * @DESC
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}
