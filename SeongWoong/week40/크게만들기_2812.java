import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 크게만들기_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");

        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);
        int resultLen = N - K;

        Stack<Character> stack = new Stack<>();

        String str = br.readLine();

        for(int i = 0; i < str.length() ; i++){
            if(!stack.empty()){
                while(!stack.empty() && K > 0 && stack.peek() < str.charAt(i)){
                    stack.pop();
                    K--;
                }
            }
            stack.push(str.charAt(i));
        }

        while(stack.size() != resultLen ){
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb.reverse());
    }
}
