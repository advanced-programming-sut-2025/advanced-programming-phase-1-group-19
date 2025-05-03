package Modules.Animal;

public class Quality {
    private double amount;

    public Quality(double amount) {
        this.amount = amount;
    }

    public String getQualityName() {
        if(amount > 0 && amount <= 0.5){
            return "Normal";
        }
        else if(amount > 0.5 && amount <= 0.7){
            return "Silver";
        }
        else if(amount > 0.7 && amount <= 0.9){
            return "Gold";
        }
        else if(amount > 0.9 ){
            return "Iridium";
        }
    }

    public double getPriceRatio() {
        if(amount > 0 && amount <= 0.5){
            return 1;
        }
        else if(amount > 0.5 && amount <= 0.7){
            return 1.25;
        }
        else if(amount > 0.7 && amount <= 0.9){
            return 1.5;
        }
        else if(amount > 0.9 ){
            return 2;
        }
    }
}
