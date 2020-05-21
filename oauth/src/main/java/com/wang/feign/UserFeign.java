package com.wang.feign;

import com.wang.constants.Modules;
import com.yibu.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@FeignClient(Modules.MODULE_USER_SERVICE)
public interface UserFeign extends UserApi {

}
