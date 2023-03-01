package SeongWoong.week28;

public class 카카오2022블라인드_사라지는발판 {
    public static void main(String[] args) {
        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};
//        int[][] board = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
//        int[] aloc = {1, 0};
//        int[] bloc = {1, 2};
        System.out.println(solution(board, aloc, bloc));
    }

    static int min;
    static int[][] boards;

    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        min = Integer.MAX_VALUE;
        boards = board;
        dfs(new Player(aloc[0], aloc[1]), new Player(bloc[0], bloc[1]), 0, 1);
        return min;
        //전체를 dfs 돌리면서 최소값 갱신
        // 12:50
    }


    public static void dfs(Player A, Player B, int cnt, int turn) {
        int[] dy = new int[]{1, -1, 0, 0};
        int[] dx = new int[]{0, 0, 1, -1};
        int cy = 0;
        int cx = 0;
        if (turn == 1) {
            cy = A.y;
            cx = A.x;
        } else {
            cy = B.y;
            cx = B.x;
        }

        if (A.y == B.y && A.x == B.x) {
            min = Math.min(min, cnt + 1);
            return;
        }
        boolean fin = true;
        for (int i = 0; i < 4; i++) {
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if (ny < 0 || nx < 0 || ny >= boards.length || nx >= boards.length) {
                continue;
            }
            if (boards[ny][nx] == 1) {
                boards[ny][nx] = 0;
                if (turn == 1) {
                    dfs(new Player(ny, nx), B, cnt + 1, 2);
                    fin = false;
                } else {
                    dfs(A, new Player(ny, nx), cnt + 1, 1);
                    fin = false;
                }
                boards[ny][nx] = 1;
            }
            if (fin) {
                min = Math.min(min, cnt);
            }
        }
        return;
    }


    public static class Player {
        int y;
        int x;

        public Player(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
