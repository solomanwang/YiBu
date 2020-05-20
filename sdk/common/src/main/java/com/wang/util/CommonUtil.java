package com.wang.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author wzq
 * @create 2020/4/3
 * @description:  一些通用方法
 */
public final class CommonUtil {

    /**
     * @discription:    对象转 JsonString
     *                  DisableCircularReferenceDetect 消除对同一对象循环引用的问题，默认为false
     *                  WriteMapNullValue               是否输出值为null的字段,默认为false
     * @Param:
     * @Return: java.lang.String
     * @Author: wzq
     * @Date: 2020/4/3 16:51
     */
    public static String toJsonString(Object o){
        return JSON.toJSONString(o,SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    public static byte[] toJsonByte(Object o){
        return JSON.toJSONBytes(o,SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    /**
     * @discription: json 字符串转object对象
     * @Param:      json    json文本
     * @Param:      tClass  解析的类
     * @Return:
     * @Author: wzq
     * @Date: 2020/4/9 16:39
     */
    public static <T> T parseObject(String json,Class<T> tClass){
        return JSON.parseObject(json,tClass);
    }
}
