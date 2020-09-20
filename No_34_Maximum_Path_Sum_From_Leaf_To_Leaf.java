
import java.util.*;
import java.io.*;

public class No_34_Maximum_Path_Sum_From_Leaf_To_Leaf {
    static class Node{
        int value;
        Node left,right;

        Node(int value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    static int res = Integer.MIN_VALUE;

    static int solve(Node root){
        if(root == null)
            return 0;

        if(root.left == null && root.right == null){
            return root.value;
        }

        int left = solve(root.left);
        int right = solve(root.right);

        if(root.left != null && root.right != null){
            int temp = Math.max(left,right) + root.value;

            res = Math.max(res,left + right + root.value);

            return temp;
        }

        if(root.left == null)
            return (right + root.value);
        else
            return (left + root.value);
    }


    public static void main(String[] args) {
        Node root = new Node(-15);
        root.left = new Node(5);
        root.right = new Node(6);
        root.left.left = new Node(-8);
        root.left.right = new Node(1);
        root.left.left.left = new Node(2);
        root.left.left.right = new Node(6);
        root.right.left = new Node(3);
        root.right.right = new Node(9);
        root.right.right.right = new Node(0);
        root.right.right.right.left = new Node(4);
        root.right.right.right.right = new Node(-1);
        root.right.right.right.right.left = new Node(10);

        solve(root);
        System.out.println("Max pathSum of the given binary tree is "
                + res);
    }
}
