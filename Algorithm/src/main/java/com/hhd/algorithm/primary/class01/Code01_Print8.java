package com.hhd.algorithm.primary.class01;

/**
 * 打印一个整数的2进制32位
 * 0~30位表示数值 31位做符号位 0表示非负 1表示负
 * 正数 2^n + ... + 1
 * 负数 除符号位 其余所有位数取反 + 1
 * Created by huhengda on 2021/1/21.
 */
public class Code01_Print8 {
    public static void main(String[] args) {
        int num = 112312312;
        print(num);

        int a = Integer.MIN_VALUE;
        // 带符号位右移
        print(a >> 1);
        // 不带符号位右移
        print(a >>> 1);

        // 相反数
        int b = 5;
        int c = ~b + 1;
        System.out.println(c);

        // 最小数的相反数 是本身
        int d = ~a + 1;
        System.out.println(d);

    }

    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            // 取num每一位的数 & 1的结果
            System.out.print((num & (1 << i)) == 0 ? 0 : 1);
        }
        System.out.println();
    }
}
