/*
    We are given a string,

      S = "agbcba"

    We have to delete min number of chars from S so that S becomes a palindrome.

    The best we can do is delete g. Then S = "abcba"
    This is nothing but the longest palindromic subsequence of S.

    So, the problem got reduced.
    ans = len(S) - lps
*/
import java.util.*;
import java.io.*;

public class No_20_Minimum_number_of_deletion_in_a_string_to_make_it_a_palindrome {

    static int[][] table;

    static int lps(char[] arr,char[] arr2,int n,int m){
        if(n == 0 || m == 0){
            table[n][m] = 0;
            return table[n][m];
        }
        else{
            if(table[n][m] != -1){
                return table[n][m];
            }
            else{
                if(arr[n-1] == arr2[m-1]){
                    table[n][m] = 1 + lps(arr,arr2,n-1,m-1);
                    return table[n][m];
                }
                else{
                    table[n][m] = Math.max(lps(arr,arr2,n,m-1),lps(arr,arr2,n-1,m));
                    return table[n][m];
                }
            }
        }
    }

    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);

       String s1 = input.nextLine();

       StringBuffer sb = new StringBuffer(s1);
       sb.reverse();

       String s2 = sb.toString();

       char[] arr = s1.toCharArray();
       char[] arr2 = s2.toCharArray();

       int n = arr.length;
       int m = arr2.length;
       table = new int[n+1][m+1];

       for(int i = 0;i <= n;i++){
           for(int j = 0;j <= m;j++){
               table[i][j] = -1;
           }
       }

       int len = lps(arr,arr2,n,m);

       int ans = n - len;

        System.out.println(ans);

    }
}
