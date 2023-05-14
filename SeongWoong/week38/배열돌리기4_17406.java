import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 배열돌리기4_17406 {
    static int N, M, K, answer;
    static int[][] arr, rcs;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NMK = br.readLine().split(" ");
        N = Integer.parseInt(NMK[0]);
        M = Integer.parseInt(NMK[1]);
        K = Integer.parseInt(NMK[2]);
        arr = new int[N][M];
        visit = new boolean[K];
        rcs = new int[K][3];
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(row[j]);
            }
        }
        for (int i = 0; i < K; i++) {
            String[] str = br.readLine().split(" ");
            rcs[i][0] = Integer.parseInt(str[0]) - 1; // r
            rcs[i][1] = Integer.parseInt(str[1]) - 1; // c
            rcs[i][2] = Integer.parseInt(str[2]); // s
        }
        int[][] copy = copy(arr);
        dfs(0, copy);
        System.out.println(answer);
    }
    public static int[][] copy(int[][] arr){    // 배열을 복사하는 메서드
        int[][] result = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i].clone();
        }
        return result;
    }

    public static void dfs(int cur, int[][] copy) { // 재귀를 통해 모든 순서확인
        if (cur == K) {
            answer = Math.min(answer, cal(copy));
            return;
        }
        for (int i = 0; i < K; i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(cur + 1, go(rcs[i][0], rcs[i][1], rcs[i][2], copy(copy)));
                visit[i] = false;
            }
        }
    }

    public static int cal(int[][] array) {  // 각 행의 합을 구해 최솟값을 구하는 메서드
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = 0; j < array[0].length; j++) {
                sum += array[i][j];
            }
            min = Math.min(min, sum);
        }
        return min;
    }

    public static int[][] go(int r, int c, int s, int[][] copied) { // 회전을 시작하는 메서드(바깥쪽부터 안쪽으로)
        for (int i = 0; i < s; i++) {
            copied = rotate(r - s + i, c - s + i, (s-i)*2, copied);
        }
        return copied;
    }

    public static int[][] rotate(int y, int x, int c, int[][] array) {  // 회전 메서드(한줄씩)
        int temp = array[y][x + c];
        for (int i = 0; i < c; i++) {   // 상단
            array[y][x + c - i] = array[y][x + c - i - 1];
        }
        for (int i = 0; i < c; i++) {   // 좌측
            array[y + i][x] = array[y + i + 1][x];
        }
        for (int i = 0; i < c; i++) {   // 하단
            array[y + c][x + i] = array[y + c][x + i + 1];
        }
        for (int i = 0; i < c - 1; i++) {   // 우측
            array[y + c - i][x + c] = array[y + c - i - 1][x + c];
        }
        array[y+1][x + c] = temp;
        return array;
    }
}
/*
주의점
2차원 배열을 복사할 때 깊은 복사를 위해 clone을 썼는데
외부 배열만 깊은 복사가 되고 내부는 그대로 얕은 복사로 주소 값이 복사가 됐다.
따라서 내부까지 clone해주는 copy메서드를 새로 만들었다.
 */