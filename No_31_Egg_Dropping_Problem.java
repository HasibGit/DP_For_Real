/*
    Another MCM varient problem

    Input : No of eggs and No of floors

    Output : Our target is to find the highest floor from where we can drop the egg
    and the egg won't break. We have to cal. the minimum number of attempts required
    to find such floor in worst case.

 */


import java.util.*;
import java.io.*;

public class No_31_Egg_Dropping_Problem {

    static int[][] table;

    static int solve(int eggs,int floors){

        // base case
        if(floors == 0 || floors == 1){
            return floors;
        }
        if(eggs == 1){
            // in worst case, we might have to check all the floors
            return floors;
        }

        if(table[eggs][floors] != -1)
            return table[eggs][floors];

        else{
            int min = Integer.MAX_VALUE;

            for(int k = 1;k <= floors;k++){
            /*
                After dropping an egg from a floor, there can be two events.
                Egg broke and egg didn't break.
                If egg breaks, we check for upper floor.
                else, we check for lower floors.
                We do this recursively and cal. min number of attempts in worst case,
            */

                int temp = 1 + Math.max(solve(eggs-1,k-1), solve(eggs,floors-k));
                min = Math.min(min,temp);
            }
            return table[eggs][floors] = min;
        }

    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int eggs = input.nextInt();
        int floors = input.nextInt();

        table = new int[eggs+1][floors+1];

        for(int i = 0;i <= eggs;i++){
            for(int j = 0;j <= floors;j++){
                table[i][j] = -1;
            }
        }

        int ans = solve(eggs,floors);

        System.out.println(ans);

    }
}
