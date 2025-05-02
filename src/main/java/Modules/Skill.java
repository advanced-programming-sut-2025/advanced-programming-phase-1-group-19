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
        this.amount += increment;
        if(level==0 && amount >= 150) {
            this.level=1;
        }
        else if(level==1 && amount >= 250) {
            this.level=2;
        }
        else if(level==2 && amount >= 350) {
            this.level=3;
        }
        else if(level==3 && amount >= 450) {
            this.level=4;
        }

    }

    public int getAmount() {
        return amount;
    }

    public int getLevel() {
        return level;
    }
}

