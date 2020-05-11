package com.yibu.goods.enums;

import lombok.AllArgsConstructor;

/**
 * @author wzq
 * Date      2020/5/11 23:53
 * Describe:
 */
@AllArgsConstructor
public enum GoodsStatusEnum {
    PUT_ON_SHELVES(2,"上架")
    ;

    private int code;
    private String msg;

}
