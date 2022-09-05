package Inhwan.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 다익스트라 알고리즘 활용 문제
public class 최소비용구하기_1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());

        // 버스의 정보를 담는 인접리스트
        List<int[]>[] Lines = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) Lines[i] = new ArrayList<>();

        int from, to, price;    // 출발도시, 도착도시, 비용
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            price = Integer.parseInt(st.nextToken());

            // 출발도시의 리스트에 도착도시와 비용을 묶어서 저장
            Lines[from].add(new int[] {to, price});
        }

        // 출발점, 도착점
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()), end =Integer.parseInt(st.nextToken());

        // 출발점으로부터 각 도시까지 가는데 드는 최소 비용을 담는 배열
        long[] Min = new long[N+1];
        long limit = 99999*N;   // 한 도시에서 다른 도시까지 가는데 필요한 최대 비용 = 99999*(N-1) -> 즉 limit 미만
        Arrays.fill(Min, limit);
        Min[start] = 0; // 출발점은 비용이 들지 않음

        // 최저 비용 계산이 끝난 도시
        boolean[] Fin = new boolean[N+1];
        Fin[start] = true;

        // 출발점에서 갈 수 있는 도시들의 최저비용 갱신
        for (int[] fromStart : Lines[start]) {
            Min[fromStart[0]] = Math.min(Min[fromStart[0]],fromStart[1]);
        }

        // 최저 비용 계산이 끝나지 않은 도시 중 가장 비용이 적게 드는 도시 선택
        int next = findIndexOfMin(Min, Fin);
        Fin[next] = true;

        // 갈 수 없는 곳이면 정지 (0은 항상 갈 수 없으므로 결국엔 정지함)
        while (Min[next] < limit) {

            // next에서 갈 수 있는 도시들의 죄저 비용 갱신
            for (int[] fromNext : Lines[next]) {
                Min[fromNext[0]] = Math.min(Min[fromNext[0]], Min[next] + fromNext[1]);
            }

            next = findIndexOfMin(Min, Fin);
            Fin[next] = true;
        }

        System.out.println(Min[end]);

    }

    // 최저 비용 계산이 끝나지 않은 도시 중 가장 비용이 적게 드는 도시 선택
    static int findIndexOfMin(long[] Min, boolean[] Fin) {
        int index = 0;
        long min = Min[0];

        for (int i = 1; i <Min.length ; i++) {
            if (!Fin[i] && min > Min[i]) {
                index = i;
                min = Min[i];
            }
        }

        return index;
    }
}