import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 퍼즐_1525 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int des = 123456789;
        int origin = sc.nextInt();

        for (int i = 0; i < 8; i++) {
            int n = sc.nextInt();
            if (n == 0) n = 9;

            origin = origin * 10 + n;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        map.put(origin, 0);

        q.add(origin);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == des) break;

            for (int i = 0; i < 4; i++) {
                int nine = Integer.toString(cur).indexOf('9');
                if (i == 0 && (nine == 0 || nine == 3 || nine == 6)) continue;
                if (i == 1 && (nine == 6 || nine == 7 || nine == 8)) continue;
                if (i == 2 && (nine == 2 || nine == 5 || nine == 8)) continue;
                if (i == 3 && (nine == 0 || nine == 1 || nine == 2)) continue;

                int next = run(Integer.toString(cur), i);
                if (map.containsKey(next)) continue;
                map.put(next, map.get(cur) + 1);
                q.add(next);
            }
        }
        if (des == origin) System.out.println(0);
        else if (!map.containsKey(des)) System.out.println(-1);
        else System.out.println(map.get(des));
    }


    public static int run(String a, int b) {
        int answer = 0;
        int nine = a.indexOf('9');
        // 왼쪽으로 밀기
        if (b == 0) {
            for (int i = 0; i < a.length(); i++) {
                if (i == nine - 1) {
                    answer = answer * 10 + 9;
                } else if (i == nine) {
                    answer = answer * 10 + a.charAt(i - 1) - '0';
                } else {
                    answer = answer * 10 + a.charAt(i) - '0';
                }
            }
            // 아래로 밀기
        } else if (b == 1) {
            for (int i = 0; i < a.length(); i++) {
                if (i == nine + 3) {
                    answer = answer * 10 + 9;
                } else if (i == nine) {
                    answer = answer * 10 + a.charAt(i + 3) - '0';
                } else {
                    answer = answer * 10 + a.charAt(i) - '0';
                }
            }
            // 오른쪽으로 밀기
        } else if (b == 2) {
            for (int i = 0; i < a.length(); i++) {
                if (i == nine + 1) {
                    answer = answer * 10 + 9;
                } else if (i == nine) {
                    answer = answer * 10 + a.charAt(i + 1) - '0';
                } else {
                    answer = answer * 10 + a.charAt(i) - '0';
                }
            }
            // 위로 밀기
        } else if (b == 3) {
            for (int i = 0; i < a.length(); i++) {
                if (i == nine - 3) {
                    answer = answer * 10 + 9;
                } else if (i == nine) {
                    answer = answer * 10 + a.charAt(i - 3) - '0';
                } else {
                    answer = answer * 10 + a.charAt(i) - '0';
                }
            }
        }
        return answer;
    }
}
