import java.util.*;

public class A에서B_16953 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextInt();
        long B = sc.nextInt();
        Queue<Number> q = new PriorityQueue<>();
        q.add(new Number(A, 0));
        int answer = -1;
        while (!q.isEmpty()) {
            Number cur = q.poll();

            if (cur.num == B) {
                answer = cur.cnt+1;
                break;
            }
            if (cur.num * 2 <= B) {
                q.add(new Number(cur.num * 2, cur.cnt + 1));
            }
            if (cur.num * 10 + 1 <= B) {
                q.add(new Number(cur.num * 10 + 1, cur.cnt + 1));
            }
        }
        System.out.println(answer);
    }
    public static class Number implements Comparable<Number> {
        long num;
        int cnt;

        public Number(long num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Number o) {
            return this.cnt - o.cnt;
        }
    }
}
