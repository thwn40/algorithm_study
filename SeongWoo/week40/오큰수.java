package SeongWoo.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 오큰수 {

    public static class Number {
        int number;
        int rightLargeNumber = -1;

        public Number(int number) {
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        Number[] sequence = new Number[size];
        Stack<Number> stack = new Stack<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < sequence.length; i++) {
            int number = Integer.parseInt(st.nextToken());
            sequence[i] = new Number(number);
        }

        for (Number rightLargeNumber : sequence) {
            while (!stack.isEmpty() && rightLargeNumber.number > stack.peek().number) {
                Number number = stack.pop();
                number.rightLargeNumber = rightLargeNumber.number;
            }
            stack.push(rightLargeNumber);
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(sequence)
                .forEach(n -> sb.append(n.rightLargeNumber).append(" "));
        System.out.println(sb);
    }
}
