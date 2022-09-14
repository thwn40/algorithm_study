package Inhwan.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 공통부분문자열_5582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine(), str2 = br.readLine();
        int l1 = str1.length(), l2 = str2.length();


        int[] arr = new int[l1];
        int[] temp;
        int max = 0;

        for (int i = 0; i < l2; i++) {;
            temp = new int[l1];

            if (str1.charAt(0) == str2.charAt(i)) temp[0]=1;

            for (int j = 1; j < l1; j++) {
                if (str1.charAt(j) == str2.charAt(i)) {
                    temp[j] = arr[j-1]+1;
                    max = Math.max(max, temp[j]);
                }
            }
            arr = temp;
        }

        System.out.println(max);
    }
}
