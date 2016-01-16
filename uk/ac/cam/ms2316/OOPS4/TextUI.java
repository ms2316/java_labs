package uk.ac.cam.ms2316.OOPS4;

import java.util.Scanner;

class TextUI implements UserInterface {
	
	
	private Scanner scan = new Scanner(System.in); // for user input
	
	@Override
	public void showStartMenu() {
		System.out.println("Welcome to Hangman Game !");
	}
	
	@Override
	public void showEndMenu() {
		System.out.println("One more try? Y/N");
	}
	
	@Override
	public boolean exitGame() {
		String s;
		while (true) {
			try {
				s = scan.nextLine();
				char ch = s.charAt(0);
				ch = Character.toUpperCase(ch);
				if (ch == 'Y') return false;
				else if (ch == 'N') return true;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid input");
				System.out.println("One more try? Y/N");
			}
		}
	}
	
	@Override
	public void startGame() {
		showStartMenu();
		HangmanGame game = new HangmanGame();
		do {
			game.playGame();
			showEndMenu();
		} while ( !exitGame() );
	
	}

}