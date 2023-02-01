package com.yibu.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT\n" +
            "\tt1.name \n" +
            "FROM\n" +
            "\t`role`  t1\n" +
            "JOIN user_role t3 \n" +
            "on t3.rid  = t1.id \n" +
            "WHERE t3.uid = #{uid}")
    List<String> selectedRoleByUid(Long uid);
}
