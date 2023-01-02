import java.util.*;

public class 숨바꼭질4_13913 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 출발점
        int N = sc.nextInt();
        // 도착점
        int K = sc.nextInt();

        int MAX = Math.max(N, K);
        // i+1 번째 좌표까지 가는데 걸리는 시간의 배열
        int[] times = new int[MAX + 2];
        // 방문 체크용 배열
        boolean[] visited = new boolean[MAX + 2];
        // 경로 찾기를 위한 이동 직전 위치
        int[] route = new int[MAX + 2];

        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == K) break;
            if (visited[cur]) continue;
            visited[cur] = true;
            if (cur + 1 <= MAX + 1) {
                if (times[cur + 1] == 0) {
                    times[cur + 1] = times[cur] + 1;
                    q.add(cur + 1);
                    route[cur + 1] = cur;
                }
            }
            if (cur - 1 >= 0) {
                if (times[cur - 1] == 0) {
                    times[cur - 1] = times[cur] + 1;
                    q.add(cur - 1);
                    route[cur - 1] = cur;
                }
            }
            if (cur * 2 <= MAX + 1) {
                if (times[cur * 2] == 0) {
                    times[cur * 2] = times[cur] + 1;
                    q.add(cur * 2);
                    route[cur * 2] = cur;
                }
            }
        }
        int answer = times[K];
        System.out.println(answer);

        int c = K;
        StringBuilder sb = new StringBuilder();
        sb.append(K + " ");
        // 경로를 모으기(역순으로 저장)
        while (c != N) {
            sb.append(route[c] + " ");
            c = route[c];
        }
        // 경로를 잘라서 뒤집기
        String[] st = sb.toString().split(" ");
        StringBuilder answerRoute = new StringBuilder();
        for (int i = 0; i < st.length; i++) {
            answerRoute.append(st[st.length - i - 1] + " ");
        }

        System.out.println(answerRoute);
    }
}

