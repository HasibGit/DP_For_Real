/*
     Two strings s1 and s2 will be given.
     We have to find the length of the longest common subsequence between these
     two strings.

     s1 = abcdfh
     s2 = acdeh

     the lcs is = acdh, len = 4;

     What is the base case of this problem?
       lets denote len(s1) = n and len(s2) = m

     if n == 0 || m == 0
        lcs = 0;
        there could be no common subsequence.
     For finding the base case, we have to think about the smallest input.And
     this is the base case.

     Choice diagram part :

        obviously for recursion, we start from the end of both strings.

        If s1[n] == s2[m] , there is nothing to think about. The optimal thing
        to do is include this to lcs and recurr for n-1 and m-1.

        abcdfh   ,   acdeh

        So, h is included to lcs and we recurr for abcdf and acde

        if they dont match up, like now f != e, there are two choices.
           we recurr for abcdf , acd       OR
           we recurr for abcd , acde

           whichever gives us max lcs len.

*/



import java.util.*;
import java.io.*;

public class No_11_LCS_Recursion {

    static int lcs(char[] arr, char[] arr2, int n,int m){
        // base case
        if(n == 0 || m == 0){
            return 0;
        }

        else{
           if(arr[n-1] == arr2[m-1]){
               return 1 + lcs(arr,arr2,n-1,m-1);
           }
           else{
               return Math.max(lcs(arr,arr2,n,m-1),lcs(arr,arr2,n-1,m));
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

         int len = lcs(arr,arr2,n,m);

        System.out.println(len);
    }
}
