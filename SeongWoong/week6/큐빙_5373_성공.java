import java.io.*;
import java.util.StringTokenizer;

public class 큐빙_5373_2 {
    static Character[][][] cube;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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
        rotate_180(0);
        rotate_270(1);
        rotate_90(3);
        rotate_basic(4,0,2,3,1,D);
        rotate_180(0);
        rotate_90(1);
        rotate_270(3);
    }
    public static void rotate_D(char D) {
        rotate_90(1);
        rotate_270(3);
        rotate_180(0);
        rotate_basic(5,2,0,3,1,D);
        rotate_270(1);
        rotate_90(3);
        rotate_180(0);

    }
    public static void rotate_F(char D) {
        rotate_basic(2,4,5,3,1,D);
    }
    public static void rotate_B(char D) {
        rotate_180(4);
        rotate_180(5);
        rotate_basic(0,4,5,1,3,D);
        rotate_180(4);
        rotate_180(5);
    }
    public static void rotate_L(char D) {
        rotate_270(4);
        rotate_90(5);
        rotate_basic(3,4,5,0,2,D);
        rotate_90(4);
        rotate_270(5);
    }
    public static void rotate_R(char D) {
        rotate_270(5);
        rotate_90(4);
        rotate_basic(1,4,5,2,0,D);
        rotate_90(5);
        rotate_270(4);
    }

    public static void rotate_90(int K){
        Character[][] temp = new Character[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][3-j-1] = cube[K][j][i];
            }
        }
        cube[K] = temp;
    }
    public static void rotate_180(int K){
        Character[][] temp = new Character[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[3-i-1][3-j-1] = cube[K][i][j];
            }
        }
        cube[K] = temp;
    }
    public static void rotate_270(int K){
        Character[][] temp = new Character[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[3-i-1][j] = cube[K][j][i];
            }
        }
        cube[K] = temp;
    }

    public static void rotate_basic(int face, int up, int down, int left, int right, char D){
        if (D == '+') {
            rotate_90(face);
            char x = cube[up][2][0];
            char y = cube[up][2][1];
            char z = cube[up][2][2];

            cube[up][2][0] = cube[left][2][2];
            cube[up][2][1] = cube[left][1][2];
            cube[up][2][2] = cube[left][0][2];

            cube[left][0][2] = cube[down][0][0];
            cube[left][1][2] = cube[down][0][1];
            cube[left][2][2] = cube[down][0][2];

            cube[down][0][0] = cube[right][2][0];
            cube[down][0][1] = cube[right][1][0];
            cube[down][0][2] = cube[right][0][0];

            cube[right][0][0] = x;
            cube[right][1][0] = y;
            cube[right][2][0] = z;

        } else {
            rotate_270(face);
            char x = cube[up][2][0];
            char y = cube[up][2][1];
            char z = cube[up][2][2];

            cube[up][2][0] = cube[right][0][0];
            cube[up][2][1] = cube[right][1][0];
            cube[up][2][2] = cube[right][2][0];

            cube[right][0][0] = cube[down][0][2];
            cube[right][1][0] = cube[down][0][1];
            cube[right][2][0] = cube[down][0][0];

            cube[down][0][0] = cube[left][0][2];
            cube[down][0][1] = cube[left][1][2];
            cube[down][0][2] = cube[left][2][2];

            cube[left][0][2] = z;
            cube[left][1][2] = y;
            cube[left][2][2] = x;
        }
    }
}
