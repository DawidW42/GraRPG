
package grarpg;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Game {
    
    Character heros = new Character();
    Character enemy = new Character();
    
    List listArmor = new ArrayList();
    List listWeapon = new ArrayList();
    
    Random rand =  new Random();
    Scanner scan = new Scanner(System.in);
    
    int licznikprzeciwnika=0, lvlpostac=1, again;
    double HPboostHeros=1, HPboostEnemy=1, DMGboostHeros=1, DMGboostEnemy=1;
    double HP1, HP2, DMG1, DMG2;
    int aim, zmienna, tura, exp=0, lvlmax=50, lvlshop=0;
    
    public void CreateCharacter() {
        
        System.out.println("Wybierz imię: ");
        String name=scan.nextLine();

        heros.setName(name);
        
        System.out.println("");
        System.out.println("Wybierz klasę postaci");
        System.out.println("1-Elf");
        System.out.println("2-Ork");
        System.out.println("3-Człowiek");
        System.out.println("4-Mag");
        
        int klasa=scan.nextInt();

        switch(klasa)
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

                heros.setWeapon("Boski kij");
                break;
            }
        }
        
        System.out.println("");
             
        heros.setLvl(1);
        heros.setExp(0);
        
        heros.setArmor("Łachmany");
    }
    
    public void CreateEnemy(){
        
        int klasa = rand.nextInt(4)+1;

        enemy.setName("Przeciwnik: ");
        
        enemy.setLvl(lvlpostac);
        
        switch(klasa)
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
        System.out.println("HP: " + heros.getHP());
        System.out.println("Broń: " + heros.getWeapon());
        System.out.println("DMG: " + heros.getDMG());
        System.out.println("");
    }
    
    public void LvlUp(){
        
        int x = rand.nextInt(1);
        double HPzmienna, DMGzmienna;
        
        if(x==0)
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
        
        if(zmienna==1)
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
        
        lvlmax*=2;        
        lvlpostac++;
        
    }
    
    public void Atak()
    {
        aim=rand.nextInt(100)+1;
        
        switch(tura)
        {
            case 0:
            {
                System.out.println("1-Atak precyzyjny  |Szansa trafienia 80%   DMG 80% |");
                System.out.println("2-Atak normalny    |Szansa trafienia 65%   DMG 100%|");
                System.out.println("3-Atak ciężki      |Szansa trafienia 50%   DMG 120%|");
                zmienna = scan.nextInt();

                switch(zmienna)
                {
                    case 1:
                    {
                        if(aim<=80)
                        {
                            System.out.print(HP2 + " - " + DMG1 + "*0.8 = ");
                            HP2=HP2-(DMG1*0.8);
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
                            System.out.print(HP2 + " - " + DMG1 + " = ");
                            HP2=HP2-(DMG1);
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
                            System.out.print(HP2 + " - " + DMG1 + "*1.2 = ");
                            HP2=HP2-(DMG1*1.2);
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
                            System.out.print(HP1 + " - " + DMG2 + "*0.8 = ");
                            HP1=HP1-(DMG2*0.8);
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
                            System.out.print(HP1 + " - " + DMG2 + " = ");
                            HP1=HP1-(DMG2);
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
                            System.out.print(HP1 + " - " + DMG2 + "*1.2 = ");
                            HP1=HP1-(DMG2*1.2);
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

            }while(HP2>0 && HP1>0);
                      
            
            if(HP2>0 && HP1<=0)
            {
                System.out.println("PRZEGRAŁEŚ");
                System.out.println("");
                System.out.println("Chcesz jeszcze raz? Wciśnij 1");
                again=scan.nextInt();
                
                break;
            }
                        
            HP1=heros.getHP();
            exp+=10;
            heros.setExp(exp);
            
            if(exp==lvlmax)
            {
                LvlUp();
                lvlmax*=2;
            }

            System.out.println("Poddajesz się?");
            System.out.println("1-TAK");
            
            int zmienna = scan.nextInt();
            
            
            
            if(zmienna==1)
            {
                break;
            }
            
            
        }while(true);
    }
    
    public void ShopList() throws IOException
    {
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
            lineWeapon=RAF1.readLine();
            listWeapon.add(lineWeapon);
        }
    }
    
    public void Shop()
    {
        System.out.println("WITAJ W SKLEPIE!");
        System.out.println("1-Sklep z Armorem  2-Sklep z Weapons");
        int x=scan.nextInt();
        String line;
        
                
        switch(x)
        {
            case 1:
            {
                for(int i=lvlshop; i<(lvlshop+3); i++)
                {
                    line = (String) listArmor.get(i);
                    System.out.println(line);
                }
                
                break;
            }
            case 2:
            {
                for(int j=lvlshop; j<(lvlshop+3); j++)
                {
                    line = (String) listWeapon.get(j);
                    System.out.println(line);
                }
                
                break;
            }
        }
        
        lvlshop+=3;
    }
    
}
