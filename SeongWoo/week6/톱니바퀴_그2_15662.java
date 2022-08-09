package SeongWoo.week6;

import java.util.ArrayList;
import java.util.Scanner;

public class 톱니바퀴_그2_15662 {

    public static class Gear {
        //톱니바퀴의 극을 담은 list
        ArrayList<Integer> poleList = new ArrayList<>();

        //톱니바퀴가 인접하는 방향(왼쪽에서 인접하는지 오른쪽에서 인접하는지), 회전 방법, 인접한 극을 받아서 Gear를 회전하는 메서드
        //결과적으로 이 Gear가 회전한 방법을 반환한다.(회전하지 않으면 0반환)
        public int linkCycle(boolean isLeftStream, int cycleDirection, int pole) {
            int linkPole;
            //톱니바퀴가 왼쪽으로부터 인접한다면 이 Gear에서 인접하는 극은 오른쪽이 된다(12로부터 3번째).
            if (isLeftStream) {
                linkPole = poleList.get(2);
            //오른쪽은 12시로부터 6번째
            } else {
                linkPole = poleList.get(6);
            }

            //맞다은 두 극이 같으면 Gear를 회전하지 않는다.
            if (linkPole == pole) {
                return 0;
            }

            //맞다은 두극이 다르면 Gear를 인접한 기어가 회전한 방법의 반대 방법으로 회전시킨다.
            cycle(-1 * cycleDirection);
            return -1 * cycleDirection;
        }

        //회전 방법을 받고 Gear를 회전하는 메서드
        private void cycle(int cycleDirection) {
            //시계 방향
            if (cycleDirection == 1) {
                Integer endPole = poleList.get(poleList.size() - 1);
                poleList.remove(poleList.size() - 1);
                poleList.add(0, endPole);

            }
            //반시계 방향
            if (cycleDirection == -1) {
                Integer firstPole = poleList.get(0);
                poleList.remove(0);
                poleList.add(firstPole);
            }
        }
    }

    //index 위치의 Gear를 회전시키고 인접한 Gear들을 연쇠적으로 회전시키는 메서드
    public static void cycleSimulation(Gear[] gearArray, int index, int cycleDirection) {
        //초기 Gear
        Gear gear = gearArray[index];
        //초기 Gear로 부터 오른쪽 방향을 탐색할 때, Gear(회전 될쪽)와 인접한 Gear(회전 한쪽)간에 인접하는 오른쪽 극
        Integer tempRightLinkPole = gear.poleList.get(2);
        //초기 Gear로 부터 왼쪽 방향을 탐색할 때,Gear(회전 될쪽)와 인접한 Gear(회전 한쪽)간에 인접하는 왼쪽 극
        Integer tempLeftLinkPole = gear.poleList.get(6);

        int tempCycleDirection = cycleDirection;
        //초기 회전한 Gear로 부터 왼쪽에 있는 Gear들을 연속적으로 회전시킨다.
        for (int i = index - 1; i >= 0; i--) {
            //왼쪽에 있는 Gear
            Gear leftGear = gearArray[i];
            //회전 시키기 전의 인접하는 왼쪽 극을 저장한다.
            Integer leftLinkPole = leftGear.poleList.get(6);
            //회전
            tempCycleDirection = leftGear.linkCycle(true, tempCycleDirection, tempLeftLinkPole);
            //만약 회전하지 않는다면 반복문을 끝낸다.
            if (tempCycleDirection == 0) {
                break;
            }
            //다음 왼쪽에 있는 Gear를 회전할 지 판단하기 위해서 현재 회전시킨 Gear의 회전시키기전 인접한 극을 다음 반복문으로 넘긴다.
            tempLeftLinkPole = leftLinkPole;
        }

        tempCycleDirection = cycleDirection;
        for (int i = index + 1; i < gearArray.length; i++) {
            Gear rightGear = gearArray[i];
            Integer rightLinkPole = rightGear.poleList.get(2);
            tempCycleDirection = rightGear.linkCycle(false, tempCycleDirection, tempRightLinkPole);
            if (tempCycleDirection == 0) {
                break;
            }
            tempRightLinkPole = rightLinkPole;
        }

        gear.cycle(cycleDirection);
    }

    public static void main(String[] args) {
        //초기화 시작
        int result = 0;
        Scanner scanner = new Scanner(System.in);

        int gearSize = scanner.nextInt();
        //주어진 Gear를 담는 배열
        Gear[] gearArray = new Gear[gearSize];

        for (int i = 0; i < gearArray.length; i++) {
            String next = scanner.next();
            Gear gear = new Gear();
            for (int j = 0; j < next.length(); j++) {
                gear.poleList.add(Integer.parseInt(String.valueOf(next.charAt(j))));
            }
            gearArray[i] = gear;
        }
        //초기화 끝

        int caseSize = scanner.nextInt();
        //주어지 케이스 횟수만큼 Gear를 회전시킨다.
        for (int i = 0; i < caseSize; i++) {
            int index = scanner.nextInt() - 1;
            int cycleDirection = scanner.nextInt();
            cycleSimulation(gearArray, index, cycleDirection);
        }

        //12시 방향이 S극인 Gear의 개수를 구한다.
        for (int i = 0; i < gearSize; i++) {
            if (gearArray[i].poleList.get(0) == 1) {
                result++;
            }
        }
        System.out.println(result);
    }
}
