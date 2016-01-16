package uk.ac.cam.ms2316.OOPS4;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

class HangmanGame {
  
	//Fields
	private ArrayList<String> dictionary;
	private String word;
	private int movesWrong;
	private int movesAllowed;
	private ArrayList<Character> correctLetters;
	private ArrayList<Character> incorrectLetters;
	private Scanner scn = new Scanner(System.in); // for user input
  
	//Constructor
	public HangmanGame() {
		try {
			System.out.println("Loading a dictionary...");
			dictionary = WordInput.loadFromURL("https://raw.githubusercontent.com/sacummins/OOPIncompleteWordGame/master/src/main/resources/words.txt");
		} catch (IOException e) {
			System.out.println("Unable to load a dictionary !");
			System.exit(0);
		}
		movesAllowed = 6; //depends on drawing
	}
  
	/*
		Public methods
	*/
  
	public void playGame() {
		//initialization of the game
		initSecretWord();
		movesWrong = 0;
		
		//game process
		while ( !gameOver() ) {
			
			printHangman();
		
			printLetters(correctLetters);
		
			System.out.print("Wrong letters: ");
			printLetters(incorrectLetters);
			
			System.out.print("Enter a letter: ");
			try {
				String guess = scn.nextLine();
				handleGuess(guess.charAt(0));
				System.out.println("");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("\nNo letters entered ! ! !");
			}
			
		}
		
		if (gameWon()) {
			System.out.println("Congratulations, you won!");
			System.out.println("The secret word: " + word.toUpperCase());
		} else {
			System.out.println("Sorry, you lost.");
			System.out.println("The secret word: " + word.toUpperCase());
			printHangman();
		}
		
	}
  
  /*
    Private methods
  */
	private void initSecretWord() {
		Random randIndex = new Random();
		int index = randIndex.nextInt(dictionary.size());
		word = dictionary.get(index);
		
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
		
		if (badMove) {
			movesWrong++;
			if (!incorrectLetters.contains(letter))  
				incorrectLetters.add(letter);
		}
		
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

