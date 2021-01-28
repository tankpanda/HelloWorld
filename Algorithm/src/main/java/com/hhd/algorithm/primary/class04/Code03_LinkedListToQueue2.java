package com.hhd.algorithm.primary.class04;

/**
 * 双向链表实现双端队列
 * Created by huhengda on 2021/1/27.
 */
public class Code03_LinkedListToQueue2 {
    public static class MyDeque<V> {
        private NodeV2<V> head;
        private NodeV2<V> tail;
        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void pushHead(V v) {
            NodeV2<V> curr = new NodeV2<>(v);
            if (head == null) {
                head = curr;
                tail = curr;
            } else {
                curr.next = head;
                head.pre = curr;
                head = curr;
            }
            size++;
        }

        public void pushTail(V v) {
            NodeV2<V> curr = new NodeV2<>(v);
            if (tail == null) {
                head = curr;
                tail = curr;
            } else {
                curr.pre = tail;
                tail.next = curr;
                tail = curr;
            }
            size++;
        }

        public V popHead() {
            V v = null;
            if (head != null) {
                v = head.v;
                size--;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = head.next;
                    head.pre = null;
                }
            }
            return v;
        }

        public V popTail() {
            V v = null;
            if (tail != null) {
                v = head.v;
                size--;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    tail = tail.pre;
                    tail.next = null;
                }
            }
            return v;
        }

        public V peekHead() {
            V v = null;
            if (head != null) {
                v = head.v;
            }
            return v;
        }

        public V peekTail() {
            V v = null;
            if (tail != null) {
                v = tail.v;
            }
            return v;
        }

    }
}
