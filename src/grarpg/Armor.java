
package grarpg;


public class Armor {
    
    private String armorName;
    private int armorBoost;
    private int price;

    public Armor(String armorName, int armorBoost, int price) {
        this.armorName = armorName;
        this.armorBoost = armorBoost;
        this.price = price;
    }

    public Armor() {
    }

    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }

    public int getArmorBoost() {
        return armorBoost;
    }

    public void setArmorBoost(int armorBoost) {
        this.armorBoost = armorBoost;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
    
}
