package SeongWoo.week40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 옥상_정원_꾸미기 {

    public static class Building {
        int height;
        int location;
        long benchMarkCount = 0L;

        public Building(int height, int location) {
            this.height = height;
            this.location = location;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        Building[] buildingArr = new Building[size];
        Stack<Building> stack = new Stack<>();

        for (int i = 0; i < buildingArr.length; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            buildingArr[i] = new Building(height, i);
        }

        for (Building benchedBuilding : buildingArr) {
            while (!stack.isEmpty() && benchedBuilding.height >= stack.peek().height) {
                Building building = stack.pop();
                building.benchMarkCount = benchedBuilding.location - building.location - 1;
            }
            stack.push(benchedBuilding);
        }

        for (Building building : stack) {
            building.benchMarkCount = size - building.location - 1;
        }

        long totalBenchMarkCount = Arrays.stream(buildingArr)
                .mapToLong(building -> building.benchMarkCount)
                .sum();

        System.out.println(totalBenchMarkCount);
    }
}
