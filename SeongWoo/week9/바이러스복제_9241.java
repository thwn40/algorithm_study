package SeongWoo.week9;

import java.util.Scanner;

public class 바이러스복제_9241 {
    private static int countTransDna(String dna, String infectedDna) {
        int count = 0;
        int dnaIndex = 0;
        int infectedDnaIndex = 0;

        //두 문자열의 접두사가 같을 때 까지 각 인덱스를 늘린다.
        while (dnaIndex < dna.length() && infectedDnaIndex < infectedDna.length()
                && dna.charAt(dnaIndex) == infectedDna.charAt(infectedDnaIndex)) {
            dnaIndex++;
            infectedDnaIndex++;
        }

        int lastDnaLength = dna.length() - dnaIndex;
        int lastInfectedDnaLength = infectedDna.length() - infectedDnaIndex;
        //문자열이 달라진 이후부터는 감염된 DNA가 삽입됐다는 것이기 때문에,
        //문자열이 달라진 이후부터 감염된 DNA의 개수가 감염된 DNA가 될 수 있는 최대 개수이다.
        int maxCount = lastInfectedDnaLength;

        int countSize = Math.min(lastDnaLength, lastInfectedDnaLength);
        //각 문자열의 뒷부분을 비교했을 때 일치한다면, 감염된 DNA가 아니라는 뜻이기 때문에 count를 늘려준다.
        for (int i = 0; i < countSize; i++) {
            char lastDnaWord = dna.charAt(dna.length() - 1 - i);
            char lastInfectedDnaWord = infectedDna.charAt(infectedDna.length() - 1 - i);
            if (lastDnaWord == lastInfectedDnaWord) {
                count++;
            } else {
                break;
            }
        }

        //감염된 DNA가 될 수 있는 최대 개수 - 감염된 DNA가 아닌 문자의 개수
        return maxCount - count;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dna = scanner.next();
        String infectedDna = scanner.next();

        System.out.println(countTransDna(dna, infectedDna));
    }
}
