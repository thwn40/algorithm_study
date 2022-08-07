import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 이모티콘_14226 {
    static int S;
    static int[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        times = new int[1001];
        boolean[][] visited = new boolean[1001][1001];

        Arrays.fill(times, -1);
        Queue<int[]> q = new LinkedList<>();

        times[1] = 0;
        q.add(new int[]{1, 0, 0});
        while (times[S] == -1) {
            int[] cur = q.poll();
            int[] next1 = copy(cur);
            int[] next2 = paste(cur);
            int[] next3 = delete(cur);

            if (times[next1[0]] == -1) {
                times[next1[0]] = next1[2];
            }
            if (!visited[next1[0]][next1[1]]) {
                visited[next1[0]][next1[1]] = true;
                q.add(next1);
            }
            if (times[next2[0]] == -1) {
                times[next2[0]] = next2[2];
            }
            if (!visited[next2[0]][next2[1]]) {
                visited[next2[0]][next2[1]] = true;
                q.add(next2);
            }
            if (times[next3[0]] == -1) {
                times[next3[0]] = next3[2];
            }
            if (!visited[next3[0]][next3[1]]) {
                visited[next3[0]][next3[1]] = true;
                q.add(next3);
            }
        }
        System.out.println(times[S]);
    }

    public static int[] copy(int[] a) {
        int[] answer = new int[]{a[0], a[0], a[2] + 1};
        return answer;
    }

    public static int[] paste(int[] a) {
        if (a[0]+a[1]>1000) return a;
        int[] answer = new int[]{a[0] + a[1], a[1], a[2] + 1};

        return answer;
    }

    public static int[] delete(int[] a) {
        if (a[0] == 0) return a;
        int[] answer = new int[]{a[0] - 1, a[1], a[2] + 1};
        return answer;
    }
}
