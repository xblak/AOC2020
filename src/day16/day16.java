package day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class day16 {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        File inputFile = new File("src\\day16\\input.txt");
        Scanner fileScanner = new Scanner(inputFile);
        // part 1
        int[] checker = new int[1000];
        // part 2
        ArrayList<int[]> allChecker = new ArrayList();
        ArrayList<int[]> allTickets = new ArrayList();
        int[] myticket = new int[0];

        while (fileScanner.hasNextLine()) {
            String next = fileScanner.nextLine();
            if(next.equals("")) {
                continue;
            } else if(next.equals("your ticket:")) {
                String[] temp = fileScanner.nextLine().split(",");
                myticket = new int[temp.length];
                for(int i = 0; i < temp.length; i++) myticket[i] = Integer.parseInt(temp[i]);
            } else if (next.equals("nearby tickets:")){
                int sum = 0;
                while(fileScanner.hasNextLine()){
                    String[] temp = fileScanner.nextLine().split(",");
                    int[] cur = new int[temp.length];
                    for(int i = 0; i < temp.length; i++) cur[i] = Integer.parseInt(temp[i]);
                    boolean good = true;
                    for(int nums : cur){
                        if(checker[nums] == 0){
                            sum += nums;
                            good = false;
                        }
                    }
                    if(good) allTickets.add(cur); // add good tickets
                }
                System.out.println("Part 1: " + sum);
            } else {
                String[] cut = next.split(" ");
                String[] range1 = cut[cut.length-3].split("-");
                String[] range2 = cut[cut.length-1].split("-");
                int[] classChecker = new int[1000];
                for(int i = Integer.parseInt(range1[0]); i <= Integer.parseInt(range1[1]); i++) {
                    checker[i] = 1;
                    classChecker[i] = 1;
                }
                for(int i = Integer.parseInt(range2[0]); i <= Integer.parseInt(range2[1]); i++) {
                    checker[i] = 1;
                    classChecker[i] = 1;
                }
                allChecker.add(classChecker);
            }
        }
        // find fields, loop all tickets,
        allTickets.add(myticket); // including mine
        ArrayList<Integer>[] fields = new ArrayList[allTickets.get(0).length];
        for(int i = 0; i < allTickets.get(0).length; i++){
            ArrayList<Integer> possibleFields = new ArrayList();
            for(int c = 0; c < allChecker.size(); c++){
                boolean good = true;
                for(int j = 0; j < allTickets.size(); j++){
                    if(allChecker.get(c)[allTickets.get(j)[i]] == 0){
                        good = false;
                        break;
                    }
                }
                if(good){
                    possibleFields.add(c);
                }
            }
            fields[i] = possibleFields;
        }

        // find solution fields, index
        HashMap<Integer, Integer> part2 = new HashMap<>();
        for(int i = 1; i <= 20; i++){
            for(int j = 0; j < fields.length; j++){
                if(fields[j].size() == i){
                    for(int cur : fields[j]){
                        if(!part2.containsKey(cur)){
                            part2.put(cur, j);
                        }
                    }
                }
            }
        }

        // multiply six fields start with departure
        long result = 1;
        for(int i = 0; i < 6; i++){
            result *= myticket[part2.get(i)];
        }
        System.out.println("Part 2: " + result);
        System.out.println("Run time: " + (System.currentTimeMillis() - startTime) + "ms"); //runtime
    }
}
