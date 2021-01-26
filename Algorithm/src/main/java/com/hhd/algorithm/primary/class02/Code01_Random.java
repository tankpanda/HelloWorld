package com.hhd.algorithm.primary.class02;

/**
 * Math.random返回[0,1)上的double值 且概率相同
 * <p>
 * f系列从1~5等概率 获取从1~7等概率
 * f 从1~5等概率
 * f1 1~5获取0 1等概率 中间点重roll 大于中间1 小于中间0
 * f2 1~7 7个数 需要3个二进制位 f2获取3个二进制位 0~7
 * f3 获取0~6 等概率 思路同f1
 * f4 f3+1 获取1~7等概率
 * <p>
 * g系列从0 1不等 获取0 1等概率
 * g 获取0 1不等概率
 * g1 获取01等概率 roll两次 结果00概率P*P 01概率P*(1-P) 10概率(1-P)*P 11概率(1-P)*(1-P) 00和11重roll 得到01 10等概率 返回第一位
 * <p>
 * Created by huhengda on 2021/1/22.
 */
public class Code01_Random {
    public static void main(String[] args) {

        int times = 1000000;
        int count = 0;
        for (int i = 0; i < times; i++) {
            if (Math.random() < 0.6) {
                count++;
            }
        }
        System.out.println((double) count / (double) times);

        System.out.println("===========================");
        count = 0;
        for (int i = 0; i < times; i++) {
            if (x2XPower2() < 0.6) {
                count++;
            }
        }
        System.out.println((double) count / (double) times);
        System.out.println("===========================");
        int[] counts = new int[8];
        for (int i = 0; i < times; i++) {
            counts[f4()]++;
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.print(counts[i] + " ");
        }
        System.out.println();
        System.out.println("===========================");
        counts = new int[2];
        for (int i = 0; i < times; i++) {
            counts[g1()]++;
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.print(counts[i] + " ");
        }
        System.out.println();
    }

    /**
     * 将x的概率改成x的平方
     * 概率 = 两次独立的随机事件概率相乘
     *
     * @return
     */
    public static double x2XPower2() {
        return Math.max(Math.random(), Math.random());
    }

    /**
     * 1~5 等概率随机数
     *
     * @return
     */
    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 使用f() 获取0 1 等概率随机数
     *
     * @return
     */
    public static int f1() {
        /*int f = f();
        if (f < 3) {
            return 0;
        }
        if (f > 3) {
            return 1;
        }
        return f1();*/
        int f = 0;
        do {
            f = f();
        } while (f == 3);
        return f < 3 ? 0 : 1;
    }

    /**
     * 使用f1() 获取0~7 等概率随机数
     *
     * @return
     */
    public static int f2() {
        return (f1() << 2) + (f1() << 1) + (f1() << 0);
    }

    /**
     * 使用f2() 获取0~6 等概率随机数
     *
     * @return
     */
    public static int f3() {
        int f = 0;
        do {
            f = f2();
        } while (f == 7);
        return f;
    }

    /**
     * 使用f3() 获取1~7 等概率随机数
     *
     * @return
     */
    public static int f4() {
        return f3() + 1;
    }

    /**
     * 获取0 1 概率不等
     *
     * @return
     */
    public static int g() {
        return Math.random() < 0.84 ? 0 : 1;
    }

    /**
     * 获取0 1 等概率
     * roll两次 结果00概率P*P 01概率P*(1-P) 10概率(1-P)*P 11概率(1-P)*(1-P) 00和11重roll 得到01 10等概率 返回第一位
     *
     * @return
     */
    public static int g1() {
        int g = 0;
        do {
            g = g();
        } while (g == g());
        return g;
    }


}
