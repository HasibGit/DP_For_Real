import java.util.*;
import java.io.*;

/*
      Here also, we are given some items
      each item has a value and a weight
      and, we have a sack with a certain capacity.

      But, in zero/one knapsack, each item had only one occurance and,
      we had two choices, either include it, or not include it.

      But in unbounded knapsack, each item has an infinite supply. That is,
      it is possible to take the same item multiple times.

      Now we want to maximize our profit and also make sure sack does not overflow.

      So, the minor diff with 0/1 knapsack and unbounded knapsack is,

      -> if we can't take this item( because of weight ), we exclude this item and recall
      for the remaining items ( same as 0/1)

      -> But if we can include this item, we have 2 choices,
        1 )  exclude it
        2 ) include it and, as we can take it again, rather than calling knapsack
        for n-1 items, we again call for n items.
 */

public class No_7_Unbounded_KnapSack {

    static int[][] table;


    static int unboundedKnapSack(int[] weight,int[] value,int items,int bagCapacity){
        if(items == 0 || bagCapacity == 0){
            table[items][bagCapacity] = 0;
            return table[items][bagCapacity];
        }
        else{
            if(table[items][bagCapacity] != -1){
                return table[items][bagCapacity];
            }
            else{
                if(weight[items-1] > bagCapacity){
                    return table[items][bagCapacity] = unboundedKnapSack(weight,value,items-1,bagCapacity);
                }
                else{
                    int include = value[items-1] + unboundedKnapSack(weight,value,items,bagCapacity-weight[items-1]);
                    /*
                       rather than calling for items-1, we called for items again.
                       because it is allowed to take this item again if possible.
                    */
                    int exclude = unboundedKnapSack(weight,value,items-1,bagCapacity);
                    return table[items][bagCapacity] = Math.max(include,exclude);
                }
            }
        }
    }


    public static void main(String[] args) {
          Scanner input = new Scanner(System.in);

          int n = input.nextInt();

          int[] weight = new int[n];
          int[] value = new int[n];


          for(int i = 0;i < n;i++)
              weight[i] = input.nextInt();

          for(int i = 0;i < n;i++)
              value[i] = input.nextInt();

          int bagCapacity = input.nextInt();

          table = new int[n+1][bagCapacity+1];

          for(int i = 0;i <= n;i++){
              for(int j = 0;j <= bagCapacity;j++){
                  table[i][j] = -1;
              }
          }

          int maxProfit = unboundedKnapSack(weight,value,n,bagCapacity);

        System.out.println(maxProfit);

    }
}
