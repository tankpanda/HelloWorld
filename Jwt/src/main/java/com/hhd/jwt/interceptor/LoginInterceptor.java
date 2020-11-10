package com.hhd.jwt.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录拦截器 验证jwt token
 *
 * @author hengda.hu
 * @date 2020/11/4 15:07
 */
@Component
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

        String token = request.getHeader("token");

        if (token == null || "".equals(token)) {
            response.getWriter().println("please login");
            return false;
        }


        try {
            DecodedJWT decode = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            String username = decode.getClaim("username").asString();
            String password = decode.getClaim("password").asString();
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            return true;
        } catch (SignatureVerificationException e) {
            response.getWriter().println("402 signature error");
            return false;
        } catch (TokenExpiredException e) {
            response.getWriter().println("401 token expired");
            return false;
        }
    }
}
