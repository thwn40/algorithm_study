import java.io.*;

public class 골드바흐의추측_6588 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 들어오는 n의 최대값이 1000000이기 때문에 1000000까지 소수 찾기
        // 소수 판별 방식 - 에라스토테네스의 체 사용
        // isPrime이 true 인 경우 소수가 아니고 false 인 경우 소수다
        // Arrays.fill() 을 이용하여 true와 false를 바꿔서 설정할 수 있지만
        // 좀 더 짧고 빠르게 하기위해 기본값으로 진행함
        boolean[] isPrime = new boolean[1000001];
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i * i <= 1000000; i++) {
            if (!isPrime[i]) {
                for (int j = i * i; j <= 1000000; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        int n = Integer.parseInt(br.readLine());
        // n이 0이 나올 때 까지 반복
        while (n != 0) {
            // canGold는 골드바흐의 추측이 성립하는 경우 true
            boolean canGold = false;
            for (int i = 3; i <= n / 2; i++) {
                if (!isPrime[i]) {
                    int temp = n - i;
                    if (!isPrime[temp]) {
                        canGold = true;
                        bw.write(n + " = " + i + " + " + temp + "\n");
                        break;
                    }
                }
            }
            if (canGold == false) bw.write("Goldbach's conjecture is wrong.\n");
            n = Integer.parseInt(br.readLine());
        }
        bw.flush();
        bw.close();
    }
}
