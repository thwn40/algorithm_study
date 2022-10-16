package SeongWoong.week15;

import java.util.*;

public class 카카오2018_1차_프렌즈4블록 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] arr = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(solution.solution(4,5,arr));
        // ==> 14
    }
    static class Solution {
        public int solution(int m, int n, String[] board) {

            Character[][] board1 = new Character[m][n];

            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    board1[i][j] = board[i].charAt(j);
                }
            }

            boolean fin = false;
            int cnt = 0;

            while(!fin){
                boolean[][] boomOrNot = new boolean[m][n];
                fin = true;
                for(int i=m-1;i>0;i--){
                    for(int j=0;j<n-1;j++){
                        if(board1[i][j]==board1[i-1][j] && board1[i][j] == board1[i][j+1] &&
                                board1[i][j] == board1[i-1][j+1] && board1[i][j] != 'x'){
                            boomOrNot[i][j] = true;
                            boomOrNot[i-1][j] = true;
                            boomOrNot[i][j+1] = true;
                            boomOrNot[i-1][j+1] = true;
                            fin = false;
                        }
                    }
                }
                if(fin) break;

                for(int i=0; i< m;i++){
                    for(int j=0;j<n;j++){
                        if(boomOrNot[i][j]==true){
                            board1[i][j] = 'x';
                            cnt++;
                        }
                    }
                }
                for(int j=0;j<n;j++){
                    Queue<Character> queue = new LinkedList<>();
                    for(int i=m-1;i>=0;i--){
                        if(board1[i][j] != 'x'){
                            queue.add(board1[i][j]);
                        }
                    }
                    for(int i=m-1;i>=0;i--){
                        if(queue.isEmpty()){
                            board1[i][j] = 'x';
                        }else{
                            board1[i][j] = queue.poll();
                        }
                    }
                }
            }

            return cnt;
        }
    }
}