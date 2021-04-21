package com.hhd.shiro.service;

import com.hhd.shiro.bean.User;
import com.hhd.shiro.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }
}
