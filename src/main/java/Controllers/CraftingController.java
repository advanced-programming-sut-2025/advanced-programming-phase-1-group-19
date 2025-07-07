package Controllers;

import Models.App;
import Models.Crafting.CraftingItem;
import Models.Crafting.CraftingRecipe;
import Models.Crafting.Recipe;
import Models.Game;
import Models.Interactions.Messages.GameMessage;
import Models.Enums.InGameMenu;
import Models.Interactions.Messages.GameMessage;
import Models.Interactions.Messages.Message;
import Models.Item;
import Models.Map.Position;
import Models.Map.Tile;
import Models.Player;

import java.io.Serializable;

import java.util.Map;

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
        for (Map.Entry<Item, Integer> itemIntegerEntry : recipe.getIngredients().entrySet()) {
            if(!player.getBackPack().checkItem(itemIntegerEntry.getKey(),itemIntegerEntry.getValue())){
                return new GameMessage(null,"You have not enough ingredients in your crafting recipe");
            }
        }
        for (Map.Entry<Item, Integer> itemIntegerEntry : recipe.getIngredients().entrySet()) {
            player.getBackPack().removeItem(itemIntegerEntry.getKey(),itemIntegerEntry.getValue());
        }
        CraftingItem craftingItem = new CraftingItem(recipe);
        player.decreaseEnergy(2);
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
