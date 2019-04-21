package com.ghostben.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author ben.zhang.b.q
 * @Time 2018/10/16 18:00
 * @Param
 * @return  程序启动入口
 **/
@SpringBootApplication
(exclude = {DataSourceAutoConfiguration.class})
public class ImageApp {

    public static void main(String[] args) {
        SpringApplication.run(ImageApp.class, args);
    }
}
