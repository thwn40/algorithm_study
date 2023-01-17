import java.util.Arrays;
import java.util.Scanner;

public class 일로만들기_1463 {
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        arr = new int[X + 1];
        Arrays.fill(arr, 10000000);
        arr[X] = 0;
        dp(X);
        System.out.println(arr[1]);
    }

    public static void dp(int X) {
        if (X == 1) return;
        if (X % 3 == 0) {
            if (arr[X / 3] > arr[X] + 1) {
                arr[X / 3] = arr[X] + 1;
                dp(X / 3);
            }
        }
        if (X % 2 == 0) {
            if (arr[X / 2] > arr[X] + 1) {
                arr[X / 2] = arr[X] + 1;
                dp(X / 2);
            }
        }
        if (arr[X - 1] > arr[X] + 1) {
            arr[X - 1] = arr[X] + 1;
            dp(X - 1);
        }
    }
}
