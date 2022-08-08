package GyungMin.week3;

/*
문제) 단지 번호 붙이기
    <그림 1>과 같이 정사각형 모양의 지도가 있다.
    1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
    철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
    여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다.
    대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다.
    지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.

입력)
    첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고,
    그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.

출력)
    첫 번째 줄에는 총 단지수를 출력하시오.
    그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.

*/

import java.io.*;
import java.util.*;

public class 단지번호붙이기_2667 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static boolean [][] visitMap;
    static int [][] map;
    static int N;
    static int nowY;
    static int nowX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visitMap = new boolean[N][N];
        int returnX = 0;
        int returnY = 0;
        String oneLine = "";
        int tmp = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < N; i++) {
            oneLine = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = oneLine.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visitMap[i][j]) {
                    tmp = bfs(i, j);
                    if (tmp != 0) ans.add(tmp);
                    count ++;
                }

            }
        }

        System.out.println(count);
        Collections.sort(ans);
        ans.forEach(System.out::println);

        br.close();

    }

    static public int bfs(int y, int x) {
        Queue<int[]> que = new LinkedList<>();
        int[] q = new int[] {y, x};
        que.offer(q);
        // 방문
        visitMap[q[0]][q[1]] = true;
        int cnt = 0;

        while (!que.isEmpty()) {
            int [] cur = que.poll();
            int cy = cur[0];
            int cx = cur[1];
            cnt++;

            // 상하좌우
            for (int i = 0; i < 4; i++) {
                int curY = cy + dy[i];
                int curX = cx + dx[i];


                if (curX < 0 || N <= curX || curY < 0 || N <= curY) continue;
                if (!visitMap[curY][curX] && map[curY][curX] == 1) {
                    que.offer(new int[]{curY, curX});
                    visitMap[curY][curX] = true;
                }
            }
        }

        return cnt;
    }
}