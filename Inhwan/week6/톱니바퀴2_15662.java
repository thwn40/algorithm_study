package Inhwan.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class 톱니바퀴2_15662 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        Cogwheel[] cogwheels = new Cogwheel[T];

        for (int i = 0; i < T; i++) {
            cogwheels[i] = new Cogwheel(Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray());
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            int[] rotation = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = rotation[0]-1;
            int d = rotation[1];

            cogwheels[start].rotate(d);

            for (int j = start + 1; j<T; j++) {
                if (cogwheels[j-1].teeth[2+d]==cogwheels[j].teeth[6]) d*=0;
                d*=-1;

                cogwheels[j].rotate(d);
            }

            d = rotation[1];

            for (int j = start - 1; j>=0; j--) {
                if (cogwheels[j].teeth[2]==cogwheels[j+1].teeth[6+d]) d*=0;
                d*=-1;

                cogwheels[j].rotate(d);
            }
        }

        int sum=0;

        for (int i = 0; i < T; i++) {
            sum+=cogwheels[i].teeth[0];
        }

        System.out.println(sum);
    }

    static class Cogwheel {
        int[] teeth;

        Cogwheel(int[] teeth) {
            this.teeth = teeth;
        }

        void rotate(int d) {
            int temp;

            if (d==1) {
                temp = teeth[7];
                for (int i = 7; i > 0; i--) {
                    teeth[i]=teeth[i-1];
                }
                teeth[0]=temp;
            }

            if (d==-1) {
                temp = teeth[0];
                for (int i = 0; i < 7; i++) {
                    teeth[i]=teeth[i+1];
                }
                teeth[7]=temp;
            }
        }
    }
}
