package com.peng.desertcamel;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.peng.desertcamel.*.dao")
@EnableTransactionManagement//事务
@EnableCaching
public class DesertcamelApplication {

private static final Logger logger = LoggerFactory.getLogger(DesertcamelApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DesertcamelApplication.class, args);
        logger.info("DesertcamelApplication 启动成功!");
    }
}
