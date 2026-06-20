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
    private TreeNode getLeftestNode(TreeNode node){
        TreeNode temp = node;

        while(temp.left != null){
            temp = temp.left;
        }

        return temp;
    }

    private boolean isLeaf(TreeNode node){
        return node.left == null && node.right == null;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)return null;
        
        if(root.val == key){
            if(isLeaf(root)){
                return null;
            }else{
                if(root.right == null)return root.left;
                else{
                    TreeNode leftestNode = getLeftestNode(root.right);
                    leftestNode.left = root.left;
                    return root.right;
                }
            }
        }else{
            TreeNode left = deleteNode(root.left, key);
            TreeNode right = deleteNode(root.right, key);

            root.left = left;
            root.right = right;

            return root;
        }
        
    }
}