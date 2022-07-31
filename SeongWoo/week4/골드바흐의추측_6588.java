package SeongWoo.week4;

import java.io.*;

public class 골드바흐의추측_6588 {

    public static boolean[] makePrimeSet(int size) {   //아리스토텔레스의 체
        boolean[] prime = new boolean[size + 1];
        prime[0] = true;
        prime[1] = true;

        for (int i = 2; i * i <= size; i++) {
            if (!prime[i]) {   //소수이면
                for (int j = i * i; j <= size; j += i) {   //i * i 부터(i * i이전의 숫자는 이미 다 걸러짐) size까지 i의 배수를 걸러준다.
                    if (!prime[j]) {                       //ex) i = 5일 경우 5 * 1, 5 * 2 ... 5 * 4는 다 걸러져서 판단할 필요없음
                        prime[j] = true;
                    }
                }
            }
        }

        return prime;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] prime = makePrimeSet(1000000);

        int number;
        while ((number = Integer.parseInt(br.readLine())) != 0){
            boolean flag = false;
            for (int i = 0; i * 2 <= number; i++) {   //합으로 나타내려는 수 number의 절반까지 탐색
                if (prime[i]) {   //소수가 아니라면 넘어간다.
                    continue;
                }

                int tempNumber = number - i;
                if (!prime[tempNumber]) {   //number에서 소수 i를 빼준 값도 소수라면 결과식을 생성한다.
                    bw.write(String.format("%d = %d + %d\n", number, i, tempNumber));
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                bw.write("Goldbach's conjecture is wrong.\n");
            }
        }

        bw.flush();
    }
}
