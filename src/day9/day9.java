package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day9 {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        File inputFile = new File("src\\day9\\input.txt");
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<Long> input = new ArrayList<Long>();
        while (fileScanner.hasNext()) {
            input.add(fileScanner.nextLong());
        }
        long firstNum = findPart1(input);
        System.out.println("First Number: " + firstNum);
        findPart2(0,0,input.get(0), firstNum, input);
        System.out.println(System.currentTimeMillis() - startTime);
    }
    public static long findPart1(ArrayList<Long> input){
        long cur = -1; //return -1 if input too short
        for(int i = 25; i < input.size(); i++){
            cur = input.get(i);
            boolean find = false;
            for(int j = i - 25; j < i; j++){
                long jnum = input.get(j);
                find = false;
                for(int k = j + 1; k < i; k++){
                    if(cur == jnum + input.get(k)) {
                        find = true;
                        break;
                    }
                }
                if(find) break;
            }
            if(!find) break;
        }
        return cur;
    }
    public static void findPart2(int first, int last, Long sum, Long target, ArrayList<Long> input){
        if(sum.equals(target)){
            long[] arr = new long[last-first+1];
            for(int i = 0; i < last-first+1; i++){
                arr[i] = input.get(first+i);
            }
            Arrays.sort(arr); //find smallest and biggest
            System.out.println("Weakness: " + (arr[0] + arr[last-first]));
        }
        else if(last < input.size()-1 && sum < target){
            findPart2(first, last+1, sum + input.get(last+1), target, input); //add one to the back
        }else if(sum > target){
            findPart2(first+1, last, sum - input.get(first), target, input); //delete one from the front
        }
    }
}
