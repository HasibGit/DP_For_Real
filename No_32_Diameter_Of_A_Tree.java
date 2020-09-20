
import java.util.*;
import java.io.*;

public class No_32_Diameter_Of_A_Tree {

    static class Node{
        int id;
        Node left,right;
        Node(int id){
            this.id = id;
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

        int temp = Math.max(left,right) + 1;

        int ans = Math.max(temp,left + right + 1);

        res = Math.max(res,ans);

        return temp;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        solve(root);

        System.out.println("The diameter of given binary tree is : "
                + res);
    }
}
