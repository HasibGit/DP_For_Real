import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

public class No_2_Subset_Sum_Problem {


    static boolean[][] table;


    public static void main(String[] args) {
        FastReader input = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);


        /*
             -> we are given an array of numbers
             -> also we are given a number sum
             -> we have to determine if we can take a subset from the given array
                where their total would be equal to sum.
         */


        /*
            lets think of the base case
             if sum = 0, then we can always take an empty set. So its always true.

             if we are taking 0 elements from the array
                 if sum = 0, then its true
                 else it will be false.
         */

        /*
            Now time for choice diagram
            if including an element means total is getting greater than sum, then we cant include it

            but if total <= sum
              we have 2 options
                 either include it
                 or not include it
                 if any of those paths returns true
                   we will memorize true
                 else
                 it will be false
         */



        int n = input.nextInt();
        int[] arr = new int[n];


        for(int i = 0;i < n;i++){
            arr[i] = input.nextInt();
        }

        int sum = input.nextInt();

        table = new boolean[n+1][sum+1];



        // if sum = 0 && n = 0 then its true
        table[0][0] = true;

        // if sum = 0, for any n, its true

        for(int i = 1;i <= n;i++){
            table[i][0] = true;
        }

        // if n = 0 && sum != 0 its false
        for(int i = 1;i <= sum;i++){
            table[0][i] = false;
        }

        for(int item = 1;item <= n;item++){
            for(int total = 1;total <= sum;total++){
                if(arr[item-1] > total){
                    table[item][total] = table[item-1][total];
                }
                else{
                    /*
                        if we include the item total will be
                           total = total - arr[item-1]
                        the remaining total, is it achievable excluding the current item?
                           table[item-1] [ total - arr[item-1]]
                     */


                    table[item][total] = (table[item-1][total - arr[item-1]] || table[item-1][total]);
                }
            }
        }


        if(table[n][sum]){
            pw.println("Possible");
        }
        else{
            pw.println("Not Possible");
        }



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
        for(int i = 0;i < list.size();i++){
            arr[i] = list.get(i);
        }
        return;
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
