package SeongWoong.week28;

public class 카카오2022블라인드_파괴되지않은건물3 {
    public static void main(String[] args) {
        int[][] board = new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        System.out.println(solution(board, skill));
        //포기 - 해설 - 누적합방식
    }

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] nooHap = new int[board.length + 1][board[0].length + 1];

        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            if (type == 1) {
                nooHap[r1][c1] -= degree;
                nooHap[r1][c2 + 1] += degree;
                nooHap[r2 + 1][c1] += degree;
                nooHap[r2 + 1][c2 + 1] -= degree;
            } else {
                nooHap[r1][c1] += degree;
                nooHap[r1][c2 + 1] -= degree;

                nooHap[r2 + 1][c1] -= degree;
                nooHap[r2 + 1][c2 + 1] += degree;
            }
        }
        for (int i = 0; i < nooHap.length; i++) {
            for (int j = 1; j < nooHap[0].length; j++) {
                nooHap[i][j] += nooHap[i][j - 1];
            }
        }
        for (int i = 1; i < nooHap.length; i++) {
            for (int j = 0; j < nooHap[0].length; j++) {
                nooHap[i][j] += nooHap[i - 1][j];
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += nooHap[i][j];
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
