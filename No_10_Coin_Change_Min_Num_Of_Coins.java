import java.util.*;
import java.io.*;

/*
      We will be given some coins {3,5,7,2,4}
      and a sum, sum = 12

      We have to cal. min number of coins needed to get the sum.

      3 + 3 + 3 + 3 = 12 (4)
      4 + 4 + 4 = 12 (3)
      7 + 5 = 12 (2)
      5 + 5 + 2 = 12 (3)
      3 + 4 + 5 = 12 (3)....

      Obviously table data will contain the needed min number of coins.

      Now, Its unbounded knapsack varient.

      Base case analyze :

        1 ) if sum = 0, no matter how many coins we are considering, we dont need any coins,
           so, table[i][0] that is first col = 0;

        2 ) if sum != 0 and we are considering 0 coins, its not possible to get the sum.
           Mathematically, as its impossible, we will fill table[0][i] = IntMAx-1
        The reason for -1 is to avoid overflow which will be clear from code

        The inbetween case are same as knapsack, either add it or disgard it. Here taking it
        will means doing +1 , cause we are considering NUMBER of coins.
*/



public class No_10_Coin_Change_Min_Num_Of_Coins {
    static int[][] table;

    static int unboundedKnapSack(int[] coins,int n,int sum){
        if(sum == 0){
            table[n][sum] = 0;
            return table[n][sum];
        }
        else if(n == 0){
            table[n][sum] = Integer.MAX_VALUE - 1;
            return table[n][sum];
        }
        else{
            if(table[n][sum] != -1){
                return table[n][sum];
            }
            else{
                if(coins[n-1] > sum){
                    table[n][sum] = unboundedKnapSack(coins,n-1,sum);
                    return table[n][sum];
                }
                else{
                    int include = unboundedKnapSack(coins,n,sum - coins[n-1]) + 1;
                    int exclude = unboundedKnapSack(coins,n-1,sum);
                    table[n][sum] = Math.min(include,exclude);
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


         int minCoins = unboundedKnapSack(coins,n,sum);

        System.out.println(minCoins);
    }
}
