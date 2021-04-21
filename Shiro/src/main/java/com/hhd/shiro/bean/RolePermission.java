package com.hhd.shiro.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePermission implements Serializable {
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
}
