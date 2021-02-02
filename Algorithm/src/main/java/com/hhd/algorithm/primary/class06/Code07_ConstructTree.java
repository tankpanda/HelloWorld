package com.hhd.algorithm.primary.class06;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Created by huhengda on 2021/2/2.
 */
public class Code07_ConstructTree {
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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
//        TreeNode head = new TreeNode(preorder[0]);
//        int headValueIndex = findIndex(inorder, preorder[0]);
//        head.left = buildTreeIndex(preorder, 1, headValueIndex, inorder, 0, headValueIndex - 1);
//        head.right = buildTreeIndex(preorder, headValueIndex + 1, preorder.length - 1, inorder, headValueIndex + 1, inorder.length - 1);
//        return head;
        return buildTreeNode(preorder, 0, preorder.length-1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeNode(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        // 左右子树为空 会导致start>end
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        if (preStart == preEnd || inStart == inEnd) {
            return new TreeNode(preorder[preStart]);
        }
        TreeNode treeNode = new TreeNode(preorder[preStart]);
        int valueIndex = findIndex(inorder, preorder[preStart]);
        treeNode.left = buildTreeNode(preorder, preStart + 1, preStart + valueIndex - inStart, inorder, inStart, valueIndex - 1);
        treeNode.right = buildTreeNode(preorder, preStart + valueIndex - inStart + 1, preEnd, inorder, valueIndex + 1, inEnd);
        return treeNode;
    }

    /**
     * 优化 将inorder转化为map key=inorder[i] value=i
     * @param inorder
     * @param num
     * @return
     */
    public int findIndex(int[] inorder, int num) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == num) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Code07_ConstructTree ct = new Code07_ConstructTree();
        TreeNode treeNode = ct.buildTree(new int[]{1,2,3}, new int[]{3,2,1});
        ct.pre(treeNode);
        ct.in(treeNode);
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
}
