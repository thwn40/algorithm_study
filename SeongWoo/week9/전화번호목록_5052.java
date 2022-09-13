package SeongWoo.week9;

import java.util.*;

public class 전화번호목록_5052 {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int caseSize = scanner.nextInt();

        while (caseSize-- > 0) {
            int size = scanner.nextInt();
            String[] strArr = new String[size];
            String result = "YES";

            for (int i = 0; i < size; i++) {
                strArr[i] = scanner.next();
            }

            //정렬하면 항상 같은 접두사를 가진 문자가 길이순으로 정렬된다.
            Arrays.sort(strArr);
            for (int i = 0; i < size - 1; i++) {
                //앞의 문자가 뒤의 문자의 접두사인지 판단.
                if (strArr[i + 1].indexOf(strArr[i]) == 0) {
                    result = "NO";
                    break;
                }
            }
            System.out.println(result);
        }
    }
}
