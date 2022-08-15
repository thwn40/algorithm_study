package Inhwan.week4;


import java.io.*;

public class 골드바흐의추측_6588 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean[] check = new boolean[1000001];
        boolean[] p = new boolean[1000001];

        int n = Integer.parseInt(br.readLine());
//        // 메모이제이션 X
//        while (n!=0) {
//            boolean det = false;
//
//            for (int i = 3; i<n/2;i+=2) {
//                if (prime(i) && prime(n-i)) {
//                    bw.write(String.format("%d = %d + %d\n",n,i,n-i));
//                    det = true;
//                    break;
//                }
//            }
//
//            if (!det) bw.write("Goldbach's conjecture is wrong.");
//
//            n = Integer.parseInt(br.readLine());
//        }


        // 메모이제이션 O
        while (n!=0) {
            boolean det = false;

            for (int i=3; i<=n/2; i+=2) {
                if (!check[i]) {
                    check[i]=true;
                    p[i]=prime(i);
                }
                if (!check[n-i]) {
                    check[n-i]=true;
                    p[n-i]=prime(n-i);
                }

                if (p[i] && p[n-i]) {
                    bw.write(String.format("%d = %d + %d\n",n,i,n-i));
                    det = true;
                    break;
                }
            }
            if (!det) bw.write("Goldbach's conjecture is wrong.");

            n = Integer.parseInt(br.readLine());

        }
        bw.flush();
        bw.close();

    }
    // 소수 판정 메서드
    static boolean prime(int n){
        if (n==1) return false;
        if (n==2) return true;
        if (n%2==0) return false;
        for (int i =3; i<=Math.sqrt(n);i+=2) if (n%i==0) return false;
        return true;
    }
}
