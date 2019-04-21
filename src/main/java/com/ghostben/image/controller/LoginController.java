package com.ghostben.image.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
*@author    : Microphoneben
*@date      : 2018/9/18
*@description : LoginController 用来控制页面的登录与错误显示
*
*/
@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登陆成功
            //return "login";
            //防止表单重复提交
            session.setAttribute("loginUser", username);
            return "redirect:/index.html";
        }else{
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
