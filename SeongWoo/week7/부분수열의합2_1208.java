package SeongWoo.week7;

import java.util.*;

public class 부분수열의합2_1208 {

    static long result = 0;

    //max 만큼의 수를 선택해서 더한 값을 combSum에 넣어주는 메서드
    public static void combination(int[] sequence, ArrayList<Integer> combSum, int sum, int index,
                                   int depth, int max) {
        if (depth == max) {
            combSum.add(sum);
            return;
        }

        for (int i = index; i < sequence.length; i++) {
            sum += sequence[i];
            combination(sequence, combSum, sum, i + 1, depth + 1, max);
            sum -= sequence[i];
        }
    }


    //부분 수열의 합을 구하는 메서드
    private static void getSumOfSubSequence(int size, int[] sequence, ArrayList<Integer> combSum) {
        for (int i = 1; i <= size; i++) {
            combination(sequence, combSum, 0, 0, 0, i);
        }
    }

    //두개의 배열에서 뽑은 원소가 target이 되는 경우를 count하는 메서드
    public static void countTarget(ArrayList<Integer> leftSumList, ArrayList<Integer> rightSumList, int target) {
        //두 배열에서 원소를 뽑지않고 한쪽 배열에서만 원소를 뽑았을 때 target과 일치하는 값이 있으면 count한다.
        result += Collections.frequency(rightSumList, target);
        result += Collections.frequency(leftSumList, target);

        //twoPointer를 사용하기 위해 정렬
        leftSumList.sort(Comparator.naturalOrder());
        rightSumList.sort(Comparator.naturalOrder());

        int start = 0;
        int end = rightSumList.size() - 1;
        //시작은 leftSum의 0번 원소, 끝은 rightSum의 마지막 원소로 두고 twoPointer를 사용해 두 포인터의 합이 target이 되는 값을 찾는다.
        while (start < leftSumList.size() && end >= 0) {
            int sum = leftSumList.get(start) + rightSumList.get(end);

            //만약 두 포인터의 합이 target과 같다면 합이 target과 같아지는 각 배열의 원소의 개수를 구해서 곱해준다.(정렬되있어서 가능)
            if (sum == target) {
                int leftSum = leftSumList.get(start);
                int rightSum = rightSumList.get(end);
                long lCount = 0;
                long rCount = 0;

                //현재 포인터(sum == target이 되는 위치)로부터 leftSumList 또는 rightSumList의 원소가 동일한 개수를 구한다.
                while (start < leftSumList.size() && leftSumList.get(start) == leftSum) {
                    start++;
                    lCount++;
                }
                while (end >= 0 && rightSumList.get(end) == rightSum) {
                    end--;
                    rCount++;
                }
                result += lCount * rCount;
            }
            if (sum < target) {
                start++;
            } else if (sum > target){
                end--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int target = scanner.nextInt();
        int[] sequence = new int[size];

        for (int i = 0; i < size; i++) {
            sequence[i] = scanner.nextInt();
        }

        int middle = (size - 1) / 2;
        int[] leftSequence = Arrays.copyOfRange(sequence, 0, middle);
        int[] rightSequence = Arrays.copyOfRange(sequence, middle, size);


        ArrayList<Integer> leftSumList = new ArrayList<>();
        ArrayList<Integer> rightSumList = new ArrayList<>();

        getSumOfSubSequence(size, leftSequence, leftSumList);
        getSumOfSubSequence(size, rightSequence, rightSumList);

        countTarget(leftSumList, rightSumList, target);
        System.out.println(result);
    }
}
