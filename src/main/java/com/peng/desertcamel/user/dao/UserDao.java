package com.peng.desertcamel.user.dao;

/**
 * Dao层用户数据库交互层
 */
import com.peng.desertcamel.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserDao {

    int deleteByPrimaryKey(String uid);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();

    User selectByNameAndPass(User user);

    /*注解方式*/
    @Select("select * from t_user")
    List<User> selectAll1();
}