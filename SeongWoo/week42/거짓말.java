package SeongWoo.week42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 거짓말 {

    public static void deleteParty(List<List<Integer>> partyList, Queue<Integer> truePeopleQueue,
                                   boolean[] truePeopleCheckArr) {

        while (!truePeopleQueue.isEmpty()) {
            Integer truePeople = truePeopleQueue.poll();

            for (int i = 0; i < partyList.size(); i++) {
                List<Integer> party = partyList.get(i);
                if (!party.contains(truePeople)) {
                    continue;
                }
                for (Integer people : party) {
                    if (truePeopleCheckArr[people]) {
                        continue;
                    }
                    truePeopleQueue.offer(people);
                }
                partyList.remove(party);
                i--;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //*** 입력, truePeopleCheckArr, truePeopleQueue, partyList 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int peopleSize = Integer.parseInt(st.nextToken());
        int partySize = Integer.parseInt(st.nextToken());

        boolean[] truePeopleCheckArr = new boolean[peopleSize + 1];
        Queue<Integer> truePeopleQueue = new LinkedList<>();
        List<List<Integer>> partyList = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        int truePeopleSize = Integer.parseInt(st.nextToken());
        for (int i = 0; i < truePeopleSize; i++) {
            int truePeople = Integer.parseInt(st.nextToken());
            truePeopleCheckArr[truePeople] = true;
            truePeopleQueue.offer(truePeople);
        }

        for (int i = 0; i < partySize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            ArrayList<Integer> party = new ArrayList<>();

            int partyPeopleSize = Integer.parseInt(st.nextToken());
            for (int j = 0; j < partyPeopleSize; j++) {
                int partyPeople = Integer.parseInt(st.nextToken());
                party.add(partyPeople);
            }
            partyList.add(party);
        }
        //***

        deleteParty(partyList, truePeopleQueue, truePeopleCheckArr);

        System.out.println(partyList.size());
    }
}
