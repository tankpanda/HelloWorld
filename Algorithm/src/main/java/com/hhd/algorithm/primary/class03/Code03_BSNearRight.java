package com.hhd.algorithm.primary.class03;

import com.hhd.algorithm.primary.class01.Code04_InsertSort;

/**
 * 查找数组中<=num的最右位置
 * Created by huhengda on 2021/1/26.
 */
public class Code03_BSNearRight {

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 100;
        int times = 10000;
        check(maxLen, maxValue, times);
    }

    public static int mostRight(int[] arr, int num) {
        if (arr == null || arr.length == 1) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] <= num) {
                // 小于 去掉左侧
                // 等于 记录位置 并在左侧继续找 有没有相等的
                L = mid + 1;
                index = mid;
            } else {
                // 大于 去掉右侧
                R = mid - 1;
            }
        }
        return index;
    }

    /**
     * 遍历查找
     * @param arr
     * @param num
     * @return
     */
    public static int test(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= num) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 对数器
     */
    public static void check(int maxLen, int maxValue, int times) {
        boolean success = true;
        for (int i = 0; i < times; i++) {
            int[] arr = new int[maxLen];
            for (int j = 0; j < maxLen; j++) {
                arr[j] = (int) (Math.random() * maxValue);
            }
            Code04_InsertSort.insertSort(arr);
            int num = (int) (Math.random() * maxValue);
            if (mostRight(arr, num) != test(arr, num)) {
                success = false;
//                for (int j = 0; j < arr.length; j++) {
//                    System.out.print(arr[j] + " ");
//                }
//                System.out.println();
//                System.out.println(num);
//                break;
            }
        }
        System.out.println(success);

    }
}
