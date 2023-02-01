package com.yibu.user.api;

import com.yibu.user.pojo.Permission;
import com.yibu.web.dto.HttpResult;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName : PermissionApi
 * @Description :
 * @Author : wzq
 * @Date: 2023-01-31 15:47
 */
public interface PermissionApi {

    @PostMapping("permission/role/pid")
    HttpResult<String> selectedRoleByPermissionId(Permission permission);
}
