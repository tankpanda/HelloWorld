package com.hhd.algorithm.basics.class01;

/**
 * 取n最右侧的1
 * Created by huhengda on 2021/2/7.
 */
public class Code04_IntRight1 {
    public static void main(String[] args) {
        int n = 1231230;
        System.out.println(n & (~n + 1));
    }
}
