package Modules;

public class Energy {
    public int amount = 200;
    public int maxAmount = 200;
    public boolean isFainted = false;
    public boolean isUnlimited = false;

    public void addEnergy(int amount) {

    }

    public void decreaseEnergy(int amount) {

    }

    public void increaseMaxAmount(int amount) {}

    public void decreaseMaxAmount(int amount) {}

    public int getAmount() {
        return amount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public boolean isFainted() {
        return isFainted;
    }

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        isUnlimited = unlimited;
    }
}
