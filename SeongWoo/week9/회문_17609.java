package SeongWoo.week9;

import java.util.Scanner;

public class 회문_17609 {

    //문자열 str이 회문인지 검사하는 메서드
    private static int checkPalindrome(String str, int start, int end) {
        int count = 0;
        //start가 가르키는 문자를 지웠을 때 결과
        int deleteStartResult = 2;
        //end가 가르키는 문자를 지웠을 때 결과
        int deleteEndResult = 2;
        //문자를 하나 지웠는지를 판단하는 flag
        boolean deleteFlag = false;

        while (start < end) {
            if (str.charAt(start) == str.charAt(end)) {
                start++;
                end--;
                continue;
            }
            //start가 가르키는 문자를 지웠을 때 회문이 될 수 있는 경우
            //start의 문자를 지움 => start + 1 부터 검사
            //start + 1부터 end까지의 문자열이 회문인지 다시 검사해서 그 결과값을 받는다.
            if (start + 1 <= end && str.charAt(start + 1) == str.charAt(end)) {
                deleteFlag = true;
                deleteStartResult = checkPalindrome(str, start + 1, end);
            }
            if (end - 1 >= start && str.charAt(start) == str.charAt(end - 1)) {
                deleteFlag = true;
                deleteEndResult = checkPalindrome(str, start, end - 1);
            }

            //문자를 삭제 했다면 count를 늘려주고, start와 end를 지운 경우의 결과값에서 가장 최소가되는 결과값(최선의 경우)을 count에 더해준다.
            if (deleteFlag) {
                count++;
                count += Math.min(deleteStartResult, deleteEndResult);
            } else {
                count = 2;
            }
            break;
        }

        //count의 결과에 따라 return값 반환
        if (count >= 2) {
            return 2;
        } else if (count == 1) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseSize = scanner.nextInt();

        for (int i = 0; i < caseSize; i++) {
            String str = scanner.next();
            System.out.println(checkPalindrome(str, 0, str.length() - 1));
        }
    }
}
