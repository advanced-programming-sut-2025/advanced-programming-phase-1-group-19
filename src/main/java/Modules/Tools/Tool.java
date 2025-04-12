package Modules.Tools;

import Modules.Item;

public class Tool extends Item {
    private ToolType toolType;
    protected int level = 0;

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
}
