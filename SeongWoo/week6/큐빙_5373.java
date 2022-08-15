package SeongWoo.week6;

import java.util.Arrays;
import java.util.Scanner;

public class 큐빙_5373 {

    //면을 나타내는 클래스
    public static class Plane {
        //면의 색 정보
        char[][] colors = new char[3][3];

        //면을 90도로 회전시키는 메서드
        public void cycleOnePlane(char direction) {
            char[][] copyColors = copyOfArray(colors);
            if (direction == '+') {
                for (int i = 0; i < colors.length; i++) {
                    for (int j = 0; j < colors[0].length; j++) {
                        colors[j][colors[0].length - 1 - i] = copyColors[i][j];
                    }
                }
            } else if (direction == '-') {
                for (int i = 0; i < colors.length; i++) {
                    for (int j = 0; j < colors[0].length; j++) {
                        colors[colors[0].length - 1 - j][i] = copyColors[i][j];
                    }
                }
            }
        }

        private char[][] copyOfArray(char[][] chars) {
            char[][] copyChar = new char[chars.length][chars[0].length];
            for (int i = 0; i < chars.length; i++) {
                copyChar[i] = Arrays.copyOf(chars[i], chars[i].length);
            }
            return copyChar;
        }
    }

    //큐브를 나타내는 클래스
    public static class Cube {
        //각 면을 나타내는 배열
        Plane[] planes = new Plane[6];

        //주어진 명령에 따라 큐브를 돌리는 메서드
        public void cycleCube(String orderSet) {
            char order = orderSet.charAt(0);
            char direction = orderSet.charAt(1);
            char[] temp = new char[3];

            if (order == 'F') {
                planes[3].cycleOnePlane(direction);
                if (direction == '-') {
                    for (int i = 0; i < 3; i++) temp[i] = planes[0].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[0].colors[0][i] = planes[4].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[4].colors[2 - i][2] = planes[5].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[5].colors[2][i] = planes[2].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[2].colors[2 - i][0] = temp[i];
                //반대로 돌리는것은 반대 방향에서 행해지는 작업을 거꾸로 하면된다.
                } else {
                    for (int i = 0; i < 3; i++) temp[i] = planes[0].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[0].colors[0][2 - i] = planes[2].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[2].colors[i][0] = planes[5].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[5].colors[2][2 - i] = planes[4].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[4].colors[i][2] = temp[i];
                }
                return;
            }
            //B는 F와 돌려지는 면이 같다. B는 F의 반대면이기 때문에 돌려지는 면의 원소의 위치가 반대 편이다.
            // B는 F의 반대면이기 때문에 F에서 '-' 방향에 돌려지는 면이 B에서 '+'일 때 돌려지는 면과 같다.(반대 방향도 동일 원리 적용)
            //이와 같은 성질은 LR, UD에도 적용된다.
            if (order == 'B') {
                planes[1].cycleOnePlane(direction);
                if (direction == '+') {
                    for (int i = 0; i < 3; i++) temp[i] = planes[0].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[0].colors[2][i] = planes[4].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[4].colors[2 - i][0] = planes[5].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[5].colors[0][i] = planes[2].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[2].colors[2 - i][2] = temp[i];
                } else {
                    for (int i = 0; i < 3; i++) temp[i] = planes[0].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[0].colors[2][2 - i] = planes[2].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[2].colors[i][2] = planes[5].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[5].colors[0][2 - i] = planes[4].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[4].colors[i][0] = temp[i];
                }
                return;
            }
            if (order == 'L') {
                planes[4].cycleOnePlane(direction);
                if (direction == '-') {
                    for (int i = 0; i < 3; i++) temp[i] = planes[0].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[0].colors[2 - i][0] = planes[1].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[1].colors[2 - i][2] = planes[5].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[5].colors[i][0] = planes[3].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[3].colors[i][0] = temp[i];
                } else {
                    for (int i = 0; i < 3; i++) temp[i] = planes[0].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[0].colors[i][0] = planes[3].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[3].colors[i][0] = planes[5].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[5].colors[2 - i][0] = planes[1].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[1].colors[2 - i][2] = temp[i];
                }
            }
            if (order == 'R') {
                planes[2].cycleOnePlane(direction);
                if (direction == '+') {
                    for (int i = 0; i < 3; i++) temp[i] = planes[0].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[0].colors[2 - i][2] = planes[1].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[1].colors[2 - i][0] = planes[5].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[5].colors[i][2] = planes[3].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[3].colors[i][2] = temp[i];
                } else {
                    for (int i = 0; i < 3; i++) temp[i] = planes[0].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[0].colors[i][2] = planes[3].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[3].colors[i][2] = planes[5].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[5].colors[2 - i][2] = planes[1].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[1].colors[2 - i][0] = temp[i];
                }
            }
            if (order == 'U') {
                planes[5].cycleOnePlane(direction);
                if (direction == '+') {
                    for (int i = 0; i < 3; i++) temp[i] = planes[1].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[1].colors[0][i] = planes[4].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[4].colors[0][i] = planes[3].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[3].colors[0][i] = planes[2].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[2].colors[0][i] = temp[i];
                } else {
                    for (int i = 0; i < 3; i++) temp[i] = planes[1].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[1].colors[0][i] = planes[2].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[2].colors[0][i] = planes[3].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[3].colors[0][i] = planes[4].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[4].colors[0][i] = temp[i];
                }
            }
            if (order == 'D') {
                planes[0].cycleOnePlane(direction);
                if (direction == '-') {
                    for (int i = 0; i < 3; i++) temp[i] = planes[1].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[1].colors[2][i] = planes[4].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[4].colors[2][i] = planes[3].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[3].colors[2][i] = planes[2].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[2].colors[2][i] = temp[i];
                } else {
                    for (int i = 0; i < 3; i++) temp[i] = planes[1].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[1].colors[2][i] = planes[2].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[2].colors[2][i] = planes[3].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[3].colors[2][i] = planes[4].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[4].colors[2][i] = temp[i];
                }
            }

        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] charset = {'y', 'o', 'b', 'r', 'g', 'w'};

        int total = scanner.nextInt();
        for (int l = 0; l < total; l++) {
            //cube 초기화 시작
            Cube cube = new Cube();
            for (int k = 0; k < charset.length; k++) {
                Plane plane = new Plane();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        plane.colors[i][j] = charset[k];
                    }
                }
                cube.planes[k] = plane;
            }
            //cube 초기화 끝

            //cube 돌리기
            int length = scanner.nextInt();
            for (int j = 0; j < length; j++) {
                String next = scanner.next();
                cube.cycleCube(next);
            }

            //윗면 출력
            for (int i = 0; i < cube.planes[5].colors.length; i++) {
                for (int j = 0; j < cube.planes[5].colors[0].length; j++) {
                    System.out.print(cube.planes[5].colors[i][j]);
                }
                if (i != cube.planes[5].colors.length - 1) {
                    System.out.println();
                }
            }
            if (l != total - 1) {
                System.out.println();
            }
        }
    }
}
