package com.hhd.idiom.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by huhengda on 2021/3/2.
 */
@Data
public class BaiduIdiom {
    private List<BaiduIdiomResult> data;
    private Integer status;
}
