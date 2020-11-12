
package grarpg;

import java.io.IOException;


public class GraRPG {

    static Game R = new Game();
    
    public static void main(String[] args) throws IOException {
        
        R.ShopList();
        R.Map();
        do
        {
        
        R.CreateCharacter();
        R.ChapterOne();
        
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
