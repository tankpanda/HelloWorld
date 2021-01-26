package com.hhd.algorithm.primary.class03;

import com.hhd.algorithm.primary.class01.Code04_InsertSort;

/**
 * 二分查找
 * Created by huhengda on 2021/1/26.
 */
public class Code01_BSExist {

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 100;
        int times = 10000;
        check(maxLen, maxValue, times);
    }

    /**
     * 二分查找
     * @param arr 保证有序
     * @param num
     * @return
     */
    public static boolean find(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] == num) {
                // 相等 找到结果
                return true;
            } else if (arr[mid] < num) {
                // 小于 去掉左边
                L = mid + 1;
            } else {
                // 大于 去掉右边
                R = mid - 1;
            }
        }
        return false;
    }

    /**
     * 遍历查找
     * @param arr
     * @param num
     * @return
     */
    public static boolean test(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
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
            if (find(arr, num) != test(arr, num)) {
                success = false;
//                for (int j = 0; j < arr.length; j++) {
//                    System.out.print(arr[i] + " ");
//                }
//                System.out.println();
//                System.out.println(num);
//                break;
            }
        }
        System.out.println(success);

    }
}
