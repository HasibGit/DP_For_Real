/*
       We have done lcs through. But does it need memorization ?

       if recursive func is like
          f(n) -> f(n-1) -> f(n-2)
          there is no overlapping sub problems. These are pure recursion.

        But if its like, f(n) -> f(n-1) , f(n-2)
           There are actually overlapping subproblems here.
           after f(n), f(n-1) will be completely processed. Then f(n-2) will be
           processed. On the way of processing f(n-1) we might also process f(n-2).
           So in this cases, memorization is needed.

       For lcs, there are overlapping subproblems.
         if s1[n] != s2[m}
           max(  f(s1,s2,n-1,m) , f(s2,s2,n,m-1) )
             it is possible that while processing first func, many of the calls of
             sec func also gets processed. So we need to memorize the return values.
*/



import java.util.*;
import java.io.*;
public class No_12_LCS_Memorization {

    static int[][] table;

    static int lcs(char[] arr,char[] arr2,int n,int m){
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
                    table[n][m] = 1 + lcs(arr,arr2,n-1,m-1);
                    return table[n][m];
                }
                else{
                    table[n][m] = Math.max(lcs(arr,arr2,n,m-1), lcs(arr,arr2,n-1,m));
                    return table[n][m];
                }
            }
        }
    }

    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       String s1 = input.nextLine();
       String s2 = input.nextLine();

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

       int len = lcs(arr,arr2,n,m);

        System.out.println(len);
    }
}
