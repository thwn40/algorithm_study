import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 프린터큐_1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테스트케이스의 수
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] NM = br.readLine().split(" ");
            // 문서의 수
            int N = Integer.parseInt(NM[0]);
            // 해당 문서의 중요도
            int M = Integer.parseInt(NM[1]);

            // 해당문서의 정보를 담은 큐
            Queue<int[]> document = new LinkedList<>();
            // 입력받은 모든 문서정보
            String[] prio = br.readLine().split(" ");

            //카운트
            int cnt = 0;
            //문서의 정보 document는 {문서의 순서, 문서의 중요도}
            for (int j = 0; j < N; j++) {
                document.add(new int[]{j, Integer.parseInt(prio[j])});
            }
            //문서를 모두 돌 때까지 반복
            while (document.size() != 0) {
                //처음 비교할 문서
                int[] num = document.poll();
                // 문서가 가장 높은 우선순위인지 확인하는 변수
                Boolean isMax = true;
                // 지금까지 꺼낸 문서의 수 n
                // 처음 비교할 문서와 각각의 문서의 중요도를 비교해서 새로 꺼낸 문서의
                // 중요도가 더 높으면 처음 문서부터 여태 꺼낸 문서들을 모두 document의 뒤로 넣어줌
                int n = 0;
                // 모든 문서를 꺼내 확인
                for (int[] temp : document) {
                    if (num[1] < temp[1]) {
                        document.add(num);
                        for (int k = 0; k < n; k++) {
                            document.add(document.poll());
                        }
                        // 최고로 중요한 문서가 아니었기 때문에 false로 바꿔주고 종료
                        n = 0;
                        isMax = false;
                        break;
                    }
                    // 새로 꺼낸 문서의 중요도가 더 높지 않아 지금까지 꺼낸 문서의 수를 1 올림
                    n++;
                }
                // 우선 순위가 가장 높은 문서인 경우 그 문서를 출력(cnt++)해주고
                // 만약 그 문서가 우리가 찾는 문서(M)라면 종료
                if (isMax == true) {
                    cnt++;
                    if (num[0] == M) {
                        break;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}
