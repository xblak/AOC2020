package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day11 {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        File inputFile = new File("src\\day11\\input.txt");
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<String> input = new ArrayList<String>();
        while (fileScanner.hasNextLine()) {
            input.add(fileScanner.nextLine());
        }
        char[][] cur =  new char[input.size()][input.get(0).length()];
        char[][] cur2 =  new char[input.size()][input.get(0).length()];
        for(int i = 0; i < input.size(); i++){
            String s = input.get(i);
            for(int j  = 0; j < input.get(0).length(); j++){
                cur[i][j] = s.charAt(j);
            }
        }
        for(int i = 0; i < cur.length; i++){
            cur2[i] = cur[i].clone();
        }
        findp1(cur);
        findp2(cur2);
        System.out.println(System.currentTimeMillis() - startTime); //runtime
    }

    public static void findp1(char cur [][]){
        while(true){
            char[][] next = new char[cur.length][cur[0].length];
            boolean change = false;
            for(int i = 0; i < cur.length; i++){
                for(int j  = 0; j < cur[0].length; j++){
                    switch (cur[i][j]){
                        case '.':
                            //Keep the ground the same
                            next[i][j] = '.';
                            break;
                        case '#':
                            if(countSeat(cur, i, j) >= 4) {
                                change = true;
                                next[i][j] = 'L';
                            }else{
                                next[i][j] = '#';
                            }
                            break;
                        case 'L':
                            if(countSeat(cur, i, j) == 0 ) {
                                change = true;
                                next[i][j] = '#';
                            }else{
                                next[i][j] = 'L';
                            }
                            break;
                    }
                }
            }
            // If no change break
            if(change) {
                for(int i = 0; i < cur.length; i++){
                    cur[i] = next[i].clone();
                }
            }
            else break;
        }
        //count how many seats are occupied
        int countO = 0;
        for(int i = 0; i < cur.length; i++){
            for(int j  = 0; j < cur[0].length; j++){
                if(cur[i][j] == '#') countO++;
            }
        }
        System.out.println("Part1: " + countO);
    }

    public static int countSeat(char cur [][], int i, int j){
        int count = 0;
        boolean up = i > 0, down = i < cur.length-1, left = j > 0, right = j < cur[0].length-1;
        if(up && cur[i-1][j] == '#'){
            count++;
        }
        if(down && cur[i+1][j] == '#'){
            count++;
        }
        if(left && cur[i][j-1] == '#'){
            count++;
        }
        if(right && cur[i][j+1] == '#'){
            count++;
        }if(up && left && cur[i-1][j-1] == '#'){
            count++;
        }
        if(up && right && cur[i-1][j+1] == '#'){
            count++;
        }if(down && left && cur[i+1][j-1] == '#'){
            count++;
        }
        if(down && right && cur[i+1][j+1] == '#'){
            count++;
        }
        return count;
    }

    //part2
    //check one direction (dx,dy)
    public static boolean dirIsEmp(char[][] cur, int i, int j, int dx, int dy){
        int nextI = i + dx, nextJ = j + dy;
        if(0 <= nextI && nextI < cur.length && 0 <= nextJ && nextJ < cur[0].length){
            if(cur[nextI][nextJ] == '.') return dirIsEmp(cur, nextI, nextJ, dx, dy);
            else if(cur[nextI][nextJ] == 'L') return true;
            else if(cur[nextI][nextJ] == '#') return false;
        }
        return true;
    }

    //count all 8 direction
    public static int countSeatDir(char[][] cur, int i, int j){
        int count = 0;
        for(int a = -1; a < 2; a++){
            for(int b = -1; b < 2; b++){
                if(!(a == 0 && b == 0) && !dirIsEmp(cur, i, j, a, b)) count++;
            }
        }
        return count;
    }

    public static void findp2(char cur [][]){
        while(true){
            char[][] next = new char[cur.length][cur[0].length];
            boolean change = false;
            for(int i = 0; i < cur.length; i++){
                for(int j  = 0; j < cur[0].length; j++){
                    switch (cur[i][j]){
                        case '.':
                            next[i][j] = '.';
                            break;
                        case '#':
                            if(countSeatDir(cur, i, j) >= 5) {
                                change = true;
                                next[i][j] = 'L';
                            }else{
                                next[i][j] = '#';
                            }
                            break;
                        case 'L':
                            if(countSeatDir(cur, i, j) == 0 ) {
                                change = true;
                                next[i][j] = '#';
                            }else{
                                next[i][j] = 'L';
                            }
                            break;
                    }
                }
            }
            // If no change break
            if(change) {
                for(int i = 0; i < cur.length; i++){
                    cur[i] = next[i].clone();
                }
            }
            else break;
        }
        //count how many seats are occupied
        int countO = 0;
        for(int i = 0; i < cur.length; i++){
            for(int j  = 0; j < cur[0].length; j++){
                if(cur[i][j] == '#') countO++;
            }
        }
        System.out.println("Part2: " + countO);
    }
}
