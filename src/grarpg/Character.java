
package grarpg;


public class Character {
    
        private String name;
        private String heroclass;
        private int HP;
        private int DMG;
        private int lvl;
    
     public Character(String name, String nazwisko, String heroclass, int HP, int DMG, int lvl, String Broń) {
        this.name = name;
        this.heroclass = heroclass;
        this.HP = HP;
        this.DMG = DMG;
        this.lvl = lvl;
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

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getDMG() {
        return DMG;
    }

    public void setDMG(int DMG) {
        this.DMG = DMG;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    
}
