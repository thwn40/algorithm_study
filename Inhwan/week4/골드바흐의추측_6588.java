package Inhwan.week4;

import java.io.*;
import java.util.*;

public class 골드바흐의추측_6588 {
    public static void main(String[] args) throws IOException {
        int max = 1000000;

        // 소수인지 여부를 저장하는 배열
        boolean[] Prime = new boolean[max+1];
        Arrays.fill(Prime,true);

        Prime[1] = false;

        // 에라토스테네스의 체
        for (int i = 2; i <=max; i++) {
            if (Prime[i]) {	// i가 소수이면
                for (int j = 2; i*j <=max ; j++) {
                    Prime[i*j] = false;	// i의 배수는 소수x
                }
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n==0) break;	// 입력값이 0이면 종료

            // 골드바흐의 추측이 틀렸을 경우
            boolean det = true;

            // n이 짝수이므로 n-2는 2보다 큰 짝수 -> 소수x -> 3부터 시작
            // 2보다 큰 짝수는 소수가 아니므로 홀수만 확인
            // i가 n/2보다 큰 경우 앞의 경우와 symmetric하므로 n/2까지만 확인
            for (int i = 3; i <=n/2 ; i+=2) {
                if (Prime[i] && Prime[n-i]) {	// i와 n-i가 모두 소수이므로 골드바흐의 추측이 옳은 경우
                    bw.write(String.format("%d = %d + %d\n",n,i,n-i));
                    det = false;
                    break;
                }
            }

            if (det) bw.write("Goldbach's conjecture is wrong.");
        }

        bw.flush();
        bw.close();
    }
}


//public class 골드바흐의추측_6588 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        boolean[] check = new boolean[1000001];
//        boolean[] p = new boolean[1000001];
//
//        int n = Integer.parseInt(br.readLine());
////        // 메모이제이션 X
////        while (n!=0) {
////            boolean det = false;
////
////            for (int i = 3; i<n/2;i+=2) {
////                if (prime(i) && prime(n-i)) {
////                    bw.write(String.format("%d = %d + %d\n",n,i,n-i));
////                    det = true;
////                    break;
////                }
////            }
////
////            if (!det) bw.write("Goldbach's conjecture is wrong.");
////
////            n = Integer.parseInt(br.readLine());
////        }
//
//
//        // 메모이제이션 O
//        while (n!=0) {
//            boolean det = false;
//
//            for (int i=3; i<=n/2; i+=2) {
//                if (!check[i]) {
//                    check[i]=true;
//                    p[i]=prime(i);
//                }
//                if (!check[n-i]) {
//                    check[n-i]=true;
//                    p[n-i]=prime(n-i);
//                }
//
//                if (p[i] && p[n-i]) {
//                    bw.write(String.format("%d = %d + %d\n",n,i,n-i));
//                    det = true;
//                    break;
//                }
//            }
//            if (!det) bw.write("Goldbach's conjecture is wrong.");
//
//            n = Integer.parseInt(br.readLine());
//
//        }
//        bw.flush();
//        bw.close();
//
//    }
//    // 소수 판정 메서드
//    static boolean prime(int n){
//        if (n==1) return false;
//        if (n==2) return true;
//        if (n%2==0) return false;
//        for (int i =3; i<=Math.sqrt(n);i+=2) if (n%i==0) return false;
//        return true;
//    }
//}
