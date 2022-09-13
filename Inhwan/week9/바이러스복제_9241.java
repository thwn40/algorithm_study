package Inhwan.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 바이러스복제_9241 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine(), str2 = br.readLine();

        int i=0, j = str2.length()-1, k = str1.length()-1;

        while (i<=Math.min(j,k) && str1.charAt(i) == str2.charAt(i)) {
            i++;
        }

        while (str1.charAt(k) == str2.charAt(j) && Math.min(j,k)>=i) {
            k--;
            j--;
        }

        System.out.println(j-i+1);

    }
}