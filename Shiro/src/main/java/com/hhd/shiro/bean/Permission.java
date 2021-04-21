package com.hhd.shiro.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {
    private Integer id;
    private String permission;
    private String desc;
}
