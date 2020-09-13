/*
   Explanation in khata
 */



import java.util.*;
import java.io.*;
public class No_24_Matrix_Chain_Multiplication_Recursive {


    static int solve(int[] arr,int i,int j){
        if(i >= j){
            return 0;
        }
        else{
            int min = Integer.MAX_VALUE; // For each portion, we have to cal. min value. Thats y new copy of min for each recursive call
            for(int k = i;k <= j-1;k++){
                int temp = solve(arr,i,k) + solve(arr,k+1,j) + (arr[i-1] * arr[k] * arr[j]);
                min = Math.min(min,temp);
            }
            return min;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int noOfMatrix = input.nextInt();

        int[] arr = new int[noOfMatrix+1];

        for(int i = 0;i < arr.length;i++){
            arr[i] = input.nextInt();
        }


        int ans = solve(arr,1,arr.length-1);

        System.out.println(ans);

    }
}
