package com.peng.desertcamel.database.mybatis;


import com.alibaba.druid.pool.DruidDataSource;
import com.peng.desertcamel.database.druid.DruidBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by PengHongfu 2018/10/30 11:31
 */
@Configuration
public class MybatisConfig {

    @Autowired
    DruidBean druidProperties;

    /**
     * guns的数据源
     */
    private DruidDataSource dataSourceStrom() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 单数据源连接池配置
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        return dataSourceStrom();
    }


}
