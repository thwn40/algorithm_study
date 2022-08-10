package SeongWoo.week6;

import java.util.Arrays;
import java.util.Scanner;

public class 큐빙_5373 {

    public static class Plane {
        char[][] colors = new char[3][3];

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

        private static char[][] copyOfArray(char[][] chars) {
            char[][] copyChar = new char[chars.length][chars[0].length];
            for (int i = 0; i < chars.length; i++) {
                copyChar[i] = Arrays.copyOf(chars[i], chars[i].length);
            }
            return copyChar;
        }
    }

    public static class Cube {
        Plane[] planes = new Plane[6];

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
                } else {
                    for (int i = 0; i < 3; i++) temp[i] = planes[0].colors[0][i];
                    for (int i = 0; i < 3; i++) planes[0].colors[0][2 - i] = planes[2].colors[i][0];
                    for (int i = 0; i < 3; i++) planes[2].colors[i][0] = planes[5].colors[2][i];
                    for (int i = 0; i < 3; i++) planes[5].colors[2][2 - i] = planes[4].colors[i][2];
                    for (int i = 0; i < 3; i++) planes[4].colors[i][2] = temp[i];
                }
                return;
            }
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
                    for (int i = 0; i < 3; i++) temp[i] = planes[1].colors[0][i];
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
        int total = scanner.nextInt();
        for (int l = 0; l < total; l++) {
            Cube cube = new Cube();
            char[] charset = {'y', 'o', 'b', 'r', 'g', 'w'};
            for (int k = 0; k < charset.length; k++) {
                Plane plane = new Plane();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        plane.colors[i][j] = charset[k];
                    }
                }
                cube.planes[k] = plane;
            }

            int length = scanner.nextInt();
            for (int j = 0; j < length; j++) {
                String next = scanner.next();
                cube.cycleCube(next);
            }

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
