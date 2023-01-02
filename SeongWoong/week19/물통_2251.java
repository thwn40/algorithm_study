import java.util.*;

public class 물통_2251 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        TreeSet<Integer> answer = new TreeSet<>();
        boolean[][] arr = new boolean[A + 1][B + 1];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,0));
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int curA = cur.a;
            int curB = cur.b;
            int curC = C - curA - curB;
            answer.add(curC);

            for (int i = 0; i < 2; i++) {
                //c -> a
                if (curC > A && curA != A) {
                    if (!arr[A][curB]){
                        arr[A][curB] = true;
                        q.add(new Pair(A, curB));
                    }
                }
                //c -> b
                if (curC > B && curB == 0) {
                    if (!arr[curA][B]){
                        arr[curA][B] = true;
                        q.add(new Pair(curA, B));
                    }
                }
                //a -> b
                if (curC > B) {
                    if (!arr[curA][B]){
                        arr[curA][B] = true;
                        q.add(new Pair(curA, B));
                    }
                }
            }
        }
        answer.stream()
                .forEach(System.out::println);
    }
    static class Pair{
        int a;
        int b;
        public Pair(int a, int b){
            this.a = a;
            this.b = b;
        }
    }
}
