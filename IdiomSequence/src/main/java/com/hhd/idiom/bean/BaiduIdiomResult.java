package com.hhd.idiom.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by huhengda on 2021/3/2.
 */
@Data
public class BaiduIdiomResult {
    private String display_title;
    private String listNum;
    private List<BaiduIdiomResultResult> result;
    private Integer status;
}
