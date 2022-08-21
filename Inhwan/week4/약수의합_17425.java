package Inhwan.week4;

import java.io.*;

public class 약수의합_17425 {
    public static void main(String[] args) throws IOException {

        // f(n)의 값을 저장할 배열
        long[] G = new long[1000000];

        for (int i=1; i<=1000; i++) {
            for (int j=1; i*j<=1000000; j++) {
                // 중복되는 경우를 피하기위해 i<=j인 경우만 계산 (i>j인 경우는 이미 계산됨)
                if (i<=j) G[i*j-1]+=i;
                // i==j인 경우는 i 한번만 더함
                if (i<j) G[i*j-1]+=j;
            }
        }

        // f(n)을 차례대로 더하면서 g(n)을 계산
        for (int i=1; i<1000000;i++) {
            G[i]+=G[i-1];
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            bw.write(G[N-1]+"\n");
        }
        bw.flush();
        bw.close();
    }
}