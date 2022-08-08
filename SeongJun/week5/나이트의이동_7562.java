package SeongJun.week5;

import java.util.*;

//체스의 좌표를 담기위한 객체
class Position {
    int x = 0;
    int y = 0;
    int dis = 0;

    public Position(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }

    @Override
    public boolean equals(Object obj) {
        Position positionB = (Position) obj;
        return (this.x == positionB.x) && (this.y == positionB.y);
    }

}


public class 나이트의이동_7562 {
    static int[] dx = {-2, -1, 1, 2};
    static int[] dy = {1, 2, 2, 1};
    static boolean[][] board;

    static int boardLength;

    static void bfs(Position currentPosition, Position destinationPosition) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(currentPosition);
        //처음 시작점 방문체크
        board[currentPosition.x][currentPosition.y] = true;

        while (!queue.isEmpty()) {

            Position position = queue.poll();

            //도착지면 리턴
            if (position.equals(destinationPosition)) {
                System.out.println(position.dis);
                return;
            }

            for (int i = 0; i < 4; i++) {
                //현재 좌표
                int currentX = position.x + dx[i];
                int currentY = position.y + dy[i];

                if (isInside(currentX, currentY)) {

                    queue.offer(new Position(currentX, currentY, position.dis + 1));
                }

                currentX = position.x + dx[i];

                currentY = position.y - dy[i];

                if (isInside(currentX, currentY)) {
//                    System.out.println("currentX = " + currentX+"  currentY = " + currentY);
                    queue.offer(new Position(currentX, currentY, position.dis + 1));
                }


            }
        }
    }

    // 체스말이 체스판 안에 있는지 확인하는 함수
    static boolean isInside(int currentX, int currentY) {
        if (currentX >= 0 && currentX <= boardLength - 1 && currentY >= 0 && currentY <= boardLength - 1 && !board[currentX][currentY]) {
            board[currentX][currentY] = true;
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();


        for (int i = 0; i < T; i++) {
            boardLength = sc.nextInt();
            board = new boolean[boardLength][boardLength];
            //도착지
            Position currentPosition = new Position(sc.nextInt(), sc.nextInt(), 0);
            //종착지
            Position destinationPosition = new Position(sc.nextInt(), sc.nextInt(), 0);
            bfs(currentPosition, destinationPosition);

        }
    }
}
