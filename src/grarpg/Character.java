
package grarpg;


public class Character {
    
        private String name;
        private String heroclass;
        private double HP;
        private double DMG;
        private int lvl;
        private int exp;
        private String Armor;
        private String Weapon;
        private int  money;
    
     public Character(String name, String nazwisko, String heroclass, double HP, double DMG, int lvl, int exp, String Armor, String Weapon, int money) {
        this.name = name;
        this.heroclass = heroclass;
        this.HP = HP;
        this.DMG = DMG;
        this.lvl = lvl;
        this.exp = exp;
        this.Armor = Armor;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Character() {
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getHeroclass() {
        return heroclass;
    }

    public void setHeroclass(String heroclass) {
        this.heroclass = heroclass;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public double getDMG() {
        return DMG;
    }

    public void setDMG(double DMG) {
        this.DMG = DMG;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getArmor() {
        return Armor;
    }

    public void setArmor(String Armor) {
        this.Armor = Armor;
    }

    public String getWeapon() {
        return Weapon;
    }

    public void setWeapon(String Weapon) {
        this.Weapon = Weapon;
    }
    
    
}
