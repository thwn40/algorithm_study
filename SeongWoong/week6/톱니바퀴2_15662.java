package SeongWoong.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 톱니바퀴2_15662 {
    static int[][] saw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 톱니바퀴의 개수
        int T = Integer.parseInt(br.readLine());
        saw = new int[T][8];
        // 각각 톱니바퀴 정보 입력받기
        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                saw[i][j] = str.charAt(j) - '0';
            }
        }
        int K = Integer.parseInt(br.readLine());
        //K번 만큼 회전
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            // num은 톱니위치 (인덱스로 표현하기 위해 -1)
            int num = Integer.parseInt(st.nextToken()) - 1;
            // LR은 회전 방향 1이면 시계방향 -1이면 반시계방향
            int LR = Integer.parseInt(st.nextToken());

            //오른쪽 톱니들 확인
            rotateR(num, LR);
            // 오른쪽톱니를 확인 하는 과정에서 num번째 톱니가 돌아갔으므로
            // 반대 방향으로 한 번 돌려 원상복구 해준다.
            rotate(num,-LR);
            // 왼쪽 톱니들 확인
            rotateL(num, LR);

        }
        // 12시 방향을 확인하고 S극이면(1이면) 체크
        int cnt = 0;
        for (int i = 0; i < T; i++) {
            if (saw[i][0] == 1) cnt++;
        }
        System.out.println(cnt);
    }

    // 오른쪽에 있는 톱니들을 확인
    public static void rotateR(int num, int LR) {
        // 가장 오른쪽의 톱니라면 자신만 회전
        if (num == saw.length - 1) {
            rotate(num, LR);
            return;
        }
        // 두 톱니의 연결된 극이 같다면 자신만 회전
        if (saw[num][2] == saw[num + 1][6]) {
            rotate(num, LR);
            return;
        }
        // 다르면 자신은 회전, 오른쪽 톱니는 반대방향으로 회전(과 동시에 그 오른쪽 톱니체크)
        else {
            //**순서중요 오른쪽 체크함수를 먼저하고 자신을 회전시켜야
            // 오른쪽 체크 때 비교가 정확히 된다. (먼저 회전하면 회전시킨걸 기준으로 비교가 된다.)
            rotateR(num + 1, -LR);
            rotate(num, LR);
        }
    }

    // 왼쪽에 있는 톱니들을 확인
    public static void rotateL(int num, int LR) {
        // 가장 왼쪽의 톱니라면 자신만 회전
        if (num == 0) {
            rotate(num, LR);
            return;
        }
        // 두 톱니의 연결된 극이 같다면 자신만 회전
        if (saw[num][6] == saw[num - 1][2]) {
            rotate(num, LR);
            return;
        }
        // 다르면 자신은 회전, 왼쪽 톱니는 반대방향으로 회전(과 동시에 그 왼쪽 톱니체크)
        else {
            rotateL(num - 1, -LR);
            rotate(num, LR);
        }
    }
    // 회전 시키는 함수
    public static void rotate(int num, int LR) {
        // temp는 배열을 한칸씩 밀기위해 임시로 설정한 숫자
        int temp = 0;
        // 회전 방향이 1이면 배열을 시계방향으로 밀어준다
        if (LR == 1) {
            temp = saw[num][7];
            for (int i = 7; i > 0; i--) {
                saw[num][i] = saw[num][i - 1];
            }
            saw[num][0] = temp;
        }
        // 회전방향이 -1이면 배열을 반시계방향으로 밀어준다
        if (LR == -1) {
            temp = saw[num][0];
            for (int i = 1; i < 8; i++) {
                saw[num][i - 1] = saw[num][i];
            }
            saw[num][7] = temp;
        }
    }
}
