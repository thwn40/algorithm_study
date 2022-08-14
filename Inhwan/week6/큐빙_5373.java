package Inhwan.week6;

import java.io.*;

public class 큐빙_5373 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            // 큐브 객체 생성
            Cube cube = new Cube();

            int n = Integer.parseInt(br.readLine());

            String[] Orders = br.readLine().split(" ");

            for (String order : Orders) {
                int m;
                if (order.charAt(1)=='+') m=1;  // + 이면 시계 방향으로 90도
                else m=3;                       // - 이면 반시계 방향으로 90도 = 시계방향으로 270도 (90도 x 3)
                char o = order.charAt(0);

                switch (o) {
                    case 'U' -> cube.u(m);
                    case 'F' -> cube.f(m);
                    case 'L' -> cube.l(m);
                    case 'R' -> cube.r(m);
                    case 'B' -> cube.b(m);
                    case 'D' -> cube.d(m);
                }
            }

            for (char[] arr : cube.U) bw.write(""+arr[0]+arr[1]+arr[2]+"\n");
        }

        bw.flush();
        bw.close();
    }

    static class Cube {
        char[][] U = {{'w','w','w'},{'w','w','w'},{'w','w','w'}};
        char[][] L = {{'g','g','g'},{'g','g','g'},{'g','g','g'}};
        char[][] F = {{'r','r','r'},{'r','r','r'},{'r','r','r'}};
        char[][] R = {{'b','b','b'},{'b','b','b'},{'b','b','b'}};
        char[][] B = {{'o','o','o'},{'o','o','o'},{'o','o','o'}};
        char[][] D = {{'y','y','y'},{'y','y','y'},{'y','y','y'}};

        // U를 시계방향으로 90도 회전
        void spin() {
            U=rotate(U);
            char[] temp = F[0];
            F[0]=R[0];
            R[0]=B[0];
            B[0]=L[0];
            L[0]=temp;
        }

        // x축 중심으로 회전
        void x() {
            char[][] temp=F;
            F=D;
            D=rotate(rotate(B));
            B=rotate(rotate(U));
            U=temp;

            R=rotate(R);
            L=rotate(rotate(rotate(L)));
        }

        // z축 중심으로 회전
        void z() {
            F=rotate(F);
            B=rotate(rotate(rotate(B)));

            char[][] temp = U;
            U=rotate(L);
            L=rotate(D);
            D=rotate(R);
            R=rotate(temp);
        }

        // 각 면 시계방향으로 90도씩 n회 회전
        void u(int n) {
            for (int i = 0; i < n; i++) this.spin();
        }

        void f(int n) {
            this.x();
            for (int i = 0; i < n; i++) this.spin();
            this.x();
            this.x();
            this.x();
        }

        void l(int n) {
            this.z();
            for (int i = 0; i < n; i++) this.spin();
            this.z();
            this.z();
            this.z();
        }

        void r(int n) {
            this.z();
            this.z();
            this.z();
            for (int i = 0; i < n; i++) this.spin();
            this.z();
        }

        void b(int n) {
            this.x();
            this.x();
            this.x();
            for (int i = 0; i < n; i++) this.spin();
            this.x();
        }

        void d(int n) {
            this.z();
            this.z();
            for (int i = 0; i < n; i++) this.spin();
            this.z();
            this.z();
        }
    }

    // 배열 회전
    static char[][] rotate(char[][] side) {
        char[][] result = new char[3][3];
        for (int i = 0; i < 3; i++) {
            result[i][0]=side[2][i];
            result[i][1]=side[1][i];
            result[i][2]=side[0][i];
        }

        return result;
    }
}