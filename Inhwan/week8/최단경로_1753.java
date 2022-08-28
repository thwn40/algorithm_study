package Inhwan.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 최단경로_1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        List<int[]>[] edges = new ArrayList[V+1];
        for (int i = 0; i < V+1; i++) edges[i] = new ArrayList<>();

        int u, v, w;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            edges[u].add(new int[] {v, w});
        }

        int limit = 10*V;
        int Min[] = new int[V+1];
        Arrays.fill(Min, limit);
        Min[start] = 0;

        boolean[] Fin = new boolean[V+1];
        Fin[start] = true;

        for (int[] fromStart : edges[start]){
            Min[fromStart[0]] = Math.min(Min[fromStart[0]], fromStart[1]);
        }

        int next = findIndexOfMin(Min, Fin);
        Fin[next] = true;

        while (Min[next] < limit) {

            for (int[] fromNext : edges[next]) {
                Min[fromNext[0]] = Math.min(Min[fromNext[0]], Min[next]+fromNext[1]);
            }

            next = findIndexOfMin(Min, Fin);
            Fin[next] = true;
        }

        for (int i = 1; i <= V ; i++) {
            if (Min[i] == limit) System.out.println("INF");
            else System.out.println(Min[i]);
        }
    }

    static int findIndexOfMin(int[] Min, boolean[] Fin) {
        int index = 0;
        int min = Min[0];

        for (int i = 1; i <Min.length ; i++) {
            if (!Fin[i] && min > Min[i]) {
                index = i;
                min = Min[i];
            }
        }

        return index;
    }
}