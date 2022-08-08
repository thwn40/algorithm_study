package SeongJun.week5;



import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;




public class 이모티콘_14426 {

    static int e =0;
    static int c =0;
    static int d = 0;

    static boolean[][] visited= new boolean[10000][10000];


    static int bfs(int S) throws InterruptedException {
        Queue<operation> queue = new LinkedList<>();
        operation operation = new operation(0,1,0);
        queue.offer(operation);

        while(!queue.isEmpty()){

            operation poll = queue.poll();

            if (poll.emoticons==S) {
                return poll.depth;
            }
            e = poll.emoticons;
            c = poll.clipboard;
            d = poll.depth;


            int copy = poll.copy(e, c);
            if(!visited[e][copy]) {
                visited[e][copy]=true;
                queue.offer(new operation(d+1,e,copy));
            }

            int paste = poll.paste(e, c);
            if(!visited[paste][c]) {
                visited[paste][c]=true;
                queue.offer(new operation(d+1,paste, c));
            }

            int delete = poll.delete(e);
            if(!visited[delete][c]) {
                visited[delete][c]=true;
                queue.offer(new operation(d+1,delete, c));
            }





        }

        return -1;
    }
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        int S = sc.nextInt();

        System.out.println(bfs(S));
    }



}


class operation{
    int depth = 0;
    int emoticons = 1;
    int clipboard = 0;
    int copy(int emoticons, int clipboard){
        return emoticons;
    }
    int paste(int emoticons, int clipboard){
        return emoticons+clipboard;
    }
    int delete(int emoticons){
        if(emoticons-1<0) return 0;
        return emoticons-1;
    }


    public operation(int depth, int emoticons, int clipboard) {
        this.depth = depth;
        this.emoticons = emoticons;
        this.clipboard = clipboard;
    }

    @Override
    public String toString() {
        return "("+emoticons+","+clipboard+",d="+depth+")";

    }
}
