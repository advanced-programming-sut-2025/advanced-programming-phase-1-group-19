package Controllers;

import Modules.Enums.InGameMenu;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;

public class CraftingController extends Controller {

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
