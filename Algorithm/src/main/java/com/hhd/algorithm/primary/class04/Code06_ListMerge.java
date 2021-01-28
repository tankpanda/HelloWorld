package com.hhd.algorithm.primary.class04;

/**
 * https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/submissions/
 * Created by huhengda on 2021/1/27.
 */
public class Code06_ListMerge {
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

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode resultHead;
        if (l1.val < l2.val) {
            resultHead = l1;
            l1 = l1.next;
        } else {
            resultHead = l2;
            l2 = l2.next;
        }
        ListNode resultPre = resultHead;

        while (l1 != null || l2 != null) {
            if (l1 == null || (l1 != null && l2 != null && l1.val >= l2.val)) {
                resultPre.next = l2;
                resultPre = l2;
                l2 = l2.next;
            } else if (l2 == null || (l1 != null && l2 != null && l1.val < l2.val)) {
                resultPre.next = l1;
                resultPre = l1;
                l1 = l1.next;
            }
        }
        return resultHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode merge = new Code06_ListMerge().merge(l1, l2);
        while (merge != null) {
            System.out.print(merge.val + " ");
            merge = merge.next;
        }
    }
}
