
package grarpg;

import java.util.Random;
import java.util.Scanner;


public class Game {
    
    Character heros = new Character();
    Character enemy = new Character();
    
    int licznikprzeciwnika=1;  
    int zmiennapomocnicza=1; 
    int lvlpostac=1;
    int HPboostHeros=1, HPboostEnemy=1, DMGboostHeros=1, DMGboostEnemy=1;
    
    public void CreateCharacter(){
        
        Scanner scan = new Scanner(System.in);
        
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
        
        if(klasa==1){
            heros.setHeroclass("Elf");
            heros.setHP(300);
            heros.setDMG(70);
        }
        else if(klasa==2){
            heros.setHeroclass("Ork");
            heros.setHP(700);
            heros.setDMG(30);
        }
        else if(klasa==3){
            heros.setHeroclass("Człowiek");
            heros.setHP(500);
            heros.setDMG(50);
        }
        else if(klasa==4){
            heros.setHeroclass("Mag");
            heros.setHP(200);
            heros.setDMG(90);
        }
        
         System.out.println("");
             
        heros.setLvl(1);
        
    }
    
    public void CreateEnemy(){
        
        Random rand = new Random();
        int klasa = rand.nextInt(4)+1;
        
        Scanner scan = new Scanner(System.in);

        enemy.setName("Przeciwnik: ");
        

        if(zmiennapomocnicza>5){
                 
            lvlpostac++;
            zmiennapomocnicza=1;
            LvlUp();
            
        }
            enemy.setLvl(lvlpostac);
            heros.setLvl(lvlpostac);
        
        if(klasa==1){
            enemy.setHeroclass("Elf");
            enemy.setHP(200*HPboostEnemy);
            enemy.setDMG(50*DMGboostEnemy);
        }
        else if(klasa==2){
            enemy.setHeroclass("Ork");
            enemy.setHP(500*HPboostEnemy);
            enemy.setDMG(10*DMGboostEnemy);
        }
        else if(klasa==3){
            enemy.setHeroclass("Człowiek");
            enemy.setHP(300*HPboostEnemy);
            enemy.setDMG(20*DMGboostEnemy);
        }
        else if(klasa==4){
            enemy.setHeroclass("Mag");
            enemy.setHP(100*HPboostEnemy);
            enemy.setDMG(70*DMGboostEnemy);
        }
        
        System.out.println("Twój przeciwnik to: ");
        System.out.println("Imie: " + enemy.getName() + licznikprzeciwnika);
        System.out.println("Klasa: " + enemy.getHeroclass());
        System.out.println("LVL: " + enemy.getLvl());
        System.out.println("HP: " + enemy.getHP());
        System.out.println("DMG: " + enemy.getDMG());
        System.out.println("");
    }
    
    public void Hero()
    {
        System.out.println("Twoja postać to: ");
        System.out.println("Imie: " + heros.getName());
        System.out.println("Klasa: " + heros.getHeroclass());
        System.out.println("LVL: " + heros.getLvl());
        System.out.println("HP: " + heros.getHP());
        System.out.println("DMG: " + heros.getDMG());
        System.out.println("");
    }
    
    public void LvlUp(){
        
        Scanner scan=new Scanner(System.in);
        
        Random lvl = new Random();
        int x = lvl.nextInt(1);
        int HPzmienna, DMGzmienna;
        
        if(x==0)
        {
             HPboostEnemy++;
        }
        else if(x==1)
        {
             DMGboostEnemy++;
        }
        
        System.out.println("LVL UP");
        System.out.println("Chcesz ulepzyć HP czy DMG?");
        System.out.println("1-HP    2-DMG");
        int zmienna = scan.nextInt();
        
        if(zmienna==1)
        {
            HPboostHeros++;
            HPzmienna=heros.getHP();
            HPzmienna=HPzmienna*HPboostHeros;
            heros.setHP(HPzmienna);
        }
        else if(zmienna==2)
        {
            DMGboostHeros++;
            DMGzmienna=heros.getDMG();
            DMGzmienna=DMGzmienna*DMGboostHeros;
            heros.setDMG(DMGzmienna);
        }
        
    }
    
    public void Gameplay()
    { 
        Scanner scan = new Scanner(System.in);

        int HP1;
        int HP2;
        int DMG1;
        int DMG2;
        
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
            
            /*System.out.println(HP1 + " HP1");  //Potem usunąć tylko do podglądu
            System.out.println(HP2 + " HP2");
            System.out.println(DMG1 + " DMG1");
            System.out.println(DMG2 + " DMG2");
            System.out.println("");*/
            
            do
                {
                    
                    System.out.println("Atakuje Gracz: " + HP2 + " - " + DMG1);
                    System.out.println(HP2=HP2-DMG1);
                    System.out.println("Atakuje Przeciwnik: " + HP1 + " - " + DMG2);
                    System.out.println(HP1=HP1-DMG2);
                    
                    System.out.println("");

                }while(HP2>0 && HP1>0);
            
            HP1=heros.getHP();
            
            licznikprzeciwnika++;
            zmiennapomocnicza++;
            
            System.out.println("Poddajesz się?");
            System.out.println("1-TAK");
            
            int zmienna = scan.nextInt();
            
            if(zmienna==1)
            {
                break;
            }
            
            
        }while(true);
    }
    
    
    
}
