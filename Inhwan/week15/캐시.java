package Inhwan.week15;

import java.util.Arrays;

class 캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        String[] cache = new String[cacheSize];
        Arrays.fill(cache, "-");

        for (String city : cities) {
            answer += time(cache, city.toUpperCase());
        }

        return answer;
    }

    public int time(String[] cache, String city) {
        boolean check = false;
        int index = cache.length - 1;

        String temp = city;
        String change;

        while (!check && index >= 0) {
            if (cache[index].equals(city)) {
                check = true;
            }

            change = cache[index];
            cache[index] = temp;
            temp = change;

            index--;
        }

        if (check) return 1;
        else return 5;
    }
}