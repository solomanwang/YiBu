package com.wang.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wzq
 * @date 2020/5/26
 * @DESC 返回的安全信息
 */
@Data
public class ProfileResult implements Serializable{

    private String mobile;
    private String username;
    private Map<String,Object> roles = new HashMap<>();

}
