import java.util.Scanner;

public class K번째수_1300 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextInt();
        long k = sc.nextInt();

        long start = 0;
        long end = N * N;

        while (start < end) {
            long mid = (start + end) / 2;
            // mid 보다 작은 수의 갯수를 더할 sum
            long sum = 0;
            // 배열 N번째 행을 순회하면서
            for (int i = 1; i <= N; i++) {
                // sum에 mid 보다 작은 갯수를 넣을건데
                // mid/i를 하면 행에서 작은 갯수를 알 수 있음
                sum += Math.min(N, mid / i);

            }
            //이분탐색을 하며 원하는 k를 찾음
            if (sum >= k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(end);
    }
}
