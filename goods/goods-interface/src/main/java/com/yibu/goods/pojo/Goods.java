package com.yibu.goods.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yibu.entity.BaseEntity;
import com.yibu.goods.enums.GoodsStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wzq
 * Date      2020/5/11 23:50
 * Describe:
 */

@Data
@TableName("goods")
public class Goods extends BaseEntity {
    private String name;
    private BigDecimal price;
    @TableField("status")
    private GoodsStatusEnum statusEnum;
}
