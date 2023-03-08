import java.util.*;

public class 카카오2019겨울인턴십_불량사용자 {
    static String[] users,bans;
    static boolean[] userChecked;
    static Set<String> banLists;
    static int cnt;
    public static void main(String[] args) {
        String[] user_id = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = new String[]{"fr*d*", "*rodo", "******", "******"};
        System.out.println(solution(user_id, banned_id));

    }

    public static int solution(String[] user_id, String[] banned_id) {
        users = user_id;
        bans = banned_id;
        cnt = 0;
        banLists = new HashSet<>();
        userChecked = new boolean[user_id.length];
        bfs(0,new ArrayList<>());
        return banLists.size();
    }

    public static void bfs(int c, List<String> banlist) {
        if (c==bans.length) {
            Collections.sort(banlist);
            banLists.add(banlist.toString());
            return;
        }

        String ban = bans[c];
        for (int i = 0; i < users.length; i++) {
            if (!userChecked[i]) {
                if (checkId(users[i], ban)) {
                    userChecked[i] = true;
                    banlist.add(users[i]);
                    bfs(c+1,banlist);
                    banlist.remove(users[i]);
                    userChecked[i] = false;
                }
            }
        }
    }

    // 두 아이디를 비교하는 함수
    public static boolean checkId(String user, String ban) {
        // 길이가 다르면 같지 않음
        if (user.length() != ban.length()) {
            return false;
        }
        // 가능성 있음
        for (int i = 0; i < user.length(); i++) {
            if (ban.charAt(i)=='*') continue;
            if (user.charAt(i) != ban.charAt(i)) {
                // 다름
                return false;
            }
        }
        //같음
        return true;
    }
}



