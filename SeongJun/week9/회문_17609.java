package SeongJun.week9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 회문_17609 {
    static List<StringBuffer> pseudo = new ArrayList<>();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < T; i++) {
            pseudo = new ArrayList<>();
            boolean isPalindrome = false;
            boolean isPseudoPalindrome =false;
            StringBuffer str = new StringBuffer(sc.nextLine());

            if (isPalindromeChecker(str)) isPalindrome=true;
            else {
                for (StringBuffer strBf : pseudo) {
                    if (isPalindrome(strBf)) isPseudoPalindrome=true;
                }
            }

            if(isPalindrome) System.out.println("0");
            if(!isPalindrome&&isPseudoPalindrome) System.out.println("1");
            if(!isPalindrome&&!isPseudoPalindrome) System.out.println("2");


        }

    }

    static boolean isPalindromeChecker(StringBuffer str) {

        StringBuffer strBf1 = new StringBuffer(str);
        StringBuffer strBf2 = new StringBuffer(str);

        int left = 0;
        int right = str.length() - 1;
        while (left < str.length() / 2) {
            if (str.charAt(left) != str.charAt(right)) {
                pseudo.add(strBf1.deleteCharAt(left));
                pseudo.add(strBf2.deleteCharAt(right));
                return false;
            }
            left++;
            right--;
        }

        return true;
    }


    static boolean isPalindrome(StringBuffer str) {

        int left = 0;
        int right = str.length() - 1;
        while (left < str.length() / 2) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
