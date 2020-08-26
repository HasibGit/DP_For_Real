/*
      we are given an array arr
      we have to divide it into two subsets such that
        the difference of their sum is minimum'

      Ex : {1,2,7};
        {1,2} {7}  Diff = 4

      Now suppose we divide arr into two partitions p1 and p2;
      p1 has the smaller sum, its S1;

      So, the other part would be, Range - S1  (range = sum of whole array);

      So, the difference would be, (Range - S1) - S1 = Range - 2S1;

      So, we have to find S1 such that Range - 2S1 is minimized.

      Now, subset sum will be of range, 0 to Range

      for {1,2,7}

      the valid sums are : 0{null}, 1, 2, 3 , 7, 8 , 9 , 10

      Now S1 will not cross Range / 2 = 10 / 2 = 5;
        because S1 is the smaller partition sum.

      SO, for finding S1, we will consider list{0,1,2,3};
      Now its just a matter of finding
          min = Min ( min , Range - 2 * list[i] ) ;

 */


import java.util.*;
import java.io.*;
public class No_5_Minimum_Subset_Sum_Difference {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int[] arr = new int[n];

        int sum = 0;

        for(int i = 0;i < n;i++){
            arr[i] = input.nextInt();
            sum += arr[i];
        }

        boolean[][] table = new boolean[n+1][sum + 1];

        // if sum = 0, all true

        for(int i = 0;i <= n;i++){
            table[i][0] = true;
        }

        // n = 0 && sum != 0 all false

        for(int i = 1;i <= sum;i++){
            table[0][i] = false;
        }

        for(int item = 1;item <= n;item++){
            for(int total = 1;total <= sum;total++){
                if(arr[item-1] > total){
                    table[item][total] = table[item-1][total];
                }
                else{
                    table[item][total] = (table[item-1][total - arr[item-1]]) || (table[item-1][total]);
                }
            }
        }


        // considering all the items what are the sums we can get

        int range = sum / 2;

        ArrayList<Integer> list =  new ArrayList<>();

        for(int total = 0; total <= range;total++){
            if(table[n][total]){
                list.add(total);
            }
        }

        // now check which S1 gives min diff

        int min = Integer.MAX_VALUE;

        for(int i : list){
            if(sum - (2 * i) <= min){
                min = sum - (2 * i);
            }
        }
        System.out.println(min);

    }
}
