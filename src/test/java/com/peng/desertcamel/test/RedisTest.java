package com.peng.desertcamel.test;

import com.peng.desertcamel.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by PengHongfu 2018/10/30 21:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void setKey()  {
        User user = new User("小明","23");

        redisTemplate.opsForValue().set("value1","tom",1000, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("value2","hello1");
        redisTemplate.opsForValue().set("value3",user);

        Map<String,String> maps = new HashMap<>();
        maps.put("multi1","multi1");
        maps.put("multi2","multi2");
        maps.put("multi3","multi3");
        redisTemplate.opsForValue().multiSet(maps);//为多个键分别设置它们的值



        redisTemplate.opsForHash().put("hash1","hashKey11","hashValue11");
        redisTemplate.opsForHash().putAll("hash2",maps);


        redisTemplate.opsForList().leftPushAll("list1","C#","C++","JAVA","Python","PHP");
        List<Object> strings = new ArrayList<Object>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        redisTemplate.opsForList().leftPushAll("list2", strings);

        String[] strarrays = new String[]{"strarr1","sgtarr2"};
        redisTemplate.opsForSet().add("set1",23,2,34,454,5656,565,56);
        redisTemplate.opsForSet().add("set2",strarrays);

        redisTemplate.opsForZSet().add("zSet1","tom",80);
        //redisTemplate.expire("userKey1",1000, TimeUnit.SECONDS);

    }


    @Test
    public void getKey()  {
        List<String> keys = new ArrayList<>();
        keys.add("multi1");
        keys.add("multi2");
        keys.add("multi3");
        System.out.println(redisTemplate.opsForValue().multiGet(keys));

        User user = (User) redisTemplate.opsForValue().get("value3");
        System.out.println(user.toString());
        //System.out.println("get key value:"+ redisService.getValue("key"));
    }
}