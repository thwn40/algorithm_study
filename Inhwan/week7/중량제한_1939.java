package Inhwan.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//public class 중량제한_1939 {
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        int N  = NM[0], M = NM[1];
//
//        List<int[]>[] adj =new List[N+1];
//        for (int i = 1; i < N+1; i++) {
//            adj[i] = new ArrayList<>();
//
//        }
//        for (int i = 0; i < M; i++) {
//            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//            adj[edge[0]].add(new int[] {edge[1], edge[2]});
//            adj[edge[1]].add(new int[] {edge[0], edge[2]});
//        }
//
//        int[] Factories = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        int[] Visited = new int[N+1];
//
//        Queue<int[]> q1 = new LinkedList<>(), q2 = new LinkedList<>();
//        q1.add(new int[] {Factories[0],1000000000});
//        Visited[Factories[0]] = 1000000000;
//
//        while (!q1.isEmpty()) {
//            int[] island = q1.poll();
//
//            for (int[] nextIsland : adj[island[0]]) {
//                if (Visited[nextIsland[0]]<nextIsland[1] && Visited[nextIsland[0]]<island[1]) {
//                    q2.add(new int[] {nextIsland[0], Math.min(island[1], nextIsland[1])});
//                    Visited[nextIsland[0]]=Math.min(island[1], nextIsland[1]);
//                }
//            }
//
//            if (q1.isEmpty()) {
//                q1 = q2;
//                q2 = new LinkedList<>();
//            }
//        }
//
//        System.out.println(Visited[Factories[1]]);
//    }
//}

public class 중량제한_1939 {
    static int f1, f2, N, M;
    static List<int[]>[] adj;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = NM[0];
        M = NM[1];

        adj =new List[N+1];
        for (int i = 1; i < N+1; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            adj[edge[0]].add(new int[] {edge[1], edge[2]});
            adj[edge[1]].add(new int[] {edge[0], edge[2]});
        }

        int[] Factories = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        f1 = Factories[0];
        f2 = Factories[1];

        System.out.println(binarySearch(1, 1000000001));
    }

    static boolean test(int w) {
        Queue<Integer> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        boolean[] Visited = new boolean[N+1];
        q1.add(f1);
        Visited[f1] = true;

        while (!q1.isEmpty()) {
            int island = q1.poll();

            for (int[] nextIsland : adj[island]) {
                if (Visited[nextIsland[0]] == false && nextIsland[1] >= w) {
                    if (nextIsland[0] == f2) return true;

                    q2.add(nextIsland[0]);
                    Visited[nextIsland[0]] = true;
                }
            }

            if (q1.isEmpty()) {
                q1 = q2;
                q2 = new LinkedList<>();
            }
        }

        return false;
    }

    static int binarySearch(int possible, int impossible) {
        if (impossible - possible == 1) {
            return possible;
        }

        int median = (impossible + possible)/2;

        if (test(median)) return binarySearch(median, impossible);
        else return binarySearch(possible, median);
    }
}