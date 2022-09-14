package SeongJun.week8;



import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 안전영역_2468 {
    static int[][] area;
    static boolean[][] visited;
    static int N;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        area = new int[N][N];

        int Max = Integer.MIN_VALUE;
        int Min = Integer.MAX_VALUE;
        int altitude = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                altitude = sc.nextInt();
                Max = Math.max(Max, altitude);


                area[i][j] = altitude;
            }
        }
        int[] dist = new int[Max+1];
        for (int i = 0; i < Max; i++) {

            int count = 0;
            visited = new boolean[N][N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (area[j][k] <= i) {
                        visited[j][k] = true;
                    }
                }
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if(!visited[j][k] &&area[j][k]>=i){
                        bfs(j,k,i);
                        count++;
                    }
                }
            }
            dist[i]=count;


        }
        int answer = 0;
        for (int i = 0; i < dist.length; i++) {
            answer=Math.max(answer,dist[i]);
        }
        System.out.println(answer);

    }

    static void bfs(int y, int x ,int water) {
        Queue<city> q = new LinkedList<>();
        q.offer(new city(y,x));
        visited[y][x] = true;
        while (!q.isEmpty()) {

            city currCity = q.poll();
//            System.out.println(currCity);
            y = currCity.y;
            x = currCity.x;

            for (int i = 0; i < 4; i++) {
                int currY = y + dy[i];
                int currX = x + dx[i];

                // 안에 있고, 방문안했고, 안전한곳
                if ((currY >= 0 && currY <= N - 1 && currX >= 0 && currX <= N - 1) && !visited[currY][currX] && area[currY][currX]>=water) {
                    visited[currY][currX] = true;
                    q.offer(new city(currY,currX));
                }
            }
        }
    }

    static class city {
        int y = 0;
        int x = 0;

        @Override
        public String toString() {
            return "{" +
                    "" + y +
                    "," + x +
                    '}';
        }

        public city(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
