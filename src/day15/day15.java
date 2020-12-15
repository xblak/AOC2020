package day15;

import java.util.HashMap;

public class day15 {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        int[] input = {0,6,1,7,2,19,20};
        // hashmap value, index, save the last occurrence
        HashMap<Integer,Integer> all = new HashMap<Integer,Integer>();
        for(int i = 0; i < input.length-1; i++){
            all.put(input[i],i+1); // put starting numbers, leave the last one
        }
        int next;
        int cur = input[input.length-1]; // start from the last starting number
        for(int i = input.length; i < 30000000; i++){
            next = all.containsKey(cur) ? i - all.get(cur) : 0; // found return cur index - last occurrence index, otherwise 0
            all.put(cur,i); // put current into hash
            cur = next; // move to next
            if(i == 2019) System.out.println("Part1: " + next); // print 2020th
            if(i == 30000000-1) System.out.println("Part2: " + next); // print 30000000th
        }
        System.out.println("Run time: " + (System.currentTimeMillis() - startTime) + "ms"); //runtime
    }
}
