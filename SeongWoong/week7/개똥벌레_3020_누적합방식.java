import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 개똥벌레_3020_누적합방식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        // 기둥의 갯수
        int N = Integer.parseInt(str[0]);
        // 장애물의 높이
        int H = Integer.parseInt(str[1]);
        // sum[i] = i번째 구간에 있는 기둥의 수(구간이 1부터라 H+1)
        int[] sum = new int[H + 1];

        for (int i = 0; i < N; i++) {
            int level = Integer.parseInt(br.readLine());
            // 홀수번째 입력은 석순
            if (i % 2 == 0) {
                sum[level]++;
                // 짝수번째 입력은 종유석이라 뒤집힌다.
            } else {
                sum[H]++;
                sum[H - level]--;
            }
        }
        int min = sum[H];
        // 부숴야하는 최소기둥 갯수 찾기
        for (int i = H - 1; i > 0; i--) {
            sum[i] += sum[i + 1];
            min = Math.min(min, sum[i]);
        }
        int cnt = 0;
        // 해당 갯수인 구간을 세준다
        for (int i = 1; i <= H; i++) {
            if (sum[i] == min) cnt++;
        }
        System.out.println(min + " " + cnt);
    }
}
