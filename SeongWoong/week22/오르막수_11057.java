import java.util.Scanner;

public class 오르막수_11057 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[][] arr = new long[N + 1][10];
        for (int i = 0; i < 10; i++) {
            arr[0][i] = 1;
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= i; j++) {
                arr[1][i] += arr[0][j];
            }
        }
        for (int c = 2; c <= N; c++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j <= i; j++) {
                    arr[c][i] += arr[c - 1][j];
                }
                arr[c][i] %= 10007;
            }
        }
        int ans =0;
        System.out.println(arr[N][9]%10007);
    }
}