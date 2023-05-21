package SeongWoo.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 크게_만들기 {
    public static class Number {
        int number;
        boolean deleted = false;

        public Number(int number) {
            this.number = number;
        }
    }

    public static int deleteLeftSideNumber(Number[] numberArr, int count) {

        Stack<Number> stack = new Stack<>();

        for (Number number : numberArr) {
            while (!stack.isEmpty() && stack.peek().number < number.number) {
                count--;
                Number deletedNumber = stack.pop();
                deletedNumber.deleted = true;
                if (count == 0) {
                    return count;
                }
            }
            stack.push(number);
        }

        return count;
    }

    public static void deleteRightSideNumber(Number[] numberArr, int count) {

        int index = numberArr.length - 1;
        while (count > 0) {
            Number deletedNumber = numberArr[index];
            if (deletedNumber.deleted) {
                index--;
                continue;
            }
            deletedNumber.deleted = true;
            count--;
            index--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int size = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());

        Number[] numberArr = new Number[size];

        st = new StringTokenizer(br.readLine());
        String totalNumber = st.nextToken();
        for (int i = 0; i < totalNumber.length(); i++) {
            numberArr[i] = new Number(Integer.parseInt(String.valueOf(totalNumber.charAt(i))));
        }

        count = deleteLeftSideNumber(numberArr, count);
        if (count > 0) {
            deleteRightSideNumber(numberArr, count);
        }

        StringBuilder sb = new StringBuilder();

        Arrays.stream(numberArr)
                .filter(number -> !number.deleted)
                .forEach(number -> sb.append(number.number));

        System.out.println(sb);
    }
}
