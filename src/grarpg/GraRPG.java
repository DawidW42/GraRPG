
package grarpg;


public class GraRPG {

    static Game R = new Game();
    
    public static void main(String[] args) {
        
        
        do
        {
            
        R.CreateCharacter();
        R.Gameplay();
        
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
