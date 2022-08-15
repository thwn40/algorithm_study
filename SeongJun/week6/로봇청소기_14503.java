package SeongJun.week6;

import java.util.Scanner;



public class 로봇청소기_14503 {
    static Scanner sc = new Scanner(System.in);
    static int N = sc.nextInt(), M = sc.nextInt(), r = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt();
    static int[][] room;

    public static void main(String[] args){


        room = new int[N][M];
        RoboticVacuum roboticVacuum = new RoboticVacuum(c, r, d);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                room[i][j] = sc.nextInt();
            }
        }

        roboticVacuum.clean();

        System.out.println(roboticVacuum.moveCount - 1);
    }

    static class RoboticVacuum {
        int x = 0, y = 0,direction = 0, moveCount = 1;
        int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0 ,-1};



        void clean() {
            //더러우면 청소
            if (room[y][x] == 0) room[y][x] = moveCount++;

            //그리고 탐색
            search();
        }


        void search() {

            boolean reachable = false;

            for (int i = 0; i < 4; i++) {
                rotate();
                if (isInside(x + dx[direction], y + dy[direction]) && room[y + dy[direction]][x + dx[direction]] == 0) {
                    move(false);
                    clean();
                    reachable = true;
                    break;
                }
            }

            if (!reachable && !behindIsWall(direction)) {
                move(true);
                search();
            }
        }


        boolean isInside(int currentX, int currentY) {
            return currentY >= 0 && currentY <= N - 1 && currentX >= 0 && currentX <= M - 1;
        }

        boolean behindIsWall(int direction) {
            if (direction > 1)
                return room[y + dy[direction - 2]][x + dx[direction - 2]] == 1;
            else
                return room[y + dy[direction + 2]][x + dx[direction + 2]] == 1;

        }


        void rotate() {
            direction = direction == 0 ? 3 : direction - 1;
        }

        void move(boolean reverse) {
            if (!reverse) {
                if (direction == 0) y = y - 1;
                if (direction == 1) x = x + 1;
                if (direction == 2) y = y + 1;
                if (direction == 3) x = x - 1;
            }
            else {
                if (direction == 0) y = y + 1;
                if (direction == 1) x = x - 1;
                if (direction == 2) y = y - 1;
                if (direction == 3) x = x + 1;
            }
        }


        public RoboticVacuum(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}

