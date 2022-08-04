// 시간초과 해결 못해서 구글링 좀 참조함

import java.io.*;

public class 약수의합_17425 {
    public static void main(String[] args) throws IOException {
        // F -> 각 수의 약수의 합
        int[] F = new int[1000000];

//        // 시간초과 -> 시간복잡도 n루트n?
//        //
//        for (int i =0; i<1000000; i++) {
//            int sum=0;
//            for (int j = 1; j<=Math.pow(i+1,0.5);j++) {
//                if ((i+1)%j==0) {
//                    sum+=j;
//                    if (j!= (i+1)/j) sum+=(i+1)/j;
//                }
//                F[i]=sum;
//            }
//        }

        //
        for (int i=1; i<=1000; i++) {
            for (int j=1; i*j<=1000000; j++) {
                if (i<=j) F[i*j-1]+=i;
                if (i<j) F[i*j-1]+=j;
            }
        }

        for (int i=1; i<1000000;i++) {
            F[i]+=F[i-1];
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            bw.write(F[N-1]+"\n");
        }
        bw.flush();
        bw.close();
    }
}
