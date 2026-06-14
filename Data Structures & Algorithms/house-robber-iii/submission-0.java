/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    class Result{
        int skipMax, consMax;
        Result(int skipMax, int consMax){
            this.skipMax = skipMax;
            this.consMax = consMax;
        }
    }

    private Result helper(TreeNode node){
        if(node == null)return new Result(0,0);

        Result leftRes = helper(node.left);
        Result rightRes = helper(node.right);

        int skipMax =  Math.max(leftRes.consMax, leftRes.skipMax) +  Math.max(rightRes.skipMax,rightRes.consMax);

        int consMax = node.val + leftRes.skipMax + rightRes.skipMax;

        return new Result(skipMax, consMax);
    }
    public int rob(TreeNode root) {
        Result res = helper(root);

        return Math.max(res.skipMax, res.consMax);
    }
}