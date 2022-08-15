package SeongJun.week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class gear implements Cloneable,Comparable {
    int num = 0;
    int[] pole;

    @Override
    protected gear clone() throws CloneNotSupportedException {
        return (gear)super.clone();
    }

    void rotate(int direction) {
        int[] changedPole = new int[8];
        if (direction == 1) {
            for (int i = 1; i < pole.length; i++) {
                changedPole[i] = pole[i - 1];
            }
            changedPole[0]=pole[pole.length-1];
        }
        if (direction == -1) {

            for (int i = 0; i < pole.length - 1; i++) {
                changedPole[i] = pole[i+1];

            }
            changedPole[changedPole.length-1]=pole[0];
        }
        pole=changedPole;
    }

    public gear(int num, int[] pole) {
        this.num = num;
        this.pole = pole;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int[] getPole() {
        return pole;
    }

    public void setPole(int[] pole) {
        this.pole = pole;
    }

    @Override
    public String toString() {
        return "gear{" +
                "num=" + num +
                ", pole=" + Arrays.toString(pole) +
                '}';
    }



    @Override
    public int compareTo(Object o) {
        gear o1 = (gear) o;
        return num-o1.num;
    }
}


public class 톱니바퀴2_15662 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        ArrayList<gear> gears = new ArrayList<>();
        sc.nextLine();
        for (int i = 0; i < T; i++) {
            int[] pole = new int[8];
            String s = sc.nextLine();

            for (int j = 0; j < 8; j++) {
                pole[j] = Character.getNumericValue(s.charAt(j));
            }
            gears.add(new gear(i, pole));
        }
        //  가운데 톱니바퀴가 하나 있으면 6번 톱니는 왼쪽의 2번이랑 결합
        // 2번 톱니는 오른쪽의 6번이랑 결합
        int K = sc.nextInt();



        int count =0;
        ArrayList<gear> ans=new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int start = sc.nextInt()-1;
            gear gear = gears.get(start);
            int direction = sc.nextInt();
            int tmp = direction;



            ans.add(gear);


            //오른족 탐색
            boolean rotate = true;
            for (int j = start; j < T; j++) {
                if(j+1>=T){
                    break;
                }

                gear leftGear = gears.get(j);
                gear rightGear = (gear)gears.get(j + 1).clone();
                if (rotate &&leftGear.getPole()[2] != rightGear.getPole()[6]) {
                    direction=-direction;
                    rightGear.rotate(direction);
                }
                else{
                    rotate=false;
                }
                ans.add(rightGear);

            }
            rotate = true;
            direction = tmp;
            for (int j = start; j >= 1; j--) {
                gear rightGear = gears.get(j);
                gear leftGear = (gear)gears.get(j-1).clone();
                if(rotate&&rightGear.getPole()[6]!=leftGear.getPole()[2]){
                    direction=-direction;
                    leftGear.rotate(direction);

                }
                else{
                    rotate=false;
                }


                ans.add(leftGear);


            }
            gear.rotate(tmp);

            Collections.sort(ans);

            gears.clear();
            gears=(ArrayList<gear>)ans.clone();
            ans.clear();
        }


        for (gear gear : gears) {
            if (gear.getPole()[0]==1) {
                count++;
            }
        }
        System.out.println(count);
    }

}
