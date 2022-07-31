package SeongWoo.week4;

import java.util.ArrayList;
import java.util.Scanner;

public class 테트로미노_14500 {

    public static class Location {   //row, col의 값을 갖는 클래스
        int row;
        int col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static class Tetromino {
        Location[] blockList = new Location[3];
        Location[] blockLocation = new Location[4];

        public Tetromino(Location block1, Location block2, Location block3) {
            this.blockList[0] = block1;
            this.blockList[1] = block2;
            this.blockList[2] = block3;
        }

        public void setBlockLocation(int row, int col) {   //주어진 좌표에서 연결된 block들을 세팅
            this.blockLocation[0] = new Location(row, col);
            for (int i = 1; i < 4; i++) {
                this.blockLocation[i] = (new Location(row + blockList[i - 1].row, col + blockList[i - 1].col));
            }
        }

        public void cycle() {   //테트로미노를 90도 회전시킨 좌표로 세팅
            for (Location block : blockList) {
                int tempRow = block.row;
                block.row = block.col;
                block.col = -tempRow;
            }
        }
    }

    public static int getMaxPoint(Tetromino tetromino, int[][] board) {   //주어진 테트로미노로 얻을 수 있는 최대 점수를 구하는 메서드
        int result = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                tetromino.setBlockLocation(i, j);   //시작 좌표 세팅

                if (!isEdge(tetromino, board.length, board[0].length)) {   //블럭위치가 주어진 board의 경계값을 넘었다면 continue
                    continue;
                }

                int point = 0;
                for (Location location : tetromino.blockLocation) {   //테트로미노의 block 위치의 점수를 구함
                    point += board[location.row][location.col];
                }

                if (point > result) {
                    result = point;
                }
            }
        }
        return result;
    }

    public static boolean isEdge(Tetromino tetromino, int rowEdge, int colEdge) {
        for (Location location : tetromino.blockLocation) {
            if (location.row < 0 || location.row >= rowEdge || location.col < 0 || location.col >= colEdge) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        //입력 & 초기화 시작
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        int result = 0;
        int[][] board = new int[row][col];
        ArrayList<Tetromino> tetrominoList = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        tetrominoList.add(new Tetromino(new Location(0, 1), new Location(0, 2), new Location(0, 3))); //I
        tetrominoList.add(new Tetromino(new Location(0, 1), new Location(1, 1), new Location(1, 0))); //O
        tetrominoList.add(new Tetromino(new Location(0, 1), new Location(0, 2), new Location(-1, 1))); //T
        tetrominoList.add(new Tetromino(new Location(0, 1), new Location(0, 2), new Location(-1, 2))); //L
        tetrominoList.add(new Tetromino(new Location(0, 1), new Location(0, 2), new Location(-1, 0))); //J
        tetrominoList.add(new Tetromino(new Location(0, 1), new Location(-1, 1), new Location(-1, 2))); //S
        tetrominoList.add(new Tetromino(new Location(0, 1), new Location(1, 1), new Location(1, 2))); //Z
        //입력 & 초기화 끝

        for (Tetromino tetromino : tetrominoList) {   //각 테트로미노로 얻을 수 있는 최대 점수를 비교해서 result를 구한다.
            for (int i = 0; i < 4; i++) {
                int maxPoint = getMaxPoint(tetromino, board);
                if (maxPoint > result) {
                    result = maxPoint;
                }
                tetromino.cycle();
            }
        }

        System.out.println(result);
    }
}
