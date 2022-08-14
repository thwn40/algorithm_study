package Inhwan.week6;

import java.io.*;
import java.util.*;

public class 로봇청소기_14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NM[0], M = NM[1];

        int[] state = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] territory = new int[N][];
        for (int i = 0; i < N; i++) {
            territory[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Cleaner cleaner = new Cleaner(state, territory);

        int count = 0;
        boolean det;

        while (territory[cleaner.state[0]][cleaner.state[1]]!=1) {

            if (territory[cleaner.state[0]][cleaner.state[1]]==0) {
                count++;
                cleaner.clean();
            }

            det = false;

            for (int i = 0; i < 4; i++) {
                if (cleaner.next()) {
                    cleaner.rotate();
                    cleaner.move(1);
                    det = true;
                    break;
                } else {
                    cleaner.rotate();
                }
            }
            if (det) continue;

            cleaner.move(-1);
        }

        System.out.println(count);
    }

    static class Cleaner {
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

        int[] state;
        int[][] territory;

        Cleaner(int[] state, int[][] territory) {
            this.state = state;
            this.territory = territory;
        }

        void clean() {
            territory[state[0]][state[1]]=2;
        }

        void rotate() {
            state[2] = (state[2]+3)%4;
        }

        void move(int d) {
            for (int i = 0; i < 2; i++) {
                state[i]+=d*dirs[state[2]][i];
            }
        }

        boolean next() {
            int d = (state[2]+3)%4;
            return territory[state[0]+dirs[d][0]][state[1]+dirs[d][1]] == 0;
        }
    }
}
