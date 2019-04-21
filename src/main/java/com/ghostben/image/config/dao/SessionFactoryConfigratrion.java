package com.ghostben.image.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @program: wechat
 * @description: 配置mybatis 的数据库sessionFactory,支持事务
 * @author: ghostben
 * @create: 2018-11-25 17:08
 **/
//这个注解只会主动读取注入属性map collection arrays
//@ConfigurationProperties(prefix = "sqlSession")
@Configuration
public class SessionFactoryConfigratrion {
    @Value("${sqlSession.mybatisConfigFilePath}")
    private String mybatisConfigFilePath;
    @Value("${sqlSession.mapperPath}")
    private String mapperPath;
    @Value("${sqlSession.entityPackage}")
    private String entityPackage;

    @Autowired
    private DataSource dataSource;
    //同样需要在IOC DI中依赖注入一个SQLSessionFactoryBean，与配置的数据库连接池绑定，同时提供事务操作

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //mybatis 提供一个数据库对话工程，mybatis的使用主要要配置两个文件，一个是全局的mybatis-config，一个是mapper
        //配置mybatis全局设置文件
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));
        //设置实体类与mybatis中Mapper的对应文件扫描路径
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        //mapperPath 就是我们的Mapper文件的扫描路径  CLASSPATH_ALL_URL_PREFIX这个其实就是ClassPath.*  就是类路径下
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(patternResolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);

        //设置实体类的扫描路径
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        return sqlSessionFactoryBean;
    }
}