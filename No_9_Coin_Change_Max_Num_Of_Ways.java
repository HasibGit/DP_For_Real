import java.util.*;
import java.io.*;

/*
    We got some coins, {1,2,3}
    and a sum, sum = 5;

    we can get sum 5 these ways..
       1,1,1,1,1
       1,1,1,2
       1,2,2
       1,1,3
       3,2
    So there are in total 5 ways to get sum = 5;

    max num of ways = number of ways

    This problem is just like subset sum problem where we had to find
    number of ways we could get a sum. But it is also unbounded knapsack
    as we can take the same coin multiple number of times. Code will be
    pretty much same with slight change.

*/



public class No_9_Coin_Change_Max_Num_Of_Ways {

    static int[][] table;


    static int unboundedKnapsack(int[] coins, int n, int sum){
        if(sum == 0){
            table[n][sum] = 1;
            return table[n][sum];
        }
        else if(n == 0){
            table[n][sum] = 0;
            return table[n][sum];
        }
        else{
            if(table[n][sum] != -1){
                return table[n][sum];
            }

            else{
                if(coins[n-1] > sum){
                    table[n][sum] = unboundedKnapsack(coins,n-1,sum);
                    return table[n][sum];
                }
                else{
                    table[n][sum] = unboundedKnapsack(coins,n-1,sum) + unboundedKnapsack(coins,n,sum - coins[n-1]);
                    return table[n][sum];
                }
            }
        }
    }

    public static void main(String[] args) {
           Scanner input = new Scanner(System.in);
           int n = input.nextInt();
           int[] coins = new int[n];

           for(int i = 0;i < n;i++){
               coins[i] = input.nextInt();
           }

           int sum = input.nextInt();

           table = new int[n+1][sum+1];

           for(int i = 0;i <= n;i++){
               for(int j = 0;j <= sum;j++){
                   table[i][j] = -1;
               }
           }

           int ways = unboundedKnapsack(coins,n,sum);

        System.out.println(ways);
    }
}
