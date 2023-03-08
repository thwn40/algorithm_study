import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 카카오2019겨울인턴십_크레인인형뽑기 {
    public static void main(String[] args) {
        int[][] board = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = new int[]{1, 5, 3, 5, 1, 2, 1, 4};
        System.out.println(solution(board, moves));
    }
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> basket = new Stack<>();

        Queue<Integer>[] allBoard = new Queue[board.length];

        for (int i = 0; i < board.length; i++) {
            allBoard[i] = new LinkedList<>();
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i]==0) continue;

                allBoard[i].add(board[j][i]);
            }
        }

        basket.add(allBoard[moves[0]-1].poll());
        for (int i = 1; i < moves.length; i++) {
            if (allBoard[moves[i] - 1].isEmpty()) {
                continue;
            }
            int cur = allBoard[moves[i]-1].poll();
            if (basket.isEmpty()) {
                basket.add(cur);
            } else {
                int basketTop = basket.peek();
                if (cur == basketTop) {
                    basket.pop();
                    answer+=2;
                } else {
                    basket.add(cur);
                }
            }
        }
        return answer;
    }
}
