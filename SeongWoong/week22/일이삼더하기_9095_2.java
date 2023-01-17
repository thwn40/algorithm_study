import java.util.Scanner;

public class 일이삼더하기_9095_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        int[] arr = new int[]{1, 1, 2, 4, 7, 13, 24, 44, 81, 149, 274};
        for (int i = 0; i < T; i++) {
            int cur = sc.nextInt();
            System.out.println(arr[cur]);
        }
    }
}

