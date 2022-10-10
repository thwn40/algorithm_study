package Inhwan.week1213;

public class Kakao_algorithm_test_5 {

    public static void main(String[] args) {
        String[] commands = {};

        System.out.println(solution(commands));
    }

    static String[] solution(String[] commands) {

        return null;

    }

    static class Table {
        String[][] table = new String[50][50];

        void init() {
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    table[i][j] = new String();
                }
            }
        }

        void update(int r, int c, String value) {
            table[r][c] = value;
        }

        void update(String value1, String value2) {
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    if (table[i][j].equals(value1)) {
                        table[i][j] = value2;
                    }
                }
            }
        }

        void merge(int r1, int c1, int r2, int c2) {
            if (table[r1][c1].equals(new String()) && !table[r2][c2].equals(new String())) {
                String temp = table[r1][c1];

                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        if (table[i][j] == temp) table[i][j] = table[r2][c2];
                    }
                }

            } else {
                String temp = table[r2][c2];

                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        if (table[i][j] == temp) table[i][j] = table[r1][c1];
                    }
                }
            }
        }

        void unmerge(int r, int c) {
            String temp = table[r][c];

            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    if (table[i][j] == temp) {
                        table[i][j] = new String();
                    }
                }
            }

            table[r][c] = temp;
        }

        void print(int r, int c) {
            if (table[r][c].equals(new String())) System.out.println("EMPTY");
            else System.out.println(table[r][c]);
        }
    }
}
