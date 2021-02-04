package com.hhd.algorithm.primary.class06;

/**
 * https://leetcode-cn.com/problems/path-sum/
 * Created by huhengda on 2021/2/4.
 */
public class Code11_PathSum {
    public static class TreeNode {
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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
//        return process(root, targetSum);
        return process1(root, 0, targetSum);
    }

    public boolean process(TreeNode root, int sumLeft) {
        if (root.left == null && root.right == null) {
            return root.val == sumLeft;
        }
        if (root.left != null) {
            if (process(root.left, sumLeft - root.val)) {
                return true;
            }
        }
        if (root.right != null) {
            if (process(root.right, sumLeft - root.val)) {
                return true;
            }
        }
        return false;
    }

    public boolean process1(TreeNode root, int currSum, int sum) {
        if (root.left == null && root.right == null) {
            return currSum + root.val == sum;
        }
        if (root.left != null) {
            if (process1(root.left, currSum + root.val, sum)) {
                return true;
            }
        }
        if (root.right != null) {
            if (process1(root.right, currSum + root.val, sum)) {
                return true;
            }
        }
        return false;
    }

}
