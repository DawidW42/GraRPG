
package grarpg;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Game {
    
    Character heros = new Character();
    Character enemy = new Character();
    
    Weapon weapon = new Weapon();
    Armor armor = new Armor();
    
    List listArmor = new ArrayList();
    List listWeapon = new ArrayList();
    
    Random rand =  new Random();
    Scanner scan = new Scanner(System.in);
    
    int licznikprzeciwnika=0, lvlpostac=1, again;
    double HPboostHeros=1, HPboostEnemy=1, DMGboostHeros=1, DMGboostEnemy=1;
    double HP1, HP2, DMG1, DMG2;
    int aim, zmienna, tura=0, exp=0, lvlmax=50, lvlshop=0, coins=200;
    
    public void CreateCharacter() {
        
        System.out.println("Wybierz imię: ");
        String name=scan.next();

        heros.setName(name);
        
        System.out.println("");
        System.out.println("Wybierz klasę postaci");
        System.out.println("1-Elf");
        System.out.println("2-Ork");
        System.out.println("3-Człowiek");
        System.out.println("4-Mag");
        
        int klasa=scan.nextInt();

        switch(klasa)  //Wybór klasy postaci
        {
        
            case 1:
            {
                heros.setHeroclass("Elf");
                heros.setHP(300);
                heros.setDMG(70);

                heros.setWeapon("Proca");
                break;
            }
            
            case 2:
            {
                heros.setHeroclass("Ork");
                heros.setHP(700);
                heros.setDMG(30);

                heros.setWeapon("Duży Patyk");
                break;
            }
            case 3:
            {
                heros.setHeroclass("Człowiek");
                heros.setHP(500);
                heros.setDMG(50);

                heros.setWeapon("Dwa Patyki");
                break;
            }
            case 4:
            {
                heros.setHeroclass("Mag");
                heros.setHP(200);
                heros.setDMG(90);

                heros.setWeapon("Kupa na Kiju");
                break;
            }
            case 5:
            {
                heros.setHeroclass("GODMODE");
                heros.setHP(9999999);
                heros.setDMG(9999999);
                heros.setMoney(999999999);

                heros.setWeapon("Boski kij");
                break;
            }
        }
        
        System.out.println("");
             
        heros.setLvl(1);
        heros.setExp(0);
        
        weapon.setWeaponBoost(0);  //zapobiega błędowi, dodaje wartość do broni i armora
        armor.setArmorBoost(0);
        
        heros.setArmor("Łachmany");
    }
    
    public void CreateEnemy(){
        
        int klasa = rand.nextInt(4)+1;

        enemy.setName("Przeciwnik: ");
        
        enemy.setLvl(lvlpostac);
        
        switch(klasa)  //randomowy wybór przeciwnika
        {
        
            case 1:
            {
                enemy.setHeroclass("Elf");
                enemy.setHP(200*HPboostEnemy);
                enemy.setDMG(50*DMGboostEnemy);
                break;
            }
            case 2:{
                enemy.setHeroclass("Ork");
                enemy.setHP(600*HPboostEnemy);
                enemy.setDMG(10*DMGboostEnemy);
                break;
            }
            case 3:
            {
                enemy.setHeroclass("Człowiek");
                enemy.setHP(400*HPboostEnemy);
                enemy.setDMG(30*DMGboostEnemy);
                break;
            }
            case 4:
            {
                enemy.setHeroclass("Mag");
                enemy.setHP(100*HPboostEnemy);
                enemy.setDMG(70*DMGboostEnemy);
                break;
            }
        }
        System.out.println("Twój przeciwnik to: ");
        System.out.println("Imie: " + enemy.getName() + licznikprzeciwnika);
        System.out.println("Klasa: " + enemy.getHeroclass());
        System.out.println("LVL: " + enemy.getLvl());
        System.out.println("HP: " + enemy.getHP());
        System.out.println("DMG: " + enemy.getDMG());
        System.out.println("");
        
        licznikprzeciwnika++;
    }
    
    public void Hero()
    {
        System.out.println("Twoja postać to: ");
        System.out.println("Imie: " + heros.getName());
        System.out.println("Klasa: " + heros.getHeroclass());
        System.out.println("LVL: " + heros.getLvl());
        System.out.println("EXP: " + heros.getExp() + "/" + lvlmax);
        System.out.println("Zbroja: " + heros.getArmor());
        System.out.println("HP: " + heros.getHP() + " + " + armor.getArmorBoost());
        System.out.println("Broń: " + heros.getWeapon());
        System.out.println("DMG: " + heros.getDMG() + " + " + weapon.getWeaponBoost());
        System.out.println("");
    }
    
    public void LvlUp(){
        
        int x = rand.nextInt(1);
        double HPzmienna, DMGzmienna;
        
        if(x==0)  //randomowy boost przeciwników po lvlup
        {
             HPboostEnemy+=0.2;
        }
        else if(x==1)
        {
             DMGboostEnemy+=0.2;
        }
        
        System.out.println("LVL UP");
        System.out.println("Chcesz ulepzyć HP czy DMG?");
        System.out.println("1-HP    2-DMG");
        int zmienna = scan.nextInt();
        
        if(zmienna==1) //wybór boosta przez gracza dla postaci 
        {
            HPboostHeros+=0.2;
            HPzmienna=heros.getHP();
            HPzmienna*=HPboostHeros;
            heros.setHP(HPzmienna);
        }
        else if(zmienna==2)
        {
            DMGboostHeros+=0.2;
            DMGzmienna=heros.getDMG();
            DMGzmienna*=DMGboostHeros;
            heros.setDMG(DMGzmienna);
        }
        
        lvlmax*=2;  //zwiększa porzebny exp do lvlupa      
        lvlpostac++;
        
    }
    
    public void Atak()
    {
        aim=rand.nextInt(100)+1;
        
        switch(tura)  // zmiana ataku gracza i przeciwnika
        {
            case 0:
            {
                System.out.println("1-Atak precyzyjny  |Szansa trafienia 80%   DMG 80% |");
                System.out.println("2-Atak normalny    |Szansa trafienia 65%   DMG 100%|");
                System.out.println("3-Atak ciężki      |Szansa trafienia 50%   DMG 120%|");
                zmienna = scan.nextInt();

                switch(zmienna) // wybór ataku
                {
                    case 1:
                    {
                        if(aim<=80)
                        {
                            System.out.print(HP2 + " - " + "(" + DMG1 + " + " + weapon.getWeaponBoost() + "}" + "*0.8 = ");
                            HP2=HP2-((DMG1 + weapon.getWeaponBoost())*0.8);
                            System.out.println(HP2 + " : HP Przeciwnika");
                            System.out.println("");
                        }
                        else if(aim>80)
                        {
                            System.out.println("");
                            System.out.println("PUDŁO  " + HP2 + " : HP Przeciwnika");
                            System.out.println("");
                        }
                        break;
                    }
                    case 2:
                    {
                        if(aim<=65)
                        {
                            System.out.print(HP2 + " - " + "(" + DMG1 + " + " + weapon.getWeaponBoost() + "}" + " = ");
                            HP2=HP2-(DMG1 + weapon.getWeaponBoost());
                            System.out.println(HP2 + " : HP Przeciwnika");
                            System.out.println("");
                        }
                        else if(aim>65)
                        {
                            System.out.println("");
                            System.out.println("PUDŁO  " + HP2 + " : HP Przeciwnika");
                            System.out.println("");
                        }
                        break;
                    }
                    case 3:
                    {
                        if(aim<=50)
                        {
                            System.out.print(HP2 + " - " + "(" + DMG1 + " + " + weapon.getWeaponBoost() + "}" + "*1.2 = ");
                            HP2=HP2-((DMG1 + weapon.getWeaponBoost())*1.2);
                            System.out.println(HP2 + " : HP Przeciwnika");
                            System.out.println("");
                        }
                        else if(aim>50)
                        {
                            System.out.println("");
                            System.out.println("PUDŁO  " + HP2 + " : HP Przeciwnika");
                            System.out.println("");
                        }
                        break;
                    }
                }
                tura++;
                break;
            }
            case 1:
            {
                zmienna=rand.nextInt(2)+1;

                switch(zmienna)
                {
                    case 1:
                    {
                        if(aim<=80)
                        {
                            System.out.print("(" + HP1 + " + " + armor.getArmorBoost() + ")" + " - " + DMG2 + "*0.8 = ");
                            HP1=(HP1 + armor.getArmorBoost())-(DMG2*0.8);
                            System.out.println(HP1 + " : HP Gracza");
                            System.out.println("");
                        }
                        else if(aim>80)
                        {
                            System.out.println("");
                            System.out.println("PUDŁO  " + HP1 + " : HP Gracza");
                            System.out.println("");
                        }
                        break;
                    }
                    case 2:
                    {
                        if(aim<=65)
                        {
                            System.out.print("(" + HP1 + " + " + armor.getArmorBoost() + ")" + " - " + DMG2 + " = ");
                            HP1=(HP1 + armor.getArmorBoost())-(DMG2);
                            System.out.println(HP1 + " : HP Gracza");
                            System.out.println("");
                        }
                        else if(aim>65)
                        {
                            System.out.println("");
                            System.out.println("PUDŁO  " + HP1 + " : HP Gracza");
                            System.out.println("");
                        }
                        break;
                    }
                    case 3:
                    {
                        if(aim<=50)
                        {
                            System.out.print("(" + HP1 + " + " + armor.getArmorBoost() + ")" + " - " + DMG2 + "*1.2 = ");
                            HP1=(HP1 + armor.getArmorBoost())-(DMG2*1.2);
                            System.out.println(HP1 + " : HP Gracza");
                            System.out.println("");
                        }
                        else if(aim>50)
                        {
                            System.out.println("");
                            System.out.println("PUDŁO  " + HP1 + " : HP Gracza");
                            System.out.println("");
                        }
                        break;
                    }
                }
                tura--;
                break;
            }    
        }   
    }
    
    public void Gameplay()
    { 

        do
        {   
            
            System.out.println("---------------------------");
            CreateEnemy();
            System.out.println("---------------------------");
            Hero();
            System.out.println("---------------------------");
            
            HP1 = heros.getHP();
            HP2 = enemy.getHP();
            DMG1 = heros.getDMG();
            DMG2 = enemy.getDMG();
            
            System.out.println("WALKA: " + licznikprzeciwnika);
            
            do
            {
                Atak();
                
                if(tura==0)
                {
                    coins-=10; //zmiejszenie pieniędzy za każdy atak przeciwnika
                }

            }while(HP2>0 && HP1>0); // pętla zakończy się po porażce jednej z postaci
                      
            
            if(HP2>0 && HP1<=0)
            {
                System.out.println("PRZEGRAŁEŚ");
                System.out.println("");
                System.out.println("Chcesz jeszcze raz? Wciśnij 1");
                again=scan.nextInt();
                
                break;
            }
            else
            {
                HP1=heros.getHP(); //przypisanienowego HP pieniędzy expa
                exp+=10;
                heros.setExp(exp);
                coins+=10;
                heros.setMoney(coins);

                if(exp==lvlmax) //przyznaje lvlup
                {
                    LvlUp();
                }

                System.out.println("1-Poddaj się    2-SKLEP");

                int zmienna = scan.nextInt();

                if(zmienna==1)
                {
                    break;
                }
                else if(zmienna==2)
                {
                    Shop();
                }
            }
            
        }while(true);
    }
    
    public void ShopList() throws IOException
    {
        //tworzy dwie listy przedmiotów z plików tekstowych
        
        RandomAccessFile RAF1 = new RandomAccessFile("Armor.txt","r");
        RandomAccessFile RAF2 = new RandomAccessFile("Weapon.txt","r");
        
        long DL1=RAF1.length();
        long DL2=RAF2.length();
        String lineArmor, lineWeapon;
        
        for(int i=0; i<DL1; i++)
        {
            lineArmor=RAF1.readLine();
            listArmor.add(lineArmor);
        }
        
        for(int i=0; i<DL1; i++)
        {
            lineWeapon=RAF2.readLine();
            listWeapon.add(lineWeapon);
        }
    }
    
    public void Shop()
    {
        System.out.println("WITAJ W SKLEPIE!");
        System.out.println("1-Sklep z Armorem  2-Sklep z Weapons");
        int x=scan.nextInt();
        
        String line=null, line2=null, weaponName, armorName;
        int armorHP, weaponDMG, armorPrice, weaponPrice;      
        int meter=1, y, exit=0;
        
        String[] tab = null;
                
        switch(x) //wybór sklepu
        {
            case 1:
            {
                for(int i=lvlshop; i<(lvlshop+3); i++) //wypisanie przedmiotów do sklepu
                {
                    System.out.println("   Zbroja-HP-Cena");
                    line = (String) listArmor.get(i);
                    System.out.print(meter + ". ");
                    System.out.println(line);
                    meter++;
                }
                
                do
                {
                System.out.println("4-Wyjdź ze sklepu");
                                System.out.println("");
                System.out.println("Co kupujesz?");
                y=scan.nextInt();
                
                switch(y) //wybór przedmiotu ze sklepu
                {
                    case 1:
                    {
                        line2 = (String) listArmor.get(0);
                        tab = line2.split(" ");
                        break;
                    }
                    case 2:
                    {
                        line2 = (String) listArmor.get(1);
                        tab = line2.split(" ");
                        break;
                    }
                    case 3:
                    {
                        line2 = (String) listArmor.get(2);
                        tab = line2.split(" ");
                        break;
                    }
                    case 4:
                    {
                        line2 = "Test 0 0";   //zapobiega błedowi gdzie tab[] nic nie przypisuje
                        tab = line2.split(" ");
                        exit=1;
                    } 
                }
                                
                armorName=tab[0];  // przypisanie wartości z pliku do zmiennych
                armorHP=Integer.parseInt(tab[1]);
                armorPrice=Integer.parseInt(tab[2]);
                
                if(heros.getMoney()>=armorPrice) //jeśli warunek jest spełniony zapisuje cechy przedmiotu do obiektu 
                {
                armor.setArmorName(armorName);
                armor.setArmorBoost(armorHP);               
                armor.setArmorPrice(armorPrice);
                
                heros.setArmor(armorName);
                
                break;
                }
                else if(exit==1) //wyjście przy braku zakupu
                {
                    exit=0;
                    break;     
                }
                else
                {
                    System.out.println("Nie masz tyle pieniędzy wybierz coś innego"); //zapobiega kupna przedmiotu bez pieniędzy
                }
                
                }while(true);
                
                break;
            }
            case 2:  
            {
                for(int j=lvlshop; j<(lvlshop+3); j++) //wypisanie przedmiotów do sklepu
                {
                    System.out.println("   Broń-DMG-Cena");
                    System.out.print(meter + ". ");
                    line = (String) listWeapon.get(j);
                    System.out.println(line);
                    meter++; //licznik przedmiotów
                }
                System.out.println("");
                System.out.println("4-Wyjdź ze sklepu");
                System.out.println("Co kupujesz?");
                y=scan.nextInt();
                
                switch(y) 
                {
                    case 1:
                    {
                        line2 = (String) listWeapon.get(0);
                        tab = line2.split(" ");
                        break;
                    }
                    case 2:
                    {
                        line2 = (String) listWeapon.get(1);
                        tab = line2.split(" ");
                        break;
                    }
                    case 3:
                    {
                        line2 = (String) listWeapon.get(2);
                        tab = line2.split(" ");
                        break;
                    }
                    case 4: 
                    {
                        line2 = "Test 0 0";   //zapobiega błedowi gdzie tab[] nic nie przypisuje
                        tab = line2.split(" ");
                        exit=1;
                    } 
                }
                
                weaponName=tab[0]; // przypisanie wartości z pliku do zmiennych
                weaponDMG=Integer.parseInt(tab[1]);
                weaponPrice=Integer.parseInt(tab[2]);
                
                if(heros.getMoney()>=weaponPrice) //jeśli warunek jest spełniony zapisuje cechy przedmiotu do obiektu
                {
                weapon.setWeaponName(weaponName);
                weapon.setWeaponBoost(weaponPrice);
                weapon.setWeaponPrice(weaponPrice);
                
                heros.setWeapon(weaponName);
                }
                else if(exit==1) //wyjście przy braku zakupu
                {
                    exit=0;
                    break;
                }
                else
                {
                    System.out.println("Nie masz tyle pieniędzy wybierz coś innego");  //zapobiega kupna przedmiotu bez pieniędzy
                }
                
                break;
            }
        }
        
        meter=1;
        lvlshop+=3; //pomaga w wczytaniu kolejnych przedmiotów ze sklepu
    }
    
}
