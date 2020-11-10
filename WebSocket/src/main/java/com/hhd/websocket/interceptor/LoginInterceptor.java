package com.hhd.websocket.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/11/9 16:11
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static List<String> notLoginUri;
    public static String SECRET = "hhd1";

    static {
        notLoginUri = new ArrayList<>();
        notLoginUri.add("/user/login");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath();
        if (notLoginUri.contains(servletPath)) {
            return true;
        }

       return false;
    }
}
