package Modules.Tools;

import Modules.App;
import Modules.Crafting.Material;
import Modules.Crafting.MaterialType;
import Modules.Farming.Plant;
import Modules.Game;
import Modules.Interactions.Messages.GameMessage;
import Modules.Item;
import Modules.Map.Lake;
import Modules.Map.Map;
import Modules.Map.Position;
import Modules.Map.Tile;

import java.lang.reflect.Member;
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

    }
    public GameMessage use(Position position){
        Game game = App.getInstance().getCurrentGame();
        Map map = game.getMap();
        Tile tile = map.getTile(position);
        switch (toolType){
            case hoe -> {
                boolean isSuccess;
                if(tile.getBuilding() == null && tile.getObject() == null){
                    isSuccess = true;
                }
                else{
                    isSuccess = false;
                }
                int energy = toolType.getEnergy(level, isSuccess);
                if(energy > game.getCurrentPlayer().getEnergy().getAmount()){
                    return new GameMessage(null, "You don't have enough energy to use this tool.");
                }
                else if(!isSuccess){
                    return new GameMessage(null, "You couldn't use this tool.");
                }
                else{
                    game.getCurrentPlayer().decreaseEnergy(energy);
                    tile.setPlowed(true);
                    return new GameMessage(null, "You plowed this tile");
                }
            }
            case axe -> {
                boolean isSuccess;
                if(tile.getBuilding() == null && tile.getObject() != null && tile.getObject() instanceof Plant){
                    isSuccess = true;
                }
                else{
                    isSuccess = false;
                }
                int energy = toolType.getEnergy(level, isSuccess);
                if(energy > game.getCurrentPlayer().getEnergy().getAmount()){
                    return new GameMessage(null, "You don't have enough energy to use this tool.");
                }
                else if(!isSuccess){
                    return new GameMessage(null, "You couldn't use this tool.");
                }
                else{
                    game.getCurrentPlayer().decreaseEnergy(energy);
                    map.setTile(position, new Tile(position));
                    game.getCurrentPlayer().getBackPack().addItem(new Material(MaterialType.wood), 50);
                    return new GameMessage(null, "You chopped this tile");
                }
            }
            case wateringCan -> {
                boolean isSuccess;
                if(tile.getBuilding() != null && tile.getBuilding() instanceof Lake){
                    isSuccess = true;
                }
                else{
                    isSuccess = false;
                }
                int energy = toolType.getEnergy(level, isSuccess);
                if(energy > game.getCurrentPlayer().getEnergy().getAmount()){
                    return new GameMessage(null, "You don't have enough energy to use this tool.");
                }
                else if(!isSuccess){
                    return new GameMessage(null, "You couldn't use this tool.");
                }
                else{
                    game.getCurrentPlayer().decreaseEnergy(energy);
                    WateringCan wateringCan = (WateringCan) game.getCurrentPlayer().getBackPack().getToolByType(ToolType.wateringCan);
                    wateringCan.fill();
                    return new GameMessage(null, "You filled your WateringCan");
                }
            }
        }
        return new GameMessage(null, "nothing happened!?");
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

    @Override
    public String toString() {
        return getLevelInfo().levelName() + " " + getName();
    }
}
