import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 스도쿠_2580_풀이1 {
    static int[][] sdoku;
    static int[][] mini;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 주어진 스도쿠판
        sdoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sdoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 빈칸에 1~9까지 입력
        run(0, 0);
    }

    static void run(int y, int x) throws IOException {
        //종료조건 모든 행렬을 다채웠을때
        if (y == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    bw.write(sdoku[i][j] + " ");
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
            System.exit(0);
        }
        //한 행을 다채우면 다음열로 넘어간다
        if (x == 9) {
            run(y + 1, 0);
            return;
        }
        // 다채울때까지 아래 실행
        // 해당칸이 빈칸이라면
        if (sdoku[y][x] == 0) {
            //해당칸에 둘 수 있는가 확인하기
            for (int c = 1; c <= 9; c++) {
                //해당칸을포함하는 작은 3*3 찾기
                mini(y, x);
                if (chk(y, x, c) == true) {
                    //만족하면 해당칸은 c로 두기
                    sdoku[y][x] = c;
                    //그 옆칸 실행
                    run(y, x + 1);
                }
            }
            //해당칸에 둘 수 없다면
//			다시 해당칸을 0으로 만들고 그 전 칸 실행
            sdoku[y][x] = 0;
            return;
        }
        //해당칸이 빈칸이 아니라면 옆칸 실행
        run(y, x + 1);
    }

    // 둘 수 있는 지 확인
    static boolean chk(int y, int x, int c) {
        // 1 행비교
        for (int i = 0; i < 9; i++) {
            if (sdoku[y][i] == c) {
                return false;
            }
        }

        // 2 열비교
        for (int i = 0; i < 9; i++) {
            if (sdoku[i][x] == c) {
                return false;
            }
        }
        // 3 미니비교(3x3)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mini[i][j] == c) {
                    return false;
                }
            }
        }

        // 놓을 수 있으면 t 없으면 f
        return true;
    }

    // 미니생성
    static void mini(int y, int x) {
        mini = new int[3][3];
        if (y < 3) {
            if (x < 3) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        mini[i][j] = sdoku[i][j];
                    }
                }
            } else if (3 <= x && x < 6) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {
                        mini[i][j - 3] = sdoku[i][j];
                    }
                }
            } else if (6 <= x && x < 9) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {
                        mini[i][j - 6] = sdoku[i][j];
                    }
                }
            }
        } else if (3 <= y && y < 6) {
            if (x < 3) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        mini[i - 3][j] = sdoku[i][j];
                    }
                }
            } else if (3 <= x && x < 6) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 3; j < 6; j++) {
                        mini[i - 3][j - 3] = sdoku[i][j];
                    }
                }
            } else if (6 <= x && x < 9) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 6; j < 9; j++) {
                        mini[i - 3][j - 6] = sdoku[i][j];
                    }
                }
            }
        } else if (6 <= x && x < 9) {
            if (y < 3) {
                for (int i = 6; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        mini[i - 6][j] = sdoku[i][j];
                    }
                }
            } else if (3 <= y && y < 6) {
                for (int i = 6; i < 9; i++) {
                    for (int j = 3; j < 6; j++) {
                        mini[i - 6][j - 3] = sdoku[i][j];
                    }
                }
            } else if (6 <= y && y < 9) {
                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        mini[i - 6][j - 6] = sdoku[i][j];
                    }
                }
            }
        }
    }

}