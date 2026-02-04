
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.IOException;

public class Hangman extends ConsoleProgram {
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanCanvas canvas;

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);

	}

	public void run() {
		while (true) {
			canvas.reset();
			String word = null;
			try {
				word = chooseWord();
			} catch (IOException e) {
				e.printStackTrace();
			}
			println("Welcome To Hangman!");
			play(word);
			String ans = readLine("write 'yes' if you want to play again : ");
			if (!ans.toUpperCase().equals("YES")) {
				break;
			}
		}
	}
// play - this method include full playing process 
	private void play(String word) {
		int leftGuess = 8;
		String currentWord = firstWord(word);

		while (leftGuess != 0) {
			println("The word now looks like this : " + currentWord);
			println("You have " + leftGuess + " guesses left");
			String symbol = getSymbol(currentWord);
			leftGuess -= containsSymbol(leftGuess, symbol, word);
			currentWord = returnWord(symbol, word, currentWord);
			canvas.displayWord(currentWord);
			if (win(word, currentWord)) {
				break;
			}
		}

		lose(leftGuess, word);
	}
//win - this method true when player wins game
	private boolean win(String word, String currentWord) {
		if (word.equals(currentWord)) {
			println("You guessed the word :" + word);
			println("You win.");
			return true;
		}
		return false;
	}
//containsSymbol - this method return 1 if player did not guessed the symbol
	private int containsSymbol(int leftGuess, String symbol, String word) {
		if (!word.contains(symbol)) {
			leftGuess--;
			println("There are no " + symbol + " in word!");
			canvas.noteIncorrectGuess(symbol.charAt(0));
			return 1;

		}
		println("That guess is correct.");
		return 0;
	}
//lose - this method is called when player lose game
	private void lose(int leftGuess, String word) {
		if (leftGuess == 0) {
			println("You are completely hung.");
			println("The word was : " + word);
			println("You lose.");
		}

	}
//chooseWord this method  chose random word every time player start new game
	private String chooseWord() throws IOException {
		HangmanLexicon lexicon = new HangmanLexicon();
		String word = lexicon.getWord(rgen.nextInt(lexicon.getWordCount()));
		word = word.toUpperCase();
		//println(word);
		return word;
	}
//returnWord - this method replace - with symbol which player guessed
	private String returnWord(String symbol, String word, String currentWord) {
		int n = 0;
		while (word.contains(symbol)) {
			int j = word.indexOf(symbol);
			word = word.replaceFirst(symbol, "");
			currentWord = currentWord.substring(0, n + j) + symbol + currentWord.substring(n + j + 1);
			n++;
		}
		return currentWord;
	}
//getSymbol - this method get symbol from player and checks if it is valid 
	private String getSymbol(String currentWord) {
		String guess;
		while (true) {
			guess = readLine("Your guess : ");
			if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {

				guess = checkSymbol(currentWord, guess);

				break;
			}
			println("Please enter valid symbol");
		}
		return guess.toUpperCase();
	}
// checkSymbol - this method checks if player entered symbol that he already guessed
	private String checkSymbol(String currentWord, String guess) {
		while (true) {
			if (currentWord.contains(guess.toUpperCase())) {
				println("You have already guessed this symbol  \nPlease try again ");
				guess = readLine("Your guess : ");

			} else {
				break;
			}
		}
		return guess;

	}
// firstWord - this method returns hidden word
	private String firstWord(String word) {
		String w = "";
		for (int i = 0; i < word.length(); i++) {
			w += "-";
		}
		return w;
	}

}
