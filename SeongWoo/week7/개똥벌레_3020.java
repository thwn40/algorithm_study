package SeongWoo.week7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class 개똥벌레_3020 {
    //높이에 따라 부딪히는 돌의 개수를 구해서 countList에 넣어주는 메서드
    //부딪히는 돌 개수의 최소값을 반환한다.
    public static int getCountResult(ArrayList<Integer> lowStone, ArrayList<Integer> highStone,
                                     ArrayList<Integer> countList, int height, int minCount) {
        //정순 & 역순으로 정렬한다.
        lowStone.sort(Comparator.naturalOrder());
        highStone.sort(Comparator.reverseOrder());

        int lPoint = 0;
        int hPoint = 0;
        int tempHeight = 1;

        while (tempHeight <= height) {
            //현재 높이가 석순의 높이보다 높으면 index를 늘려준다.
            //lowStone.size() - lPoint가 tempHeight일 때 부딪히는 석순의 개수가 된다.
            while (lPoint < lowStone.size() && tempHeight > lowStone.get(lPoint)) {
                lPoint++;
            }
            //현재 높이가 종유석에 부딪히는 높이면 index를 늘려준다.(역순으로 정렬되서 가장 긴 종유석부터 count한다)
            //hPoint가 곧 tempHeight일 때 부딪히는 종유석의 개수가 된다.
            while (hPoint < highStone.size() && tempHeight >= height - highStone.get(hPoint) + 1) {
                hPoint++;
            }

            int count = (lowStone.size() - lPoint) + hPoint;
            if (count < minCount) {
                minCount = count;
            }
            countList.add(count);
            tempHeight++;
        }
        return minCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int minCount = 200001;

        //높이에 따라 부딪히는 돌의 개수를 담는 리스트
        ArrayList<Integer> countList = new ArrayList<>();
        //석순의 높이를 담는 리스트
        ArrayList<Integer> lowStone = new ArrayList<>();
        //종유석의 높이를 담는 리스트
        ArrayList<Integer> highStone = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            if (i % 2 == 0) {
                lowStone.add(scanner.nextInt());
            } else {
                highStone.add(scanner.nextInt());
            }
        }

        int result = getCountResult(lowStone, highStone, countList, height, minCount);
        int frequency = Collections.frequency(countList, result);
        System.out.println(result + " " + frequency);
    }
}
