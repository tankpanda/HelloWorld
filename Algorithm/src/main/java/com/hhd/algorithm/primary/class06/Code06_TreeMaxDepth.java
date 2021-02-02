package com.hhd.algorithm.primary.class06;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/
 * Created by huhengda on 2021/2/2.
 */
public class Code06_TreeMaxDepth {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
