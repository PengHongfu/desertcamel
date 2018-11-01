package com.peng.desertcamel.user.service.impl;


import com.peng.desertcamel.user.dao.UserDao;
import com.peng.desertcamel.user.entity.User;
import com.peng.desertcamel.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
    public User selectByPrimaryKey(String uid) {
        return userDao.selectByPrimaryKey(uid);
    }

    @Override
    public int insertSelective(User user) throws RuntimeException {
        //补全Uid
        user.setUid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        try{
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
    public User selectByNameAndPass(User user) {
        return userDao.selectByNameAndPass(user);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userDao.updateByPrimaryKeySelective(record);
    }
}
