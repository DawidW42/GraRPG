
package grarpg;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class GraRPG {

    
    
    public static void main(String[] args) throws IOException {
        
        Game R = new Game();
        Scanner scanMain=new Scanner(System.in);
        
        int x=0;
                
        R.ShopList();
        
        do
        {
            System.out.println("WERSJA DEMO");
            
            
            
            R.Map();
        
            do
            {
                System.out.println("1-Wczytaj postać      2-Stwórz nową postać");
                do
                {
                    try
                    {
                        x=scanMain.nextInt();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Błąd");
                        scanMain.nextLine();
                    }
                            
                    switch(x)
                    {
                        case 1:
                        {
                            R.UploadCharacter();
                            break;
                        }
                        case 2:
                        {
                            R.CreateCharacter();
                            
                            break;
                        }
                    }
                    
                    if(x==1 || x==2)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Niepoprawy wybór opcji, spróbuj jeszcze raz");
                    }
                
                }while(true);
                
                R.ChapterOne();
                R.SaveCharacter(); 
                R.Shop();

                if(R.licznikprzeciwnika>=50)
                {
                    break;
                }

            }while(true);
                
        if(R.again==1)
        {
            System.out.println("Nowa gra");
        }
        else if(R.again==2)
        {
            System.out.println("Koniec gry");
            break;
        }
        
        }while(true);
        
    }
    
}
