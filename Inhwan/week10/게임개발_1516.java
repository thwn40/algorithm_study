package Inhwan.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 위상정렬 활용
public class 게임개발_1516 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] buildTimes = new int[N+1];            // 건물이 완성되는 시간
        int[] countPreceding = new int[N+1];        // 건물을 짓기 위해 먼져 지어져야하는 건물 수
        int[] startBuildingTime = new int [N+1];    // 건물을 짓기 시작하는 시간

        List<Integer>[] nextPoints = new ArrayList[N+1];    // 각 건물을 선행조건으로 갖는 건물 리스트
        for (int i = 1; i < N + 1; i++) nextPoints[i] = new ArrayList<>();

        int n;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            buildTimes[i] = Integer.parseInt(st.nextToken());   // 각 건물을 짓는데 걸리는 시간으로 초기화

            n = Integer.parseInt(st.nextToken());
            while(n > -1) {
                countPreceding[i]++;
                nextPoints[n].add(i);

                n = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> minimalPoints = new LinkedList<>();

        // 먼저 지어야 하는 건물이 없는 건물
        for (int i = 1; i < N + 1; i++) {
            if (countPreceding[i] == 0) minimalPoints.add(i);
        }

        while(!minimalPoints.isEmpty()) {
            n = minimalPoints.poll();
            buildTimes[n] += startBuildingTime[n];

            for (int nextPoint : nextPoints[n]) {
                countPreceding[nextPoint]--;
                // 건물을 짓기 시작하는 시간 = 먼저 지어져야하는 건물이 완성되는 시간 중 최대값
                startBuildingTime[nextPoint] = Math.max(startBuildingTime[nextPoint], buildTimes[n]);

                // 먼저 지어야하는 건물이 모두 지어졌을 때
                if (countPreceding[nextPoint] == 0) minimalPoints.add(nextPoint);
            }
        }

        for (int i = 1; i < N + 1; i++) {
            System.out.println(buildTimes[i]);
        }
    }
}




//// 메모이제이션 활용
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int N = Integer.parseInt(br.readLine());
//        int[] buildTimes = new int[N+1];        // 건물이 완성되는 시간
//        boolean[] checked = new boolean[N+1];   // 계산이 완료된 건물인지 확인
//
//        List<Integer>[] preceding = new ArrayList[N+1]; // 먼저 지어야하는 건물 리스트
//        for (int i = 1; i <= N; i++) preceding[i] = new ArrayList<>();
//
//        int n;
//        for (int i = 1; i <= N; i++) {
//            st = new StringTokenizer(br.readLine());
//            buildTimes[i] = Integer.parseInt(st.nextToken());
//
//            n = Integer.parseInt(st.nextToken());
//            while (n > -1) {
//                preceding[i].add(n);
//                n = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        for (int i = 1; i <= N; i++) System.out.println(calculateBuildTime(i, buildTimes,checked,preceding));
//    }
//
//    // 건물이 완성되는 시간을 계산하는 메서드
//    static int calculateBuildTime(int n, int[] buildTimes, boolean[] checked, List<Integer>[] preceding) {
//        // 이미 계산되었으면 바로 리턴
//        if (checked[n]) return buildTimes[n];
//
//        checked[n] = true;
//
//        // 건설 시작시간
//        int startTime = 0;
//
//        // 건설 시작시간 = 먼저 지어져야 하는 건물 완성 시간 중 최대값
//        for (int t : preceding[n]) {
//            startTime = Math.max(startTime, calculateBuildTime(t, buildTimes, checked, preceding));
//        }
//
//        buildTimes[n] += startTime;
//        return buildTimes[n];
//    }
//}