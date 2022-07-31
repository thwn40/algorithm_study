package SeongJun.week4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 약수의합_17425 {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        long[] arr = new long[1000001];
        long[] psum = new long[1000001];
        Arrays.fill(arr,1);

        for (int i = 2; i < 1000001; i++) {
            for (int j = 1; j*i < 1000001; j++) {
                arr[(j*i)]+=i;
            }

        }

        for (int i = 1; i < 1000001; i++) {
            psum[i] = psum[i-1] + arr[i];
        }


//        System.out.println(Arrays.toString(psum));
//        System.out.println(Arrays.toString(arr));
        long T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int answer = Integer.parseInt(br.readLine());
            bw.write(psum[answer]+"\n");
        }

        bw.flush();
    }
}

