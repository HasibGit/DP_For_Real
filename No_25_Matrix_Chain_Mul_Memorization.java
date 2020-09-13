
import java.util.*;
import java.io.*;
public class No_25_Matrix_Chain_Mul_Memorization {

    static int[][] table;

    static int solve(int[] arr,int i,int j){
        if(i >= j){
            return 0;
        }
        else{
            if(table[i][j] != -1){
                return table[i][j];
            }
            else{
                int min = Integer.MAX_VALUE; // For each portion, we have to cal. min value. Thats y new copy of min for each recursive call
                for(int k = i;k <= j-1;k++){
                    int temp = solve(arr,i,k) + solve(arr,k+1,j) + (arr[i-1] * arr[k] * arr[j]);
                    min = Math.min(min,temp);
                }
                return table[i][j] = min;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int noOfMatrix = input.nextInt();

        int[] arr = new int[noOfMatrix+1];

        for(int i = 0;i < arr.length;i++){
            arr[i] = input.nextInt();
        }

        table = new int[arr.length][arr.length];

        for(int i = 0;i < arr.length;i++){
            for(int j = 0;j < arr.length;j++){
                table[i][j] = -1;
            }
        }


        int ans = solve(arr,1,arr.length-1);

        System.out.println(ans);

    }
}
