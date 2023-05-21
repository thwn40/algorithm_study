package SeongWoo.week29;

import java.util.Stack;

public class 크레인_인형뽑기_게임 {

    private static int drawing(int[][] board, int move) {

        int result = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i][move - 1] == 0) {
                continue;
            }
            result = board[i][move - 1];
            board[i][move - 1] = 0;
            return result;
        }
        return result;
    }

    public int solution(int[][] board, int[] moves) {

        int result = 0;
        Stack<Integer> stack = new Stack<>();

        for (int move : moves) {
            int puppet = drawing(board, move);
            if (puppet == 0) {
                continue;
            }
            if (stack.isEmpty()) {
                stack.push(puppet);
                continue;
            }

            Integer peek = stack.peek();
            if (peek == puppet) {
                stack.pop();
                result += 2;
            } else {
                stack.push(puppet);
            }
        }

        return result;
    }
}
