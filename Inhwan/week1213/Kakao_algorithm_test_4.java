package Inhwan.week1213;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kakao_algorithm_test_4 {

    public static void main(String[] args) {
        long[] numbers = {26779};

        System.out.println(Arrays.toString(solution(numbers)));
    }

    static int[] solution(long[] numbers) {
        int l = numbers.length;

        int[] result = new int[l];

        for (int i = 0; i < l; i++) {
            result[i] = isPossible(makeBinary(numbers[i]));
        }

        return result;
    }

    static int isPossible(List<Integer> list) {

        int size = list.size();
        int mid = size/2;

        if (size == 1) return 1;

        if (list.get(mid) == 0) {
            for (Integer integer : list) {
                if (integer > 0) return 0;
            }
        }

        int result = 1;

        result *= isPossible(list.subList(0, mid));
        result *= isPossible(list.subList(mid+1, size));

        return result;
    }

    static List<Integer> makeBinary(long num) {
        int[] sizes = {0,1,3,7,15,31,63};

        List<Integer> result = new ArrayList<>();

        while (num > 0) {
            if (num%2 ==0) result.add(0);
            else result.add(1);

            num /= 2;
        }

        int size = result.size();
        for (int i = 0; i < 7; i++) {
            if (sizes[i] == size) break;

            if (sizes[i] > size) {
                while (sizes[i] > size) {
                    result.add(0);
                    size++;
                }
                break;
            }
        }

        return result;
    }
}
