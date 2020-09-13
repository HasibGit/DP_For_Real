/*
      we are given a string,
       S = "AABEBCDD"

     we have to find the longest subsequence that occurs more than once in S.
     Here, its ABD, it occurs two times.

     Using lcs, we can find longest common subsequence.
       What we can do,
          S1 = S
          S2 = S

        Now, cal. lcs of S1 and S2. But..
          we only include when both chars match and n != m **

         S1 = AABEBCDD
         S2 = AABEBCDD

         S1[0} && S2[0] are same, but i == j
         S1[0] && S2[1] same, and i != j. So, we can take it.

*/



import java.util.*;
import java.io.*;
public class No_22_Longest_Repeating_Subsequence {

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
                if(arr[n-1] == arr2[m-1] && n != m){  // ***
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
         String s = input.nextLine();

         char[] arr = s.toCharArray();
         char[] arr2 = s.toCharArray();


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
