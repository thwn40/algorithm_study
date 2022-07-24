package Gyungmin.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 랜선자르기_1654 {
    public static long [] ownedLanArray;
    public static int numOfLine;
    public static int numOfNeed;

    public static void maxCutLanLine(long start, long end) {

        int numOfCutLine;
        long mid;
        long elementOwnedLanLine;
        long findMaxLineLength = 0;

        while (start <= end) {
            numOfCutLine = 0;
            mid = (start + end) / 2;

            for (int i = 0; i < numOfLine; i++) {
                elementOwnedLanLine = ownedLanArray[i];
                numOfCutLine += elementOwnedLanLine / mid;
            }

            if(numOfNeed <= numOfCutLine) {
                start = mid + 1;
                findMaxLineLength = Math.max(findMaxLineLength, mid);
            } else end = mid - 1;
        }

        System.out.println(findMaxLineLength);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfLine = Integer.parseInt(st.nextToken());
        numOfNeed = Integer.parseInt(st.nextToken());
        ownedLanArray = new long[numOfLine];
        long start = 1;
        long end = 0;
        long input;
        for (int i = 0; i < numOfLine; i++) {
            input = Long.parseLong(br.readLine());
            end = Math.max(end, input);
            ownedLanArray[i] = input;

        }

        maxCutLanLine(start, end);
    }
}