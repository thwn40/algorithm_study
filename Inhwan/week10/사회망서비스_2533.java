package Inhwan.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 사회망서비스_2533 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        int v1, v2;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            adj[v1].add(v2);
            adj[v2].add(v1);
        }

        int[][] numOfEarlyAdopter = new int[N+1][2];
        for (int i = 1; i <= N; i++) numOfEarlyAdopter[i][1] = 1;

        boolean[] checked = new boolean[N + 1];

        int start = 1;
        for (int i = 1; i <=N ; i++) {
            if (adj[i].size() > 1) {
                start = i;
                break;
            }
        }

        System.out.println(Math.min(count(start, numOfEarlyAdopter, checked, adj)[0],
                count(start, numOfEarlyAdopter, checked, adj)[1]));
    }

    static int[] count(int n, int[][] numOfEarlyAdopter, boolean[] checked, List<Integer>[] adj) {
        checked[n] = true;

        for (int m : adj[n]) {
            if (!checked[m]) {
                numOfEarlyAdopter[n][0] += count(m, numOfEarlyAdopter, checked, adj)[1];
                numOfEarlyAdopter[n][1] += Math.min(count(m, numOfEarlyAdopter, checked, adj)[0],
                        count(m, numOfEarlyAdopter, checked, adj)[1]);
            }
        }

        return numOfEarlyAdopter[n];
    }
}