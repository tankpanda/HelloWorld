package com.hhd.algorithm.primary.class04;

/**
 * 反转链表
 * Created by huhengda on 2021/1/26.
 */
public class Code01_ReverseList {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1 = reverseLinkedList(n1);
        while (n1!=null) {
            System.out.println(n1.value);
            n1 = n1.next;
        }

        Node2 m1 = new Node2(1);
        m1.next = new Node2(2);
        m1.next.pre = m1;
        m1.next.next = new Node2(3);
        m1.next.next.pre = m1.next;
        m1 = reverseLinkedList(m1);
        while (m1!=null) {
            System.out.println(m1.value);
            m1 = m1.next;
        }
    }

    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node2 reverseLinkedList(Node2 head) {
        Node2 pre = null;
        Node2 next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
