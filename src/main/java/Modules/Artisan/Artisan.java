package Modules.Artisan;

import Modules.App;
import Modules.Item;
import Modules.Time;

public class Artisan {

    private ArtisanType type;
    private Item activeItem;
    private Time activationTime;
    private boolean isActive;

    public Artisan(ArtisanType type) {
        this.type = type;
    }

    public ArtisanType getType() {
        return type;
    }

    public Item get() {

        return null;
    }

    public void start(Item item, int count) {
        activeItem = item;
        activationTime = new Time(App.getInstance().getCurrentGame().getTime());
        isActive = true;
    }
}
