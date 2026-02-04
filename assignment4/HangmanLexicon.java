
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;
import acmx.export.java.io.FileReader;

public class HangmanLexicon {
	private ArrayList<String> words = new ArrayList<String>();

// this conductor add words in arraylist
	public HangmanLexicon() throws IOException {
		BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));

		while (true) {
			String w = rd.readLine();
			if (w == null) {
				break;
			}
			words.add(w);
		}
		rd.close();
	

	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return  words.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return words.get(index);
	};
}
