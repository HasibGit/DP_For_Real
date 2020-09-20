/*

   MCM varient
   Problem description :
     https://www.geeksforgeeks.org/check-if-a-string-is-a-scrambled-form-of-another-string/

 */


import java.util.*;
import java.io.*;
public class No_30_Scrambled_Strings {

    static Map<String,Boolean> map = new HashMap<>();

    static boolean solve(String s1,String s2){

           // base case
           if(s1.equals(s2)){
               return true;
           }
           if(s1.length() <= 1){
               //  s1 not equals s2 has been checked
               return false;
           }

           String temp = s1 + " " + s2;

           if(map.get(temp) != null){
               return map.get(temp);
           }
           else{
               boolean con1 = false;
               boolean con2 = false;

               int n = s1.length();

               for(int i = 1;i < n;i++){
                   // empty string not allowed in tree

                   // without switching
                   if(solve(s1.substring(0,i),s2.substring(0,i)) && solve(s1.substring(i,n),s2.substring(i,n))){
                       con1 = true;
                       break;
                   }

                   // when two parts are switched

                   if(solve(s1.substring(0,n-i),s2.substring(i,n)) && solve(s1.substring(n-i,n),s2.substring(0,i))){
                       con2 = true;
                       break;
                   }
               }

               boolean scrambled;

               if(con1 || con2){
                   scrambled = true;
               }
               else{
                   scrambled = false;
               }

               map.put(temp,scrambled);
               return scrambled;

           }
    }

    public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
         String s1 = input.nextLine();
         String s2 = input.nextLine();
         char[] arr = s1.toCharArray();
         char[] arr2 = s2.toCharArray();

         if(s1.length() != s2.length()){
             System.out.println("False");
         }
         else if(s1.isEmpty() && s2.isEmpty()){
             System.out.println("True");
         }

         else{
             System.out.println(solve(s1,s2));
         }
    }
}
