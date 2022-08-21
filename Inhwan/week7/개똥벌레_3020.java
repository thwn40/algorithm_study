package Inhwan.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 개똥벌레_3020 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NH = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = NH[0], H = NH[1];

        int[] Stalagmites = new int[H], Stalactites = new int[H];

        for (int i = 0; i < N/2; i++) {
            Stalagmites[Integer.parseInt(br.readLine())-1]++;
            Stalactites[H-Integer.parseInt(br.readLine())]++;
        }

        for (int i = 1; i < H; i++) {
            Stalagmites[H-i-1] += Stalagmites[H-i];
            Stalactites[i] += Stalactites[i-1];
        }

        int[] Obstacles = new int[H];
        for (int i = 0; i < H; i++) {
            Obstacles[i] = Stalactites[i] + Stalagmites[i];
        }

        int min = N, count = 0;

        for (int i = 0; i < H; i++) {
            if (Obstacles[i] < min) {
                min = Obstacles[i];
                count = 1;
            } else if (Obstacles[i] == min)  {
                count++;
            }
        }

        System.out.println(String.format("%d %d", min, count));
    }
}
