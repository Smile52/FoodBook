package com.smile.food.filter;

import com.smile.food.annotation.PassToken;
import com.smile.food.annotation.RoleAnnotation;
import com.smile.food.annotation.UserLoginToken;
import com.smile.food.exception.FoodException;
import com.smile.food.model.User;
import com.smile.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (token!=null){
            System.out.println("token "+token);
        }
        if(!(handler instanceof HandlerMethod)){
            return true;
        }


        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();

        if (method.isAnnotationPresent(RoleAnnotation.class)){
            RoleAnnotation roleAnnotation=method.getAnnotation(RoleAnnotation.class);
            if (token==null){
                throw new RuntimeException("无Token,请重新登录");
            }else {
                User u = userService.findUserByToken(token);
                if (u==null){
                    throw new RuntimeException("Token错误");
                }else {
                    if (u.getLeave()<=roleAnnotation.leave()){
                        return true;
                    }else {
                        throw new RuntimeException("权限不够");

                    }
                }
            }
        }

        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);

            if (userLoginToken.required()){
                    if (token==null){
                        throw new RuntimeException("无Token,请重新登录");

                    }else {
                       User u = userService.findUserByToken(token);
                       String cToken=userService.findTokenByUserId(u.getUser_id());
                       if (cToken.equals(token)){
                           return true;
                       }else {
                           throw new RuntimeException("401");

                       }
                    }
                }else {
                throw new RuntimeException("401");

            }
        }

            return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
