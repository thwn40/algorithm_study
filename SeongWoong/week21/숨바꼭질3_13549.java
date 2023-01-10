import java.util.*;

public class 숨바꼭질3_13549 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 출발점
        int N = sc.nextInt();
        // 도착점
        int K = sc.nextInt();

        // 뒤로 가는 경우는 -1 밖에 없으므로
        if (N >= K) {
            System.out.println(N - K);
            return;
        }

        int MAX = Math.max(N, K);
        // i+1 번째 좌표까지 가는데 걸리는 시간의 배열
        int[] times = new int[MAX + 2];
        Arrays.fill(times, 100000000);
        boolean[] visited = new boolean[MAX + 2];

        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        times[N] = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            int[] next = new int[]{cur + 1, cur - 1, cur * 2};

            for (int i = 0; i < 3; i++) {
                if (next[i] >= MAX + 2 || next[i] < 0) continue;
                visited[next[i]] = true;
                // 순간이동 경우 따로
                if (i == 2) {
                    if (times[next[i]] > times[cur]) {
                        times[next[i]] = times[cur];
                        q.add(next[i]);
                    }
                } else {
                    if (times[next[i]] > times[cur] + 1) {
                        times[next[i]] = times[cur] + 1;
                        q.add(next[i]);
                    }
                }
            }
        }
        System.out.println(times[K]);
    }
}
