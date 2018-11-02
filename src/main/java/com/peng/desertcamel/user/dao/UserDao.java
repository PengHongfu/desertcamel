package com.peng.desertcamel.user.dao;

/**
 * Dao层用户数据库交互层
 */
import com.peng.desertcamel.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserDao {

    @Insert("INSERT INTO t_user(uid, loginname, loginpass) VALUES (#{uid}, #{loginname}, #{loginpass})")
    int insertSelective(User user);

    List<User> selectAll();

    @Select("SELECT * FROM t_user WHERE loginname = #{loginname}")
    User selectByLoginName(String loginname);

    @Select("SELECT relename FROM t_user_roles WHERE loginname = #{loginname}")
    List<String> selectRolesByLoginName(String loginname);

}