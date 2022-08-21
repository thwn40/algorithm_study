package SeongWoo.week7;

import java.util.Scanner;

public class K번째수_1300 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int target = scanner.nextInt();

        long left = 1;
        long right = target;

        while (left < right) {
            long mid = (left + right) / 2;
            long count = 0;

            //mid와 같거나 작은 원소의 개수를 구한다.
            for (int i = 1; i <= n; i++) {
                count += Math.min(mid / i, n);
            }

            //mid와 같거나 작은 원소의 개수(count)가 target보다 크면 오른쪽 탐색
            if (count >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }
}
