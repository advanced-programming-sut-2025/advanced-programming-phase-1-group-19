package Modules.Store;

public class Store {
    private int openingTime;
    private int closingTime;
    private String ownerName;

    public Store(int openingTime, int closingTime, String ownerName) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.ownerName = ownerName;
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
