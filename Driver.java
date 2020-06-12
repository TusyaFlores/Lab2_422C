/* EE422C Assignment #2 submission by 
 * Tatiana Flores
 * TH27979
 */

package assignment2;
import java.util.Scanner; 
import java.util.Arrays;
//Stuff to dynamically check for methods
import java.lang.reflect.Method; 
import java.lang.reflect.Field; 
import java.lang.reflect.Constructor; 

//Required by assignment. Driver contain the main method

public class Driver {
	//Takes an input 1 for testing
	public static void main(String[] args) {
		boolean testing=false;
		//Init a single scanner
		Scanner sc = new Scanner(System.in);
		if(args.length>=1 && args[0].equals("1")) {
			testing=true;
			System.out.println("running in Test mode");
		}
		Game.print_game_message();
	
		
				//The driver just asks them if they are ready to pla and based on that starts a game or exits
				while(true) {
					System.out.println("Are you ready to play? (Y/N):");
					String c=sc.nextLine();
					if(c.equals("Y")) {
						Game mygame=new Game(sc,testing);
						mygame.runGame();		
					}else if(c.equals("N")) {
						//They said N, just exit the program
						return;
					}
				}
	 }

}
