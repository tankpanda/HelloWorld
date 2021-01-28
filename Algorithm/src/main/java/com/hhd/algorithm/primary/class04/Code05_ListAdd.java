package com.hhd.algorithm.primary.class04;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 * Created by huhengda on 2021/1/27.
 */
public class Code05_ListAdd {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int tag = 0;
        ListNode result = new ListNode();
        ListNode head = result;
        while(l1 != null || l2 != null || tag != 0) {
            int l1Val = 0, l2Val = 0;
            if (l1 != null) {
                l1Val = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                l2Val = l2.val;
                l2 = l2.next;
            }
            result.next = new ListNode((l1Val + l2Val + tag) % 10);
            tag = (l1Val + l2Val + tag) / 10;
            result = result.next;
        }
        return head.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int l1Len = getLength(l1);
        int l2Len = getLength(l2);
        ListNode l = l1Len > l2Len ? l1 : l2;
        ListNode s = l1Len > l2Len ? l2 : l1;
        ListNode crrL = l;
        ListNode crrS = s;

        int tag = 0;
        ListNode lastL = crrL;
        while (crrS != null) {
            int sum = crrL.val + crrS.val + tag;
            tag = sum / 10;
            crrL.val = sum % 10;
            lastL = crrL;
            crrL = crrL.next;
            crrS = crrS.next;
        }
        while (crrL != null) {
            int sum = crrL.val + tag;
            tag = sum / 10;
            crrL.val = sum % 10;
            lastL = crrL;
            crrL = crrL.next;
        }
        if (tag != 0) {
            lastL.next = new ListNode(1);
        }
        return l;
    }

    public int getLength(ListNode ln) {
        int length = 0;
        while (ln != null) {
            length++;
            ln = ln.next;
        }
        return length;
    }
}
