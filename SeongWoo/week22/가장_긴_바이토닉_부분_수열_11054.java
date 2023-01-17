package SeongWoo.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장_긴_바이토닉_부분_수열_11054 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        int[] numberArr = new int[size + 1];
        int[] memorization = new int[size + 1];
        int[] memorizationReverse = new int[size + 1];
        int max = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= size; i++) {
            numberArr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= size; i++) {
            memorization[i] = 1;
            for (int j = 1; j < i; j++) {
                if (numberArr[i] > numberArr[j] && memorization[i] < memorization[j] + 1) {
                    memorization[i] = memorization[j] + 1;
                }
            }
        }

        //배열을 뒤에서부터의 길이로 계산
        //특정 인덱스 i에서 memorization[i] + memorizationReverse[i] => i까지 증가하는 부분 수열 + i부터 감소하는 부분 수열 (각 최대값)
        for (int i = size; i >= 1; i--) {
            memorizationReverse[i] = 1;
            for (int j = size; j > i; j--) {
                if (numberArr[i] > numberArr[j] && memorizationReverse[i] < memorizationReverse[j] + 1) {
                    memorizationReverse[i] = memorizationReverse[j] + 1;
                }
            }
        }

        for (int i = 1; i <= size; i++) {
            int tempMax = memorization[i] + memorizationReverse[i] - 1;
            if (max < tempMax) {
                max = tempMax;
            }
        }

        System.out.println(max);
    }
}
