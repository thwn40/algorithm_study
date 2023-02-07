package SeongJun.week17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class 성격유형검사하기{
	class Type implements Comparable<Type>{
		private Character type;
		private Integer score;

		@Override
		public String toString() {
			return "Type{" +
				"type=" + type +
				", score=" + score +
				'}';
		}

		Type(Character type, Integer score) {
			this.type = type;
			this.score = score;
		}

		@Override
		public int compareTo(Type o) {
			if (Objects.equals(this.score, o.score)) {
				return Character.compare(this.type,o.type);
			}
			return -Integer.compare(this.score,o.score);
		}
	}

	public String solution(String[] survey, int[] choices) {
		StringBuilder answer = new StringBuilder();
		Map<Integer, List<Type>> testTable = new HashMap<>();
		Map<Character,Type> typeMap = new HashMap<>();
		List<Character> typeList = List.of('R', 'T', 'C', 'F', 'J', 'M', 'A', 'N');
		for (int i = 0; i < 4; i++) {
			testTable.put(i,new ArrayList<>());
		}
		for (int i = 0; i < typeList.size(); i++) {
			Type type = new Type(typeList.get(i), 0);
			typeMap.put(typeList.get(i),type);
			testTable.get(i/2).add(type);
		}
		for (int i = 0; i < choices.length; i++) {
			int score = choices[i];
			if (score <= 3) {
				typeMap.get(survey[i].charAt(0)).score += 4-score;
			} else if (score == 4) {

			} else {
				typeMap.get(survey[i].charAt(1)).score += score-4;
			}
		}

		for (Integer test : testTable.keySet()) {
			Collections.sort(testTable.get(test));
			answer.append(testTable.get(test).get(0).type);
		}


		return answer.toString();
	}

	public static void main(String[] args) {
		성격유형검사하기 성격유형검사하기 = new 성격유형검사하기();
		성격유형검사하기.solution(new String[]{"AN", "CF", "MJ", "RT", "NA"},new int[]{5, 3, 2, 7, 5});
	}


}
