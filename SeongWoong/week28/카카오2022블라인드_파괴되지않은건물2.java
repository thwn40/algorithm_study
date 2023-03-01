package SeongWoong.week28;

import java.util.ArrayList;

public class 카카오2022블라인드_파괴되지않은건물2 {
    public static void main(String[] args) {
        int[][] board = new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        System.out.println(solution(board, skill));
        //13:01 ~ 13:10 시간초과
        // ~ 14:00  75점
    }

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        ArrayList<Work>[][] start = new ArrayList[board.length][board[0].length];

        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];

            if (type == 1) {
                if (start[r1][r2]==null) {
                    start[r1][r2] = new ArrayList<>();
                    start[r1][r2].add(new Work(c1, c2, -degree));
                } else {
                    boolean find = false;
                    for (Work work : start[r1][r2]) {
                        if (work.endX == c1 && work.endY == c2) {
                            work.changeCost(-degree);
                            find = true;
                        }
                    }
                    if (!find) {
                        start[r1][r2].add(new Work(c1, c2, -degree));
                    }
                }
            } else {
                if (start[r1][r2]==null) {
                    start[r1][r2] = new ArrayList<>();
                    start[r1][r2].add(new Work(c1, c2, degree));
                } else {
                    boolean find = false;
                    for (Work work : start[r1][r2]) {
                        if (work.endX == c1 && work.endY == c2) {
                            work.changeCost(degree);
                            find = true;
                        }
                    }
                    if (!find) {
                        start[r1][r2].add(new Work(c1, c2, degree));
                    }
                }
            }
        }

        for (int i = 0; i < start.length; i++) {
            for (int j = 0; j < start[0].length; j++) {
                if (start[i][j]==null) continue;
                for (Work works : start[i][j]) {
                    for (int k = i; k <= j; k++) {
                        for (int l = works.endX; l <= works.endY; l++) {
                            board[k][l] += works.cost;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    static class Work{
        int endX;

        int endY;

        int cost;
        public Work(int endX, int endY, int cost) {
            this.endX = endX;
            this.endY = endY;
            this.cost = cost;
        }

        public void changeCost(int cost) {
            this.cost += cost;
        }
    }
}
