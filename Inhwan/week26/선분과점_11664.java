package Inhwan.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 선분과점_11664 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double[] coordinates = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

        double[] x = new double[3];
        for (int i = 0; i < 3; i++) {
            x[i] = coordinates[i];
        }

        double[] y = new double[3];
        for (int i = 0; i < 3; i++) {
            y[i] = coordinates[i + 3];
        }

        double[] c = new double[3];
        for (int i = 0; i < 3; i++) {
            c[i] = coordinates[i + 6];
        }

        double d1 = distance(x, c);
        double d2 = distance(y, c);

        if (d1 < d2) {
            double[] temp = x;
            x = y;
            y = temp;

            double d = d1;
            d1 = d2;
            d2 = d;
        }

        do {
            double[] z = new double[3];
            for (int i = 0; i < 3; i++) {
                z[i] = (x[i] + y[i]) / 2;
            }

            double d3 = distance(z, c);

            if (d3 <= d2) {
                x = y;
                y = z;
                d1 = d2;
                d2 = d3;
            } else {
                x = z;
                d1 = d3;
            }
        } while (d1 - d2 > 0.000001);

        System.out.println(d2);
    }

    static double distance(double[] x, double[] y) {
        return Math.sqrt(Math.pow(x[0] - y[0], 2) + Math.pow(x[1] - y[1], 2) + Math.pow(x[2] - y[2], 2));
    }
}