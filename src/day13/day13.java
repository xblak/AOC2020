package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day13 {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        File inputFile = new File("src\\day13\\input.txt");
        Scanner fileScanner = new Scanner(inputFile);
        int time = fileScanner.nextInt();
        String[] input = fileScanner.next().split(",");
        part1(time, input);
        part2(input);
        System.out.println("Run time: " + (System.currentTimeMillis() - startTime)); //runtime
    }

    public static void part1(int time, String[] input){
        int res = time;
        int id = 0;
        for(String s : input){
            if(s.equals("x")) continue;
            int wait = Integer.parseInt(s) - (time % Integer.parseInt(s));
            if(wait < res) {
                id = Integer.parseInt(s);
                res = wait;
            }
        }
        System.out.println(res * id); // return wait * bus id
    }

    public static void part2(String[] input){
        boolean go = true;
        long factor = Integer.parseInt(input[0]);
        long res = factor;
        while(go){
            for(int i = 1; i < input.length; i++) {
                if(input[i].equals("x")) continue;
                while((res + i)%Integer.parseInt(input[i])!=0) res += factor; //find new base
                factor *= Integer.parseInt(input[i]); //get new factor
                if(i == input.length-1) {
                    go = false; //break outer loop
                    System.out.println(res);
                }
            }
        }
    }
}
