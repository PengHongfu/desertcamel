package com.peng.desertcamel.user.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.peng.desertcamel.user.entity.User;
import com.peng.desertcamel.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by PengHongfu 2018/10/29 17:51
 */
@Slf4j
@Controller
public class HelloController {
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


    @RequestMapping(value = "/login")
    public String testLogin(Model model) {
        log.info("登录操作");
        model.addAttribute("title","登录页");
        return "/login";
    }
    @RequestMapping(value = "/regist")
    public String testRegist(Model model) {
        log.info("注册操作");
        model.addAttribute("title","注册页");
        return "/regist";
    }

    @ResponseBody
    @RequestMapping("user/toregist")
    public String toregist(@Valid User user, Model model) {
        System.out.println("User..." + user.toString());
        log.info("User={}" ,user.toString());
        //int i = userService.insertSelective(user);

        return "";
    }@ResponseBody
    @RequestMapping("user/tologin")
    public String tologini(@Valid User user, Model model) {
        System.out.println("User..." + user.toString());
        log.info("User={}" ,user.toString());


        return "";
    }
}
