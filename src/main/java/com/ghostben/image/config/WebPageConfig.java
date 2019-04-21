package com.ghostben.image.config;

import com.ghostben.image.config.component.LoginHanderlerInterceptor;
import com.ghostben.image.config.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
*@author    : Microphoneben
*@date      : 2018/9/17
*@description : WebPageConfig
*
*/
//想要让我们的这个配置启动器起作用，我们一定要添加configuration注解

@Configuration
public class WebPageConfig extends WebMvcConfigurerAdapter {

    /**
     * {@inheritDoc}
     * <p>This implementation is empty.
     *
     * @param registry
     */

    //  super.addViewControllers(registry);
    //  用来定向加载，以及转发我们的WebPage

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }


    /**
    *@author    : Microphoneben
    *@date      : 2018/9/17
    *@description :   webMvcConfigurerAdapter方法 里面使用新建一个内部类，然后重写页面加载方法，作为一个
     *               WebMvcConfigurerAdapater返回，然后添加到我们的springboot容器中，与原来的springboot组件一起起作用
     *
     *   要让这个组件起作用我们还必须添加Bean注解或者Component注解
    */

    //@Component 这个注解不能添加在方法上


    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter myAdapter = new WebMvcConfigurerAdapter() {
             //   super.addViewControllers(registry);

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //配置国际化页面后的解析器
                registry.addViewController("/login.html").setViewName("login");

                //通用图片识别
                registry.addViewController("/generalBasic.html").setViewName("generalBasic.html");

                //身份证识别结果页面
                registry.addViewController("idCard.html").setViewName("idCard.html");

                //车牌号识别结果页面
                registry.addViewController("multiCarPic.html").setViewName("multiCarPic.html");

                //银行卡识别页面
                registry.addViewController("bankCard.html").setViewName("bankCard.html");

                //配置通用识别图片上载页面
                registry.addViewController("upgeneral.html").setViewName("upgeneral");

                //配置车辆图片上载页面
                registry.addViewController("CarCard.html").setViewName("CarCard.html");

                //车牌号上传页面
                registry.addViewController("upimage.html").setViewName("upimage");

                //花草识别上传页面
                registry.addViewController("flowers.html").setViewName("flowers");

                //银行卡识别上传页面
                registry.addViewController("upbankcard.html").setViewName("upbankcard.html");

                //身份证识别上传页面
                registry.addViewController("upIdCard.html").setViewName("upIdCard.html");

            }
        };
        return myAdapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    //注册拦截器，让他起作用
    //super.addInterceptors(registry);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //  这里的/** 表示拦截任意多层路径下的任意请求
        registry.addInterceptor(new LoginHanderlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login.html","/","index.html","/user/login","login","index",
                        "generalBasic.html","uploadPic","upgeneral.html","zyupload","image/upload");
    }
}
