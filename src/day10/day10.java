package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day10 {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        File inputFile = new File("src\\day10\\input.txt");
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<Integer> input = new ArrayList<Integer>();
        input.add(0);
        while (fileScanner.hasNext()) {
            input.add(fileScanner.nextInt());
        }
        Collections.sort(input);
        int[] arr = new int[input.size()-1];
        int count1 = 0, count3 = 0;
        arr[arr.length-1] = 3; //difference, length-1
        for(int i = 0; i < input.size()-1; i++){
            int diff = input.get(i+1) - input.get(i);
            if(diff == 1) count1++;
            if(diff == 3) count3++;
            arr[i] = diff;
        }
        System.out.println("Part1: " + count1*count3);
        System.out.println("Part2: " + countArrangement(arr));
        System.out.println(System.currentTimeMillis() - startTime); //runtime
    }

    public static long countArrangement(int[] arr){
        long[] dp = new long[arr.length];
        //initialize first 2
        dp[0] = 1;
        if(arr[0]+arr[1]<=3){
            dp[1] = 2;
        }else{
            dp[1] = 1;
        }
        for(int i = 2; i < arr.length; i++){
            int range = 3 - arr[i]; //1 jump guaranteed
            long curCount = dp[i-1];
            range -= arr[i-1];
            if(range >= 0){ //2 jumps?
                curCount += dp[i-2];
            }
            if(i != 2 && range - arr[i-2] >= 0) { //3 jumps?
                curCount += dp[i - 3];
            }
            dp[i] = curCount; //save count
        }
        return dp[dp.length-1]; //return the last one
    }
}
