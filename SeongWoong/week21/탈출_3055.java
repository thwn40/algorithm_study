import java.util.*;

public class 탈출_3055 {
    public static void main(String[] args) {
        // 4방향
        int[] dy = new int[]{0, 0, 1, -1};
        int[] dx = new int[]{1, -1, 0, 0};

        Scanner sc = new Scanner(System.in);

        int R = sc.nextInt();
        int C = sc.nextInt();
        sc.nextLine();

        // 물 시간 큐
        Queue<Node> waterQ = new LinkedList<>();
        // 이동 시간 큐
        Queue<Node> moveQ = new LinkedList<>();

        Character[][] arr = new Character[R][C];
        // 물이 차는데 걸리는 시간 배열
        int[][] waterTime = new int[R][C];
        // 움직이는데 걸리는 시간 배열
        int[][] moveTime = new int[R][C];

        // 목표인 비버 소굴
        int desY = 0;
        int desX = 0;

        for (int i = 0; i < R; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < C; j++) {
                waterTime[i][j] = 100000000;
                moveTime[i][j] = 100000000;

                char cur = str.charAt(j);
                arr[i][j] = cur;
                if (cur == '*') {
                    waterQ.add(new Node(i, j));
                    waterTime[i][j] = 0;
                }
                if (cur == 'S') {
                    moveQ.add(new Node(i, j));
                    moveTime[i][j] = 0;
                }
                if (cur == 'D') {
                    desY = i;
                    desX = j;
                }
            }
        }

        // 물 시간 구하기
        while (!waterQ.isEmpty()) {
            Node cur = waterQ.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                if (arr[ny][nx] == 'X') continue;
                if (arr[ny][nx] == '.') {
                    if (waterTime[ny][nx] > waterTime[cur.y][cur.x] + 1) {
                        waterTime[ny][nx] = waterTime[cur.y][cur.x] + 1;
                        waterQ.add(new Node(ny, nx));
                    }
                }
            }
        }

        // 이동 시간 구하기
        while (!moveQ.isEmpty()) {
            Node cur = moveQ.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                if (arr[ny][nx] == 'X') continue;
                if (arr[ny][nx] == '.' && waterTime[ny][nx] > moveTime[cur.y][cur.x] + 1) {
                    if (moveTime[ny][nx] > moveTime[cur.y][cur.x] + 1) {
                        moveTime[ny][nx] = moveTime[cur.y][cur.x] + 1;
                        moveQ.add(new Node(ny, nx));
                    }
                }
                if (arr[ny][nx] == 'D' && waterTime[ny][nx] > moveTime[cur.y][cur.x] + 1) {
                    if (moveTime[ny][nx] > moveTime[cur.y][cur.x] + 1) {
                        moveTime[ny][nx] = moveTime[cur.y][cur.x] + 1;
                    }
                }
            }
        }

        int answer = moveTime[desY][desX];
        if (answer == 100000000) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }
    }

    static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
