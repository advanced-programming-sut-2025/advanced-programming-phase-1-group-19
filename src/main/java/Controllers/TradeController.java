package Controllers;

import Modules.App;
import Modules.Communication.FriendShip;
import Modules.Communication.Trade;
import Modules.Game;
import Modules.Interactions.Messages.GameMessage;
import Modules.Item;
import Modules.Player;

public class TradeController extends Controller {
    public GameMessage startTrade(){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("These are all players in this game:\n");
        for (Player gamePlayer : game.getPlayers()) {
            stringBuilder.append(gamePlayer.getUser().getUsername()+"\n");
        }
        return new GameMessage(null,stringBuilder.toString());
    }

    public GameMessage trading(String username,String type,String itemName,int amount,int price,String targetItemName,int targetItemAmount){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player player2 = game.getPlayerByUsername(username);
        if(player2 == null) {
            return new GameMessage(null, "There is no player with that username in this game!");
        }
        Item item = player.getBackPack().getItemByName(itemName);
        if(amount <= 0 ){
            return new GameMessage(null, "Invalid amount");
        }
        int backpackAmount = player.getBackPack().getItemCount(item);
        if(item == null) {
            return new GameMessage(null, "You do not have this item in your backpack os this item doesn't exist!");
        }
        if(amount > backpackAmount){
            return new GameMessage(null, "You do not have enough backpack amount!");
        }
        if(price !=0 && targetItemName!=null){
            return new GameMessage(null, "You can just choose one ways of trading");
        }
        Trade trade=null;
        if(type.equals("offer")){
            if(price != 0){
                trade = new Trade(player,false,item,amount,price);
            }
            else {
                Item targetitem = Item.getItemByName(targetItemName);
                trade = new Trade(player,false,item,targetitem,targetItemAmount,amount);
            }
            player2.getFriendShipByPlayer(player).addTradeOffer(trade);
        }
        else if(type.equals("request")){
            if(price != 0){
                trade = new Trade(player,false,item,amount,price);
            }
            player2.getFriendShipByPlayer(player).addTradeOffer(trade);
        }
        else {
            return new GameMessage(null, "Invalid type");
        }
        return new GameMessage(null,"Your offer was sent to "+player2.getUser().getUsername());
    }

    public GameMessage tradeResponse(Player player2,String answer,int id){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Trade trade = null;
        if(answer.equals("accept")){
            trade = player.getFriendShipByPlayer(player2).getById(id);
            trade.doTrade(player,player2);
            player2.getFriendShipByPlayer(player).removeTradeOffer(trade);
            player2.getFriendShipByPlayer(player).addTrade(trade);
            player.getFriendShipByPlayer(player2).addTrade(trade);
            player.getFriendShipByPlayer(player2).increaseXp(20);
            player2.getFriendShipByPlayer(player).increaseXp(20);
            return new GameMessage(null , "Your offer was accepted by "+player2.getUser().getUsername());
        }
        else if(answer.equals("reject")){
            trade = player.getFriendShipByPlayer(player2).getById(id);
            player2.getFriendShipByPlayer(player).removeTradeOffer(trade);
            player.getFriendShipByPlayer(player2).decreaseXp(20);
            player2.getFriendShipByPlayer(player).decreaseXp(20);
            return new GameMessage(null,"Your offer was rejected by "+player2.getUser().getUsername());
        }
        else {
            return new GameMessage(null, "Invalid answer");
        }
    }

    public GameMessage tradeHistory(String username){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player player2 = game.getPlayerByUsername(username);
        StringBuilder stringBuilder = new StringBuilder();
        for (Trade trade : player.getFriendShipByPlayer(player2).getTradeLog()) {
            stringBuilder.append("The Item: "+trade.getItem().getName()+"\n");
            if(trade.getType()){
                stringBuilder.append("Type: Offer"+"\n");
            }
            else {
                stringBuilder.append("Type: Request"+"\n");
            }
            if(trade.getPrice() == 0){
                stringBuilder.append("Target item: "+trade.getTargetItem().getName()+"\n");
                stringBuilder.append("Target amount: "+trade.getTargetAmount()+"\n");
            }
            else {
                stringBuilder.append("Price: "+trade.getPrice()+"\n");
            }
        }
        return new GameMessage(null,stringBuilder.toString());
    }

    public GameMessage tradeList(){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder stringBuilder = new StringBuilder();
        for (FriendShip friendShip : player.getFriendShips()) {
            for (Trade tradeOffer : friendShip.getTradeOffers()) {
                stringBuilder.append("The Item: "+tradeOffer.getItem().getName()+"\n");
                if(tradeOffer.getType()){
                    stringBuilder.append("Type: Offer"+"\n");
                }
                else {
                    stringBuilder.append("Type: Request"+"\n");
                }
                if(tradeOffer.getPrice() == 0){
                    stringBuilder.append("Target item: "+tradeOffer.getTargetItem().getName()+"\n");
                    stringBuilder.append("Target amount: "+tradeOffer.getTargetAmount()+"\n");
                }
                else {
                    stringBuilder.append("Price: "+tradeOffer.getPrice()+"\n");
                }
                stringBuilder.append("id: "+friendShip.getTradeId(tradeOffer)+"\n");
            }
        }
        return new GameMessage(null,stringBuilder.toString());
    }

}
