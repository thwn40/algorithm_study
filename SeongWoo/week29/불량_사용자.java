package SeongWoo.week29;

import java.util.*;

public class 불량_사용자 {

    Set<Set<String>> result = new HashSet<>();

    public static class BannedId {
        String id;
        List<String> possibleIdList = new ArrayList<>();

        public BannedId(String id) {
            this.id = id;
        }

        public void getPossibleId(String[] user_id) {
            for (String id : user_id) {
                if (checkEqual(id)) {
                    this.possibleIdList.add(id);
                }
            }
        }

        public boolean checkEqual(String user_id) {
            if (user_id.length() != this.id.length()) {
                return false;
            }

            for (int i = 0; i < user_id.length(); i++) {
                if (this.id.charAt(i) == '*') {
                    continue;
                }
                if (user_id.charAt(i) != this.id.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    public void dfs(int depth, BannedId[] bannedIdArr, Map<String, Boolean> idCheckMap, Set<String> set) {
        if (depth == bannedIdArr.length) {
            Set<String> resultSet = new HashSet<>(set);
            result.add(resultSet);
            return;
        }

        BannedId bannedId = bannedIdArr[depth];
        for (String userId : bannedId.possibleIdList) {
            Map<String, Boolean> cloneIdCheckMap = new HashMap<>(idCheckMap);
            Set<String> cloneSet = new HashSet<>(set);
            if (cloneIdCheckMap.get(userId) != null) {
                continue;
            }
            cloneIdCheckMap.put(userId, true);
            cloneSet.add(userId);
            dfs(depth + 1, bannedIdArr, cloneIdCheckMap, cloneSet);
        }
    }


    public int solution(String[] user_id, String[] banned_id) {
        BannedId[] bannedIdArr = new BannedId[banned_id.length];
        Map<String, Boolean> idCheckMap = new HashMap<>();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < bannedIdArr.length; i++) {
            bannedIdArr[i] = new BannedId(banned_id[i]);
            bannedIdArr[i].getPossibleId(user_id);
        }

        dfs(0, bannedIdArr, idCheckMap, set);

        return result.size();
    }
}
