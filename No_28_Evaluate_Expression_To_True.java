import java.util.*;
import java.io.*;
public class No_28_Evaluate_Expression_To_True {

    /**
     * So input will be like
     * [T, OR, F, AND, T, XOR, F]
     * Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
     * ((T OR F) AND (T XOR F)) -----> this is how we can group them and it will return to true.
     * ((T OR F AND T) XOR (F)) -----> this is another way.
     *
     * This is somewhat similar to Matrix Chain Multiplication where we had to group the input dimensions in a certain manner.
     *
     * symbol[]    = {T, F, T}
     * operator[]  = {^, &}
     *
     * So our expression will become ==> [T ^ F & T] and we have to find number of ways.
     *
     * 4 step approach for Matrix Chain Multiplication related problems.
     * 1) Find i and j ====> it can be i=0; and j = len()-1;
     * 2) Find Base Condition :
     *          Before Base condition one important twist in this question.
     *
     *          Assume Question is :  [T ^ F]
     *          Now we know T ^ T = false
     *                      T ^ F = true
     *                      F ^ T = true
     *                      F ^ F = false.
     *                      So we can have false on either side.
     * Hence in sub-problem we might also want to find false records in both left and right because they all will contribute
     * to becoming true.
     *
     *                      2 (false) XOR (4 true).
     *
     *                      Now if i match any false with any true, i will get true, hence we need to solve for both
     *                      true and false in our sub-problems.
     *      solve(arr, i, j, isTrue)
     *       // Base Condition
     *       if(i > j) return 0;
     *       it(i==j) {
     *          if(isTrue) {
     *              if(arr[i] == true) return 1;
     *              return 0;
     *          } else {
     *              // VICE VERSA.
     *          }
     *       }
     *
     * 3) Choose K
     *          since operators are always sandwiched between T and False, so we will start k = 1
     *          and do k+=2 every iteration.
     *
     */




    static int solve(char[] arr,int i,int j,boolean required){
        if(i > j){
            return 0;
        }
        if(i == j){
            if(required == true){
                if(arr[i] == 'T'){
                    return 1;
                }
                else{
                    return 0;
                }
            }
            else{
                if(arr[i] == 'F')
                    return 1;
                else
                    return 0;
            }
        }

        else{
            int ans = 0;

            for(int k = i+1;k < j;k += 2){
                int leftTrue = solve(arr,i,k-1,true);
                int rightTrue = solve(arr,k+1,j,true);
                int leftFalse = solve(arr,i,k-1,false);
                int rightFalse = solve(arr,k+1,j,false);

                if(arr[k] == '&'){
                    if(required == true){
                        ans = ans + (leftTrue * rightTrue);
                    }
                    else{
                        ans = ans + (leftTrue * rightFalse) + (leftFalse * rightTrue) + (leftFalse * rightFalse);
                    }
                }

                else if(arr[k] == '|'){
                    if(required == true){
                        ans = ans + (leftTrue * rightTrue) + (leftTrue * rightFalse) + (leftFalse * rightTrue);
                    }
                    else{
                        ans = ans + (leftFalse * rightFalse);
                    }
                }

                else if(arr[k] == '^'){
                    if(required == true){
                        ans = ans + (leftFalse * rightTrue) + (leftTrue * rightFalse);
                    }
                    else{
                        ans = ans + (leftTrue * rightTrue) + (leftFalse * rightFalse);
                    }
                }
            }
            return ans;
        }

    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String s = input.nextLine();
        char[] arr = s.toCharArray();

        int ans = solve(arr,0,arr.length-1,true);

        System.out.println(ans);
    }
}
