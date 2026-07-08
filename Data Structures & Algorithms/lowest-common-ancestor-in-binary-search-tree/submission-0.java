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
    List<TreeNode> pStk, qStk;
    private boolean getStack(TreeNode node, TreeNode tar, boolean p){
        if(node == null)return false;

        if(node.val == tar.val){
            if(p){
                pStk.addLast(node);
            }else{
                qStk.addLast(node);
            }

            return true;
        }

        boolean left = getStack(node.left, tar, p);
        boolean right = getStack(node.right, tar, p);

        if(left || right){
            if(p){
                pStk.addLast(node);
            }else{
                qStk.addLast(node);
            }
        }

        return left || right;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        pStk = new LinkedList<>();
        qStk = new LinkedList<>();

        getStack(root, p, true);
        getStack(root, q, false);

        // System.out.println(pStk);
        // System.out.println(qStk);

        while(pStk.size() > qStk.size()){
            pStk.removeFirst();
        }

        while(qStk.size() > pStk.size()){
            qStk.removeFirst();
        }

        

        while(!pStk.isEmpty() && !qStk.isEmpty() && pStk.get(0) != qStk.get(0)){
            pStk.removeFirst();
            qStk.removeFirst();
        }

        // System.out.println(pStk);
        // System.out.println(qStk);

        return pStk.get(0);
    }
}
