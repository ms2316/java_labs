package uk.ac.cam.ms2316.OOPS4;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

class HangmanGame {
  
	//Fields
	private static String [] dictionary =   //choose secret word from these
	{"geography", "cat", "yesterday", "java", "truck", "opportunity",
		"fish", "token", "transportation", "bottom", "apple", "cake",
		"remote", "pocket", "terminology", "arm", "cranberry", "tool",
		"caterpillar", "spoon", "watermelon", "laptop", "toe", "toad",
		"fundamental", "capitol", "garbage", "anticipate", "apple"};
	//private String[] dictionary;
	private String word;
	private int movesWrong;
	private int movesAllowed;
	private ArrayList<Character> correctLetters;
	private ArrayList<Character> incorrectLetters;
	private Scanner scn = new Scanner(System.in); // for user input
  
	//Constructor
	public HangmanGame() {
		//dictionary = FileIO.getWords();
		movesAllowed = 6; //depends on drawing
	}
  
	/*
		Public methods
	*/
  
	public void playGame() {
		//initialization of the game
		initSecretWord();
      
		//game

		while ( !gameOver() ) {
			//print current Hangman
			printHangman();
		
			//Print correct letters guessed
			printLetters(correctLetters);
		
			//Print incorrect letters  guessed
			System.out.print("Wrong letters: ");
			printLetters(incorrectLetters);
			
			//Prompt and read the next guess
			System.out.print("Enter a letter: ");
			String guess = scn.nextLine();
				
			//Process the next guess
			handleGuess(guess.charAt(0));
		}
		
		if (gameWon()) {
			System.out.println("Congratulations, you won!");
		} else {
			System.out.println("Sorry, you lost.");
			printHangman();
		}
		
	}
  
  /*
    Private methods
  */
	private void initSecretWord() {
		Random randIndex = new Random();
		int index = randIndex.nextInt(dictionary.length);
		word = dictionary[index];
		
		correctLetters = new ArrayList<Character>();
		for (int i = 0; i < word.length(); i++)
		correctLetters.add('_');
		incorrectLetters = new ArrayList<Character>();
	}
  
	private boolean gameOver() {
		if (movesWrong >= movesAllowed)
			return true;
		return gameWon();
	}
  
	private boolean gameWon() {
		for (int i = 0; i < correctLetters.size(); i++) {
			if (correctLetters.get(i) == '_')
				return false;
		}
		return true;
	}
  
	private void printLetters(ArrayList<Character> letters) {
		for (int i = 0; i < letters.size(); i++) 
			System.out.print(letters.get(i) + " ");
		System.out.println("");
	}
	
	private void handleGuess(char letter) { 
		boolean badMove = true;
		letter = Character.toUpperCase(letter);

		for (int i = 0; i < word.length(); i++) {
			if ( letter == Character.toUpperCase( word.charAt(i) ) ) {
				correctLetters.set(i, letter);
				badMove = false;
			}
		}
		
		if (badMove)
			movesWrong++;
	}
	
	
	private void printHangman() {
		int poleLines = 6; 
		System.out.println("  ____");
		System.out.println("  |  |");
		
		
		if (movesWrong == 7) {
			System.out.println("  |  |");
			System.out.println("  |  |");
		}
		
		if (movesWrong > 0) {	    	   
			System.out.println("  |  O");
			poleLines = 5;
		}
		if (movesWrong > 1) {
			poleLines = 4;
			if (movesWrong == 2) {
				System.out.println("  |  |");
			} else if (movesWrong == 3) {
				System.out.println("  | \\|");
			} else if (movesWrong >= 4) {
				System.out.println("  | \\|/");
			}
		}
		if (movesWrong > 4) {
			poleLines = 3;
			if (movesWrong == 5) {
				System.out.println("  | /");
			} else if (movesWrong >= 6) {
				System.out.println("  | / \\");
			}
		}
		if (movesWrong == 7) {
			poleLines = 1;
		}

		for (int k = 0; k < poleLines; k++) {
			System.out.println("  |");
		}
		System.out.println("__|__");
		System.out.println();
	}
	
}

