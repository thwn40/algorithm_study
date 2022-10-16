package Inhwan.week15;

import java.util.ArrayList;
import java.util.List;

class 프렌즈4블록 {
    public int solution(int m, int n, String[] board) {
        char[][] Board = new char[m][n];
        for (int i = 0; i < m; i++) {
            Board[i] = board[i].toCharArray();
        }

        boolean flag = true;

        while (flag) {
            flag = delete(Board);
        }

        return count(Board);
    }

    boolean check(char[][] Board, int i, int j) {
        if (Board[i][j] == '-') return false;

        return (Board[i][j] == Board[i+1][j] &&
                Board[i+1][j] == Board[i][j+1] &&
                Board[i][j+1] == Board[i+1][j+1]);
    }

    void fall(char[][] Board) {
        int m = Board.length;
        int n = Board[0].length;

        List<Character> temp;
        int l = 0;

        for (int i = 0; i < n; i++) {
            temp = new ArrayList<>();

            for (int j = m-1; j > -1; j--) {
                if (Board[j][i] != '-') {
                    temp.add(Board[j][i]);
                }
            }

            l = temp.size();

            for (int j = 0; j < m; j++) {
                if (j < l) {
                    Board[m-1-j][i] = temp.get(j);
                } else{
                    Board[m-1-j][i] = '-';
                }
            }
        }
    }

    boolean delete(char[][] Board) {
        int m = Board.length;
        int n = Board[0].length;

        List<int[]> deleteList = new ArrayList<>();

        for (int i = 0; i < m-1; i++) {
            for (int j = 0; j < n-1; j++) {
                if (check(Board, i, j)) {
                    deleteList.add(new int[] {i,j});
                }
            }
        }

        if (deleteList.size() == 0) return false;

        for (int[] d : deleteList) {
            Board[d[0]][d[1]] = '-';
            Board[d[0]+1][d[1]] = '-';
            Board[d[0]][d[1]+1] = '-';
            Board[d[0]+1][d[1]+1] = '-';
        }

        fall(Board);

        return true;
    }

    int count(char[][] Board) {
        int m = Board.length;
        int n = Board[0].length;
        int c = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (Board[i][j] == '-') c++;
            }
        }

        return c;
    }
}