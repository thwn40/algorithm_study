package Inhwan.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 회문_17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            System.out.println(palindrome(br.readLine()));
        }
    }

    static int palindrome(String str) {
        int i = 0;
        int j = str.length()-1;
        int I, J;

        while (i<j && str.charAt(i)==str.charAt(j)) {
            i++;
            j--;
        }

        if (i>=j) return 0;

        I = i+1;
        J = j;

        while (I<J && str.charAt(I)==str.charAt(J)) {
            I++;
            J--;
        }

        if (I>=J) return 1;

        I=i;
        J=j-1;

        while (I<J && str.charAt(I)==str.charAt(J)) {
            I++;
            J--;
        }

        if (I>=J) return 1;

        return 2;
    }
}