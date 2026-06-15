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
        int max, min;
        boolean isValid;
        Result(int max, int min, boolean isValid){
            this.max = max;
            this.min = min;
            this.isValid = isValid;
        }
    }

    private Result isValidBSTHelper(TreeNode node){
        if(node == null)return new Result(Integer.MIN_VALUE, Integer.MAX_VALUE, true);

        Result left = isValidBSTHelper(node.left);
        Result right = isValidBSTHelper(node.right);
        //System.out.println("Left at node "+node.val+" is max : "+left.max+ " min : "+left.min+" isValid "+left.isValid);
        //System.out.println("RIght at node "+node.val+" is max : "+right.max+ " min : "+right.min+" isValid "+right.isValid);
        if(left.isValid && right.isValid && (node.val < right.min) && (node.val > left.max)){
            int min = left.min == Integer.MAX_VALUE ? node.val : left.min;
            int max = right.max == Integer.MIN_VALUE ? node.val : right.max;
            return new Result(max, min, true);
        }

        return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, false);
    }
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root).isValid;
    }
}
