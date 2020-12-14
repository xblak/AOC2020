package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class day14 {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        File inputFile = new File("src\\day14\\input.txt");
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<String> input = new ArrayList<String>();
        while (fileScanner.hasNextLine()) {
            input.add(fileScanner.nextLine());
        }
        part1(input);
        part2(input);
        System.out.println("Run time: " + (System.currentTimeMillis() - startTime) + "ms"); //runtime
    }

    public static void getSum(HashMap<Object, Long> mem) { // get the sum of all values in mem
        long sum = 0;
        for (long f : mem.values()) {
            sum += f;
        }
        System.out.println(sum);
    }

    public static void part1(ArrayList<String> input) {
        String mask = "";
        HashMap<Object, Long> mem = new HashMap<Object, Long>();
        for (String s : input) {
            if (s.startsWith("mask")) {
                mask = s.substring(7); // length will be 36
            } else if (s.startsWith("mem")) {
                int index = Integer.parseInt(s.substring(s.indexOf('[') + 1, s.indexOf(']')));
                long value = Long.parseLong(s.substring(s.lastIndexOf(' ') + 1));
                String cur = Long.toBinaryString(value); // convert value to binary string
                StringBuilder res = new StringBuilder("");
                for (int i = 0; i < 36; i++) {
                    if (mask.charAt(i) == 'X') {
                        if (i < 36 - cur.length()) res.append(0); // if x, add 0 if value don't have a digit
                        else res.append(cur.charAt(i - 36 + cur.length())); // add unchanged digit from value
                    } else {
                        res.append(mask.charAt(i)); // overwrite if 0 or 1
                    }
                }
                value = Long.parseLong(res.toString(), 2); // convert res back to decimal
                mem.put(index, value); // put a new value in mem[index]
            }
        }
        getSum(mem);
    }

    public static void part2(ArrayList<String> input) {
        String mask = "";
        HashMap<Object, Long> mem = new HashMap<Object, Long>();
        for (String s : input) {
            if (s.startsWith("mask")) {
                mask = s.substring(7); // length will be 36
            } else if (s.startsWith("mem")) {
                long index = Long.parseLong(s.substring(s.indexOf('[') + 1, s.indexOf(']')));
                long value = Long.parseLong(s.substring(s.lastIndexOf(' ') + 1));
                String cur = Long.toBinaryString(index); // convert index to binary string
                StringBuilder res = new StringBuilder("");
                ArrayList<Integer> allX = new ArrayList<Integer>(); // keep the power of all X in mask
                for (int i = 0; i < 36; i++) {
                    if (mask.charAt(i) == 'X') {
                        res.append(0);
                        allX.add(35-i); // save x into a list
                    } else if(mask.charAt(i) == '0'){
                        if (i < 36 - cur.length()) res.append(0); // if x, add 0 if value don't have a digit
                        else res.append(cur.charAt(i - 36 + cur.length())); // add unchanged digit from value
                    } else {
                        res.append(1); // overwrite if 1
                    }
                }
                index = Long.parseLong(String.valueOf(res), 2); // get the base index (all 'X' = 0)
                writeMem(allX, mem, index, value, 0); // write at all possible mem location
            }
        }
        getSum(mem);
    }

    public static void writeMem(ArrayList<Integer> allX, HashMap<Object, Long> mem, long index, long value, int i){
        if(i == allX.size()-1) { // put value to two index (x = 0 or x = 1 for last x) if went over all Xs. 
            mem.put(index+(long)Math.pow(2, allX.get(i)), value);
            mem.put(index, value);
        }else{ // x = 0 or x = 1 for current x
            writeMem(allX, mem, index+(long)Math.pow(2, allX.get(i)), value, i+1);
            writeMem(allX, mem, index, value, i+1);
        }
    }
}