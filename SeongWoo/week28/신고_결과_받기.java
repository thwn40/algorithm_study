package SeongWoo.week28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 신고_결과_받기 {

    private static class Account {
        String name;
        List<Account> reportList = new ArrayList<>();
        int reportedCount = 0;
        boolean isBlock = false;
        int mailCount = 0;

        public Account(String name) {
            this.name = name;
        }

        public void report(Account reportedAccount) {
            if (reportList.contains(reportedAccount)) {
                return;
            }
            reportList.add(reportedAccount);
            reportedAccount.reportedCount++;
        }

        public void checkBlock(int k) {
            if (this.reportedCount >= k) {
                this.isBlock = true;
            }
        }

        public void getMailCount() {
            for (Account reportedAccount : reportList) {
                if (reportedAccount.isBlock) {
                    mailCount++;
                }
            }
        }
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Account> accountMap = new HashMap<>();
        int[] result = new int[id_list.length];

        for (String name : id_list) {
            accountMap.put(name, new Account(name));
        }

        for (String reportContent : report) {
            String[] split = reportContent.split(" ");
            String reportName = split[0];
            String reportedName = split[1];

            Account reportAccount = accountMap.get(reportName);
            Account reportedAccount = accountMap.get(reportedName);

            reportAccount.report(reportedAccount);
        }

        for (int i = 0; i < result.length; i++) {
            Account account = accountMap.get(id_list[i]);
            account.checkBlock(k);
        }

        for (int i = 0; i < result.length; i++) {
            Account account = accountMap.get(id_list[i]);
            account.getMailCount();
            result[i] = account.mailCount;
        }

        return result;
    }
}
