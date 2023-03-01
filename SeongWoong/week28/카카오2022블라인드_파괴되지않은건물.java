package SeongWoong.week28;

public class 카카오2022블라인드_파괴되지않은건물 {
    public static void main(String[] args) {
        int[][] board = new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        System.out.println(solution(board, skill));
        //13:01 ~ 13:10 시간초과
        // ~
    }
    public static int solution(int[][] board, int[][] skill) {
    int answer = 0;
    for (int i = 0; i < skill.length; i++) {
        int type = skill[i][0];
        int r1 = skill[i][1];
        int c1 = skill[i][2];
        int r2 = skill[i][3];
        int c2 = skill[i][4];
        int degree = skill[i][5];

        if (type == 1) {
            for (int j = r1; j <= r2; j++) {
                for (int k = c1; k <= c2; k++) {
                    board[j][k] -= degree;
                }
            }
        } else {
            for (int j = r1; j <= r2; j++) {
                for (int k = c1; k <= c2; k++) {
                    board[j][k] += degree;
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
}
