import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 큐빙_5373 {
    static Character[][][] cube;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // cube[0]=뒤 cube[1]=오
        // 2=앞 3=왼 4=위 5=바닥

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            // 반복횟수
            int R = Integer.parseInt(br.readLine());
            cube = new Character[][][]{
                    {{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}},
                    {{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}},
                    {{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}},
                    {{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}},
                    {{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}},
                    {{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}}
            };
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < R; j++) {
                String str = st.nextToken();
                char udfblr = str.charAt(0);
                char D = str.charAt(1);
                rotate(udfblr, D);
                System.out.println("----------------"+(j+1));
                for (int c = 0; c < 6; c++) {
                    for (int t = 0; t < 3; t++) {
                        System.out.println(Arrays.toString(cube[c][t]));
                    }
                    System.out.println();
                }

            }
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    bw.write(cube[4][j][k]);
                }
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
    public static void rotate(char udfblr, char D){
        switch (udfblr){
            case 'U' :
                rotate_U(D);
                break;
            case 'D' :
                rotate_D(D);
                break;
            case 'F' :
                rotate_F(D);
                break;
            case 'B' :
                rotate_B(D);
                break;
            case 'L' :
                rotate_L(D);
                break;
            case 'R' :
                rotate_R(D);
                break;
        }
    }
    public static void rotate_U(char D) {
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                q.add(cube[4][i][j]);
            }
        }
        if (D == '+') {
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    cube[4][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[1][0][0];
            y = cube[1][0][1];
            z = cube[1][0][2];

            cube[1][0][0] = cube[0][0][0];
            cube[1][0][1] = cube[0][0][1];
            cube[1][0][2] = cube[0][0][2];

            cube[0][0][0] = cube[3][0][2];
            cube[0][0][1] = cube[3][0][1];
            cube[0][0][2] = cube[3][0][0];

            cube[3][0][0] = cube[2][0][0];
            cube[3][0][1] = cube[2][0][1];
            cube[3][0][2] = cube[2][0][2];

            cube[2][0][0] = x;
            cube[2][0][1] = y;
            cube[2][0][2] = z;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >=0; j--) {
                    cube[4][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[1][0][0];
            y = cube[1][0][1];
            z = cube[1][0][2];

            cube[1][0][0] = cube[2][0][0];
            cube[1][0][1] = cube[2][0][1];
            cube[1][0][2] = cube[2][0][2];

            cube[2][0][0] = cube[3][0][0];
            cube[2][0][1] = cube[3][0][1];
            cube[2][0][2] = cube[3][0][2];

            cube[3][0][0] = cube[0][0][2];
            cube[3][0][1] = cube[0][0][1];
            cube[3][0][2] = cube[0][0][0];

            cube[0][0][0] = z;
            cube[0][0][1] = y;
            cube[0][0][2] = x;
        }
    }

    public static void rotate_D(char D) {
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                q.add(cube[5][i][j]);
            }
        }
        if (D == '+') {
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    cube[5][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[1][2][0];
            y = cube[1][2][1];
            z = cube[1][2][2];

            cube[1][2][0] = cube[2][2][0];
            cube[1][2][1] = cube[2][2][1];
            cube[1][2][2] = cube[2][2][2];

            cube[2][2][0] = cube[3][2][2];
            cube[2][2][1] = cube[3][2][1];
            cube[2][2][2] = cube[3][2][0];

            cube[3][2][0] = cube[0][2][0];
            cube[3][2][1] = cube[0][2][1];
            cube[3][2][2] = cube[0][2][2];

            cube[0][2][0] = x;
            cube[0][2][1] = y;
            cube[0][2][2] = z;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >= 0; j--) {
                    cube[5][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[1][2][0];
            y = cube[1][2][1];
            z = cube[1][2][2];

            cube[1][2][0] = cube[0][2][2];
            cube[1][2][1] = cube[0][2][1];
            cube[1][2][2] = cube[0][2][0];

            cube[0][2][0] = cube[3][2][0];
            cube[0][2][1] = cube[3][2][1];
            cube[0][2][2] = cube[3][2][2];

            cube[3][2][0] = cube[2][2][2];
            cube[3][2][1] = cube[2][2][1];
            cube[3][2][2] = cube[2][2][0];

            cube[2][2][0] = x;
            cube[2][2][1] = y;
            cube[2][2][2] = z;
        }
    }

    public static void rotate_F(char D) {
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                q.add(cube[2][i][j]);
            }
        }
        if (D == '+') {
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    cube[2][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[1][0][0];
            y = cube[1][1][0];
            z = cube[1][2][0];

            cube[1][0][0] = cube[4][2][0];
            cube[1][1][0] = cube[4][2][1];
            cube[1][2][0] = cube[4][2][2];

            cube[4][2][0] = cube[3][2][2];
            cube[4][2][1] = cube[3][1][2];
            cube[4][2][2] = cube[3][0][2];

            cube[3][0][2] = cube[5][2][2];
            cube[3][1][2] = cube[5][2][1];
            cube[3][2][2] = cube[5][2][0];

            cube[5][2][0] = z;
            cube[5][2][1] = y;
            cube[5][2][2] = x;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >=0; j--) {
                    cube[2][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[4][2][0];
            y = cube[4][2][1];
            z = cube[4][2][2];

            cube[4][2][0] = cube[1][0][0];
            cube[4][2][1] = cube[1][1][0];
            cube[4][2][2] = cube[1][2][0];

            cube[1][0][0] = cube[5][0][2];
            cube[1][1][0] = cube[5][0][1];
            cube[1][2][0] = cube[5][0][0];

            cube[5][0][0] = cube[3][0][2];
            cube[5][0][1] = cube[3][1][2];
            cube[5][0][2] = cube[3][2][2];

            cube[3][2][2] = x;
            cube[3][1][2] = y;
            cube[3][0][2] = z;
        }
    }

    public static void rotate_B(char D) {
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                q.add(cube[0][i][j]);
            }
        }
        if (D == '+') {
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    cube[0][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[4][0][0];
            y = cube[4][0][1];
            z = cube[4][0][2];

            cube[4][0][0] = cube[1][0][2];
            cube[4][0][1] = cube[1][1][2];
            cube[4][0][2] = cube[1][2][2];

            cube[1][0][2] = cube[5][2][2];
            cube[1][1][2] = cube[5][2][1];
            cube[1][2][2] = cube[5][2][0];

            cube[5][2][0] = cube[3][0][0];
            cube[5][2][1] = cube[3][1][0];
            cube[5][2][2] = cube[3][2][0];

            cube[3][0][0] = z;
            cube[3][1][0] = y;
            cube[3][2][0] = x;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >=0; j--) {
                    cube[0][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[1][0][2];
            y = cube[1][1][2];
            z = cube[1][2][2];

            cube[1][0][2] = cube[4][0][0];
            cube[1][1][2] = cube[4][0][1];
            cube[1][2][2] = cube[4][0][2];

            cube[4][0][0] = cube[3][2][0];
            cube[4][0][1] = cube[3][1][0];
            cube[4][0][2] = cube[3][0][0];

            cube[3][0][0] = cube[5][0][2];
            cube[3][1][0] = cube[5][0][1];
            cube[3][2][0] = cube[5][0][1];

            cube[5][0][0] = x;
            cube[5][0][1] = y;
            cube[5][0][2] = z;
        }
    }

    public static void rotate_L(char D) {
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                q.add(cube[3][i][j]);
            }
        }
        if (D == '+') {
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    cube[3][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[0][0][2];
            y = cube[0][1][2];
            z = cube[0][2][2];

            cube[0][0][2] = cube[5][2][0];
            cube[0][1][2] = cube[5][1][0];
            cube[0][2][2] = cube[5][0][0];

            cube[5][0][0] = cube[2][0][0];
            cube[5][1][0] = cube[2][1][0];
            cube[5][2][0] = cube[2][2][0];

            cube[2][0][0] = cube[4][0][0];
            cube[2][1][0] = cube[4][1][0];
            cube[2][2][0] = cube[4][2][0];

            cube[4][0][0] = z;
            cube[4][1][0] = y;
            cube[4][2][0] = x;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >=0; j--) {
                    cube[3][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[0][0][2];
            y = cube[0][1][2];
            z = cube[0][2][2];

            cube[0][0][2] = cube[4][2][0];
            cube[0][1][2] = cube[4][1][0];
            cube[0][2][2] = cube[4][0][0];

            cube[4][0][0] = cube[2][0][0];
            cube[4][1][0] = cube[2][1][0];
            cube[4][2][0] = cube[2][2][0];

            cube[2][0][0] = cube[5][2][0];
            cube[2][1][0] = cube[5][1][0];
            cube[2][2][0] = cube[5][0][0];

            cube[5][0][0] = z;
            cube[5][1][0] = y;
            cube[5][2][0] = x;
        }
    }

    public static void rotate_R(char D) {
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                q.add(cube[1][i][j]);
            }
        }
        if (D == '+') {
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 3; j++) {
                    cube[1][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[0][0][0];
            y = cube[0][1][0];
            z = cube[0][2][0];

            cube[0][0][0] = cube[4][2][2];
            cube[0][1][0] = cube[4][1][2];
            cube[0][2][0] = cube[4][0][2];

            cube[4][0][2] = cube[2][0][2];
            cube[4][1][2] = cube[2][1][2];
            cube[4][2][2] = cube[2][2][2];

            cube[2][0][2] = cube[5][2][2];
            cube[2][1][2] = cube[5][1][2];
            cube[2][2][2] = cube[5][0][2];

            cube[5][0][2] = x;
            cube[5][1][2] = y;
            cube[5][2][2] = z;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 2; j >=0; j--) {
                    cube[1][j][i] = q.poll();
                }
            }
            Character x, y, z;
            x = cube[0][0][0];
            y = cube[0][1][0];
            z = cube[0][2][0];

            cube[0][0][0] = cube[5][2][2];
            cube[0][1][0] = cube[5][1][2];
            cube[0][2][0] = cube[5][0][2];

            cube[5][0][2] = cube[2][0][2];
            cube[5][1][2] = cube[2][1][2];
            cube[5][2][2] = cube[2][2][2];

            cube[2][0][2] = cube[4][0][2];
            cube[2][1][2] = cube[4][1][2];
            cube[2][2][2] = cube[4][2][2];

            cube[4][0][2] = z;
            cube[4][1][2] = y;
            cube[4][2][2] = x;
        }
    }
}
