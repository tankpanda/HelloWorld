package com.hhd.algorithm.primary.class01;

/**
 * Created by huhengda on 2021/1/21.
 */
public class Code04_InsertSort {
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minIndex = i;
            while (minIndex > 0 && arr[minIndex] < arr[minIndex - 1]) {
                swap(arr, minIndex, minIndex - 1);
                minIndex --;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1,7,4,6,8,5,6};
        print(arr);
        insertSort(arr);
        print(arr);
    }
}
