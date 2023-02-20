package SeongWoong.week26;

import java.util.Scanner;

public class 수이어쓰기2_1790 {
    static int n;
    static int k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        getLen();

    }

    public static void getLen() {
        int len = 1; // 자릿수
        int targetNum = 0; // 찾을 숫자
        // sizeByTen을 long으로 해줘야함
        long sizeByTen = 9; //자릿수에 따른 갯수 9,90,900,...
        while (k > sizeByTen * len) {
            k -= sizeByTen * len;
            targetNum += sizeByTen;
            len++;
            sizeByTen *= 10;
        }
        targetNum = targetNum + 1 + (k - 1) / len;

        if (targetNum > n) {
            System.out.println(-1);
        }
        else {
            int idx = (k - 1) % len;
            System.out.println(String.valueOf(targetNum).charAt(idx));
        }
    }
}
