
package grarpg;


public class Armor {
    
    private String armorName;
    private int armorBoost;
    private int armorprice;

    public Armor(String armorName, int armorBoost, int price) {
        this.armorName = armorName;
        this.armorBoost = armorBoost;
        this.armorprice = price;
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

    public int getArmorPrice() {
        return armorprice;
    }

    public void setArmorPrice(int price) {
        this.armorprice = armorprice;
    }
    
    
    
}
