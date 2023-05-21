package SeongWoo.week37;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 스킬트리 {

    public static class Skill {

        Character name;
        boolean check = false;
        Skill beforeSkill;

        public Skill(Character name, Skill beforeSkill) {
            this.name = name;
            this.beforeSkill = beforeSkill;
        }
    }

    public void initSkill(List<Skill> skillList) {
        for (Skill skill : skillList) {
            skill.check = false;
        }
    }

    private static void initSkills(String skill, List<Skill> skillList, Map<Character, Skill> skillMap) {
        Skill dumpSkill = new Skill(null,null);
        dumpSkill.check = true;
        Skill firstSkill = new Skill(skill.charAt(0), dumpSkill);

        skillList.add(firstSkill);
        skillMap.put(skill.charAt(0), firstSkill);

        for (int i = 1; i < skill.length(); i++) {
            skillList.add(new Skill(skill.charAt(i), skillList.get(i - 1)));
        }

        for (int i = 1; i < skillList.size(); i++) {
            skillMap.put(skillList.get(i).name, skillList.get(i));
        }
    }

    private static int judgeSkillTree(int answer, Map<Character, Skill> skillMap, String skillTree) {

        for (int j = 0; j < skillTree.length(); j++) {
            char skillName = skillTree.charAt(j);
            if (!skillMap.containsKey(skillName)) {
                continue;
            }

            Skill skillInfo = skillMap.get(skillName);
            if (!skillInfo.beforeSkill.check) {
                return answer;
            }

            skillInfo.check = true;
        }
        return answer + 1;
    }

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        List<Skill> skillList = new ArrayList<>();
        Map<Character, Skill> skillMap = new HashMap<>();

        initSkills(skill, skillList, skillMap);

        for (int i = 0; i < skill_trees.length; i++) {
            String skillTree = skill_trees[i];
            initSkill(skillList);

            answer = judgeSkillTree(answer, skillMap, skillTree);
        }

        return answer;
    }
}
