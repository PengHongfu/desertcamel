package com.peng.desertcamel.common;

/**
 * Created by PengHongfu 2018/11/2 11:21
 */
public class ProjectConstants {
    //盐值
    public  static final String SHIRO_SALT="sjdfklsjflj***";

    //shiro session key
    public static final String SESSION_USER_INFO = "userInfo";

    //TOKEN 与shiro sessionid 映射关系过期时间
    public static final Integer REDIS_SHIRO_TOKEN_EXPIRES = 60*60;
}
