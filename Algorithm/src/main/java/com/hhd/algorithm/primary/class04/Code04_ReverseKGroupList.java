package com.hhd.algorithm.primary.class04;

/**
 * 单向链表 每k个反转
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * Created by huhengda on 2021/1/27.
 */
public class Code04_ReverseKGroupList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 获取start开始的第k个节点
     * @param start
     * @param k
     * @return
     */
    public ListNode getKGroup(ListNode start, int k) {
        ListNode head = start;
        while (k > 1 && start != null) {
            k--;
            start = start.next;
        }
        return k >= 1 ? start : head;
    }

    /**
     * 反转start到end
     * @param start
     * @param end
     */
    public void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode next = null;
        while (start != null && start != end) {
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
    }

    /**
     * 反转
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 记录局部反转的start end
        ListNode start = head;
        ListNode end = getKGroup(head, k);
        if (start == end || end == null) {
            return head;
        }
        // 记录下一个头部指针
        ListNode nextStart = end.next;
        // 局部反转 end->start 即start=end end=start
        reverse(start, end);
        // 返回值=反转后的头部
        head = end;
        // 尾部指向下一个start
        start.next = nextStart;
        // 记录尾部指针
        ListNode lastEnd = start;
        // 下次开始的头部
        start = nextStart;


        while (start != null) {
            end = getKGroup(start, k);
            if (start == end || end == null) {
                return head;
            }
            // 记录下一个头部指针
            nextStart = end.next;;
            // 局部反转 end->start 即start=end end=start
            reverse(start, end);
            // 上个尾部指向新的头部
            lastEnd.next = end;
            // 尾部指向下一个start
            start.next = nextStart;
            // 记录尾部指针
            lastEnd = start;
            // 下次开始的头部
            start = nextStart;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(3);
        ListNode ln4 = new ListNode(4);
        ListNode ln5 = new ListNode(5);
        ListNode ln6 = new ListNode(6);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;
//        ln5.next = ln6;

        ListNode reverse = new Code04_ReverseKGroupList().reverseKGroup(ln1, 2);
        while (reverse != null) {
            System.out.print(reverse.val + " ");
            reverse = reverse.next;
        }
    }

}
