package GyungMin.week2;

import java.io.*;

public class 파도반수열_9461 {
    static Long[] padovan = new Long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(padovanSequence(Integer.parseInt(br.readLine()))+"\n"));
        }
        br.close();
        bw.close();
    }

    public static long padovanSequence(int num) {
        if (num == 0) return 0;
        else if (num == 1) return 1;
        else if (num == 2) return 1;
        else if (num == 3) return 1;
        if (padovan[num] != null) return padovan[num];
        padovan[num] = padovanSequence(num-3) + padovanSequence(num-2);
        return padovan[num];
    }
}
