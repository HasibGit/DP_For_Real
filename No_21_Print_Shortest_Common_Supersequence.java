/*
       Difference with printing lcs :
         -> if both are same, include ( same )
         -> if both are not same
               if we move up, include left
               if we move left, include up
            Because.., as we know, in scs, both s1 and s2 must remain in scs as subsequence.
         -> if any string becomes null, and another one is not null, include the other one
            in scs.
*/



import java.util.*;
import java.io.*;
public class No_21_Print_Shortest_Common_Supersequence {

    static int[][] table;

    static int lcs(char[] arr,char[] arr2,int n,int m){
        if(n == 0 || m == 0){
            table[n][m] = 0;
            return table[n][m];
        }
        else{
            if(table[n][m] != -1){
                return table[n][m];
            }
            else{
                if(arr[n-1] == arr2[m-1]){
                    table[n][m] = 1 + lcs(arr,arr2,n-1,m-1);
                    return table[n][m];
                }
                else{
                    table[n][m] = Math.max(lcs(arr,arr2,n,m-1),lcs(arr,arr2,n-1,m));
                    return table[n][m];
                }
            }

        }
    }


    static void printScs(char[] arr,char[] arr2,int n,int m){
        ArrayList<Character> list = new ArrayList<>();
        while (n > 0 && m > 0){
            if(arr[n-1] == arr2[m-1]){
                list.add(arr[n-1]);
                n--;
                m--;
            }
            else{
                if(table[n-1][m] >= table[n][m-1]){
                    list.add(arr[n-1]);
                    n--;
                }
                else{
                    list.add(arr2[m-1]);
                    m--;
                }
            }
        }
        while (n > 0){
            list.add(arr[n-1]);
            n--;
        }
        while (m > 0){
            list.add(arr2[m-1]);
            m--;
        }
        Collections.reverse(list);
        for(char c : list){
            System.out.print(c);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s1 = input.nextLine();
        String s2 = input.nextLine();

        char[] arr = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        int n = arr.length;
        int m = arr2.length;

        table = new int[n+1][m+1];

        for(int i = 0;i <= n;i++){
            for(int j = 0;j <= m;j++){
                table[i][j] = -1;
            }
        }

        lcs(arr,arr2,n,m);

        printScs(arr,arr2,n,m);
    }
}
