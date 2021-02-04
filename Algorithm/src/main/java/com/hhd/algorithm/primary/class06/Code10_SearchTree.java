package com.hhd.algorithm.primary.class06;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * Created by huhengda on 2021/2/3.
 */
public class Code10_SearchTree {
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

    public static class TreeInfo {
        int minVal;
        int maxVal;
        boolean bst;

        public TreeInfo(int minVal, int maxVal, boolean bst) {
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.bst = bst;
        }

        public TreeInfo() {
        }
    }

    public static boolean isValidBST(TreeNode root) {
        return getTreeInfo(root).bst;
    }

    public static TreeInfo getTreeInfo(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        TreeInfo left = getTreeInfo(treeNode.left);
        TreeInfo right = getTreeInfo(treeNode.right);
        boolean bst = true;
        int minValue = treeNode.val;
        int maxValue = treeNode.val;
        if (left != null) {
            minValue = Math.min(left.minVal, minValue);
            maxValue = Math.max(left.maxVal, maxValue);
//            bst = left.bst && treeNode.val > left.maxVal && bst;
        }
        if (right != null) {
            minValue = Math.min(right.minVal, minValue);
            maxValue = Math.max(right.maxVal, maxValue);
//            bst = right.bst && right.minVal > treeNode.val && bst;
        }
        if (left != null && !left.bst) {
            bst = false;
        }
        if (right != null && !right.bst) {
            bst = false;
        }
        boolean leftMaxLessCurr = left == null ? true : left.maxVal < treeNode.val;
        boolean rightMinGreaterCurr = right == null ? true : right.minVal > treeNode.val;
        if (!(leftMaxLessCurr && rightMinGreaterCurr)) {
            bst = false;
        }
        return new TreeInfo(minValue, maxValue, bst);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, null, new TreeNode(1));
        TreeNode treeNode1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode treeNode2 = new TreeNode(5, new TreeNode(4), new TreeNode(6, new TreeNode(3), new TreeNode(7)));
        TreeNode treeNode3 = new TreeNode(32, new TreeNode(26, new TreeNode(19, null, new TreeNode(27)), null), new TreeNode(47, null, new TreeNode(56)));
        System.out.println(isValidBST(treeNode3));
    }
}
