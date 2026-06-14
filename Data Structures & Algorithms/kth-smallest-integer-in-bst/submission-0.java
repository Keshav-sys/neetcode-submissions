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
    int globalCounter, ans, k;
    private void helper(TreeNode node){
        if(node == null)return;

        helper(node.left);

        globalCounter++;

        if(globalCounter == k)ans = node.val;

        helper(node.right);
    }
    public int kthSmallest(TreeNode root, int k) {
        this.globalCounter = 0;
        this.k = k;
        this.ans = -1;

        helper(root);

        return ans;
    }
}
