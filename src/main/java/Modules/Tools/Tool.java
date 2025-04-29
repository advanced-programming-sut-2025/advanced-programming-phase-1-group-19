package Modules.Tools;

import Modules.Item;

import java.util.ArrayList;

public class Tool extends Item {
    private ToolType toolType;
    protected int level = 0;

    public Tool(String name, int takenSpace) {
        super(name, takenSpace);
        this.toolType = ToolType.getToolTypeByName(name);
        this.level = 0;
    }

    public LevelInfo getLevelInfo() {
//        TODO: get level info from tool type
        return toolType.getLevel(level);
    }

    public void upgradeLevel() {
        if(toolType.getLevels().size() > level + 1){
            level++;
        }
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
}
