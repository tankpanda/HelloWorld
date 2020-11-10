package com.hhd.jwt.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hhd.jwt.interceptor.LoginInterceptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hengda.hu
 * @date 2020/11/4 12:07
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    public String login(String username, String password) {

        // 数据库校验username&password
        // User user = userService.login();
        if (!"hhd".equals(username) || !"hhd".equals(password)) {
            return "failed";
        }

        Instant instant = LocalDateTime.now().plusDays(30L).atZone(ZoneId.systemDefault()).toInstant();
        Map<String, Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        String token = JWT.create()
                .withHeader(header)
                .withClaim("username", username)
                .withClaim("password", password)
                //.withClaim("userId", user.getId())
                .withExpiresAt(Date.from(instant))
                .sign(Algorithm.HMAC256(LoginInterceptor.SECRET));
        return token;
    }

    @PostMapping("test")
    public String test(@RequestAttribute("username") String username, @RequestAttribute("password") String password) {
        return "hello" + username;
    }

}
