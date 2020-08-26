/*
      we will be given an array arr
      we have to determine if we can divide arr into two subsets where
        - the total sum of each subset will be equal
      EX :
            5 6 11 0 -> {5,6} {11,0}

      Observation 1 : lets suppose, the two subsets are S1 and S2 and their sum is S
      so, the total sum of the array will be, S + S = 2 * S;
      2 * S... this is always an even number

      So, if the total sum of the array is not even, then we cant partition it into two equal sums.


      Step 2 :
         sum = total sum of the array (even)

         now we need to determine if we can find a subset where total of subset will be sum / 2;
         That's how problem got reduced to subset sum problem.

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;

public class No_3_Equal_Sum_Partition {
    public static void main(String[] args) {
        FastReader input = new FastReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = input.nextInt();
        int[] arr = new int[n];

        int sum = 0;
        for(int i = 0;i < n;i++){
            arr[i] = input.nextInt();
            sum += arr[i];
        }

        if(sum % 2 != 0){
            pw.println("Not possible");
        }
        else{
            sum = sum / 2;

            boolean[][] table = new boolean[n+1][sum+1];

            // if sum = 0 all true
            for(int items = 0;items <= n;items++){
                table[items][0] = true;
            }

            // if items is 0 and sum > 0 all false

            for(int total = 1;total <= sum;total++){
                table[0][total] = false;
            }


            for(int item = 1;item <= n;item++){
                for(int total = 1;total <= sum;total++){
                    if(arr[item - 1] > total){
                        table[item][total] = table[item-1][total];
                    }
                    else{
                        table[item][total] = (table[item-1][total - arr[item-1]]) || (table[item-1][total]);
                    }
                }
            }


            if(table[n][sum]){
                pw.println("Possible");
            }
            else{
                pw.println("Impossible");
            }


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

