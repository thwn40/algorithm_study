package SeongWoo.week30;

import java.util.ArrayList;
import java.util.List;

public class 수식_최대화 {

    static String[] exArr = {"+", "-", "*"};
    static long result = 0;

    public static void parsing(List<Long> numberList, List<String> exList, String expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                exList.add(String.valueOf(c));
                numberList.add(Long.parseLong(sb.toString()));
                sb.delete(0, sb.length());
                continue;
            }
            sb.append(c);
        }
        numberList.add(Long.parseLong(sb.toString()));
    }

    public static long calculate(long n1, long n2, String ex) {
        switch (ex) {
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            case "*":
                return n1 * n2;
        }
        return 0;
    }

    public static void dfs(int depth, String[] exArr, List<Long> numberList, List<String> exList) {
        if (depth == exArr.length) {
            Long tempResult = Math.abs(numberList.get(0));
            if (tempResult > result) {
                result = tempResult;
            }
        }

        for (int i = 0; i < exArr.length; i++) {
            if (exArr[i] == null) {
                continue;
            }

            List<Long> tempNumberList = new ArrayList<>(numberList);
            List<String> tempExList = new ArrayList<>(exList);
            String[] tempExArr = exArr.clone();

            String currentEx = tempExArr[i];
            tempExArr[i] = null;

            int numberIndex = 0;
            int exIndex = 0;
            while (exIndex < tempExList.size()) {
                String ex = tempExList.get(exIndex);
                if (!ex.equals(currentEx)) {
                    numberIndex++;
                    exIndex++;
                    continue;
                }

                Long n1 = tempNumberList.get(numberIndex);
                Long n2 = tempNumberList.get(numberIndex + 1);
                long calculatedNumber = calculate(n1, n2, ex);

                tempNumberList.remove(numberIndex);
                tempNumberList.remove(numberIndex);
                tempNumberList.add(numberIndex, calculatedNumber);
                tempExList.remove(exIndex);
            }

            dfs(depth + 1, tempExArr, tempNumberList, tempExList);
        }

    }

    public long solution(String expression) {
        List<Long> numberList = new ArrayList<>();
        List<String> exList = new ArrayList<>();

        parsing(numberList, exList, expression);
        dfs(0, exArr, numberList, exList);

        return result;
    }
}
