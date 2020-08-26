import java.util.*;
import  java.io.*;

/*
    We have and array {1,1,2,3} and a difference diff = 1;

    suppose, S1 represents subset 1
             S2 represents subset 2

    Problem is, Sum( S1 ) - Sum( S2 ) === diff
    We have to count the number of such subsets.

    Observation :
             Sum(S1) - Sum(S2) = diff
             Sum(S1) + Sum(S2) = Sum(arr)  // obviously
         --------------------------------------
             2 * Sum(S1) = diff + Sum(arr)
             => Sum(S1) = (diff + Sum(arr)) / 2

    Now, we can easily cal. Sum(S1). Now, problem boiled down to counting
    number of subsets where sum is equal to Sum(S1). This is nothing but
    subset sum problem.
 */

public class No_6_Count_The_Number_Of_Subsets_With_A_Given_Difference {
    public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
         int n = input.nextInt();

         int[] arr = new int[n];

         int sum = 0;
         for(int i = 0;i < n;i++){
             arr[i] = input.nextInt();
             sum += arr[i];
         }

         int diff = input.nextInt();


         int sum_s1 = (diff + sum) / 2;

         int[][] table = new int[n+1][sum_s1+1];

         table[0][0] = 1;

         for(int i = 1;i <= n;i++){
             table[i][0] = 1;
         }

         for(int i = 1;i <= sum_s1;i++){
             table[0][i] = 0;
         }

        for(int item = 1;item <= n;item++){
            for(int total = 1;total <= sum_s1;total++){
                if(arr[item-1] > total){
                    table[item][total] += table[item-1][total];
                }
                else{
                    table[item][total] += (table[item-1][total - arr[item-1]]) + (table[item-1][total]);
                }
            }
        }

        System.out.println(table[n][sum_s1]);
    }
}
