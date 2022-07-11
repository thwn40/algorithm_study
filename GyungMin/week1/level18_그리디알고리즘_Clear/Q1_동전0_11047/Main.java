package level18_그리디알고리즘_Clear.Q1_11047;

/*
문제) 동전 0
    준규가 가지고 있는 동전은 총 N 종류이고, 각각의 동전을 매우 많이 가지고 있다.
    동전을 적절히 사용해서 그 가치의 합을 K로 만들려고 한다.
    이때 필요한 동전 개수의 최솟값을 구하는 프로그램을 작성하시오.

입력)
    첫째 줄에 N과 K가 주어진다.
    (1 ≤ N ≤ 10, 1 ≤ K ≤ 100,000,000)
    둘째 줄부터 N개의 줄에 동전의 가치 Ai가 오름차순으로 주어진다.
    (1 ≤ Ai ≤ 1,000,000, A1 = 1, i ≥ 2인 경우에 Ai는 Ai-1의 배수)

출력)
    첫째 줄에 K원을 만드는데 필요한 동전 개수의 최솟값을 출력한다.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int coin = 0;
        int ans = 0;
        int[] coinList = new int[N];

        // 이미 오름차순으로 정렬되있으므로 따로 정렬해주지 않는다.
        for (int i = N - 1; i > -1; i--) {
            coinList[i] = Integer.parseInt(br.readLine());
        }
        // 출력용
//        System.out.println(Arrays.toString(coinList));

        for (int x : coinList) {
            if (x <= K) {
                while (coin + x <= K) {
                    ans ++;
                    coin += x;
                }
            }
        }

        System.out.println(ans);
        br.close();
    }
}

