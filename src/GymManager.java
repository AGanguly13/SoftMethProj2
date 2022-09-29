/**
 * User Interface class that processes command line inputs from user
 * Accepts input as single command line or batch
 * Terminates only when "Q" is typed
 * @author Kennan Guan
 */
import java.util.Scanner;

public class GymManager {
    public void run(){
        System.out.println("Gym Manager running...");
        Scanner keyboard = new Scanner(System.in);
        String inputLine = keyboard.nextLine();
        String[] inputs = inputLine.split(" ");
        boolean terminate = false;

        do{
            for(int i = 0; i < inputs.length; i++){
                if(inputs[i].equals("Q")){
                    terminate = true;
                    break;
                }
            }
        }while(!terminate);

        System.out.println("Gym Manager Terminated");
    }
}
