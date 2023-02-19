package SeongWoong.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class 놀이공원_1561 {
    static int M, N;
    static int[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int maxTime = 0;
        int answer = 0;
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 각 놀이기구의 운행 시간
        times = new int[M];
        for (int i = 0; i < M; i++) {
            times[i] = Integer.parseInt(st.nextToken());
            maxTime = Math.max(maxTime, times[i]);
        }
        // 만약 사람수가 기구수보다 적으면 한번에
        if (N <= M) {
            answer = N;
        } else {
            long time = search(0, (N / M) * maxTime);
            long cnt = M;
            for (int i = 0; i < M; i++) {
                cnt += (time - 1) / times[i];
            }
            for (int i = 0; i < M; i++) {
                if (time % times[i] == 0) {
                    cnt++;
                }
                if (cnt == N) {
                    answer = i;
                    return;
                }
            }
        }
        System.out.println(answer);

    }

    private static long search(long left, long right) {
        long mid = (left + right) / 2;
        // 각
        long sum = M;
        while (left <= right) {
            for (int i = 0; i < M; i++) {
                sum += mid / times[i];
            }

            if (sum < N) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
