/* EE422C Assignment #2 submission by 
 * Tatiana Flores
 * TH27979
 */
package assignment2;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Arrays;
//Stuff to dynamically check for methods
import java.lang.reflect.Method; 
import java.lang.reflect.Field; 
import java.lang.reflect.Constructor; 


public class Game {
	public boolean testing=false;
	public Scanner inputstream;
	public int round=0;
	public Guess[] history= new Guess[GameConfiguration.guessNumber];
	public String secret="";
	
	public Game(Scanner sc, boolean testing) {
		this.testing=testing;
		inputstream=sc;
	}
	
	public int runGame() {
		secret=SecretCodeGenerator.getInstance().getNewSecretCode();
		if(testing) {
			System.out.println("Generating secret code ...(for this example the secret code is "+secret+")");
		}
		for(int loop=0;loop<GameConfiguration.guessNumber;) {
			System.out.println("You have "+(GameConfiguration.guessNumber-loop)+" guesses left.\n\r"+
					"What is your next guess?\n\r"+
					"Type in the characters for your guess and press enter.\r\n" + 
					"Enter guess:");	
			//Get user input
			String userinput=inputstream.nextLine();
			//Convert to UC
			userinput=userinput.toUpperCase();
			//If it's valid then do something
			if(Guess.isValid(userinput)) {				
				Guess result=new Guess(userinput,secret);
				history[loop]=result;
				System.out.print(result);
				loop++;
				if(GameConfiguration.pegNumber==result.get_blackpegs()) {
					System.out.println("- You Win !!");
					return(1);//They Won
				}else if(loop==GameConfiguration.guessNumber) {
					//They Lost
					System.out.println("- Sorry, you are out of guesses. You lose, boo-hoo.");
					return(0);
				}else {
					//They didn't win or lose. Just print the EOL.
					System.out.println("");
				}
			//If it's HISTORY do something else
	}else if(userinput.equals("HISTORY")) {
				printHistory();
			//Invalid guesses just print INVALID GUESS
			}else {
					System.out.println("-> INVALID GUESS");
			}
		}
	return(0);
	}
	//Print the history
	public void printHistory() {
		for(int loop=0;loop<GameConfiguration.guessNumber;loop++) {
			Guess g=history[loop];
			//Null is ok means that we haven't had this guess yet we just ignore it then
			if(g!=null) {
				System.out.println(g.get_their_guess()+"\t\t"+g.get_blackpegs()+"B_"+g.get_whitepegs()+"W");
			}
		}	
	};
	
	public static void print_game_message() {
		
		//This is some serious fun logic solely for the purpose of making it more modular. 
		// We've added a variable colorNames to GameCofiguration, but maybe the TA will replace this with their own class and it'll break. We don't want that.
		//So basically if GameConfiguration has colornames variable then use that
		//Otherwise use the colors(letters) as the colorNames.
		String[] colorNames;
		try {
			//Get the field colorNames or throw an exception
			Field f=new GameConfiguration().getClass().getDeclaredField("colorNames");
			//set colorNames to the colorNames static variable in GameConfiguration
			colorNames=(String [])f.get(null);
		}catch (NoSuchFieldException | IllegalAccessException e) {
			//This version of GameConfiguration doesn't have the colorNames field. Instead just use the colors (letter array)
			colorNames=GameConfiguration.colors;
		}
		
		System.out.println("Welcome to Mastermind. Here are the rules. This is a text version of the classic board game Mastermind.\r\n" + 
				"The computer will think of a secret code. The code consists of "+GameConfiguration.pegNumber+"\r\n" + 
				"colored pegs. The pegs MUST be one of "+toNumber(GameConfiguration.colors.length)+" colors: \r\n"
				//Ok this basically takes the array of colors and prints it out. It assumes there is at least two colors
				//It prints out the colors but the first joined by a , and then it prints an or and the last color to make it proper English(Russian is much better).
						+ String.join(",", Arrays.copyOfRange(colorNames,1,colorNames.length))+", or "+colorNames[0]+".\r\n"+
				 "A color may appear more than once in\r\n" + 
				"the code. You try to guess what colored pegs are in the code and\r\n" + 
				"what order they are in. After you make a valid guess the result\r\n" + 
				"(feedback) will be displayed.\r\n" + 
				"The result consists of a black peg for each peg you have guessed\r\n" + 
				"exactly correct (color and position) in your guess. For each peg in\r\n" + 
				"the guess that is the correct color, but is out of position, you get\r\n" + 
				"a white peg. For each peg, which is fully incorrect, you get no\r\n" + 
				"feedback.\r\n" + 
				"Only the first letter of the color is displayed. B for Blue, R for\r\n" + 
				"Red, and so forth. When entering guesses you only need to enter the\r\n" + 
				"first character of each color as a capital letter.\r\n" + 
				"You have "+GameConfiguration.guessNumber+" guesses to figure out the secret code or you lose the\r\n" + 
				"game.");
	}
	
	//List of the names of numbers
	private static String[] units=
	    {"Zero",
	     " One",
	     " Two",
	     " Three",
	     " Four",
	     " Five",
	     " Six",
	     " Seven",
	     " Eight",
	     " Nine"
	    };
	
	/*
	 * Converts a number (IE 5) to a word (IE "five").
	 */
	public static String toNumber(int i) {
		//If it's bigger then our array
		if(i>=units.length) {
			return Integer.toString(i);
		}else {
			return units[i];
		}
	}
	
};


