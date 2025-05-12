package Controllers;

import Modules.App;
import Modules.Crafting.CraftingItem;
import Modules.Crafting.CraftingRecipe;
import Modules.Game;
import Modules.Interactions.Messages.GameMessage;
import Modules.Enums.InGameMenu;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;
import Modules.Item;
import Modules.Map.Position;
import Modules.Map.Tile;
import Modules.Player;

public class CraftingController extends Controller {
    private static CraftingController instance;

    private CraftingController() {
    }

    public static CraftingController getInstance() {
        if(instance == null) {
            instance = new CraftingController();
        }
        return instance;
    }
    public GameMessage showCraftingRecipe(){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        if(game.getInGameMenu() != InGameMenu.craftingMenu){
            return new GameMessage(null,"You are not in crafting menu");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("These are your known crafting recipes:\n");
        for (CraftingRecipe knownCraftingRecipe : player.getKnownCraftingRecipes()) {
            stringBuilder.append(knownCraftingRecipe.toString()+"\n");
        }
        return new GameMessage(null,stringBuilder.toString());
    }


    public GameMessage crafting(String craftName){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        if(game.getInGameMenu() != InGameMenu.craftingMenu){
            return new GameMessage(null,"You are not in crafting menu");
        }
        CraftingRecipe recipe = CraftingRecipe.getCraftingRecipeByName(craftName);
        if(recipe == null){
            return new GameMessage(null,"There is no item with this name");
        }
        if(!player.knowCraftingRecipe(recipe)){
            return new GameMessage(null,"You haven't yet learned this crafting recipe");
        }
        if(player.getBackPack().getCapacity() >= player.getBackPack().getMaxCapacity() ){
            return new GameMessage(null,"You have no remain place in your backpack");
        }
        CraftingItem craftingItem = new CraftingItem(recipe);
        player.decreaseEnergy(2);
//        TODO : remove used item of player's backpack
        player.getBackPack().addItem(craftingItem,1);
        return new GameMessage(null,"You successfully craft "+craftName+"!");
    }


    @Override
    public Message showCurrentMenu() {
        return new GameMessage(null, InGameMenu.craftingMenu.toString());
    }

    @Override
    public Message exit() {
//        TODO: fix this
        return null;
    }
}
