package com.hhd.algorithm.primary.class05;

/**
 * 位图
 * Created by huhengda on 2021/1/28.
 */
public class Code01_BitMap {
    public static void main(String[] args) {

    }

    public static class BitMap {
        private long[] bits;

        /**
         * long 64位
         * (i >> 6) = (i / 64)
         *
         * @param maxNum 最大数
         */
        public BitMap(int maxNum) {
            bits = new long[(maxNum + 64) >> 6];
        }

        /**
         * bits[i]上的数第j位置为1
         *
         * (num & 63) = (num % 64)
         * (1L << i) = 第i位上置1
         * (i |= j) = (i = i | j)
         *
         * @param num
         */
        public void add(int num) {
            bits[num >> 6] |= (1L << (num & 63));
        }

        /**
         * bits[i]上的数第j位置为0
         *
         * ~(i) = 01反转
         * (i &= j) = (i = i & j)
         *
         * @param num
         */
        public void delete(int num) {
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        /**
         * bits[i]上的数与j是否为0
         *
         * @param num
         */
        public boolean contains(int num) {
            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }
    }
}
