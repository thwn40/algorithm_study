package SeongJun.week11;

import java.util.*;

public class 주사위_1041 {
    //2개 일때 최소인 3개 붙어있는거 4개 + 2개중에 최소인거 4개
    //3개 일때
    //3층 최소인 3개 붙어있는거 4개 + 2개중에 최소인가 4개+ 가운데 최소 1개 =24+12+1=37
    //2층 최소인 2개 붙어있는거 4개 + 가운데용 최소 1개 = 12+4=16
    //1층 위랑 똑같이
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> dice = new HashMap<>();
        ArrayList<Integer> side3 = new ArrayList<>();
        ArrayList<Integer> side2 = new ArrayList<>();
        int N = scan.nextInt();
        Integer side1min = Integer.MAX_VALUE;
        Integer side1max = Integer.MIN_VALUE;
        for (int i = 0; i < 6; i++) {
            int num = scan.nextInt();
            dice.put(Character.toString(65+i), num);
            side1min=Math.min(side1min,num);
            side1max=Math.max(side1max,num);
        }
        //ACE,ACB,ABD,AED,FCE,FCB,FBD,FED
        for (int i = 0; i < 6; i+=5) {
            String topBottom = Character.toString(65 + i);
//            System.out.println(topBottom);
            side3.add(dice.get(topBottom) + dice.get("C") + dice.get("E"));
            side3.add(dice.get(topBottom) + dice.get("C") + dice.get("B"));
            side3.add(dice.get(topBottom) + dice.get("B") + dice.get("D"));
            side3.add(dice.get(topBottom) + dice.get("E") + dice.get("D"));
        }
        //EA,DA,BA,CA,EC,ED,BD,CB,EF,DF,BF,CF
        for (int i = 0; i < 6; i+=5) {
            String topBottom = Character.toString(65 + i);
//            System.out.println(topBottom);
            side2.add(dice.get(topBottom) + dice.get("E"));
            side2.add(dice.get(topBottom) + dice.get("D"));
            side2.add(dice.get(topBottom) + dice.get("B"));
            side2.add(dice.get(topBottom) + dice.get("C"));
        }
        for (String s : Arrays.asList("C", "D")) {
            side2.add(dice.get("E") + dice.get(s));
        }
        for (String s : Arrays.asList("D", "C")) {
            side2.add(dice.get("B") + dice.get(s));
        }

        Collections.sort(side3);
        Collections.sort(side2);
        Integer side3min = side3.get(0);
        Integer side2min = side2.get(0);

//
//        System.out.println("side2min = " + side2min);
//        System.out.println("side3min = " + side3min);

        long sum=0;
        if(N==1){
            for (Integer value : dice.values()) {
                sum+=value;
            }
           sum-=side1max;
        }
        //꼭대기층
        if(N==2){
            sum=side3min*4;
        }
        if(N>2){
            sum=side3min*4+side1min*((long)Math.pow(N-2,2))+side2min*((4*N)-8);
        }
        //나머지층
        if(N==2){
            for (int i = 1; i < N; i++) {
                sum+=(side2min*4);
            }
        }
        if(N>2){
            for (int i = 1; i < N; i++) {
                sum+=(side2min*4)+((long) side1min *(N-2)*4);
            }
        }





//        System.out.println(dice);
        System.out.println(sum);

    }
}
