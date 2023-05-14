package summerWinterCodingTo2018;

import java.util.HashMap;
import java.util.Map;

public class 방문길이 {
    class Solution {
        public int solution(String dirs) {
            Map<Character, Integer> map = new HashMap<>();
            map.put('U',0);
            map.put('D',2);
            map.put('R',1);
            map.put('L',3);
            int[] dy = new int[]{-1,0,1,0};
            int[] dx = new int[]{0,1,0,-1};
            int answer = 0;
            boolean[][][] visit = new boolean[11][11][4];
            int y = 5;
            int x = 5;
            for(int i=0;i<dirs.length();i++){
                int c = map.get(dirs.charAt(i));
                System.out.println("i"+(i+1));
                if(y+dy[c]>10 || y+dy[c]<0 || x+dx[c]>10 || x+dx[c]<0 ) continue;
                if(!visit[y][x][c]){
                    answer++;
                }
                visit[y][x][c] = true;
                y += dy[c];
                x += dx[c];
                c = c+2>3 ? c-2 : c + 2;
                visit[y][x][c] = true;
            }
            return answer;
        }
    }
}
