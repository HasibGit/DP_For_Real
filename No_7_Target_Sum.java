import java.util.*;
import java.io.*;

/*
     An array is given, suppose  {1,1,2,3}
     and a target sum is given, sum = 1;

     We can make any array element pos or neg.
     We have to count the  number of ways we can get sum of array elements = target sum.

     Observation :

     - We can think of the problem like this.
       we can divide the array into two subsets s1 and s2.
       And sum(s1) - sum(s2) = targetSum
           sum(s1) + sum(s2) = sumOfArray
           ------------------------------
           2 * sum(s1) = (targetSum + sumOfArray)
           => sum(s1) = (targetSum + sumOfArray) / 2;

       So, this is same as the count number of subsets with a given difference problem.
*/


public class No_7_Target_Sum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        int[] arr = new int[n];

        int sumOfArray = 0;
        for(int i = 0;i < n;i++){
            arr[i] = input.nextInt();
            sumOfArray += arr[i];
        }

        int targetSum = input.nextInt();


        int sum_s1 = (targetSum + sumOfArray) / 2;

        int[][] table = new int[n+1][sum_s1+1];

        table[0][0] = 1;

        for(int i = 1;i <= n;i++){
            table[i][0] = 1;
        }

        for(int i = 1;i <= sum_s1;i++){
            table[0][i] = 0;
        }


        for(int items = 1;items <= n;items++){
            for(int total = 1;total <= sum_s1;total++){
                if(arr[items-1] > total){
                    table[items][total] = table[items-1][total];
                }
                else{
                    table[items][total] = table[items-1][total - arr[items-1]] + table[items-1][total];
                }
            }
        }

        System.out.println(table[n][sum_s1]);
    }
}
