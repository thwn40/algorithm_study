package SeongWoo.week5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 토마토_7576 {

    static int[] rowSet = {-1, 0, 1, 0};
    static int[] colSet = {0, 1, 0, -1};

    public static class Tomato {   //토마토의 위치, 상태, 익기까지의 날짜를 나타내는 class
        int row;
        int col;
        int status;
        int day = 0;

        public Tomato(int row, int col, int status) {
            this.row = row;
            this.col = col;
            this.status = status;
        }
    }

    public static void bfs(Tomato[][] tomatoes) {   //익은 토마토로부터 시작해서 안 익은 토마토를 탐색하는 메서드
        Queue<Tomato> queue = new LinkedList<>();
        for (int i = 0; i < tomatoes.length; i++) {   //우선적으로 주어진 토마토의 배열에서 "익은 토마토"를 Queue에 넣어준다.
            for (int j = 0; j < tomatoes[0].length; j++) {
                if (tomatoes[i][j].status == 1) {
                    queue.offer(tomatoes[i][j]);
                }
            }
        }

        while (!queue.isEmpty()) {
            Tomato tomato = queue.poll();   //Queue에서 맨 앞에 있는 토마토를 꺼낸다.
            for (int i = 0; i < 4; i++) {   //꺼내진 토마토로 부터 반복문을 사용해서 상하좌우를 탐색한다.
                int nextRow = tomato.row + rowSet[i];  //다음 토매토의 row와 col을 설정.
                int nextCol = tomato.col + colSet[i];
                if (nextRow < 0 || nextRow >= tomatoes.length || nextCol < 0 || nextCol >= tomatoes[0].length
                        || tomatoes[nextRow][nextCol].status == -1 || tomatoes[nextRow][nextCol].status == 1) {
                    continue;
                }   //다음에 탐색할 토매토(tomatoes[nextRow][nextCol])가 주어진 배열을 벗어나거나 익었거나(=1), 비어있는(=-1) 상태라면 반복문을 넘긴다.

                tomatoes[nextRow][nextCol].day = tomato.day + 1;   //탐색될 토매토가 익기까지 걸린 날짜는 "처음에 꺼내진 토마토의 날짜" + 1
                tomatoes[nextRow][nextCol].status = 1;   //탐색된 토매토를 익은 상태로 변경
                queue.offer(tomatoes[nextRow][nextCol]);
            }
        }
    }

    public static void main(String[] args) {
        //초기화 시작
        Scanner scanner = new Scanner(System.in);
        int colSize = scanner.nextInt();
        int rowSize = scanner.nextInt();

        Tomato[][] tomatoes = new Tomato[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                tomatoes[i][j] = new Tomato(i, j, scanner.nextInt());
            }
        }
        //초기화 끝

        bfs(tomatoes);

        boolean isZero = Arrays.stream(tomatoes)
                .flatMap(Arrays::stream)
                .mapToInt(tomato -> tomato.status)
                .anyMatch(status -> status == 0);   //bfs 탐색을 끝낸 토마토의 배열에 안익은 토마토가 있는지 검사

        if (isZero) {
            System.out.println(-1);
            return;
        }

        int maxDist = Arrays.stream(tomatoes)
                .flatMap(Arrays::stream)
                .mapToInt(tomato -> tomato.day)
                .max()
                .getAsInt();   //bfs 탐색을 끝낸 토마토의 배열에서 익기까지 걸린 날짜의 최대값을 구한다.

        System.out.println(maxDist);
    }
}
