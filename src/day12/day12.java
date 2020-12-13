package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day12 {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        File inputFile = new File("src\\day12\\input.txt");
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<String> input = new ArrayList<String>();
        while (fileScanner.hasNextLine()) {
            input.add(fileScanner.nextLine());
        }
        part1(input);
        part2(input);
        System.out.println(System.currentTimeMillis() - startTime); //runtime
    }

    public static void part1(ArrayList<String> input){
        int x = 0, y = 0, dir = 0;
        for(String s : input){
            int num = Integer.parseInt(s.substring(1, s.length()));
            switch(s.charAt(0)){
                case 'N':
                    x += num;
                    break;
                case 'S':
                    x -= num;
                    break;
                case 'E':
                    y += num;
                    break;
                case 'W':
                    y -= num;
                    break;
                case 'L':
                    dir += num/90;
                    dir %= 4;
                    break;
                case 'R':
                    dir -= num/90;
                    while(dir<0) dir += 4;
                    break;
                case 'F':
                    switch(dir){
                        case 0:
                            y += num;
                            break;
                        case 1:
                            x += num;
                            break;
                        case 2:
                            y -= num;
                            break;
                        case 3:
                            x -= num;
                            break;
                    }
                    //System.out.println(x + " "+ y);
            }
        }
        System.out.println(Math.abs(x) + Math.abs(y));
    }

    public static void part2(ArrayList<String> input){
        int[] pos = new int[]{0,0}, wayPoint = new int[]{10,1};
        for(String s : input){
            int num = Integer.parseInt(s.substring(1, s.length()));
            switch(s.charAt(0)){
                case 'N':
                    wayPoint[1] += num;
                    break;
                case 'S':
                    wayPoint[1] -= num;
                    break;
                case 'E':
                    wayPoint[0] += num;
                    break;
                case 'W':
                    wayPoint[0] -= num;
                    break;
                case 'L':
                    rotate(wayPoint, num/90);
                    break;
                case 'R':
                    rotate(wayPoint, 4-(num/90));
                    break;
                case 'F':
                    pos[0] += wayPoint[0]*num;
                    pos[1] += wayPoint[1]*num;
                    break;
            }
        }
        System.out.println(Math.abs(pos[0]) + Math.abs(pos[1]));
    }

    public static void rotate(int[] wayPoint, int times){
        int x = wayPoint[0], y = wayPoint[1];
        switch(times){
            case 1:
                wayPoint[0] = -y;
                wayPoint[1] = x;
                break;
            case 2:
                wayPoint[0] = -x;
                wayPoint[1] = -y;
                break;
            case 3:
                wayPoint[0] = y;
                wayPoint[1] = -x;
                break;
        }
    }
}
