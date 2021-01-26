package com.hhd.algorithm.primary.class03;

/**
 * 数组无序 任意相邻位置不等 查找局部最小 1.0位置小于1位置 2.n-1位置小于n-2位置 3.x位置小于x-1位置且小于x+1位置
 * Created by huhengda on 2021/1/26.
 */
public class Code04_Awesome {
    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 100;
        int times = 10000;
        check(maxLen, maxValue, times);
    }

    public static int oneMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        int N = arr.length;
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[N - 1] < arr[N - 2]) {
            return N-1;
        }
        // 当0>1且n-1>n-2时 必有局部最小（0>1 趋势下降 n-1>n-2 趋势上升）
        int L = 0;
        int R = N - 1;
        // L<R-1 保证最终的L~R范围内有三个数 防止数组越界 最终返回arr[L] arr[R]较小的位置
        while (L < R - 1) {
            int mid = (L + R) / 2;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                // 小于两边 是局部最小 返回
                return mid;
            }
            if (arr[mid] > arr[mid - 1]) {
                // 大于左边 左侧有局部最小（0>1 趋势下降 mid>mid-1 趋势上升）
                R = mid - 1;
                continue;
            }
            if (arr[mid] > arr[mid + 1]) {
                // 大于右边 右侧有局部最小（mid>mid+1 趋势下降 n-1>n-2 趋势上升）
                L = mid + 1;
                continue;
            }
        }
        return arr[L] < arr[R] ? L : R;
    }

    public static boolean test(int[] arr, int minIndex) {
        if (arr == null || arr.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

    /**
     * 对数器
     */
    public static void check(int maxLen, int maxValue, int times) {
        boolean success = true;
        for (int i = 0; i < times; i++) {
            int[] arr = new int[maxLen];
            arr[0] = (int) (Math.random() * maxValue);
            for (int j = 1; j < maxLen; j++) {
                do {
                    arr[j] = (int) (Math.random() * maxValue);
                } while (arr[j - 1] == arr[j]);
            }
            if (!test(arr, oneMinIndex(arr))) {
                success = false;
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(arr[j] + " ");
                }
                System.out.println();
                break;
            }
        }
        System.out.println(success);

    }
}
