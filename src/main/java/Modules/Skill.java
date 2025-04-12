package Modules;

import Modules.Enums.SkillType;

public class Skill {

    private SkillType skillType;
    private int level;
    private int amount;

    public Skill(SkillType skillType) {
        this.skillType = skillType;
        level = 0;
        amount = 0;
    }

    public void addAmount(int increment) {
//        TODO:also check if it is needed to level up
    }

    public int getLevel() {}
}

