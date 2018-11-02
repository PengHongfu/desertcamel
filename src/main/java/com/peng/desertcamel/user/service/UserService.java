package com.peng.desertcamel.user.service;


import com.peng.desertcamel.user.entity.User;

import java.util.List;

/**
 * Created by Peng
 * Time: 2017/4/17 16:51
 */

public interface UserService {

     User selectByloginName(String loginname);

     int insertSelective(User user) throws RuntimeException;

     List<User> selectAll();

     List<String> selectRolesByLoginName(String loginname);


}
