package Modules.Crafting;

import Modules.Enums.SkillType;

public class Buff {

    private final SkillType skillType; // null if energy;
    private final int hours;
    private final int energyAddition;

    private Buff(SkillType skillType, int hours, int energyAddition) {
        this.skillType = skillType;
        this.hours = hours;
        this.energyAddition = energyAddition;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public int getHours() {
        return hours;
    }

    public int getEnergyAddition() {
        return energyAddition;
    }
}
