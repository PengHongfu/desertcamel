package com.peng.desertcamel.user.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.peng.desertcamel.common.CodeEnum;
import com.peng.desertcamel.common.ProjectConstants;
import com.peng.desertcamel.common.ResponseData;
import com.peng.desertcamel.user.entity.User;
import com.peng.desertcamel.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by PengHongfu 2018/10/29 17:51
 */
@Slf4j
@Controller
public class HelloController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

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
    @RequestMapping(value = "/unauth")
    public String unauth() {
      return "/unauth";
    }

    @RequestMapping(value = "/login")
    public String testLogin(Model model) {
        log.info("登录页");
        model.addAttribute("title","登录页");
        return "/login";
    }
    @RequestMapping(value = "/regist")
    public String testRegist(Model model) {
        log.info("注册页");
        model.addAttribute("title","注册页");
        return "/regist";
    }

    @ResponseBody
    @RequestMapping("/toregist")
    public String toregist(User user, Model model) {
        log.info("User={}" ,user.toString());
        int i = userService.insertSelective(user);
        return "";
    }
    @ResponseBody
    @RequestMapping("/tologin")
    public ResponseData tologin( User user, Model model) {
        log.info("User={}" ,user.toString());

        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginname(),user.getLoginpass());
            subject.login(token);
            Session session = subject.getSession();
            log.info("isAuthenticated:{}+session={}",subject.isAuthenticated(),session);
            session.setAttribute(ProjectConstants.SESSION_USER_INFO,user);
            redisTemplate.opsForValue().set(user.getLoginname()+"",token,ProjectConstants.REDIS_SHIRO_TOKEN_EXPIRES, TimeUnit.SECONDS);

            //subject.checkRoles("user");
        } catch (UnknownAccountException e) {
            log.error("{}",e);
            return new ResponseData<Object>(CodeEnum.SYSTEM_ERROR.getCode(),"用户名不存在!",e.getMessage());
        } catch (IncorrectCredentialsException e) {
            log.error("{}",e);
            return new ResponseData<Object>(CodeEnum.SYSTEM_ERROR.getCode(),"用户登录失败!",e.getMessage());
        } catch (AuthenticationException e) {
            log.error("{}",e);
            return new ResponseData<Object>(CodeEnum.SYSTEM_ERROR.getCode(),"登录失败!",e.getMessage());
        }
        return new ResponseData<Object>(CodeEnum.SUCCESS.getCode(),"登录成功!");
    }
}
