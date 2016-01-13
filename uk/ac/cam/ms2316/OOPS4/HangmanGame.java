package uk.ac.cam.ms2316.OOPS4;

import java.util.Random;
import java.util.ArrayList;

class HangmanGame {
  
	//Fields
	private String[] dictionary;
	private String word;
	private int movesMade;
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
		for (int i = 0; i < correctLetters.size(); i++) 
			System.out.print(correctLetters.get(i) + " ");
		System.out.println("\n");
      
		//Print incorrect letters  guessed
		System.out.print("Wrong letters: ");
		for (int i = 0; i < this.incorrectLetters.size(); i++)
			System.out.print(this.incorrectLetters.get(i) + " ");
			
		//Prompt and read the next guess
		System.out.print("\nEnter a lower-case letter as your guess: ");
		String guess = stdin.nextLine();
			
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
	private String initSecretWord() {
		Random randIndex = new Random();
		int index = randIndex.nextInt(dictionary.length);
		word = dictionary[index];
		
		correctLetters = new ArrayList<Character>();
		for (int i = 0; i < word.length(); i++)
		correctLetters.add('_');
		incorrectLetters = new ArrayList<Character>();
	}
  
	private boolean gameOver() {
		//ToComplete
		return false;
	}
  
	private boolean gameWon() {
		//ToComplete
		return false;
	}
  

}

