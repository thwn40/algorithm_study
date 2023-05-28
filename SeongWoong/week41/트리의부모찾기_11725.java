import java.io.*;
import java.util.*;

public class 트리의부모찾기_11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        List<Integer>[] link = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            link[i] = new ArrayList<>();
        }
        boolean[] used = new boolean[N + 1];
        int[] answer = new int[N + 1];
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            link[from].add(to);
            link[to].add(from);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while (!q.isEmpty()) {
            int parent = q.poll();
            for (int i = 0; i < link[parent].size(); i++) {
                int child = link[parent].get(i);
                if (!used[child]) {
                    used[child] = true;
                    answer[child] = parent;
                    q.add(child);
                }
            }
        }
        for (int i = 2; i < answer.length; i++) {
            bw.write(answer[i]+"\n");
        }
        bw.flush();
        bw.close();
    }
}
