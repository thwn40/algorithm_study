import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질2_12851 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 출발점
        int N = sc.nextInt();
        // 도착점
        int K = sc.nextInt();

        int MAX = Math.max(N, K);
        // i+1 번째 좌표까지 가는데 걸리는 시간의 배열
        int[] times = new int[MAX + 2];
        // 방법의 수 배열
        int[] cnt = new int[MAX + 2];
        // 방문 체크용 배열
        boolean[] visited = new boolean[MAX + 2];
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        cnt[N] = 1;
        visited[N] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int[] next = new int[]{cur + 1, cur - 1, cur * 2};


            for (int i = 0; i < 3; i++) {
                if (next[i] > MAX + 1 || next[i] < 0) continue;
                if (!visited[next[i]]) {
                    visited[next[i]] = true;
                    q.add(next[i]);
                    times[next[i]] = times[cur] + 1;
                    cnt[next[i]] = cnt[cur];
                } else if (times[next[i]] == times[cur] + 1) {
                    cnt[next[i]] += cnt[cur];
                }
            }
        }
        System.out.println(times[K]);
        System.out.println(cnt[K]);
    }
}
