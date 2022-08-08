package GyungMin.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로탐색_2178 {
    static boolean [][] visitMaze;
    static int [][] maze;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int height;
    static int width;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        maze = new int[height][width];
        visitMaze = new boolean[height][width];

        String oneLine;

        for (int i = 0; i < height; i++) {
            oneLine = br.readLine();
            for (int j = 0; j < width; j++) {
                maze[i][j] = oneLine.charAt(j) - '0';
            }
        }

        visitMaze[0][0] = true;
        maze_running(0, 0);
        System.out.println(maze[height - 1][width - 1]);


    }

    static void maze_running(int x, int y) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{y, x});

        while (!que.isEmpty()) {
            int [] cur = que.poll();
            int cy = cur[0];
            int cx = cur[1];

            // 상하좌우
            for (int i = 0; i < 4; i++) {
                int curY = cy + dy[i];
                int curX = cx + dx[i];

                if(curX < 0 || width <= curX || curY < 0 || height <= curY ) {
                    continue;
                }
                if (visitMaze[curY][curX] == true || maze[curY][curX] == 0) {
                    continue;
                }

                que.add(new int[]{curY, curX});
                maze[curY][curX] = maze[cy][cx] + 1;
                visitMaze[curY][curX] = true;
            }
        }

    }

}
