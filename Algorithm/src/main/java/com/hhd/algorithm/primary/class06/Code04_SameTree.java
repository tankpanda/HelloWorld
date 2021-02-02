package com.hhd.algorithm.primary.class06;

/**
 * https://leetcode-cn.com/problems/same-tree/
 *
 * 先序 根节点 -> 左子树 -> 右子树
 * 中序 左子树 -> 根节点 -> 右子树
 * 后序 左子树 -> 右子树 -> 根节点
 *
 * Created by huhengda on 2021/2/1.
 */
public class Code04_SameTree {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 先序
     * @param treeNode
     */
    public void pre(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.val);
        pre(treeNode.left);
        pre(treeNode.right);
    }

    /**
     * 中序
     * @param treeNode
     */
    public void in(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        in(treeNode.left);
        System.out.println(treeNode.val);
        in(treeNode.right);
    }

    /**
     * 后序
     * @param treeNode
     */
    public void pos(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        pos(treeNode.left);
        pos(treeNode.right);
        System.out.println(treeNode.val);
    }

}
