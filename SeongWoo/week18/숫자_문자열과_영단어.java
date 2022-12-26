package SeongWoo.week18;

public class 숫자_문자열과_영단어 {

    static String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public int solution(String s) {

        int answer = 0;

        for (int i = 0; i < s.length(); i++) {

            char point = s.charAt(i);
            int number = 0;

            if (point < '0' || point > '9') {
                number = convert(s, i);
                i += numbers[number].length() - 1;
            } else {
                number = Integer.parseInt(String.valueOf(point));
            }

            if (answer == 0) {
                answer += number;
            } else {
                answer = answer * 10 + number;
            }
        }

        return answer;
    }

    public int convert(String s, int index) {

        for (int i = 0; i < numbers.length; i++) {

            String number = numbers[i];
            int length = number.length();
            if (index + length > s.length()) {
                continue;
            }

            String subNumber = s.substring(index, index + length);

            if (number.equals(subNumber)) {
                return i;
            }
        }

        return -1;
    }
}
