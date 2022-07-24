package SeongJun.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class 유기농배추_1012 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N = 0;
    static int M = 0;

    static boolean[][] field;




    static void bfs(int x, int y) {

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));

        while (!q.isEmpty()) {
            Point poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nX = poll.x + dx[i];
                int nY = poll.y + dy[i];
                if (nX >= 1 && nX <= M && nY >= 1 && nY <= N&&field[nX][nY]) {
                    field[nX][nY]=false;
                    q.offer(new Point(nX,nY));


                }
            }

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


        for (int k = 0; k < T; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            field = new boolean[M+1][N+1];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                field[x+1][y+1] = true;
            }


            int worm =0;
            for (int i = 1; i < M+1; i++) {
                for (int j = 1; j < N+1; j++) {
                    if(field[i][j]){
                        worm++;
                        bfs(i,j);
                    }
                }
            }
            System.out.println(worm);



        }

    }
}
