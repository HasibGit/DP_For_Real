/*
      As input, we are gonna be given an input,
        S = "appappqrs"

      We can partition this string into as many strings as we want.

      We have to cal. min number of partition we need to make So that every string is a
      palindrome.

      This problem is an MCM varient.

      How?
       -> To find min number of partitions needed, we are gonna have to test for all inbetween parts.
         like, test for appa|ppqrs
                        ap|pa|ppqrs
          Basically between a range, we have to cal. min value for all situations and record the min one
          between all of em.

      So, lets get to MCM pattern.,
             appappqrs
             012345678
       => Determine i and j.
          if i = 0, no issues. 0 will be one partition, (1-8) could be another
          if j = s.len no issues.

          So, i = 0, j = s.len

       => Determine base case
          -if we have empty string, i = j and we don't need any partition ret 0
          - if i > j, len = j - i + 1 < 0, So no string, ret 0,
          -if i = j, Only on char, so its already palindrome.

        So... base case,
            if(i >= j)
               ret 0;

            Also, check if the curr string is a palindrome or not. If so, ret 0.

            if(checkPalindrome(s,i,j))
                ret 0;


       =>  Determine loop for k

           - can K be equals to i? Yes, solve(s,i,k) solve(s,k+1,j)
           - can k be equals to j? No, k+1 will be outside the string.
           But k can be till j-1.

        => Cal. temp value

             temp = solve(s,i,k) + solve(s,k+1,j) + 1
                    ** 1 because we are dividing it in pos K. The two other solve func. will
                       cal. rest of the required partitions.

             And, we just have to record the minimum temp.
          -

*/



import java.util.*;
import java.io.*;
public class No_26_Palindrome_Partitioning_Recursive {


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

        else if(isPalindrome(arr,i,j))
            return 0;

        else{
            int min = Integer.MAX_VALUE;

            for(int k = i;k <= j-1;k++){
                int temp = solve(arr,i,k) + solve(arr,k+1,j) + 1;
                min = Math.min(min,temp);
            }
            return min;
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        char[] arr = s.toCharArray();

        int ans = solve(arr,0,arr.length-1);

        System.out.println(ans);
    }
}
