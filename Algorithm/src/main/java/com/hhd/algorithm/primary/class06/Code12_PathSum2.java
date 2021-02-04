package com.hhd.algorithm.primary.class06;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/path-sum-ii/
 * Created by huhengda on 2021/2/4.
 */
public class Code12_PathSum2 {
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

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        process(root, new ArrayList<>(), 0, targetSum, result);
        return result;
    }

    public void process(TreeNode root, List<Integer> list, int currSum, int sum, List<List<Integer>> result) {
        list.add(root.val);
        if (root.left == null && root.right == null) {
            if (currSum + root.val == sum) {
                List<Integer> res = new ArrayList<>();
                res.addAll(list);
                result.add(res);
            }
            return;
        }
        if (root.left != null) {
            process(root.left, list, currSum + root.val, sum, result);
            list.remove(list.size() - 1);
        }
        if (root.right != null) {
            process(root.right, list, currSum + root.val, sum, result);
            list.remove(list.size() - 1);
        }
    }

}
