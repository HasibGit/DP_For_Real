/*
   Just a variation of subset sum problem.
 */


import java.util.*;
import java.io.*;

public class No_4_Count_Number_Of_Subsets_Where_Sum_Equals_K {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];

        for(int i = 0;i < n;i++){
            arr[i] = input.nextInt();
        }

        int sum = input.nextInt();

        int[][] table = new int[n+1][sum + 1];

        for(int items = 0;items <= n;items++){
            table[items][0] = 1;
        }
        for(int total = 1;total <= sum;total++){
            table[0][total] = 0;
        }

        for(int item = 1;item <= n;item++){
            for(int total = 1;total <= sum;total++){
                if(arr[item-1] > total){
                    table[item][total] += table[item-1][total];
                }
                else{
                    table[item][total] += (table[item-1][total - arr[item-1]]) + (table[item-1][total]);
                }
            }
        }

        System.out.println(table[n][sum]);
    }
}
