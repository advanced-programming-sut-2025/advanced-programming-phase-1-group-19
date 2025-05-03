package Modules.Farming;

import Modules.Enums.Season;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public enum MixedSeed {
    spring(Season.spring),
    summer(Season.summer),
    fall(Season.fall),
    winter(Season.winter)
    ;

    private final ArrayList<SeedType> seeds;
    Season season;

    MixedSeed(Season season) {
        seeds = new ArrayList<>();
        this.season = season;
        switch (this.name()) {
            case "spring": {
                seeds.add(SeedType.cauliflower);
                seeds.add(SeedType.parsnip);
                seeds.add(SeedType.potato);
                seeds.add(SeedType.jazz);
                seeds.add(SeedType.tulipBulb);
                break;
            }
            case "summer": {
                seeds.add(SeedType.corn);
                seeds.add(SeedType.pepper);
                seeds.add(SeedType.radish);
                seeds.add(SeedType.wheat);
                seeds.add(SeedType.poppy);
                seeds.add(SeedType.sunflower);
                seeds.add(SeedType.spangle);
                break;
            }
            case "fall": {
                seeds.add(SeedType.artichoke);
                seeds.add(SeedType.corn);
                seeds.add(SeedType.eggplant);
                seeds.add(SeedType.pumpkin);
                seeds.add(SeedType.sunflower);
                seeds.add(SeedType.fairy);
                break;
            }
            case "winter": {
                seeds.add(SeedType.powdermelon);
                break;
            }

        }
    }

    public ArrayList<SeedType> getSeeds() {
        return seeds;
    }

    public Season getSeason() {
        return season;
    }

    public SeedType getRandomSeed() {
        int n = seeds.size();
        int rand = ThreadLocalRandom.current().nextInt(0, n);
        return seeds.get(rand);
    }

    public static MixedSeed getMixedSeedBySeason(Season season) {
        for(MixedSeed seed : MixedSeed.values()) {
            if(seed.getSeason().equals(season)) {
                return seed;
            }
        }
        return null;
    }
}
