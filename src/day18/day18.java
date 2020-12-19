package day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day18 {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        File inputFile = new File("src\\day18\\input.txt");
        Scanner fileScanner = new Scanner(inputFile);
        long part1Res = 0, part2Res = 0;
        while (fileScanner.hasNextLine()) {
            String cur = fileScanner.nextLine();
            part1Res += lineResult(cur, 1); // mode 1
            part2Res += lineResult(cur, 2); // mode 2
        }
        System.out.println(part1Res);
        System.out.println(part2Res);
        System.out.println("Run time: " + (System.currentTimeMillis() - startTime) + "ms"); //runtime
    }

    // handle all ()
    public static long lineResult(String s, int mode){
        StringBuilder sb = new StringBuilder(s);
        while(sb.indexOf("(") != -1) { // calculate all the ()
            int start = sb.lastIndexOf("("); // find last of (
            int end = sb.indexOf(")", start); // find the first ) after last (, the last pair
            // replace (...) to result
            if(mode == 1) sb.replace(start, end + 1, "" + getResult(sb.substring(start + 1, end)));
            else sb.replace(start, end + 1, "" + getResult2(sb.substring(start + 1, end)));
        }
        return mode == 1 ? getResult(sb.toString()) : getResult2(sb.toString()); // result of everything else, no () now
    }

    // handle + * left to right
    public static long getResult(String s){
        String[] input = s.split(" ");
        long res = Long.parseLong(input[0]);
        for(int i = 1; i < input.length; i++){
            if(input[i].equals("*")){
                res *= Long.parseLong(input[++i]); // times the number at i+1
            }else{
                res += Long.parseLong(input[++i]); // add the number at i+1
            }
        }
        return res;
    }

    // handle + *, prioritize +
    public static long getResult2(String s){
        LinkedList<String> input = new LinkedList<String>(Arrays.asList(s.split(" "))); // linkedlist for fast remove
        // do all + first
        for(int i = 1; i < input.size(); i++){
            if(input.get(i).equals("+")){
                // save the sum to previous, remove two slots, move index back by 1.
                input.set(i-1, Long.parseLong(input.get(i-1)) + Long.parseLong(input.get(i+1)) + "");
                input.remove(i);
                input.remove(i--);
            }
        }
        // do * after
        long res = Long.parseLong(input.get(0));
        for(int i = 1; i < input.size(); i++){
            if(input.get(i).equals("*")){
                res *= Long.parseLong(input.get(++i)); // times the number at i+1
            }
        }
        return res;
    }
}
