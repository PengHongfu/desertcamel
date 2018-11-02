package com.peng.desertcamel.user.service.impl;


import com.peng.desertcamel.common.ProjectConstants;
import com.peng.desertcamel.user.dao.UserDao;
import com.peng.desertcamel.user.entity.User;
import com.peng.desertcamel.user.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by Peng
 * Time: 2017/4/17 16:53
 * User业务层
 */
@CacheConfig(cacheNames = "user")
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectByloginName(String username) {
        return userDao.selectByLoginName(username);
    }

    @Transactional
    @Override
    public int insertSelective(User user) throws RuntimeException {
        //补全Uid
        user.setUid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        try{

            // 将用户名作为盐值
            ByteSource salt = ByteSource.Util.bytes(user.getLoginname());
            /*
             * MD5加密：
             * 使用SimpleHash类对原始密码进行加密。
             * 第一个参数代表使用MD5方式加密
             * 第二个参数为原始密码
             * 第三个参数为盐值，即用户名
             * 第四个参数为加密次数
             * 最后用toHex()方法将加密后的密码转成String
             * */
            String newPs = new SimpleHash("MD5", user.getLoginpass(), salt, 1024).toHex();
            user.setLoginpass(newPs);
            return userDao.insertSelective(user);
        } catch(RuntimeException e){
            throw new RuntimeException("注册出错了！"+e.getMessage());
        }
    }
    //使用自定义的keyGenerator
    //@Cacheable(cacheNames = {"user"},keyGenerator = "cacheKeyGenerator")
    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public List<String> selectRolesByLoginName(String loginname) {
        return userDao.selectRolesByLoginName(loginname);
    }

}
