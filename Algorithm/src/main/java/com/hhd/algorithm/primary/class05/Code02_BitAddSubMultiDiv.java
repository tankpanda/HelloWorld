package com.hhd.algorithm.primary.class05;


/**
 * 位运算 + - * /
 * https://leetcode-cn.com/problems/divide-two-integers/
 * Created by huhengda on 2021/1/28.
 */
public class Code02_BitAddSubMultiDiv {
    /**
     * (i ^ j) = 无进位i+j
     * (i & j) << 1 = i+j进位信息
     * result =
     *
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        int sum = a;
        // 当进位信息为0 说明完成运行
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /**
     * (-b) = (~b + 1)
     * @param a
     * @param b
     * @return
     */
    public int sub(int a, int b) {
        return add(a, add(~b, 1));
    }

    /**
     * 分别计算 a*(b的每一位)
     * (a <<= 1) = (a = a << 1)
     * (b >>>= 1) = (b = b >>> 1)
     * (a <<= 1)和(b >>>= 1) 两步 类似于十进制 a * (b / 10 - b % 10)
     *
     * @param a
     * @param b
     * @return
     */
    public int multi(int a, int b) {
        int mul = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                mul = add(mul, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return mul;
    }

    // ---- 第一种方式start ----
    /**
     * 乘法逆向 将a b转为正数(这样方便比较大小)
     * 获取x右移n+1位比y小(x=y*(2^n)+余数) (x右移与y左移效果相同 但当x=01xxxx时 y左移到n+1位会造成y=1xxxxx 第一位为符号位 导致结果错误)
     * 结果在第n位上位1 x=余数 重复执行
     * 最后比较a b是否同号 不同号加负号
     * 计算过程同小学数字除法过程 只是所有计算过程使用位移操作
     * @param a
     * @param b
     * @return
     */
    public int div(int a, int b) {
        int x = a < 0 ? add(~a, 1)  : a;
        int y = b < 0 ? add(~b, 1)  : b;
        int div = 0;
        while (x != 0 && x >= y) {
            int n = getN(x, y);
            x = sub(x, y << n);
            div |= 1 << n;
        }
        return (a ^ b) < 0 ? add(~div, 1) : div;
    }

    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        }
        if (b == Integer.MIN_VALUE) {
            return 0;
        }
        if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                int ans = div(add(a, 1), b);
                return add(ans, div(sub(a, multi(ans, b)), b));
            }
        }
        return div(a, b);
    }

    private int getN(int a, int b) {
        int n = 0;
        while (a != 0) {
            if ((a >> n) < b) {
                return n - 1;
            }
            n++;
        }
        return n;
    }
    // ---- 第一种方式end ----


    // ---- 第二种方式start 左神思路 ----
    public boolean isNeg(int n) {
        return n < 0;
    }

    public int negNum(int n) {
        return add(~n, 1);
    }

    public int div1(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = sub(i, 1)) {
            if ((x >> 1) >= y) {
                res |= (1 << i);
                x = sub(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public int divide1(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
            return 1;
        }
        if (divisor == Integer.MIN_VALUE) {
            return 0;
        }
        // dividend == Integer.MIN_VALUE 无法转成正数
        // 取dividend( = dividend + 1) / divisor得到ans1
        // 计算(余数 - 1) / divisor得到ans2 即计算(余数-1) 是否需要补偿
        // ans = ans1 + ans2;
        if (dividend == Integer.MIN_VALUE) {
            if (dividend == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                int ans = div1(add(dividend, 1), dividend);
                return add(ans, div1(sub(dividend, multi(ans, divisor)), divisor));
            }
        }
        return div1(dividend, divisor);
    }
    // ---- 第一种方式end ----
}
