package com.hhd.algorithm.primary.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * Created by huhengda on 2021/2/1.
 */
public class Code03_MergeLinkedList {
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
     * O(N*logM)
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pr = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }
            pr.add(lists[i]);
        }

        if (pr.isEmpty()) {
            return null;
        }

        ListNode head = pr.poll();
        ListNode pre = head;
        if (head.next != null) {
            pr.add(head.next);
        }

        while(!pr.isEmpty()) {
            ListNode curr = pr.poll();
            pre.next = curr;
            pre = curr;
            if (curr.next != null) {
                pr.add(curr.next);
            }
        }
        return head;
    }
}
