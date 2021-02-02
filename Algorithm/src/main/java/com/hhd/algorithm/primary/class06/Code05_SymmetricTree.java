package com.hhd.algorithm.primary.class06;

/**
 * https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 * Created by huhengda on 2021/2/1.
 */
public class Code05_SymmetricTree {
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

    public boolean isSymmetric(TreeNode root) {
        return isSymmetricTree(root, root);
    }

    public boolean isSymmetricTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return p.val == q.val && isSymmetricTree(p.left, q.right) && isSymmetricTree(p.right, q.left);
    }
}
