package com.hhd.algorithm.basics.class01;

/**
 * 一个数组中 有两个数出现了奇数次 求这两个数
 * (n & (~n + 1)) 获取n最右侧的1
 * Created by huhengda on 2021/2/7.
 */
public class Code05_Eor {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,9,8,6,5,4,2,1};
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int index = eor & (~eor + 1);

        // 1 start
        eor = 0;
        int eor1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & index) == 0) {
                eor ^= arr[i];
            } else {
                eor1 ^= arr[i];
            }
        }
        System.out.println(eor + " " + eor1);
        // 1 end


        // 2 start
        eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        index = eor & (~eor + 1);
        int eor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & index) == 0) {
                eor2 ^= arr[i];
            }
        }
        System.out.println(eor2 + " " + (eor ^ eor2));
        // 2 end

    }
}
