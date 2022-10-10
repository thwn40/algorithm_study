package Inhwan.week1213;

import java.util.*;

public class Kakao_algorithm_test_1 {

    public static void main(String[] args) {
//        String today = "2022.05.19";
//        String[] terms = {"A 6", "B 12", "C 3"};
//        String[] privacies = {"2021.05.02 A",
//                "2021.07.01 B",
//                "2022.02.19 C",
//                "2022.02.20 C"};

        String today = "2020.01.01";
        String[] terms = {"Z 3", "D 5"};
        String[] privacies = {"2019.01.01 D",
                "2019.12.15 Z",
                "2019.08.02 D",
                "2019.07.01 D",
                "2018.12.28 Z"
        };

        System.out.println(Arrays.toString(solution(today, terms, privacies)));
    }

    static int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();

        int[] Today = dateTransform(today);
        Map<Character,Integer> Terms = termsToMap(terms);

        int[] startDay;
        int[] expiration;
        char type;
        for (int i = 0; i < privacies.length; i++) {
            startDay = dateTransform(privacies[i]);
            type =privacies[i].charAt(11);
            expiration = dateAfterNMonth(Terms.get(type), startDay);

            if (expired(Today, expiration)) {
                result.add(i);
            }
        }

        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i) + 1;
        }

        return ans;
    }

    static int[] dateAfterNMonth(int N, int[] date) {

        date[1] += N;
        date[0] += (date[1]/12);
        date[1] %= 12;

        return date;
    }

    static boolean expired(int[] Today, int[] expiration) {
//        System.out.println(Arrays.toString(Today));
//        System.out.println(Arrays.toString(expiration));

        if (Today[0] > expiration[0]) return true;
        if (Today[0] == expiration[0] && Today[1] > expiration[1]) return true;
        if (Today[0] == expiration[0] && Today[1] == expiration[1] && Today[2] >= expiration[2]) return true;
        else return false;
    }

    static int[] dateTransform(String date) {
        int[] result = new int[3];

        result[0] = Integer.parseInt(date.substring(0,4));
        result[1] = Integer.parseInt(date.substring(5,7));
        result[2] = Integer.parseInt(date.substring(8,10));

        return result;
    }

    static Map<Character,Integer> termsToMap(String[] terms) {
        Map<Character,Integer> result = new HashMap<>();

        for (String term : terms) {
            result.put(term.charAt(0), Integer.parseInt(term.substring(2)));
        }

        return result;
    }
}
