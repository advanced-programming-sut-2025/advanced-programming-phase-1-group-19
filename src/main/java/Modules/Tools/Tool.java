package Modules.Tools;

import Modules.Item;

public class Tool extends Item {
    private ToolType toolType;
    protected int level = 0;

    public int getLevel() {
        return level;
    }

    public Tool(String name, int takenSpace) {
        super(name, takenSpace);
    }

    public LevelInfo getLevelInfo() {
//        TODO: get level info from tool type
    }

    public void upgradeLevel() {

    }

    @Override
    public void use() {
//        TODO: implement if needed
    }

    @Override
    public void drop() {

    }

    @Override
    public void delete() {

    }

    @Override
    public String getName() {
        return toolType.getName();
    }

    public ToolType getToolType() {
        return toolType;
    }
}
