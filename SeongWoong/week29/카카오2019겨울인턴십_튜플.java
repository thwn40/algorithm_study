import java.util.*;

public class 카카오2019겨울인턴십_튜플 {
    public static void main(String[] args) {
        String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
        System.out.println(Arrays.toString(solution(s)));
    }
    public static int[] solution(String s) {
        int[] answer = {};
        // {4,2,3},{3},{2,3,4,1},{2,3}
        s = s.substring(1, s.length() - 1);
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                n++;
            }
        }
        List<Integer>[] lists = new List[n];
        answer = new int[n];
        StringTokenizer st = new StringTokenizer(s, "}");
        // {4,2,3
        // ,{3
        // ,{2,3,4,1
        // ,{2,3
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            str = str.replace("{", "");
            // 4,2,3
            // ,3
            // ,2,3,4,1
            // ,2,3
            str = str.replace(",", " ");
            // 4 2 3
            //  3
            //  2 3 4 1
            //  2 3
            String[] a = str.split(" ");
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                if (a[i].equals("")) continue;
                list.add(Integer.parseInt(a[i]));
            }
            lists[list.size()-1] = list;
        }
        for (int i = lists.length-1; i >0 ; i--) {
            for (int j = 0; j < lists[i-1].size(); j++) {
                lists[i].remove(lists[i - 1].get(j));
            }
        }
        for (int i = 0; i < lists.length; i++) {
            answer[i] = lists[i].get(0);
        }
        return answer;
    }
}
