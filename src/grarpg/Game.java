
package grarpg;

import java.util.Scanner;


public class Game {
    
    Character heros = new Character();
    Character enemy = new Character();
    
    int licznikprzeciwnika=1;  //zwiększyć o 1 co przeciwnika
    int zmiennapomocnicza=1;     //zwiększać razem z licznikiem przeciwnika
    
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
        
        
        heros.setLvl(1);
        
        System.out.println("Twoja postać to: ");
        System.out.println("Imie: " + heros.getName());
        System.out.println("Klasa: " + heros.getHeroclass());
        System.out.println("LVL: " + heros.getLvl());
        System.out.println("HP: " + heros.getHP());
        System.out.println("DMG: " + heros.getDMG());
    }
    
    public void CreateEnemy(){
        
        Scanner scan = new Scanner(System.in);

        enemy.setName("Przeciwnik: ");
        
        int lvlenemy=0;
        
        if(licznikprzeciwnika<=5){
                 
            lvlenemy++;
            zmiennapomocnicza=1;
            
        }
            enemy.setLvl(lvlenemy);
        
        System.out.println("Twój przeciwnik to: ");
        System.out.println("Imie: " + enemy.getName() + licznikprzeciwnika);
        System.out.println("Klasa: " + enemy.getHeroclass());
        System.out.println("LVL: " + enemy.getLvl());
        System.out.println("HP: " + enemy.getHP());
        System.out.println("DMG: " + enemy.getDMG());
    }
    
}
