package SeongWoo.week4;

import java.util.ArrayList;
import java.util.Scanner;

public class 리모컨_1107 {
    static ArrayList<Integer> brokenButtons = new ArrayList<>();
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int targetChannel = sc.nextInt();
        int brokenButtonSize = sc.nextInt();
        result = Math.abs(targetChannel - 100);

        for (int i = 0; i < brokenButtonSize; i++) {
            brokenButtons.add(sc.nextInt());
        }

        for (int i = 0; i <= 1000000; i++) {

            if (i == 100 && targetChannel == 100) {
                result = 0;
                continue;
            }

            if (i == 0 && !brokenButtons.contains(0)) {
                result = Math.min(result, targetChannel + 1);
                continue;
            } else if (i == 0) {
                continue;
            }

            if (checkBrokenButton(i)) {
                int length = (int) (Math.log10(i) + 1);
                int clickLength = Math.abs(targetChannel - i);
                result = Math.min(result, length + clickLength);
            }
        }

        System.out.println(result);

    }

    private static boolean checkBrokenButton(int i) {

        while (i > 0) {
            if (brokenButtons.contains(i % 10)) {
                return false;
            }
            i = i / 10;
        }

        return true;
    }
}
