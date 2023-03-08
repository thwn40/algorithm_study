public class 카카오2019겨울인턴십_불량사용자2 {
    static String[] users,bans;
    static boolean[] userChecked;
    static int cnt;
    public static int solution(String[] user_id, String[] banned_id) {
        users = user_id;
        bans = banned_id;
        cnt = 0;
        for (int i = 0; i < users.length; i++) {
            userChecked = new boolean[user_id.length];
        }
        bfs(0);
        sameCheck();
        return cnt;
    }
    public static void sameCheck(){
        boolean[] used = new boolean[bans.length];
        for (int i = 0; i < bans.length; i++) {
            if (used[i]== true) continue;

            int s = 1;
            for (int j = i+1; j < bans.length; j++) {
                if (bans[i].equals(bans[j])) {

                    s++;
                    used[i] = true;
                    used[j] = true;
                }
            }
            System.out.println(i+"번째가"+s+"번 같음");
            for (int j = 1; j <= s; j++) {
                cnt /= j;
            }
        }
    }

    public static void bfs(int c) {
        if (c==bans.length) {
            cnt++;
            return;
        }

        String ban = bans[c];
        for (int i = 0; i < users.length; i++) {
            if (!userChecked[i]) {
                if (checkId(users[i], ban)) {
                    userChecked[i] = true;
                    bfs(c+1);
                    userChecked[i] = false;
                }
            }
        }
    }

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
    public static void main(String[] args) {
//        String[] user_id = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        String[] banned_id = new String[]{"fr*d*", "abc1**"};
//        String[] user_id = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
//        String[] banned_id = new String[]{"*rodo", "*rodo", "******"};
        String[] user_id = new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = new String[]{"fr*d*", "*rodo", "******", "******"};
        System.out.println(solution(user_id, banned_id));
    }
}

