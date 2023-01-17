import java.util.Scanner;

public class 이친수_2193 {
    public static void main(String[] args) {
        // 풀이 : 노가다로 점화식 구함
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] arr = new long[N + 1];
        arr[1] = 1;
        if (N > 1) {
            arr[2] = 1;
            for (int i = 3; i <= N; i++) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
        }
        System.out.println(arr[N]);
    }
}
