
package grarpg;

import java.io.IOException;
import java.util.Scanner;


public class GraRPG {

    
    
    public static void main(String[] args) throws IOException {
        
        Game R = new Game();
        Scanner scanMain=new Scanner(System.in);
        
        
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
                    int x=scanMain.nextInt();
                    
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
            System.out.println("Koniec gry");
        }
        else
        {
            break;
        }
        
        }while(true);
        
    }
    
}
