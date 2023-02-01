package com.yibu.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yibu.user.pojo.Permission;
import com.yibu.user.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("SELECT t1.name FROM role_permission t2 join `role` t1 on t2.rid  = t1.id where t2.pid = #{pid}")
    List<String> selectRoleByPermissionId(int pid);
}
