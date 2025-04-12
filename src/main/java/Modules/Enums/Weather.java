package Modules.Enums;

import java.util.ArrayList;

public enum Weather {
    ;

    private ArrayList<Season> seasons;

    Weather(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public boolean containsSeason(Season season) {
        return seasons.contains(season);
    }
}
