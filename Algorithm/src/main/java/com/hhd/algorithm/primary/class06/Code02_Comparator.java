package com.hhd.algorithm.primary.class06;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先级队列 小根堆
 * Created by huhengda on 2021/2/1.
 */
public class Code02_Comparator {

    /**
     * 自定义比较器 实现大根堆
     */
    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 < o2 ? 1 : -1;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(1);
        heap.add(3);
        heap.add(2);
        heap.add(4);
        System.out.println(heap.peek());
        heap.add(0);
        System.out.println(heap.peek());
        System.out.println("==============");
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
