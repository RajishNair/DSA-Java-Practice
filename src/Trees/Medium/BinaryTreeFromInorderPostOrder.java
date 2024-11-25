package Trees.Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/*
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

 

Example 1:


Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: inorder = [-1], postorder = [-1]
Output: [-1]
 * 
 */
public class BinaryTreeFromInorderPostOrder {
    public static void main(String[] args) {
        //Integer []inorder = {9,3,15,20,7};
        //Integer [] postorder = {9,15,7,20,3};
        Integer []postorder = {4, 5, 2, 6, 3, 1};
        Integer [] inorder = {4, 2, 5, 1, 6, 3};
        TreeNode binaryTree = BinaryTreeFromInorderPostOrder.buildBinaryTree(Arrays.asList(inorder),Arrays.asList(postorder));
        System.out.println("Pre-order traversal of the constructed tree:");
        BinaryTreeFromInorderPostOrder.printPreOrder(binaryTree);
    }

    public static TreeNode buildBinaryTree(List<Integer> inorder, List<Integer> postorder){
        Map<Integer,Integer> inorderIndexMap = new HashMap<>();
        for(int i=0; i<inorder.size();i++){
            inorderIndexMap.put(inorder.get(i),i);
        }
        return buildBinaryTreeHelper(inorderIndexMap,0,inorder.size()-1,0,postorder.size()-1, inorder,postorder);
    }
public static TreeNode buildBinaryTreeHelper(Map<Integer,Integer> inorderIndexMap, int inStart,int inEnd, int postStart,int postEnd,List<Integer> inorder, List<Integer> postorder){

    if(postStart>postEnd || inStart>inEnd){
        return null;
    }
    
        
        TreeNode root = new TreeNode(postorder.get(postEnd));
        int rootIndex = inorderIndexMap.get(postorder.get(postEnd));
        
        root.right = buildBinaryTreeHelper(inorderIndexMap, rootIndex+1,inEnd, postStart+rootIndex-inStart, postEnd-1,inorder,postorder);
        root.left = buildBinaryTreeHelper(inorderIndexMap,inStart,rootIndex-1,postStart,postStart+rootIndex-inStart-1,inorder,postorder);
        return root;
}
public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// Helper method to print the tree in pre-order traversal
public static void printPreOrder(TreeNode node) {
    if (node == null) {
        return;
    }
    System.out.print(node.val + " ");
    printPreOrder(node.left);
    
    printPreOrder(node.right);
}
}

