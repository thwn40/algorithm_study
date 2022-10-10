package Inhwan.week14;

class 행렬테두리회전하기 {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] table = new int[rows][columns];

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                table[j][i] = columns*j + i+1;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(table, queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
        }

        return answer;
    }

    private int rotate(int[][] table, int x1, int y1, int x2, int y2) {
        int row = x2 - x1;
        int column = y2 - y1;

        x1--;
        y1--;
        x2--;
        y2--;

        int temp = table[x1][y1];
        int min = temp;

        for (int i = 0; i < row; i++) {
            table[x1+i][y1] = table[x1+i+1][y1];
            min = Math.min(min, table[x1+i][y1]);
        }

        for (int i = 0; i < column; i++) {
            table[x2][y1+i] = table[x2][y1+i+1];
            min = Math.min(min, table[x2][y1+i]);
        }

        for (int i = 0; i < row; i++) {
            table[x2-i][y2] = table[x2-i-1][y2];
            min = Math.min(min, table[x2-i][y2]);
        }

        for (int i = 0; i < column; i++) {
            table[x1][y2-i] = table[x1][y2-i-1];
            min = Math.min(min, table[x1][y2-i]);
        }

        table[x1][y1+1] = temp;

        return min;
    }
}