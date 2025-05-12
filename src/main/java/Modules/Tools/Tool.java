package Modules.Tools;

import Modules.Item;
import Modules.Map.Tile;

import java.util.ArrayList;

public class Tool extends Item {
    private ToolType toolType;
    protected int level = 0;

    public Tool(ToolType toolType) {
        super(toolType.getName(), 0, false);
        this.toolType = toolType;
        this.level = 0;
    }

    public int getLevel() {
        return level;
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

    public void setLevel(int level) {
        this.level = level;
    }
    @Override
    public int getPrice() {
        return 0;
//        TODO: fix this
    }

    @Override
    public void use() {
//        TODO: implement if needed
    }

    @Override
    public void drop(Tile tile) {
        tile.setObject(Tool.this);
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

    @Override
    public String toString() {
        return getLevelInfo().levelName() + " " + getName();
    }
}
