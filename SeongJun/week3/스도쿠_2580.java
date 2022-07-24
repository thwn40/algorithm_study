package SeongJun.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 스도쿠_2580 {
    static int[][] sudoku = new int[9][9];
    static HashMap<Integer,Integer> gibon = new HashMap<>();
    static HashMap<Integer,Integer> hashMap = new HashMap<>();
    static int sum =0;

    static boolean garo(int x, int y,int k){
        for (int i : sudoku[x]) {
            if(i==k){
                return false;
            }
        }
        return true;
    }

    static boolean sero(int x, int y,int k){
        for (int i = 0; i < 9; i++) {
            if(sudoku[i][y]==k){
                return false;
            }
        }
        return true;
    }

    static boolean box(int x, int y,int k){
        x=(x/3)*3;
        y=(y/3)*3;
        for (int i = x; i < x+3; i++) {
            for (int j = y; j < y+3; j++) {
                if(k==sudoku[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void dfs(int row, int col) {
        System.out.println("dfs("+row+","+col+")");
        for (int[] ints : sudoku) {
            System.out.println(Arrays.toString(ints).replace("[","").replace("]","").replace(",",""));
        }
        if(col == 9) {
            dfs(row+1,0);
            return;
        }

        if(row == 9) {
            for (int[] ints : sudoku) {
                System.out.println(Arrays.toString(ints).replace("[","").replace("]","").replace(",",""));
            }
            System.exit(0);

        }

        if(sudoku[row][col]==0) {
            for(int i = 1 ; i <= 9 ; i++) {
                if(garo(row, col, i)&&sero(row, col, i)&&box(row, col, i)) {
                    sudoku[row][col] = i;
                    dfs(row, col+1);
                }
            }
            sudoku[row][col]=0;
            return;
        }
        dfs(row, col+1);	//빈칸이 아닌경우
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            gibon.put(i+1,0);
            hashMap.put(i+1,0);
            for (int j = 0; j < 9; j++) {
                sudoku[i][j]= Integer.parseInt(st.nextToken());
            }

        }

//        for (int k = 0; k < 9; k++) {
//            for (int j = 0; j < 9; j++) {
//                if(sudoku[k][j]==0) {
//                    for(int i = 1 ; i <= 9 ; i++) {
//                        if(garo(k,j, i)&&sero(k, j, i)&&box(k, j, i)) {
//                            sudoku[k][j] = i;
//                            break;
////                            dfs(k, j+1);
//                        }
//                    }
////                    sudoku[k][j]=0;
//                }
//            }
//        }
//        for (int[] ints : sudoku) {
//            System.out.println(Arrays.toString(ints).replace("[","").replace("]","").replace(",",""));
//        }

        dfs(0,0);



    }
}
