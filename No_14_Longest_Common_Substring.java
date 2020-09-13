/*
  The only difference with lcs is when we don't find a match,
  we have to start counting from 0
 */


import java.util.*;
import java.io.*;

public class No_14_Longest_Common_Substring {


    static int func(char[] arr,char[] arr2,int n,int m,int count){
         if(n == 0 || m == 0)
             return count;
         else{
             if(arr[n-1] == arr2[m-1]){
                 count = func(arr,arr2,n-1,m-1,count+1);
             }
             else{
                 count = Math.max(count,
                           Math.max(
                                   func(arr,arr2,n-1,m,0),
                                   func(arr,arr2,n,m-1,0)
                                   )
                 );
             }
         }
         return count;
    }

    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       String a = input.nextLine();
       String b = input.nextLine();

       char[] arr = a.toCharArray();
       char[] arr2 = b.toCharArray();

       int n = a.length();
       int m = b.length();
       int count = 0;

       int maxLen = func(arr,arr2,n,m,count);
        System.out.println(maxLen);

    }
}
