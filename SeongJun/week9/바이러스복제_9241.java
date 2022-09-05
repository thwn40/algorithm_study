package SeongJun.week9;

import java.util.Scanner;

public class 바이러스복제_9241 {
    static int left = 0;
    static int right = 0;
    static int min;
    static StringBuffer dna;
    static StringBuffer infectedDNA;
    static StringBuffer reversedDNA;
    static StringBuffer reversedInfectedDNA;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        dna = new StringBuffer(sc.nextLine());
        infectedDNA = new StringBuffer(sc.nextLine());

        min = Math.min(dna.length(), infectedDNA.length());
        virusChecker();

    }

    private static void virusChecker() {

        for (int i = 0; i < min; i++) {
            if (dna.charAt(i) == infectedDNA.charAt(i)) left++;
            else break;
        }

        reversedDNA = dna.reverse();
        reversedInfectedDNA = infectedDNA.reverse();
        for (int i = 0; i < min - left; i++) {
            if (reversedDNA.charAt(i) == reversedInfectedDNA.charAt(i)) right++;
            else break;
        }

        System.out.println(infectedDNA.length() - (left + right));
    }
}
