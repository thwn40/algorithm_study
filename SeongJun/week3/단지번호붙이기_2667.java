package SeongJun.week3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x = 0;
    int y = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}


public class 단지번호붙이기_2667 {


    static int N;
    static int M;
    static boolean[][] maze;
    static int[][] dis;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int danji = 0;

    static int bfs(int x, int y) {
        int count =0;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        maze[x][y]=false;
        dis[x][y]=danji;
        while(!q.isEmpty()){
            Point poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll.x+dx[i];
                int ny = poll.y+dy[i];


                if(nx>=1 && nx<=N && ny>=1 && ny<=N && maze[nx][ny]){

                    maze[nx][ny]=false;
                    q.offer(new Point(nx,ny));
                    dis[nx][ny]=danji;
                    count++;
                }
            }
        }
        danji++;
        return count;


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        maze = new boolean[N+1][N+1];
        dis = new int[N+1][N+1];


        for (int i = 1; i <=N; i++) {
            String s = br.readLine();
            for (int j = 1; j <=N; j++) {
                if (s.charAt(j-1) == '1') {
                    maze[i][j] = true;
                } else {
                    maze[i][j] = false;
                }
            }
        }
//            for (int i = 0; i < N+1; i++) {
//                System.out.println(Arrays.toString(maze[i]));
//            }

        bfs(0,0);
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < N+1; j++) {
                if(maze[i][j]&&dis[i][j]==0){

                    ans.add(bfs(i,j)+1);
                }

            }

        }
//            for (int i = 0; i < N+1; i++) {
//                System.out.println(Arrays.toString(dis[i]));
//            }
//            System.out.println(dis[2][2]);
//            System.out.println(dis[N][M]+1);
        System.out.println(ans.size());
        ans.stream().sorted().forEach(System.out::println);
    }
}

