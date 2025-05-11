package Controllers;

import Modules.App;
import Modules.Crafting.CraftingItem;
import Modules.Crafting.CraftingRecipe;
import Modules.Game;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;
import Modules.Item;
import Modules.Player;

public class CraftingController extends Controller {
    public GameMessage showCraftingRecipe(){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
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
        CraftingRecipe recipe = CraftingRecipe.getCraftingRecipeByName(craftName);
        if(recipe == null){
            return new GameMessage(null,"There is no item with this name");
        }
        if(!player.knowCraftingRecipe(recipe)){
            return new GameMessage(null,"You haven't yet learned this crafting recipe");
        }
        if(player.getBackPack().getCapacity() <= 0 ){
            return new GameMessage(null,"You have no remain place in your backpack");
        }
        CraftingItem craftingItem = new CraftingItem(recipe);
        player.decreaseEnergy(2);
//        TODO : remove used item of player's backpack
        player.getBackPack().addItem(craftingItem,1);
        return new GameMessage(null,"You successfully craft "+craftName+"!");
    }

    public GameMessage laceItem(String itemName,String direction){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        CraftingRecipe recipe = CraftingRecipe.getCraftingRecipeByName(itemName);
        if(recipe == null){
            return new GameMessage(null,"There is no item with this name");
        }
        if(direction.equals("north")){

        }
    }

    public GameMessage cheatAddItem(String itemName,int amount){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Item item = Item.getItemByName(itemName);
        if(item == null){
            return new GameMessage(null,"There is no item with this name");
        }
        if(player.getBackPack().getCapacity() <= 0 ){
            return new GameMessage(null,"You have no remain place in your backpack");
        }
        player.getBackPack().addItem(item,amount);
        return new GameMessage(null,"You successfully add "+amount+" "+itemName);
    }


    @Override
    public Message exit() {}
}
