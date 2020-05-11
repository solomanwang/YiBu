package com.yibu.goods.pojo;

import com.yibu.goods.enums.GoodsStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wzq
 * Date      2020/5/11 23:50
 * Describe:
 */
@Data
public class Goods {
    private Long id;
    private String name;
    private BigDecimal price;
    private GoodsStatusEnum statusEnum;
}
