import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // dp[i] = i번째 포도주까지 계산한 최댓값
        int[] dp = new int[n + 1];
        // all[i]  = i번째 포도잔에 들어있는 포도주의 양
        int[] all = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            all[i] = sc.nextInt();
        }
        //dp[1]은 무조건 1번와인의 양
        dp[1] = all[1];
        for (int i = 2; i <= n; i++) {
            //dp[2] 도 무조건 dp[1]+dp[2]의 양
            if(i==2){
                dp[2] = all[1] + all[2];
                continue;
            }
            // dp[3] = 1. oox /2. oxo /3. xoo 중 가장 큰 값
            //적어보면  1. dp[2] /2. dp[1] + all[3] /3. all[2] + all[3]
            if (i==3){
                dp[3] = Math.max(dp[2], Math.max(dp[1] + all[3], all[2] + all[3]));
                continue;
            }
            //여기서부터 규칙 반복
            // dp[i] = 1. ~oxoo  /2. ~x /3. ~xo
            // ~의 의미는 ~까지 가장 큰값을 의미한다
            // 예를들어 2번에서 i가 4라고 할 때 ~는 oox, oxo, xoo... 중 가장 큰 값인데
            // 이 값을 우리는 dp[i-1]에 저장해놨기 때문에 꺼내서 쓰면된다.
            // 3번도 마찬가지로
            // 예를 들어 i가 4라고 할 때 ~는 ox, oo, xx...중 가장 큰 값인데
            // 이 값을 우리는 dp[i-2]에 저장해놨기 때문에 꺼내서 쓰면된다.
            dp[i] = Math.max(dp[i - 3] + all[i - 1] + all[i], Math.max(dp[i - 1], dp[i - 2] + all[i]));
        }
        System.out.println(dp[n]);
    }
}