package com.hhd.algorithm.primary.class07;

/**
 * 归并排序
 * Created by huhengda on 2021/2/4.
 */
public class Code01_MergeSort {

    // 递归方式 start-----
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 2);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        int l1 = L;
        int l2 = M + 1;
        while (l1 <= M && l2 <= R) {
            if (arr[l1] <= arr[l2]) {
                help[helpIndex++] = arr[l1++];
            } else {
                help[helpIndex++] = arr[l2++];
            }
        }
        while (l1 <= M) {
            help[helpIndex++] = arr[l1++];
        }
        while (l2 <= R) {
            help[helpIndex++] = arr[l2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
    // 递归方式 end-----

    // 步长方式 O(N * logN) start-----
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int len = arr.length;
        while (step < len) {
            int L = 0;
            while (L < len) {
                // 计算中间位置
                int M = 0;
                // 防止溢出 用减法计算
                if (L > len - step) {
                    M = len - 1;
                } else {
                    M = L + step - 1;
                }

                // 归并 右侧没有
                if (M == len - 1) {
                    break;
                }

                // 计算右侧位置
                int R = 0;
                if (L > len - (step << 1)) {
                    R = len - 1;
                } else {
                    R = L + (step << 1) - 1;
                }
                merge(arr, L, M, R);
                if (R == len - 1) {
                    break;
                }
                L = R + 1;
            }
            // 防止int溢出 不使用step*2>n判断
            if (step > (len >> 1)) {
                break;
            }
            step <<= 1;
        }
    }
    // 步长方式 end-----

}
