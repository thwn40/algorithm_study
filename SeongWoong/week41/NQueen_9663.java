import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueen_9663 {
    static int N, answer;
    static int[] board, selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1]; // i번째 행에서 몇번 열이 선택됐는지 저장
        answer = 0;
        selected = new int[N + 1];  // 열의 사용 여부
        run(1);
        System.out.println(answer);
    }

    static void run(int k) throws IOException { // k는 행 번호
        if (k == N + 1) {
            answer++;
        } else {
            for (int i = 1; i <= N; i++) {
                boolean can = true;
                if (selected[i] == 1) continue; // 선택된 열은 넘어간다
                for (int j = 1; j < k; j++) {   // 대각선에 해당하면 넘어가기
                    if (board[k - j] == i + j || board[k - j] == i - j) {
                        can = false;
                        break;
                    }
                }
                if (!can) continue;
                board[k] = i;
                selected[i] = 1;    // i열 사용체크
                run(k + 1);     // 다음 행으로~
                selected[i] = 0;    // 원상복구
            }
        }
    }
}