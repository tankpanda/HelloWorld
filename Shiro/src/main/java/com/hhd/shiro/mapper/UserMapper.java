package com.hhd.shiro.mapper;

import com.hhd.shiro.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
//@Repository
public interface UserMapper {
    User findByAccount(@Param("account") String account);
}
