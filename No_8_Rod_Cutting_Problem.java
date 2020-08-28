import java.util.*;
import java.io.*;

/*
        Input :
         We have a rod of size = Len
         We have an array length[] which specifies different cuts like 1,4,6,8
         we have a price[] array which specifies price of those cuts   6,8 9 2

        We have to cut the rod in such a way that our profit is maximized.

        We can apply the same cut again if possible.
        Like if len is 4, we can apply 2,2 or,   1 1 1 1 or 1,1,2

        This problem is same as unbounded knapsack.
        unbounded knapsack                  rod cutting problem
           cap                    =              Len
           weight[]               =              Length[]
           value[]                =              price[]
           maximize profit        =              maximize profit


        So, for each cut, if we can acually cut it,( Len >= cut)
            we can choose to cut it and not cut it.

         if we cut it, we can use that cut again if possible

         Code is exactly same as unbounded knapsack.

*/

public class No_8_Rod_Cutting_Problem {

    static int[][] table;


    static int unboundedKnapsack(int[] cuts,int[] price,int rodLength, int n){
             if(n == 0 || rodLength == 0){
                 table[n][rodLength] = 0;
                 return table[n][rodLength];
             }
             else{
                 if(table[n][rodLength] != -1){
                     return table[n][rodLength];
                 }
                 else{
                     if(cuts[n-1] > rodLength){
                         return table[n][rodLength] = unboundedKnapsack(cuts,price,rodLength,n-1);
                     }
                     else{
                         int include = price[n-1] + unboundedKnapsack(cuts,price,rodLength - cuts[n-1],n);
                         int exclude = unboundedKnapsack(cuts,price,rodLength,n-1);

                         return table[n][rodLength] = Math.max(include,exclude);
                     }
                 }
             }
    }

    public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
         int rodLength = input.nextInt();

         int n = input.nextInt();

         int[] cuts = new int[n];
         int[] price = new int[n];

         for(int i = 0;i < n;i++){
             cuts[i] = input.nextInt();
         }

         for(int i = 0;i < n;i++){
             price[i] = input.nextInt();
         }

         table = new int[n+1][rodLength+1];

         for(int i = 0;i <= n;i++){
             for(int j = 0;j <= rodLength;j++){
                 table[i][j] = -1;
             }
         }

         int maxProfit = unboundedKnapsack(cuts,price,rodLength,n);

        System.out.println(maxProfit);
    }
}
