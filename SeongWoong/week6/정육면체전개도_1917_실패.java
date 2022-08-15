import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 정육면체전개도_1917 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        for (int c = 0; c < 3; c++) {
            int[][] full = new int[6][6];
            int[] dice = new int[6];
            int[] dx = new int[]{0, 0, 1, -1};
            int[] dy = new int[]{1, -1, 0, 0};
            for (int i = 0; i < 6; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < 6; j++) {
                    full[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            HashMap<Character, Integer> map = new HashMap<>();
            map.put('f', 0);
            map.put('r', 1);
            map.put('l', 2);
            map.put('b', 3);
            map.put('u', 4);
            map.put('d', 5);
            Queue<Integer[]> q = new LinkedList<>();
            HashMap<String, Character> map2 = new HashMap<>();
            boolean can = true;
            boolean findone = false;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (full[i][j] == 1) {
                        findone = true;
                        String str = Integer.toString(i) + Integer.toString(j);
                        map2.put(str, 'u');
                        full[i][j] = 2;
                        dice[4] = 1;
                        q.add(new Integer[]{i, j});

                        while (!q.isEmpty()) {
                            Integer[] cur = q.poll();
                            int cur_y = cur[0];
                            int cur_x = cur[1];
                            for (int k = 0; k < 4; k++) {
                                int ny = cur_y + dy[k];
                                int nx = cur_x + dx[k];
                                if (ny < 0 || ny >= 6 || nx < 0 || nx >= 6) continue;
                                if (full[ny][nx] == 1) {
                                    Character pre_dice = map2.get(Integer.toString(cur_y) + Integer.toString(cur_x));
                                    Character next_dice = 'a';
                                    if (k == 2) {
                                        //오른쪽
                                        next_dice = right(pre_dice);
                                    }
                                    if (k == 3) {
                                        //왼쪽
                                        next_dice = left(pre_dice);
                                    }
                                    if (k == 0) {
                                        //아래쪽
                                        next_dice = down(pre_dice);
                                    }
                                    if (k == 1) {
                                        //위쪽
                                        next_dice = up(pre_dice);
                                    }

                                    if (dice[map.get(next_dice)] == 0) {
                                        dice[map.get(next_dice)] = 1;
                                        map2.put(Integer.toString(ny) + Integer.toString(nx), next_dice);
                                    } else {
                                        can = false;
                                    }

                                    full[ny][nx] = 2;
                                    q.add(new Integer[]{ny, nx});
                                }
                            }

                        }

                    }
                    if (findone) break;
                }
                if (findone) break;
            }
            if (can) {
                System.out.println("yes");
            } else {
                System.out.println("No");
            }
        }
    }

    // b,d의 경우에 좌우 반대로 해줘야 한다.
    public static Character right(Character A) {
        switch (A) {
            case 'f':
                return 'r';
            case 'b':
                return 'l';
            case 'r':
                return 'b';
            case 'l':
                return 'f';
            case 'u':
                return 'r';
            case 'd':
                return 'r';
        }
        return null;
    }

    public static Character left(Character A) {
        switch (A) {
            case 'f':
                return 'l';
            case 'b':
                return 'r';
            case 'r':
                return 'f';
            case 'l':
                return 'b';
            case 'u':
                return 'l';
            case 'd':
                return 'l';
        }
        return null;
    }

    public static Character up(Character A) {
        switch (A) {
            case 'f':
                return 'u';
            case 'b':
                return 'u';
            case 'r':
                return 'u';
            case 'l':
                return 'u';
            case 'u':
                return 'b';
            case 'd':
                return 'f';
        }
        return null;
    }

    public static Character down(Character A) {
        switch (A) {
            case 'f':
                return 'd';
            case 'b':
                return 'd';
            case 'r':
                return 'd';
            case 'l':
                return 'd';
            case 'u':
                return 'f';
            case 'd':
                return 'b';
        }
        return null;
    }
//    public static Character right(Character A) {
//        switch (A) {
//            case 'f':
//                return 'r';
//            case 'b':
//                return 'r';
//            case 'r':
//                return 'b';
//            case 'l':
//                return 'f';
//            case 'u':
//                return 'r';
//            case 'd':
//                return 'l';
//        }
//        return null;
//    }
//
//    public static Character left(Character A) {
//        switch (A) {
//            case 'f':
//                return 'l';
//            case 'b':
//                return 'l';
//            case 'r':
//                return 'f';
//            case 'l':
//                return 'b';
//            case 'u':
//                return 'l';
//            case 'd':
//                return 'r';
//        }
//        return null;
//    }
//
//    public static Character up(Character A) {
//        switch (A) {
//            case 'f':
//                return 'u';
//            case 'b':
//                return 'u';
//            case 'r':
//                return 'u';
//            case 'l':
//                return 'u';
//            case 'u':
//                return 'b';
//            case 'd':
//                return 'f';
//        }
//        return null;
//    }
//
//    public static Character down(Character A) {
//        switch (A) {
//            case 'f':
//                return 'd';
//            case 'b':
//                return 'd';
//            case 'r':
//                return 'd';
//            case 'l':
//                return 'd';
//            case 'u':
//                return 'f';
//            case 'd':
//                return 'b';
//        }
//        return null;
//    }
}
