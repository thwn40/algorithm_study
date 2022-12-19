package SeongWoong.week18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 카카오2021인턴십_거리두기확인하기_방법1 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[][] places = new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        System.out.println(Arrays.toString(sol.solution(places)));
    }

    static class Solution {
        public int[] solution(String[][] places) {
            int[] answer = new int[]{1, 1, 1, 1, 1};
            for (int t = 0; t < 5; t++) {
                String[] rooms = places[t];
                List<Coordinate> coordinates = new ArrayList<>();

                for (int i = 0; i < 5; i++) {
                    String room = rooms[i];
                    for (int j = 0; j < 5; j++) {
                        if (room.charAt(j) == 'P') {
                            coordinates.add(new Coordinate(i, j));
                        }
                    }
                }

                for (int i = 0; i < coordinates.size(); i++) {
                    int y = coordinates.get(i).y;
                    int x = coordinates.get(i).x;
                    for (int j = i + 1; j < coordinates.size(); j++) {
                        int ny = coordinates.get(j).y;
                        int nx = coordinates.get(j).x;

                        if (!check(rooms, y, ny, x, nx)) {
                            answer[t] = 0;
                        }
                    }
                }
            }
            return answer;
        }
    }

    static class Coordinate {
        public int y;
        public int x;

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static public boolean check(String[] rooms, int y, int ny, int x, int nx) {
        int manhattan = Math.abs(y - ny) + Math.abs(x - nx);
        if (manhattan <= 1) {
            return false;
        } else if (manhattan == 2) {
            if (y == ny) {
                if (rooms[y].charAt(Math.min(x, nx) + 1) == 'X') {
                    return true;
                } else {
                    return false;
                }
            } else if (x == nx) {
                if (rooms[Math.min(y, ny) + 1].charAt(x) == 'X') {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (x < nx) {
                    if (rooms[y].charAt(x + 1) == 'X' && rooms[ny].charAt(nx - 1) == 'X') {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (rooms[y].charAt(x - 1) == 'X' && rooms[ny].charAt(nx + 1) == 'X') {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else return true;
    }
}
