import java.util.Scanner;

public class 일이삼더하기_9095 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        int[] num = new int[T];
        int max = 0;
        for (int i = 0; i < T; i++) {
            num[i] = sc.nextInt();
            max = Math.max(max, num[i]);
        }

        int[] arr = new int[max + 1];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;
        for (int i = 4; i <= max; i++) {
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
        }

        for (int i = 0; i < T; i++) {
            System.out.println(arr[num[i]]);
        }
    }
}

