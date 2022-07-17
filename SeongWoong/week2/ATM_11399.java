import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ATM_11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N은 사람의 수
        int N = Integer.parseInt(br.readLine());
        // P[i]는 인덱스 i번째 사람의 대기시간
        int[] P = new int[N];
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        // 정렬을 한다
        Arrays.sort(P);
        // sum은 0번째 사람부터 i-1번째 사람까지 대기시간의 총합
        int sum = 0;
        // mid_sum 은 인덱스 i번째 사람의 총 대기시간
        int mid_sum = 0;
        for (int i = 0; i < N; i++) {
            mid_sum += P[i];
            sum += mid_sum;
        }
        System.out.println(sum);
    }
}
