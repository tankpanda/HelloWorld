package com.hhd.websocket.controller;

import com.hhd.websocket.bean.User;
import com.hhd.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/11/9 16:39
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private HttpServletRequest request;

    @GetMapping("login")
    public ModelAndView login(String username, String password) {
        User user = userService.login(username, password);
        if (user != null) {
            request.setAttribute("userId", user.getId());
            return new ModelAndView("index");
        }
        return new ModelAndView("error");
    }

}
