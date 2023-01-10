import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질_1697 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 출발점
        int N = sc.nextInt();
        // 도착점
        int K = sc.nextInt();

        // 뒤로 가는 경우는 -1 밖에 없으므로
        if (N >= K) {
            System.out.println(N-K);
            System.out.println(1);
            return;
        }

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
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == K) break;
            if (visited[cur]) continue;
            visited[cur] = true;
            if (cur + 1 <= MAX + 1) {
                cnt[cur + 1] += cnt[cur];
                if (times[cur + 1] == 0) {
                    times[cur + 1] = times[cur]+1;
                    q.add(cur + 1);
                }
            }
            if (cur - 1 >= 0) {
                cnt[cur - 1] += cnt[cur];
                if (times[cur - 1] == 0) {
                    times[cur - 1] = times[cur]+1;
                    q.add(cur - 1);
                }
            }
            if (cur * 2 <= MAX + 1) {
                cnt[cur * 2] += cnt[cur];
                if (times[cur * 2] == 0) {
                    times[cur * 2] = times[cur]+1;
                    q.add(cur * 2);
                }
            }
        }
        int answer = times[K];
        int cntAnswer = cnt[K];
        System.out.println(answer);
        System.out.println(cntAnswer);
    }
}
