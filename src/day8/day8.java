package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Arrays;

public class day8 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();;
        File inputFile = new File("src\\day8\\input.txt");
        Scanner fileScanner;
        ArrayList<String> allinput = new ArrayList<String>();
        {
            try {
                fileScanner = new Scanner(inputFile);
                while (fileScanner.hasNext()) {
                    allinput.add(fileScanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        boolean[] loop = new boolean[allinput.size()];
        Arrays.fill(loop, false);
        int i = 0, acu = 0;
        part1(loop, i, acu, allinput);
        part2(loop, i, acu, allinput, true);
        System.out.println(System.currentTimeMillis() - startTime);
    }


    public static void part1(boolean[] loop, int i, int acu, ArrayList<String> input) {
        boolean[] loop2 = loop.clone();
        while (true) {
            while (i < 0) i += input.size();
            i %= input.size();
            if (loop2[i]){
                System.out.println("Part 1: " + acu);
                break;
            }
            loop2[i] = true;
            String cur = input.get(i);
            String comm = cur.substring(0, 3);
            int num = Integer.parseInt(cur.substring(4));
            if (comm.equals("acc")) {
                acu += num;
                i++;
            } else if (comm.equals("jmp")) {
                i += num;
            } else {
                i++;
            }
        }
    }

    public static boolean part2(boolean[] loop, int i, int acu, ArrayList<String> input, boolean change) {
        boolean[] loop2 = loop.clone();
        while (true) {
            if (i == input.size()) {
                System.out.println("Part 2: " + acu);
                return true;
            }
            while (i < 0) i += input.size();
            i %= input.size();
            if (loop2[i]) break;
            loop2[i] = true;
            String cur = input.get(i);
            String comm = cur.substring(0, 3);
            int num = Integer.parseInt(cur.substring(4));
            if (comm.equals("acc")) {
                acu += num;
                i++;
            } else if (comm.equals("jmp")) {
                if (change && part2(loop, i + 1, acu, input, false)) break;
                i += num;
            } else {
                if (change && part2(loop, i + num, acu, input, false)) break;
                i++;
            }
        }
        return false;
    }
}