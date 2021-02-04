package com.hhd.algorithm.primary.class06;

import sun.reflect.generics.tree.Tree;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 * Created by huhengda on 2021/2/3.
 */
public class Code09_BalancedTree {
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

    public class TreeInfo {
        int depth = 0;
        boolean balanced = true;

        public TreeInfo(int depth, boolean balanced) {
            this.depth = depth;
            this.balanced = balanced;
        }

        public TreeInfo() {
        }
    }

    public boolean isBalanced(TreeNode root) {
        return getTreeInfo(root).balanced;
    }

    public TreeInfo getTreeInfo(TreeNode head) {
        if (head == null) {
            return new TreeInfo();
        }
        TreeInfo left = getTreeInfo(head.left);
        TreeInfo right = getTreeInfo(head.right);
        return new TreeInfo(Math.max(left.depth, right.depth) + 1, left.balanced && right.balanced && (left.depth - right.depth) <= 1 && (left.depth - right.depth) >= -1);
    }

}
