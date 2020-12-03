package com.hhd.validation.controller;

import com.hhd.validation.bean.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/12/3 10:14
 */
@RestController
@Validated
public class UserController {

    @PostMapping("test")
    public String test(@RequestBody @Validated User user) {
        return "success";
    }

    @GetMapping("test1/{id}")
    public String test1(@PathVariable("id") @Min(1L) Integer id) {
        return String.valueOf(id);
    }
}
