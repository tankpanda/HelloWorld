package com.hhd.algorithm.primary.class07;

import java.util.Stack;

/**
 * 快排
 * Created by huhengda on 2021/2/4.
 */
public class Code02_QuickSort {

    public static int[] partition(int[] arr, int L, int R) {
        int len = arr.length;
        int lessIndex = L - 1;
        int greateIndex = R;
        int index = L;
        while (index < greateIndex) {
            if (arr[index] <= arr[len - 1]) {
                swap(arr, ++lessIndex, index++);
            } else if (arr[index] > arr[len - 1]) {
                swap(arr, --greateIndex, index);
            } else {
                index++;
            }
        }
        swap(arr, greateIndex, R);
        return new int[]{lessIndex - 1, greateIndex};
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0 , arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalE = partition(arr, 0, arr.length - 1);
        process(arr, L, equalE[0] - 1);
        process(arr, equalE[1] + 1, R);
    }



    public static class Info {
        int L;
        int R;

        public Info(int l, int r) {
            L = l;
            R = r;
        }
    }

    public static void quickSort1(int[] arr) {
        Stack<Info> stack = new Stack();
        stack.push(new Info(0, arr.length - 1));
        while (!stack.isEmpty()) {
            Info pop = stack.pop();
            int[] equalE = partition(arr, pop.L, pop.R);
            if (equalE[0] > pop.L) {
                stack.push(new Info(pop.L, equalE[0] - 1));
            }
            if (equalE[1] < pop.R) {
                stack.push(new Info(equalE[1] + 1, pop.R));
            }
        }
    }

    public static void splitNum(int[] arr) {
        int index = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (arr[i] <= arr[len - 1]) {
                swap(arr, index++, i);
            }
        }
    }

    public static void splitNum1(int[] arr) {
        int len = arr.length;
        int lessIndex = 0;
        int greateIndex = len - 1;
        int index = 0;
        while (index < greateIndex) {
            if (arr[index] <= arr[len - 1]) {
                swap(arr, lessIndex++, index++);
            } else if (arr[index] > arr[len - 1]) {
                swap(arr, --greateIndex, index);
            } else {
                index++;
            }
        }
        swap(arr, greateIndex, len - 1);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 8, 4, 8, 2, 4};
//        splitNum(arr);
        quickSort1(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
