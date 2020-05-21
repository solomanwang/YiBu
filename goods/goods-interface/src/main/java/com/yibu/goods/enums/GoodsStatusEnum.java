package com.yibu.goods.enums;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.wang.exception.WErrorException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wzq
 * Date      2020/5/11 23:53
 * Describe:
 */
@Getter
@AllArgsConstructor
public enum GoodsStatusEnum {
    OFF_SHELF(1,"下架"),
    ON_SHELF(2,"上架")
    ;

    /**
    * @EnumValue 表示该字段才是枚举类对应数据库的值
     * 另一种写法 枚举类 implements IEnum<T>
    */
    @EnumValue
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private final int code;
    private String msg;

    private static final Map<Integer,GoodsStatusEnum> lookup = new HashMap<>();

    static {
        for(GoodsStatusEnum e : EnumSet.allOf(GoodsStatusEnum.class)){
            lookup.put(e.getCode(),e);
        }
    }

    public static GoodsStatusEnum findByCode(int code){
        GoodsStatusEnum statusEnum = lookup.get(code);
        if (ObjectUtil.isNull(statusEnum)){
            throw new WErrorException("NOT_FOUND","没找到对应对象");
        }
        return statusEnum;
    }
}
