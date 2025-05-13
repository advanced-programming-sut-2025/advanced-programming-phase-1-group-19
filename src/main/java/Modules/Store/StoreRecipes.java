package Modules.Store;

import Modules.Crafting.Recipe;
import Modules.Enums.Season;

public class StoreRecipes {
    private Recipe recipe;
    private int price;
    private int dailyLimit;// 2000 if unlimited
    private Season season;
    private int seasonPrice;

    public StoreRecipes(Recipe recipe, int price, int dailyLimit, Season season, int seasonPrice) {
        this.recipe = recipe;
        this.price = price;
        this.dailyLimit = dailyLimit;
        this.season = season;
        this.seasonPrice = seasonPrice;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getPrice() {
        return price;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public Season getSeason() {
        return season;
    }

    public int getSeasonPrice() {
        return seasonPrice;
    }

    public void removeDailyLimit(int cnt) {
        this.dailyLimit -= cnt;
    }

}
