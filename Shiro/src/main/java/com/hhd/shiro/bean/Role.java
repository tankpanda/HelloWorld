package com.hhd.shiro.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    private Integer id;
    private String role;
    private String desc;
}
