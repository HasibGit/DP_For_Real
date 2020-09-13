/*
  Suppose we have 2 strings,
     S1 = AGGTAB
     S2 = GXTXAYB

     We have to come up with a string S such that,
     -S1 is present in S as a *subsequence
     -S2 is present in S as a *subsequence
     -The length of S is minimum possbile

    Such S is called super sequence.

    S = ""
    -> A != G, lets write , S = "A"
    -> G == G, We can take G 1 time, S = "AG" **
    -> X != G, S = "AGGX"
    -> T == T, take T only once, S = "AGGXT" **
    -> X != A, S = "AGGXTX"
    -> A == A, take once, S = "AGGXTXA" **
    -> Y != B, S = "AGGXTXAY"
    -> B == B, Take once, S = "AGGXTXAYB" **

    So, S = "AGGXTXAYB"
    Now, we are minimizing S by taking , GTAB sequence only once. And,
    If we observe, GTAB is nothing but the lcs of S! and S2.

    Ans = len(s1) + len(s2) - len(lcs)

 */


import java.util.*;
import java.io.*;
public class No_17_Shortest_Common_Suoersequence {

    static int[][] table;

    static int lcs(char[] arr,char[] arr2,int n,int m){
        if(n == 0 || m == 0){
            table[n][m] = 0;
            return table[n][m];
        }
        else{
            if(table[n][m] != -1)
                return table[n][m];
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

        for(int i = 0;i <= n;i++){
            for(int j = 0;j <= m;j++){
                table[i][j] = -1;
            }
        }

        int len = lcs(arr,arr2,n,m);

        int ans = n + m - len;
        System.out.println(ans);

    }
}
