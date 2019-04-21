package com.ghostben.image.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * @program: wechat
 * @description: 配置Mybatis使用C3P0的数据库连接池对象，为数据库连接提供配置
 * @author: ghostben
 * @create: 2018-11-25 16:23
 **/
@Configuration
//配置mybatis mapper的扫描路径
@MapperScan("com.ghostben.image.dao")
public class DataSourceConfiguration {

    //使用@Values 装配Properties中的配置信息
    @Value("${jdbc.driver-class-name}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    /**
    *@author    : Microphoneben
    *@date      : 2018/11/25
    *@Parameter :  在IOC容器中配置C3P0数据库连接池，提供数据库多线程操作
    *@eturn    :  返回一个数据库连接池对象，使用spring IOC DI注入
    *@description : DataSourceConfiguration  这里的Bean注解可以直接指定这个Bean在IOC容器中注入的实体名称
    */
    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUsername);
        dataSource.setPassword(jdbcPassword);

        //关闭连接后不自动提交到数据库
        dataSource.setAutoCommitOnClose(false);
        return dataSource;
    }

}