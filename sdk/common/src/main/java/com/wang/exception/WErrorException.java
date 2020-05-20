package com.wang.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wzq
 * @create 2020/4/7
 * @description: 自定义错误异常
 */
@Getter
@Setter
@AllArgsConstructor
public class WErrorException extends RuntimeException{

    private String key;

    private String msg;


}
