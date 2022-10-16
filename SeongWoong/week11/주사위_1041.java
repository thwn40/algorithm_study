import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 주사위_1041 {
    static int[] arr;
    static int[][] connection;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        connection = new int[6][];
        connection[0] = new int[]{1, 2, 4, 3};
        connection[1] = new int[]{0, 2, 5, 3};
        connection[2] = new int[]{0, 1, 5, 4};
        connection[3] = new int[]{0, 1, 5, 4};
        connection[4] = new int[]{0, 2, 5, 3};
        connection[5] = new int[]{1, 2, 4, 3};

        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        arr = new int[6];

        for (int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        if (N == 1) {
            System.out.println(getFive());
        } else {
            long one =//1면 갯수
                    getOne() * (long) (N - 2) * 4
                            + getOne() * (long) Math.pow((N - 2), 2) * 5;
            //2면 갯수
            long two = getTwo() * (long) ((N - 2) * 4 * 2)
                    + getTwo() * 4;
            //3면 갯수
            long three = getThree() * 4;
            System.out.println(
                    one + two + three
            );
        }

    }

    static public long getFive() {
        long max = 0;
        long sum = 0;
        for (int i = 0; i < 6; i++) {
            max = Math.max(max, arr[i]);
            sum += arr[i];
        }
        return sum - max;
    }

    static public long getTwo() {
        long min = arr[0] + arr[connection[0][0]];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                min = Math.min(min, arr[i] + arr[connection[i][j]]);
            }
        }
        return min;
    }

    static public long getThree() {
        long min = arr[0] + arr[connection[0][0]] + arr[connection[0][1]];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                int second = connection[i][j];
                int third;
                if (j == 3) {
                    third = connection[i][0];
                } else {
                    third = connection[i][j + 1];
                }
                min = Math.min(min, arr[i] + arr[second] + arr[third]);
            }
        }
        return min;
    }

    static public long getOne() {
        long min = arr[0];
        for (int i = 0; i < 6; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }
}
