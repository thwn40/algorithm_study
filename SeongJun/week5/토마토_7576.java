package SeongJun.week5;

import java.util.*;


public class 토마토_7576 {
    static ArrayList<Position> startingPoint = new ArrayList<>();

    static int[][] board;
    //
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int M;
    static int N;

    static int count = 0;

    static int bfs() {

        Queue<Position> queue = new LinkedList<>(startingPoint);
        for (Position position : startingPoint) {
            board[position.x][position.y]=1;
        }
        int max = 0;
        while (!queue.isEmpty()) {

            Position poll = queue.poll();
            count--;
            max = Math.max(max, poll.dis);
            for (int i = 0; i < 4; i++) {
                int currentX = poll.x + dx[i];
                int currentY = poll.y + dy[i];

                if(isInside(currentX,currentY)) {

                    queue.offer(new Position(currentX,currentY, poll.dis+1));

                }
            }
        }
        return max;

    }

    static boolean isInside(int currentX, int currentY) {

        if (currentX >= 0 && currentX <= M - 1 && currentY>=0 && currentY <= N-1 && board[currentX][currentY]==0){
            board[currentX][currentY]=1;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        N = sc.nextInt();
        M = sc.nextInt();


        board = new int[M][N];
        count = M*N;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
                if(board[i][j] == -1){
                    count--;
                }
                if (board[i][j] == 1) {

                    startingPoint.add(new Position(i, j, 0));
                }
            }
        }
        int result = bfs();
        if(count>0) result = -1;
        System.out.println(result);
    }
}
