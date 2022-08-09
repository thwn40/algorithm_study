package SeongWoo.week6;

import java.util.HashMap;
import java.util.Scanner;

public class 정육면체전개도_1917 {
    static int[] dRow = {-1, 0, 1, 0};
    static int[] dCol = {0, 1, 0, -1};
    static HashMap<Integer, Integer> numberSet = new HashMap<>();
    static boolean result;

    //면을 나타내는 클래스
    public static class Plane{
        //면의 위치 번호
        int number;
        int row;
        int col;
        //면과 이어진 면들의 위치 번호를 나타내는 필드
        int up;
        int right;
        int down;
        int left;

        public Plane(int number, int row, int col, int up, int right, int down, int left) {
            this.number = number;
            this.row = row;
            this.col = col;
            this.up = up;
            this.right = right;
            this.down = down;
            this.left = left;
        }

        public Plane() {
        }
    }

    public static void dfs(int[][] board, boolean[] check, Plane plane) {

        for (int i = 0; i < 4; i++) {
            int nextRow = plane.row + dRow[i];
            int nextCol = plane.col + dCol[i];
            if (nextRow < 0 || nextRow >= board.length || nextCol < 0 || nextCol >= board[0].length
                    || board[nextRow][nextCol] != 1) {
                continue;
            }

            Plane nextPlane = new Plane();
            //연결된 면의 변이 이어져있으면 해당 변과 연결된 위치 번호가 이어진다.
            if (dRow[i] != 0) {
                if (dRow[i] == 1) {
                    nextPlane = new Plane(plane.down, nextRow, nextCol, plane.number,
                            plane.right, numberSet.get(plane.number), plane.left);
                } else {
                    nextPlane = new Plane(plane.up, nextRow, nextCol, numberSet.get(plane.number),
                            plane.right, plane.number, plane.left);
                }
            } else {
                if (dCol[i] == 1) {
                    nextPlane = new Plane(plane.right, nextRow, nextCol, plane.up,
                            numberSet.get(plane.number), plane.down, plane.number);
                } else {
                    nextPlane = new Plane(plane.left, nextRow, nextCol, plane.up,
                            plane.number, plane.down, numberSet.get(plane.number));
                }
            }

            //정육면체의 위치번호가 겹치면 정육면체를 만들 수 없다.
            if (check[nextPlane.number]) {
                result = false;
                return;
            }

            check[nextPlane.number] = true;
            //board에서 탐색한 위치는 0으로 만들어서 다시 탐색하지 않도록 한다.
            board[plane.row][plane.col] = 0;
            dfs(board, check, nextPlane);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //면의 반대 위치 설정
        numberSet.put(0, 5);
        numberSet.put(5, 0);
        numberSet.put(1, 3);
        numberSet.put(3, 1);
        numberSet.put(2, 4);
        numberSet.put(4, 2);

        for (int k = 0; k < 3; k++) {
            int[][] board = new int[6][6];
            boolean[] check = new boolean[6];
            Plane firstPlane = new Plane();
            result = true;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    int element = scanner.nextInt();
                    //탐색을 시작할 첫번재 plane 설정
                    if (!check[0] && element == 1) {
                        firstPlane = new Plane(0, i, j, 1, 2, 3, 4);
                        check[0] = true;
                    }
                    board[i][j] = element;
                }
            }

            dfs(board, check, firstPlane);
            if (result) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }
}
