package SeongWoo.week6;

import java.util.Scanner;

public class 로봇청소_14503 {

    public static class ClearBot {
        //상우하좌 이동하는데 사용되는 배열
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};
        //로봇청소기의 위치
        int row;
        int col;
        //로봇 청소기의 방향
        int direction;
        //주어진 map
        int[][] map;
        //로봇청소기가 청소한 양
        int count = 0;
        //로봇청소기가 멈췄는지 판단하는 flag
        boolean end = false;

        public ClearBot(int row, int col, int direction, int[][] map) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.map = map;
        }

        //현재 위치 청소하는 메서드
        private void clear() {
            //0은 빈칸, 1은 벽, 2는 청소된 공간
            if (map[row][col] == 0) {
                map[row][col] = 2;
                count++;
            }
        }

        //이동할 곳을 탐색하고 이동한다.
        private void searchAndMove() {
            //direction을 바꿔가며 왼쪽을 탐색
            for (int i = 0; i < 4; i++) {
                int leftRow = row + dRow[(direction + 3) % 4];
                int leftCol = col + dCol[(direction + 3) % 4];
                //왼쪽이 청소되어있거나 막혀있으면 반복문을 넘김
                if (map[leftRow][leftCol] == 2 || map[leftRow][leftCol] == 1) {
                    direction = (direction + 3) % 4;
                    continue;
                }
                //왼쪽이 비어있으면 direction을 왼쪽으로 변경하고 한칸이동하고 return으로 메서드 탈출
                direction = (direction + 3) % 4;
                row = row + dRow[direction];
                col = col + dCol[direction];
                return;
            }

            //반복문을 벗어남 -> 사면이 막혀있거나 청소되있음을 의미
            int backRow = row + dRow[(direction + 2) % 4];
            int backCol = col + dCol[(direction + 2) % 4];
            //뒤쪽이 막혀잇으면 로봇청소기 작동끄기
            if (map[backRow][backCol] == 1) {
                end = true;
                return;
            }

            //뒤쪽이 안막혀있다면 뒤로한칸 이동하고 처음부터 탐색 시작
            row = backRow;
            col = backCol;
            searchAndMove();
        }

        //로봇청소기 작동
        public void start() {
            while (!end) {
                clear();
                searchAndMove();
            }
        }
    }

    public static void main(String[] args) {
        //초기화 시작
        Scanner scanner = new Scanner(System.in);
        int rowMax = scanner.nextInt();
        int colMax = scanner.nextInt();
        int[][] map = new int[rowMax][colMax];

        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int direction = scanner.nextInt();
        ClearBot clearBot = new ClearBot(row, col, direction, map);

        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
        //초기화 끝

        clearBot.start();
        System.out.println(clearBot.count);
    }
}
