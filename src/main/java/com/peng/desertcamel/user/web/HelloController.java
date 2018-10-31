package com.peng.desertcamel.user.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.peng.desertcamel.user.entity.User;
import com.peng.desertcamel.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by PengHongfu 2018/10/29 17:51
 */
@Controller
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/index")
    public String testt() {
        int pageNum=1;
        int pageSize=3;
        //Page<User> page = PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.selectAll();
//        System.out.println("总共条数："+page.getTotal());
//        for (User user : page.getResult()) {
//            System.out.println(user.toString());
//        }
        return JSON.toJSONString(userList);
    }


    @RequestMapping(value = "/greeting")
    public String test(Model model) {
        logger.trace("这是一个trace日志");
        logger.debug("这是一个debug日志");
        logger.info("这是一个info日志");
        logger.warn("这是一个warn日志");
        logger.error("这是一个error日志");

        model.addAttribute("title","欢迎使用Thymeleaf!");
        return "/index";
    }
}
