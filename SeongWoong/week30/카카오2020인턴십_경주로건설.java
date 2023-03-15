import java.util.*;
public class 카카오2020인턴십_경주로건설 {
    public static void main(String[] args) {

    }

    class Solution {
        private static int len;
        private static int[][] map;
        private static boolean[][][] visit;

        private static int[] dx = {-1, 0, 1, 0}; //상우하좌
        private static int[] dy = {0, 1, 0 ,-1};

        private static int cost = Integer.MAX_VALUE;

        public int solution(int[][] board) {
            int answer = 0;

            len = board.length;

            map = board;
            visit = new boolean[len][len][4];

            bfs(0,0,-1,0);

            answer = cost;

            return answer;
        }

        private void bfs(int x, int y, int dir, int price) {

            Queue<Road> q = new LinkedList();
            q.add(new Road(x,y,dir,price));
            visit[x][y][0] = visit[x][y][1] = visit[x][y][2] = visit[x][y][3] = true;

            while (!q.isEmpty()) {
                Road curRoad = q.remove();

                int cx = curRoad.x;
                int cy = curRoad.y;
                int cDir = curRoad.dir;
                int cPrice = curRoad.cost;

                if (cx == len -1 && cy == len -1) {
                    cost = Math.min(cost, cPrice);
                }

                for (int i=0; i<dx.length; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    int nDir = i;
                    int nPrice = cPrice;

                    if (nx < 0 || ny < 0 || nx >= len || ny >= len || map[nx][ny] == 1) {
                        continue;
                    }

                    if (cDir == -1) {
                        //처음엔 직선
                        nPrice += 100;
                    } else if (cDir == nDir) {
                        //방향 같을 때
                        nPrice += 100;
                    } else {
                        //방향 다를 때
                        nPrice += 600;
                    }

                    if (!visit[nx][ny][i] || map[nx][ny] >= nPrice) {
                        //방문 x, 이전 값이 더 클 경우 작은값을 넣어준다.
                        visit[nx][ny][i] = true;
                        map[nx][ny] = nPrice;
                        q.add(new Road(nx, ny, nDir, nPrice));
                    }

                }
            }

        }

    }


    class Road {
        int x, y, dir, cost;

        Road(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
}

