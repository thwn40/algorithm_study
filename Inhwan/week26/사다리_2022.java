package Inhwan.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 사다리_2022 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double[] xyc = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        double x = xyc[0], y = xyc[1], c = xyc[2];

        double s = 0;
        double e = Math.min(x, y);
        double d = 0.001;

        while (e - s > d) {
            double m = (s + e) / 2;

            if (Math.sqrt((x * x - m * m) * (y * y - m * m)) / (Math.sqrt(x * x - m * m) + Math.sqrt(y * y - m * m)) < c) e = m;
            else s = m;
        }

        System.out.println(s);
    }
}