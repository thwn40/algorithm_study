package Inhwan.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 전화번호목록_5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            String[] arr = new String[n];

            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine();
            }

            Arrays.sort(arr, ((String s1, String s2) -> s2.length() - s1.length()));

            Set<String> set = new HashSet<>();

            boolean det = true;

            for (String str : arr) {
                if (!set.contains(str)) {
                    for (int i = 0; i < str.length(); i++) {
                        set.add(str.substring(0, i+1));
                    }
                } else {
                    det = false;
                    break;
                }
            }

            if (det) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}