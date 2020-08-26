import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

public class No_1_Zero_One_Knapsack {


    static int[][] table;
    /*
       when we are calling our recursive func, which values are actually changing?
          bacCapacity and items
       for a certaining bagCapacity and items, we are calculating the best profit we can do.
       So we can memorize this infomation in the table matrix, and thats whats we are gonna do.
     */


    static int knapSack(int[] weights, int[] values, int bagCapacity, int items){

        // base case
        if(items == 0 || bagCapacity == 0)
            return 0;

        else{

            if(table[items][bagCapacity] != -1){
                return table[items][bagCapacity];
            }

            else{
                if(weights[items] > bagCapacity){
                    // cant add this to the bag
                    return table[items][bagCapacity] = knapSack(weights,values,bagCapacity,items-1);

                    // just ignore this item and recurr for the rest ones we got
                }

                else{
                    // we can add this item if we want
                    // but we don't now if it will be optimal yet
                    // so we get results for both options and return the max one

                    int included = values[items] + knapSack(weights,values,bagCapacity - weights[items],items-1);


                    int notIncluded = knapSack(weights,values,bagCapacity,items-1);


                    return table[items][bagCapacity] = Math.max(included,notIncluded);


                }
            }

        }
    }



    public static void main(String[] args) {
        FastReader input = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);

        /*
             - Input : you are given some items
                     -> each item has some weight and some value
                     -> you have a bag of weight W
                     ->I need to fill my bag with the items in such a way that
                       - profit is maximized
                       - total weight of items that i pick does not cross W

         */


         int items = input.nextInt();

         int bagCapacity = input.nextInt();

         table = new int[items+1][bagCapacity+1];

         for(int i = 0;i <= items;i++){
             for(int j = 0;j <= bagCapacity;j++){
                 table[i][j] = -1;
             }
         }


         int[] weights = new int[items+1];
         int[] values = new int[items+1];


         for(int i = 1;i <= items;i++){
             weights[i] = input.nextInt();
         }

         for(int i = 1;i <= items;i++){
             values[i] = input.nextInt();
         }

         /*
              Base case : (think of the min input case)
                 - if my bag capacity is 0, then i cant add anything to the bag. So profit is 0;
                 - if i don't have any items, cant gain anything so profit is 0

              So if items = 0 OR capacity = 0
                  return 0;


              Choice Diagram : Now think of the choices we can make for each item

                - if weight of the item is greater than capacity
                     we can't add this item to our bag
                - if weight <= capacity
                    we have 2 options
                       either include it in the bag
                       don't include it in the bag

              Based on  these things , we will design the recursive function
          */


         int maxProfit = knapSack(weights,values,bagCapacity,items);

         pw.println("Max Profit : " + maxProfit);



        // ****If sorting is required, use ArrayList
        // *** If string concatenation is required, use StringBuffer
        // ** check for overflow
        // ** Check for case with min value
        // ** check for case with max value

        pw.flush();
        pw.close();
    }

    static void sort(int[] arr){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i : arr)
            list.add(i);
        Collections.sort(list);
        for(int i = 0;i < list.size();i++) {
            arr[i] = list.get(i);
        }
    }

    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
}
