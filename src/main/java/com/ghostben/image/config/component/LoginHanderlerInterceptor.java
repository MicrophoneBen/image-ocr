package com.ghostben.image.config.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Microphoneben
 * @date : 2018/9/18
 * @description : LoginHanderlerInterceptor  对没有登录的用户进行拦截
 */

public class LoginHanderlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");

        if (StringUtils.isEmpty(user)) {
            //未登录，拦截，返回登录页面
            request.setAttribute("msg", "没有权限，请先登录");
            //获取转发器，转回登录页面
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        } else {
            //已登录，放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
