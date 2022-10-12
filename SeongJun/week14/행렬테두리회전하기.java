package SeongJun.week14;

import java.util.Arrays;

public class 행렬테두리회전하기 {

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int num = 1;
        int[][] board = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j]=num++;
            }
        }

        int index = 0;
        for (int[] query : queries) {
            int startRow = query[0]-1;
            int startColumn = query[1]-1;
            int endRow = query[2]-1;
            int endColumn = query[3]-1;
            int min = Integer.MAX_VALUE;
            //윗쪽 모서리
            int upperEdgeLast = board[startRow][endColumn];
            for (int i = endColumn; i > startColumn; i--) {
                board[startRow][i] = board[startRow][i-1];
                min=Math.min(min,board[startRow][i-1]);
            }
            int upperEdgeLast2 = board[startRow][endColumn];
            board[startRow][endColumn]=upperEdgeLast;
            int lowerEdgeLast = board[endRow][endColumn];

            //오른쪽 모서리
            for(int i = endRow; i> startRow; i--){
                board[i][endColumn] = board[i-1][endColumn];
                min=Math.min(min,board[i-1][endColumn]);
            }
            int lowerEdgeLast2= board[endRow][endColumn];
            board[startRow][endColumn]=upperEdgeLast2;
            board[endRow][endColumn]=lowerEdgeLast;

            int lowerEdgeFirst = board[endRow][startColumn];

            //아랫쪽 모서리
            for (int i = startColumn; i < endColumn; i++) {
                board[endRow][i] = board[endRow][i+1];
                min=Math.min(min,board[endRow][i+1]);
            }
            board[endRow][endColumn]=lowerEdgeLast2;

            int lowerEdgeFirst2 = board[endRow][startColumn];
            board[endRow][startColumn]=lowerEdgeFirst;

            for(int i = startRow; i< endRow; i++){
                board[i][startColumn] = board[i+1][startColumn];
                min=Math.min(min,board[i+1][startColumn]);
            }
            board[endRow][startColumn]=lowerEdgeFirst2;


            answer[index]=min;
            index++;

        }




        return answer;
    }



    public static void main(String[] args) {
        행렬테두리회전하기 s = new 행렬테두리회전하기();
//        s.solution(6,6,new int[][]{{2,2,5,4},{3,3,6,6},{5,1,6,3}});
        System.out.println(Arrays.toString(s.solution(3, 3, new int[][]{{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}})));;

    }
}
