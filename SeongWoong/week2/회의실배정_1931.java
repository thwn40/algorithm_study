import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class 회의실배정_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N : 회의의 수
        int N = Integer.parseInt(br.readLine());
        // confer[i][0] : i번째 회의의 시작시간
        // confer[i][1] : i번째 회의의 종료시간
        int[][] confer = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            confer[i][0] = Integer.parseInt(str[0]);
            confer[i][1] = Integer.parseInt(str[1]);
        }
        // 종료시간을 기준으로 우선정렬 같으면 시작시간 기준으로 정렬
        Arrays.sort(confer, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        // 현재 회의가 끝난 시간
        int endTime = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (confer[i][0] >= endTime) {
                cnt++;
                endTime = confer[i][1];
            }
        }
        System.out.println(cnt);
    }
}