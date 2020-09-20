import java.util.Scanner;

public class No_27_Palindrome_Partitioning_Memorization {


    static int[][] table;

    static boolean isPalindrome(char[] arr,int start,int end){
        boolean con = true;
        for(int i = start,j = end;i < j;i++,j--){
            if(arr[i] != arr[j]){
                con = false;
                break;
            }
        }
        return con;
    }

    static int solve(char[] arr,int i,int j){
        if(i >= j)
            return 0;

        else if(table[i][j] != -1)
            return table[i][j];

        else if(isPalindrome(arr,i,j))  // This is an optimization. If the val is present
                                       // in the table, we don't need to call isPalindrome func.
            return 0;


        else{
            int min = Integer.MAX_VALUE;

            for(int k = i;k <= j-1;k++){
                int temp = solve(arr,i,k) + solve(arr,k+1,j) + 1;
                min = Math.min(min,temp);
            }
            return table[i][j] = min;
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        char[] arr = s.toCharArray();

        table = new int[arr.length][arr.length];

        for(int i = 0;i < arr.length;i++){
            for(int j = 0;j < arr.length;j++){
                table[i][j] = -1;
            }
        }

        int ans = solve(arr,0,arr.length-1);

        System.out.println(ans);
    }
}
