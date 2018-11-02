package com.peng.desertcamel.shiro.realm;

import com.peng.desertcamel.common.ProjectConstants;
import com.peng.desertcamel.user.dao.UserDao;
import com.peng.desertcamel.user.entity.User;
import com.peng.desertcamel.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by PengHongfu 2018/11/1 18:01
 */
@Slf4j
@Component
public class MyUserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       log.info("-----------------------身份认证方法-----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        User user = userService.selectByloginName(token.getUsername());
        if (null == user) {
            //没找到帐号
            throw new UnknownAccountException();
        }/* else if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }*/
        //当前realm对象的name
        String realmName = getName();
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getLoginname());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                token.getPrincipal(), user.getLoginpass(),credentialsSalt,realmName);
        return authenticationInfo;
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("-----------------------权限认证-----------------------");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        List<String> role = userService.selectRolesByLoginName(username);

        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        for(String roles:role){
            set.add(roles);
        }
        //设置该用户拥有的角色
        info.setRoles(set);

       /* //获取用户权限
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("add");
        permissionSet.add("user");
        info.setStringPermissions(permissionSet);*/

        return info;
    }
}
