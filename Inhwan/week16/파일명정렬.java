package Inhwan.week16;

import java.util.Arrays;
import java.util.Comparator;

class 파일명정렬 {
    public String[] solution(String[] files) {
        String[][] arr = new String[files.length][];

        for (int i = 0; i < files.length; i++) {
            arr[i] = parseFile(files[i]);
        }

        String[] ans = Arrays.stream(arr)
                .sorted(Comparator.comparing(file -> Integer.parseInt(file[1])))
                .sorted(Comparator.comparing(file -> file[0].toUpperCase()))
                .map(file -> file[0] + file[1] + file[2])
                .toArray(String[]::new);

        return ans;
    }

    String[] parseFile(String file) {
        String[] result = new String[3];

        int i = 0;
        while (!isNumber(file.charAt(i))) {
            i++;
        }
        result[0] = file.substring(0, i);

        int j = i;
        while (j < file.length() &&
                isNumber(file.charAt(j))) {
            j++;
        }
        result[1] = file.substring(i, j);

        if (j < file.length()) {
            result[2] = file.substring(j, file.length());
        } else {
            result[2] = "";
        }

        return result;
    }

    boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }
}