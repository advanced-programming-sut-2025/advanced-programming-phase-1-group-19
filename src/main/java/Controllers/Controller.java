package Controllers;

import Models.Interactions.Messages.Message;

public abstract class Controller {
    public abstract Message showCurrentMenu();

    public abstract Message exit();
}
