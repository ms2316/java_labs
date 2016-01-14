package uk.ac.cam.ms2316.OOPS4;

import java.util.Scanner;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


class WordInput {

	public static ArrayList<String> loadFromURL(String url) throws IOException {
		URL destination = new URL(url);
		Scanner scanner = new Scanner(destination.openStream());
		ArrayList<String> words = new ArrayList<String>();
		
		while ( scanner.hasNext() ) {
				words.add( scanner.next() );
		}
		
		return words;
	}

}