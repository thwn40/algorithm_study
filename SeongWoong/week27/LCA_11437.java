package SeongWoong.week27;

import java.util.*;

public class LCA_11437 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer>[] arr = new List[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<Integer>();
        }
        for (int i = 1; i < N; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            arr[from].add(to);
            arr[to].add(from);
        }
        int[] level = new int[N+1];
        int[] parent = new int[N+1];
        boolean[] check = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();

        check[1] = true;
        level[1] = 0;
        q.add(1);

        while (!q.isEmpty()) {
            int c = q.poll();
            for (int n : arr[c]) {
                if (!check[n]) {
                    level[n] = level[c] + 1;
                    check[n] = true;
                    parent[n] = c;
                    q.add(n);
                }
            }
        }

        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            while (level[a] != level[b]) {
                if (level[a] > level[b]) {
                    a = parent[a];
                }
                if (level[a] < level[b]) {
                    b = parent[b];
                }
            }
            while (a != b) {
                a = parent[a];
                b = parent[b];
            }
            System.out.println(a);
        }
    }
}
