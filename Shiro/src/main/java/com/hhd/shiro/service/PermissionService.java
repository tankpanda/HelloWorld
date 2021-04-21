package com.hhd.shiro.service;

import com.hhd.shiro.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    public List<String> findByRoleId(List<Integer> roleIds) {
        return permissionMapper.findByRoleIds(roleIds);
    }
}
