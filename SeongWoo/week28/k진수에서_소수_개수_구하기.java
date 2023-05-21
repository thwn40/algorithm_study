package SeongWoo.week28;

public class k진수에서_소수_개수_구하기 {

    private static String convert(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int remain = n % k;
            sb.insert(0, remain);
            n = n / k;
        }

        return sb.toString();
    }

    private static boolean isPrime(long n) {
        if (n == 1) {
            return false;
        }

        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public int solution(int n, int k) {
        int answer = 0;
        String numbers = convert(n, k);

        String[] split = numbers.split("0");
        for (String splitNumber : split) {
            if (splitNumber.equals("")) {
                continue;
            }
            if (isPrime(Long.parseLong(splitNumber))) {
                answer++;
            }
        }

        return answer;
    }
}
