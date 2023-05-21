package SeongWoo.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 탑 {

    public static class Tower {
        int height;
        int location;
        int sandedLocation = 0;

        public Tower(int height, int location) {
            this.height = height;
            this.location = location;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int towerSize = Integer.parseInt(st.nextToken());
        Tower[] towerArr = new Tower[towerSize];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < towerArr.length; i++) {
            int height = Integer.parseInt(st.nextToken());
            towerArr[i] = new Tower(height, i + 1);
        }

        Stack<Tower> stack = new Stack<>();

        for (int i = towerArr.length - 1; i >= 0; i--) {
            Tower tower = towerArr[i];
            while (!stack.isEmpty() && tower.height >= stack.peek().height) {
                Tower sandTower = stack.pop();
                sandTower.sandedLocation = tower.location;
            }
            stack.push(tower);
        }

        Arrays.stream(towerArr)
                .forEach(a -> System.out.print(a.sandedLocation + " "));
    }
}
