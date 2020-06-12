package assignment2;

public class Guess {
	private String theirguess;
	private int blackpegs=0;
	private int whitepegs=0;
	public int get_blackpegs() {return(blackpegs);}
	public int get_whitepegs() {return(whitepegs);}
	public String get_their_guess() {return(theirguess);}
	
	
	//Checks to see if a users input is valid
	//static so I can check if it is valid without creating an instance 
	public static boolean isValid(String input) {
		if(input.length()!=GameConfiguration.pegNumber) {return false;}
		 for (String c1 : input.split("")) {
			 boolean found=false;
			 for(String c2:  GameConfiguration.colors) {
				 if(c2.substring(0,1).equals(c1)) { found=true;}
			 }
			 if(! found) { return false; }
		 }
		return true;
	}
	public String toString() {
		String result=theirguess;
		result+="-> Result:\t";
		if(blackpegs==1) {
			result+=" "+blackpegs+" black peg";
		}else if(blackpegs>1){
			result+=" "+blackpegs+" black pegs";			
		}
		if(whitepegs==1) {
			result+=" "+whitepegs+" white peg";
		}else if(whitepegs>1){
			result+=" "+whitepegs+" white pegs";			
		}	
		if(whitepegs==0 && blackpegs==0) {
			result+=" No Pegs";
		}
		return(result);
	}
	
	//Guess takes a user input string and a secret to compare it to and store results
	public Guess(String input,String secret) {
		theirguess=input;
		char s[]=secret.toCharArray();
		char i[]=input.toCharArray();
		//Pass one for each input put a black peg if in right location
		//Then delete it and what it matched so we don't also put a white peg
		for(int loop=0;loop<i.length;loop++) {
			if(i[loop]==s[loop]) {
				blackpegs++;
				i[loop]=' ';
				s[loop]=' ';
			}
		}			
		//Pass two white pegs
		//Then delete it and what it matched so we don't also put a second white peg
		for(int loop=0;loop<i.length;loop++) {
			for(int loop2=0;loop2<s.length;loop2++) {
				//If the input is not a space(already matched) and it matches anything
				if((i[loop]!=' ') && (i[loop]==s[loop2])) {
					whitepegs++;
					i[loop]=' ';
					s[loop2]=' ';
				}
			}
		}			

	}
}