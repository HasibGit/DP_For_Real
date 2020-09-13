/*
We are given 2 strings
    S1 = "heap"
    S2 = "pea"
   there are 2 operations. insert and delete.
   We have to cal. min number of insert and delete op. to convert S1 to S2.
   * We apply these operations only in S1. To make it equal to S2.

   To, convert S1, to S2
     del. h and p from S1, S1 = "ea"
     insert p to S1, S1 = "pea"

   observe, we didn't have to do anything about "ea"
   "ea" is the lcs of S1 and S2.

   To convert S1 into S2,
         -> All other characters besides lcs, we have to delete from S1
         -> All other characters in S2 besides lcs, we have to insert into S1
   Number of insertions = len( S2) - lcs
   Number of deletions = len( S1) - lcs
 */


import java.util.*;
import java.io.*;
public class No_18_Minimum_Number_Of_Inser_And_Del_To_Convert_Str_A_To_Str_B {

    static int[][] table;

    static int lcs(char[] arr,char[] arr2,int n,int m){
        if(n == 0 || m == 0){
            table[n][m] = 0;
            return table[n][m];
        }
        else{
            if(arr[n-1] == arr2[m-1]){
                table[n][m] = 1 + lcs(arr,arr2,n-1,m-1);
                return table[n][m];
            }
            else{
                table[n][m] = Math.max(lcs(arr,arr2,n,m-1),lcs(arr,arr2,n-1,m));
                return table[n][m];
            }
        }
    }

    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);

       String s1 = input.nextLine();
       String s2 = input.nextLine();

       char[] arr = s1.toCharArray();
       char[] arr2 = s2.toCharArray();

       int n = s1.length();
       int m = s2.length();

       table = new int[n+1][m+1];

       int len = lcs(arr,arr2,n,m);

       int del = s1.length() - len;
       int ins = s2.length() - len;

        System.out.println("insertions: " + ins + " " + "deletions: " + del);

    }
}
