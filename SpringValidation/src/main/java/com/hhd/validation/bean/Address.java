package com.hhd.validation.bean;

import javax.validation.constraints.NotBlank;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/12/3 11:30
 */
public class Address {
    private Integer id;
    @NotBlank(message = "province is blank")
    private String province;
    @NotBlank(message = "city is blank")
    private String city;
    @NotBlank(message = "area is blank")
    private String area;
    @NotBlank(message = "address detail is blank")
    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
