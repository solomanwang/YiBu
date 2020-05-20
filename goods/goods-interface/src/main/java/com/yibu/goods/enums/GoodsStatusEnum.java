package com.yibu.goods.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wzq
 * Date      2020/5/11 23:53
 * Describe:
 */
@Getter
@AllArgsConstructor
public enum GoodsStatusEnum {
    OFF_SHELF(1,"下架"),
    PUT_ON_SHELVES(2,"上架")
    ;

    private int code;
    private String msg;

}
