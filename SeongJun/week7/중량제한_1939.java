package SeongJun.week7;

import java.util.*;

public class 중량제한_1939 {
    static ArrayList<ArrayList<island>> list = new ArrayList<>();
    static int s;
    static int e;

    static boolean[] visited;

    /**
     * bfs로 도착지점까지 중량제한에 걸리지 않는 섬만 탐색
     */
    public static boolean bfs(int weight) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int currentIsland = queue.poll();
            if (currentIsland == e) {
                return true;
            }

            for (int i = 0; i < list.get(currentIsland).size(); i++) {
                if (list.get(currentIsland).get(i).limit >= weight && !visited[list.get(currentIsland).get(i).num]) {
                    visited[list.get(currentIsland).get(i).num] = true;
                    queue.offer(list.get(currentIsland).get(i).num);
                }
            }
        }


        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt();
        int num1, num2, limit, result = 0;

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }
        int end = 0;
        int start = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            num1 = sc.nextInt();
            num2 = sc.nextInt();
            limit = sc.nextInt();
            end = Math.max(limit, end);
            start = Math.min(limit, start);
            list.get(num1).add(new island(num2, limit));
            list.get(num2).add(new island(num1, limit));
        }

        s = sc.nextInt();
        e = sc.nextInt();




        /**
         *   이분탐색으로
         */
        while (start <= end) {
            int middle = (start + end) / 2;
            visited = new boolean[N + 1];
            if (bfs(middle)) {

                start = middle + 1;
                result = middle;
            } else {

                end = middle - 1;
            }
        }

        System.out.println(result);


    }


    /**
     * 인접리스트가 있는데 중량제한이라는 가중치가 있으므로 island 클래스를 하나 새로 만든다.
     */

    public static class island {
        int num = 0;
        int limit = 0;

        public island(int num, int limit) {
            this.num = num;
            this.limit = limit;
        }

        @Override
        public String toString() {
            return "island{" +
                    "num=" + num +
                    '}';
        }
    }


}
