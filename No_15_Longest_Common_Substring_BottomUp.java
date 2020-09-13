import java.util.*;
import java.io.*;
public class No_15_Longest_Common_Substring_BottomUp {
    public static void main(String[] args) {
       Scanner input =  new Scanner(System.in);

       String a = input.nextLine();
       String b = input.nextLine();

       char[] arr = a.toCharArray();
       char[] arr2 = b.toCharArray();

       int n = arr.length;
       int m = arr2.length;

       int[][] table = new int[n+1][m+1];

       for(int i = 0;i <= n;i++){
           table[i][0] = 0;
       }

       for(int i = 0;i <= m;i++){
           table[0][i] = 0;
       }

       for(int i = 1;i <= n;i++){
           for(int j = 1;j <= m;j++){
               if(arr[i-1] == arr2[j-1]){
                   table[i][j] = table[i-1][j-1] + 1;
               }
               else{
                   table[i][j] = 0;
               }
           }
       }

        System.out.println(table[n][m]);
    }
}
