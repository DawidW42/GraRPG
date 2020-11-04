
package grarpg;




public class Weapon {
    
    private String weaponName;
    private int weaponBoost;
    private int price;

    public Weapon(String weaponName, int weaponBoost, int price) {
        this.weaponName = weaponName;
        this.weaponBoost = weaponBoost;
        this.price = price;
    }
    
    public Weapon() {
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getWeaponBoost() {
        return weaponBoost;
    }

    public void setWeaponBoost(int weaponBoost) {
        this.weaponBoost = weaponBoost;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
}
