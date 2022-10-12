package SeongJun.week11;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;

public class 크게만들기_2812 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        sc.nextLine();
        StringBuffer num = new StringBuffer(sc.nextLine());

        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.parseInt(String.valueOf(num.charAt(0))));
        int index = 0;
        while(K!=0){
            if(index==N-1){
                break;
            }
            System.out.println(num);
            System.out.println(stack);

            int i = Integer.parseInt(String.valueOf(num.charAt(index)));
            if(stack.peek()<i){
                Integer pop = stack.pop();
                Integer push = stack.push(i);
                K--;
                num.deleteCharAt(index);
                index++;
                continue;
            }
            Integer push = stack.push(i);
            index++;
        }
        System.out.println(stack);
    }
}
