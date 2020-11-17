
package grarpg;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Game {
    
    Character heros = new Character();
    Character enemy = new Character();
    
    String[][] MapTab = new String[5][5];
    
    Weapon weapon = new Weapon();
    Armor armor = new Armor();
    
    List listArmor = new ArrayList();
    List listWeapon = new ArrayList();
    List list = new ArrayList();
    
    Random rand =  new Random();
    Scanner scan = new Scanner(System.in);
    
    
    
    int licznikprzeciwnika=0, lvlpostac=1, again, exitMap;
    double HPboostHeros=1, HPboostEnemy=1, DMGboostHeros=1, DMGboostEnemy=1;
    double HP1, HP2, DMG1, DMG2;
    int aim, zmienna, tura=0, exp=0, lvlmax=50, lvlshop=0, coins;
    int enemyAmount;
    int doorU, doorL, doorR, doorD, doorUD; //0,1,2,33
    String lineDoor="";
    int horizontal=0, vertical=0, U, L, R, D;
    
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

        if(klasa<1 && klasa>5)
        {
            System.out.println("Źle wybrana klasa postaci, zostanie ci ona wybrana losowo");
            klasa=rand.nextInt(5)+1;
        }
            
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
                heros.setHP(9999);
                heros.setDMG(9999);
                heros.setMoney(9999);

                heros.setWeapon("Boski kij");
                break;
            }
        }
        
        System.out.println("");
             
        heros.setLvl(1);
        heros.setExp(0);
        
        weapon.setWeaponBoost(0);  //zapobiega błędowi, dodaje wartość do broni i armora
        armor.setArmorBoost(0);
        
        heros.setArmor("lachmany");
    }  //zabezpieczono
    
    public void SaveCharacter() throws IOException
    {
        FileWriter FW = new FileWriter("Character.txt");
        
        String line = "";
        line+=heros.getName();
        line+="-";
        line+=heros.getHeroclass();
        line+="-";
        line+=heros.getHP();
        line+="-";
        line+=heros.getDMG();
        line+="-";
        line+=heros.getLvl();
        line+="-";
        line+=heros.getExp();
        line+="-";
        line+=heros.getArmor();
        line+="-";
        line+=heros.getWeapon();
        line+="-";
        line+=heros.getMoney();
        
        FW.write(line);
        FW.close();
    }
    
    public void UploadCharacter() throws IOException
    {
        RandomAccessFile RAFCharacter = new RandomAccessFile("Character.txt","r");

        String[] tab = null;
        String[] tabDouble = null;
        String line=null;
        int x, test2;
        
        line=RAFCharacter.readLine();
        
        
        if(line!=null)
        {
            tab = line.split("-");
            
            for(int i=0; i<9; i++)
            {
                System.out.println(i + " " + tab[i]); //test
            }
            
            heros.setName(tab[0]); 
            heros.setHeroclass(tab[1]);
            heros.setHP(Double.parseDouble(tab[2]));  // naprawić string to double
            heros.setDMG(Double.parseDouble(tab[3]));
            heros.setLvl(Integer.parseInt(tab[4]));
            heros.setExp(Integer.parseInt(tab[5]));
            heros.setArmor(tab[6]); 
            heros.setWeapon(tab[7]); 
            heros.setMoney(Integer.parseInt(tab[8]));
        }
        else if(line==null)
        {
            System.out.println("Nie ma zapisu postaci");
            CreateCharacter();
        }
        
        RAFCharacter.close();
    }
    
    public void RL() //metoda sprawdzająca czy drzwi na siebie nachodzą z prawej i lewej
    {
        switch(doorR) //jeśli w poprzednim pokoju drzwi z prawej to tu z lewej
        {
            case 0:
            {
                lineDoor+="0 ";
                break;
            }
            case 1:
            {
                lineDoor+="1 ";
                break;
            }
        }
    }
    
    public void UD(int wfor, int efor) //metoda sprawdzająca czy drzwi na siebie nachodzą na dole i na górze 
    {
        String[] tab = null;
        
        String line=MapTab[wfor-1][efor];
        
        tab = line.split(" ");
        
        doorUD = Integer.parseInt(tab[3]);
        
        switch(doorUD) //jeśli w poprzednim pokoju drzwi z dołu to tu u góry
        {
            case 0:
            {
                lineDoor+="0 ";
                break;
            }
            case 1:
            {
                lineDoor+="1 ";
                break;
            }
        }
        
    }
    
    public void RandR() //randomowe drzwi z prawej
    {
        doorR =  rand.nextInt(2);
                    switch(doorR)
                    {
                        case 0:
                        {
                            lineDoor+="0 ";
                            break;
                        }
                        case 1:
                        {
                            lineDoor+="1 ";
                            break;
                        }
                    }
    }
    
    public void RandD() //randomowe drzwi z dołu
    {
        doorD =  rand.nextInt(2);
                    switch(doorD)
                    {
                        case 0:
                        {
                            lineDoor+="1 ";
                            break;
                        }
                        case 1:
                        {
                            lineDoor+="0 ";
                            break;
                        }
                    }
    }
    
    public void Map() throws IOException 
    {
 
        RandomAccessFile RAF = new RandomAccessFile("MapRoom.txt","r");
        long DL=RAF.length();
        String line;
        
        
        for(int j=0; j<DL; j++)
        {
            line=RAF.readLine();
            list.add(line);
        }
              
        // doorU, doorL, doorR, doorD; //0,1,2,3
        
        for(int wfor=0; wfor<5; wfor++)
        {
            for(int efor=0; efor<5; efor++)
            {
                if(wfor==0 && efor==0) //tab[0][0]
                {
                    lineDoor+="0 ";
                    
                    lineDoor+="0 ";
                    
                    RandR();
                    
                    RandD();
                   
                }              
                else if(wfor==0 && efor!=0 && efor!=4) //tab[0][1-3]
                {
                    lineDoor+="0 ";
                    
                    RL();
                    
                    RandR();
                    
                    RandD();
                    
                }               
                else if(wfor==0 && efor==4)
                {
                    lineDoor+="0 ";
                    
                    RL();
                       
                    lineDoor+="0 ";                  
                    
                    RandD();
                }
                else if(wfor!=0 && wfor!=4 && efor==0)
                {
                    UD(wfor, efor);
                    
                    lineDoor+="0 ";  
                    
                    RandR();
                    
                    RandD();
                    
                }
                else if(wfor!=0 && wfor!=4 && efor!=0 && efor!=4)
                {
                    UD(wfor, efor);
                    
                    RL();
                    
                    RandR();
                    
                    RandD();
                }
                else if(wfor!=0 && wfor!=4 && efor==4)
                {
                    UD(wfor, efor);
                    
                    RL();
                    
                    lineDoor+="0 ";
                    
                    RandD();
                    
                }
                else if(wfor==4 && efor==0)
                {
                    UD(wfor, efor);
                    
                    lineDoor+="0 ";
                    
                    RandR();
                    
                    lineDoor+="0 ";
                    
                }
                else if(wfor==4 && efor!=0 && efor!=4)
                {
                    UD(wfor, efor);
                    
                    RL();
                    
                    RandR();
                    
                    lineDoor+="0 ";
                    
                }
                else if(wfor==4 && efor==4)
                {
                    UD(wfor, efor);
                    
                    RL();
                    
                    lineDoor+="0 ";
                    
                    lineDoor+="0 ";
                    
                }
                                  
                MapTab[wfor][efor]=lineDoor;
                lineDoor="";
            }
            
            
            
            
        }       
        
    }
    
    public void MapRoom()
    {
        
        String[] tab = null;
        
        String line=MapTab[vertical][horizontal];
        
        tab = line.split(" ");
        
        U=Integer.parseInt(tab[0]);
        L=Integer.parseInt(tab[1]);
        R=Integer.parseInt(tab[2]);
        D=Integer.parseInt(tab[3]);
        
        /*int U,L,R,D;  //test      
        U=0;
        L=1;
        R=1;
        D=0;*/
        
        if(U==1 && L==1 && R==1 && D==1)  // 0
        {
            for(int q=0; q<5; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==0 && L==1 && R==1 && D==1) // 1
        {
            for(int q=5; q<10; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==0 && L==1 && R==0 && D==1) // 2
        {
            for(int q=10; q<15; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==0 && L==1 && R==0 && D==0) // 3
        { 
            for(int q=15; q<20; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==1 && L==1 && R==0 && D==1) // 4
        {
            for(int q=20; q<25; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==1 && L==1 && R==0 && D==0) // 5
        {
            for(int q=25; q<30; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==1 && L==0 && R==0 && D==0) // 6
        {
            for(int q=30; q<35; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==1 && L==1 && R==1 && D==0) // 7
        {
            for(int q=35; q<40; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==1 && L==0 && R==1 && D==1) // 8
        {
            for(int q=40; q<45; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==1 && L==0 && R==1 && D==0) // 9
        {
            for(int q=45; q<50; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==0 && L==0 && R==1 && D==0) // 10
        {
            for(int q=50; q<55; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==0 && L==0 && R==1 && D==1) // 11
        {
            for(int q=55; q<60; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==0 && L==0 && R==0 && D==1) // 12
        {
            for(int q=60; q<65; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==1 && L==0 && R==0 && D==1) // 13
        {
            for(int q=65; q<70; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==0 && L==1 && R==1 && D==0) // 14
        {
            for(int q=70; q<75; q++)
            {
                System.out.println(list.get(q));
            }
        }
        else if(U==0 && L==0 && R==0 && D==0) // 15
        {
            for(int q=75; q<80; q++)
            {
                System.out.println(list.get(q));
            }
            
            System.out.println("-Mała ta skrytka");
        }
                
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
        System.out.println("Money: " + heros.getMoney());
        System.out.println("");
    }
    
    public void LvlUp(){
        
        int x = rand.nextInt(2);
        double HPzmienna, DMGzmienna;
        
        switch(x)
        {
            case 0:  //randomowy boost przeciwników po lvlup
            {
                 HPboostEnemy+=0.2;
                 break;
            }
            case 1:
            {
                 DMGboostEnemy+=0.2;
                 break;
            }
    }
        System.out.println("LVL UP");
        System.out.println("Chcesz ulepzyć HP czy DMG?");
        System.out.println("1-HP    2-DMG");
        int zmienna = scan.nextInt();
        
        if(zmienna!=1 || zmienna!=2)
        {
            System.out.println("Żle wybrany boost, zostanie on wybrany losowo");
            zmienna=rand.nextInt(2)+1;
        }
        
        switch(zmienna)
        {
            case 1: //wybór boosta przez gracza dla postaci 
            {
                HPboostHeros+=0.2;
                HPzmienna=heros.getHP();
                HPzmienna*=HPboostHeros;
                heros.setHP(HPzmienna);
                break;
            }

            case 2:
            {
                DMGboostHeros+=0.2;
                DMGzmienna=heros.getDMG();
                DMGzmienna*=DMGboostHeros;
                heros.setDMG(DMGzmienna);
                break;
            }
        }
        lvlmax*=2;  //zwiększa porzebny exp do lvlupa      
        lvlpostac++;
        
    }  //zabezpieczono
    
    public void Atak() //zabezpieczono
    {
        aim=rand.nextInt(100)+1;
        
        switch(tura)  // zmiana ataku gracza i przeciwnika
        {
            case 0:
            {
                System.out.println("1-Atak precyzyjny  |Szansa trafienia 80%   DMG 80% |");
                System.out.println("2-Atak normalny    |Szansa trafienia 65%   DMG 100%|");
                System.out.println("3-Atak ciężki      |Szansa trafienia 50%   DMG 120%|");
                
                do
                {
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
                        case 997:
                        {
                            
                            System.out.print(HP2 + " - " + "(" + DMG1 + " + " + weapon.getWeaponBoost() + "}" + "*1.2 = ");
                            HP2=HP2-((DMG1 + weapon.getWeaponBoost())*1.5);
                            System.out.println(HP2 + " : HP Przeciwnika");
                            System.out.println("");
                            
                        }
                    }
                    
                    if(zmienna<1 || zmienna>4)
                    {
                        System.out.println("Niepoprawnie wybrano atak, zrób to jeszcze raz");
                        System.out.println("");
                    }
                    else if(zmienna>1 || zmienna<4)
                    {
                        break;
                    }
                    
                }while(true);
                
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
    
    public void ChapterOne() throws IOException  //zabezpieczone
    {
        RandomAccessFile RAF = new RandomAccessFile("ChapterOne.txt","r");
        long DL=RAF.length();
        String line;
        
        System.out.println("Witaj " + heros.getName());
        for(int i=0; i<8; i++)
        {
            line=RAF.readLine();
            System.out.println(line);           
        }
        
        System.out.println("");
        
        do
        {
            System.out.println("");
            
            
            GamePlay();
            MapRoom();
            
            System.out.println("Gdzie chcesz iść?");
            
            if(U==1)
            {
                System.out.println("1-Idź w górę");
            }
            if(L==1)
            {
                System.out.println("2-Idź w lewo");
            }
            if(R==1)
            {
                System.out.println("3-Idź w prawo");
            }
            if(D==1)
            {
                System.out.println("4-Idź w dół");
            }
            System.out.println("5-Uciekaj do miasteczka");
            
            do
            {
                int way=scan.nextInt();
                
                if(way==1 && U==1)
                {
                    vertical--;
                    break;
                }
                else if(way==1 && U==0)
                {
                    System.out.println("Tam nie ma przejścia, wybierz inną drogę");
                }
                else if(way==2 && L==1)
                {
                    horizontal--;
                    break;
                }
                else if(way==2 && L==0)
                {
                    System.out.println("Tam nie ma przejścia, wybierz inną drogę");
                }
                else if(way==3 && R==1)
                {
                    horizontal++;
                    break;
                }
                else if(way==3 && R==0)
                {
                    System.out.println("Tam nie ma przejścia, wybierz inną drogę");
                }
                else if(way==4 && D==1)
                {
                    vertical++;
                    break;
                }
                else if(way==4 && D==0)
                {
                    System.out.println("Tam nie ma przejścia, wybierz inną drogę");
                }
                else if(way==5)
                {
                    exitMap=1;
                    break;
                }
                
            }while(true);
            
            if(exitMap==1)
            {
                break;
            }
                   
        }while(true);
        
    }
      
    public void GamePlay()
    {
        int AmountEnemies = rand.nextInt(101);
        int x=0, coinsH;
        
        if(AmountEnemies>=0 && AmountEnemies<15)
        {
            x=1;
        }
        else if(AmountEnemies>=15 && AmountEnemies<40)
        {
            x=2;
        }
        else if(AmountEnemies>=40 && AmountEnemies<60)
        {
            x=3;
        }
        else if(AmountEnemies>=60 && AmountEnemies<75)
        {
            x=4;
        }
        else if(AmountEnemies>=75 && AmountEnemies<95)
        {
            x=5;
        }
        else if(AmountEnemies>=95 && AmountEnemies<100)
        {
            x=6;
        }
        
        for(int i=0; i<x; i++)
        {
            System.out.println("Liczba walk na arenie " + i + "/" + x);
            
            System.out.println("---------------------------");
            CreateEnemy();
            System.out.println("---------------------------");
            Hero();
            System.out.println("---------------------------");
            coins=200;
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
                
            }
            else
            {
                
                exp+=10;
                heros.setExp(exp);
                coins+=10;
                coinsH=heros.getMoney();
                coinsH+=coins;
                heros.setMoney(coinsH);

                if(exp==lvlmax) //przyznaje lvlup
                {
                    LvlUp();
                }

            }
            
            HP1=heros.getHP(); //przypisanienowego HP pieniędzy expa
            
        }     
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
        
        RAF1.close();
        RAF2.close();
    }
    
    public void Shop()  //zabezpieczone
    {
        System.out.println("WITAJ W SKLEPIE!");
        System.out.println("1-Sklep z Armorem  2-Sklep z Weapons");
       
        String line=null, line2=null, weaponName, armorName;
        int armorHP, weaponDMG, armorPrice, weaponPrice;      
        int meter=1, y, exit=0;
        
        String[] tab = null;
          
        do
        {
            int x=scan.nextInt();
            
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
                    
                    do
                    {
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
                        
                        if(y!=1 || y!=2)
                        {
                            System.out.println("Niepoprawnie wybrano przedmiot, spróbuj jeszcze raz");
                            System.out.println("");
                        }
                        else if(y==1 || y==2)
                        {
                            break;
                        }
                        
                    }while(true);
                    
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
                    else if(heros.getMoney()<=armorPrice)
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
                    
                    do
                    {
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
                        if(y!=1 || y!=2)
                        {
                            System.out.println("Niepoprawnie wybrano przedmiot, spróbuj jeszcze raz");
                            System.out.println("");
                        }
                        else if(y==1 || y==2)
                        {
                            break;
                        }
                        
                    }while(true);
                    
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
            
            if(x!=1 || x!=2)
            {
                System.out.println("Niepoprawnie wybrano sklep, zrób to jeszcze raz");
                System.out.println("");
            }
            else if(x==1 || x==2)
            {
                break;
            }
            
        }while(true);
        
        meter=1;
        lvlshop+=3; //pomaga w wczytaniu kolejnych przedmiotów ze sklepu
    }
    
}
