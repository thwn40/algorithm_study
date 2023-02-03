package SeongWoong.week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class 문자열집합_14425 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = Integer.parseInt(str.split(" ")[0]);
        int M = Integer.parseInt(str.split(" ")[1]);

        HashSet<String> S = new HashSet<>();
        // contains의 실행속도가
        // list는 O(N)이지만 set의 경우는 O(1)이다.
        for(int i=0; i<N; i++) {
            S.add(br.readLine());
        }

        String[] check =new String[M];
        for(int i=0; i<M; i++) {
            check[i] = br.readLine();
        }

        int cnt = 0 ;
        for(int i =0; i<M ;i++) {
            if(S.contains(check[i])) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
