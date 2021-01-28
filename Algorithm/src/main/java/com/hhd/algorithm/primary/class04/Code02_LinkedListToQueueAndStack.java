package com.hhd.algorithm.primary.class04;

/**
 * 单链表实现队列和栈
 * Created by huhengda on 2021/1/26.
 */
public class Code02_LinkedListToQueueAndStack {
    public static class MyQueue<V> {
        private NodeV<V> head;
        private NodeV<V> tail;
        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(V v) {
            NodeV<V> curr = new NodeV<>(v);
            if (head == null && tail ==null) {
                head = curr;
                tail = curr;
            } else {
                tail.next = curr;
                tail = curr;
            }
            size++;
        }

        public V poll() {
            V v = null;
            if (head != null) {
                v = head.v;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return v;
        }

        public V peek() {
            V v = null;
            if (head != null) {
                v = head.v;
            }
            return v;
        }
    }

    public static class MyStack<V> {
        private NodeV<V> head;
        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(V v) {
            NodeV<V> curr = new NodeV<>(v);
            if (head == null) {
                head = curr;
            } else {
                curr.next = head;
                head = curr;
            }
            size++;
        }

        public V pop() {
            V v = null;
            if (head != null) {
                v = head.v;
                head = head.next;
                size--;
            }
            return v;
        }

        public V peek() {
            V v = null;
            if (head != null) {
                v = head.v;
            }
            return v;
        }
    }
}
