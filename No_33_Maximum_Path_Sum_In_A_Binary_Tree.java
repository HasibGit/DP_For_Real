
import java.util.*;
import java.io.*;

public class No_33_Maximum_Path_Sum_In_A_Binary_Tree {

    static class Node{
        int value;
        Node left;
        Node right;

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

        int left = solve(root.left);
        int right = solve(root.right);

        int temp = Math.max(root.value,Math.max(left,right) + root.value);
        int ans = Math.max(temp,left + right + root.value);
        res = Math.max(res,ans);
        return temp;
    }

    public static void main(String[] args) {
         Node root = new Node(-10);
         root.left = new Node(9);
         root.right = new Node(20);
         root.right.left = new Node(15);
         root.right.right = new Node(7);

        solve(root);

        System.out.println("Max Sum : " + res);
    }
}
