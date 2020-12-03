package com.hhd.validation.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/12/3 10:16
 */
public class User {

    private Integer id;

    @NotEmpty(message = "username is empty", groups = {Save.class, Update.class})
    @NotBlank(message = "username is blank")
    @Length(min = 2, max = 10, groups = {Save.class, Update.class})
    private String username;
    @Email(message = "not a email")
    @NotNull(message = "email is null")
    private String email;
    @Max(value = 100L, message = "age too large")
    @Min(value = 0L, message = "age too small")
    private Integer age;

    /**
     * 对象和list 需要加上@Valid
     */
    @NotNull
    @Valid
    private Address address;
    /**
     * 对象和list 需要加上@Valid
     */
    @Valid
    private List<Address> addresses;

    public interface Update{}
    public interface Save{}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
