
import java.util.*;
import java.io.*;
public class No_29_Evaluate_Expression_To_True_Memorization {

    static Map<String,Integer> map = new HashMap<String,Integer>();

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
            StringBuffer temp = new StringBuffer();
            temp.append(Integer.toString(i));
            temp.append(Integer.toString(j));
            temp.append(Boolean.toString(required));
            String tempString = temp.toString();

            if(map.get(tempString) == null){
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
                map.put(tempString,ans);
                return ans;
            }
            else{
               return map.get(tempString);
            }

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
