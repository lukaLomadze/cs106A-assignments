
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		while (true) {
			nPlayers = dialog.readInt("Enter number of players");
			if (!(nPlayers > MAX_PLAYERS)) {
				break;
			}
		}
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			
			while(true){
				
				String name = dialog.readLine("Enter name for player " + i);
				playerNames[i - 1] = name;
				if(!name.equals("")){
					break;
				}
				playerNames[i - 1] = name;
			}
			
			
			
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		int[][] playersScores = new int[nPlayers][N_CATEGORIES];
		boolean[][] choseCategories = new boolean[nPlayers][N_CATEGORIES];
		for (int n = 1; n <= nPlayers * N_SCORING_CATEGORIES; n++) {
			int player = chosePlayer(n);
			int[] dices = new int[N_DICE];
			onePlayer(dices, choseCategories, player, playersScores);
		}
		upperBonus(playersScores);
		lowerScore(playersScores);
		winner(playersScores);
	}

	// onePlayer - there is full set of methods which is neccessary for one
	// player to play one round
	private void onePlayer(int[] dices, boolean[][] choseCategories, int player, int[][] playersScores) {
		for (int i = 0; i < dices.length; i++) {
			dices[i] = rgen.nextInt(1, 6);
		}
		display.waitForPlayerToClickRoll(player);
		display.displayDice(dices);
		chooseDices(dices);
		chooseDices(dices);
		display.printMessage("Select a category for this roll.");
		int selected = category(choseCategories, player);
		updateSores(dices, selected, player, playersScores);

	}

	// category - this method is to chose a category and checks if a player has
	// already chosen a category
	private int category(boolean[][] choseCategories, int player) {
		int selected;
		while (true) {
			selected = display.waitForPlayerToSelectCategory();
			if (!choseCategories[player - 1][selected - 1]) {
				choseCategories[player - 1][selected - 1] = true;
				println(selected);
				break;
			}
			display.printMessage("You have already chosen this category, please chose another one.");

		}
		return selected;
	}

	// updateScorecard - this method writes scores in scorecard
	private void updateSores(int[] dices, int selected, int player, int[][] playersScores) {
		int score = 0;
		if (checkCategory(dices, selected)) {
			score = calculateScore(dices, selected);
		}
		playersScores[player - 1][selected - 1] = score;
		playersScores[player - 1][N_CATEGORIES - 1] += score;
		display.updateScorecard(selected, player, score);
		display.updateScorecard(TOTAL, player, playersScores[player - 1][N_CATEGORIES - 1]);
	}

	// checkCategory - this method checks if a player chosed correct category
	private boolean checkCategory(int[] dices, int selected) {
		if (selected == THREE_OF_A_KIND || selected == FOUR_OF_A_KIND || selected == FULL_HOUSE
				|| selected == YAHTZEE) {
			return sames(dices, selected);
		} else if (selected == SMALL_STRAIGHT || selected == LARGE_STRAIGHT) {
			return straight(dices, selected);
		}
		return true;
	}

	// straigt - this method checks if a player chosed small or large straight
	// correctly
	private boolean straight(int[] dices, int selected) {
		Arrays.sort(dices);
		int n = 0;
		for (int i = 1; i < dices.length; i++) {
			if (dices[i] - dices[i - 1] == 1) {
				n++;
			}
		}
		if (n == 4) {
			return true;
		}
		if (n == 3 && selected == SMALL_STRAIGHT) {
			return true;
		}
		return false;
	}

	// sames - this method checks if a player chosed tree of a kind, four of a
	// kind, yahtzee or full house correctly
	private boolean sames(int[] dices, int selected) {
		HashMap<Integer, Integer> diceValues = new HashMap<Integer, Integer>();
		for (int i = 0; i < dices.length; i++) {
			diceValues.putIfAbsent(dices[i], 0);
			int value = diceValues.get(dices[i]);
			diceValues.put(dices[i], value + 1);
		}
		if (diceValues.containsValue(5) && selected != FULL_HOUSE) {
			return true;
		}
		if (diceValues.containsValue(4) && selected != FULL_HOUSE && selected != YAHTZEE) {
			return true;
		}
		if (diceValues.containsValue(3) && diceValues.containsValue(2)
				&& (selected == FULL_HOUSE || selected == THREE_OF_A_KIND)) {
			return true;
		}
		if (diceValues.containsValue(3) && selected == THREE_OF_A_KIND) {
			return true;
		}
		return false;
	}

	// chosePlayer - this method chose which players turn is
	private int chosePlayer(int n) {
		int player = n % nPlayers;
		if (player == 0) {
			player = nPlayers;
		}
		display.printMessage(playerNames[player - 1] + "'s turn ! Click 'Roll dice' button to roll the dice .");
		return player;
	}

	// winner - this method calcuate who is winner and if it is draw
	private void winner(int[][] playersScores) {
		int score = 0;
		ArrayList<String> names = new ArrayList<String>();
		String name = "";
		for (int i = 0; i < playersScores.length; i++) {
			if (playersScores[i][N_CATEGORIES - 1] > score) {
				score = playersScores[i][N_CATEGORIES - 1];
				name = playerNames[i];
			}
		}
		for (int i = 0; i < playersScores.length; i++) {
			if (playersScores[i][N_CATEGORIES - 1] == score) {
				names.add(playerNames[i]);
			}
		}
		if (names.size() == 1) {
			display.printMessage("Congradulation , " + name + ", you are the winner with the total score of  " + score);
		} else {

			display.printMessage("Congradulation , " + names.toString().substring(1, names.toString().length() - 1)
					+ ", it is draw,  you are the winners with the total score of  " + score);
		}
	}

	// lowerScore - this method sum lower scores
	private void lowerScore(int[][] playersScores) {
		for (int i = 0; i < playersScores.length; i++) {
			for (int j = THREE_OF_A_KIND - 1; j < CHANCE; j++) {
				playersScores[i][LOWER_SCORE - 1] += playersScores[i][j];
			}
			display.updateScorecard(LOWER_SCORE, i + 1, playersScores[i][LOWER_SCORE - 1]);
		}
	}

	// upperBonus this method checks if players upperscore is mor than 63 and
	// write bonus score
	private void upperBonus(int[][] playersScores) {
		for (int i = 0; i < playersScores.length; i++) {
			for (int j = 0; j < 6; j++) {
				playersScores[i][UPPER_SCORE - 1] += playersScores[i][j];
			}
			if (playersScores[i][UPPER_SCORE - 1] >= 63) {
				playersScores[i][UPPER_BONUS - 1] = 35;
			}
			display.updateScorecard(UPPER_SCORE, i + 1, playersScores[i][UPPER_SCORE - 1]);
			display.updateScorecard(UPPER_BONUS, i + 1, playersScores[i][UPPER_BONUS - 1]);
		}
	}

	// calculateScore - this score returns score according to chosen category
	private int calculateScore(int[] dices, int selected) {
		if (selected >= ONES && selected <= SIXES) {
			return calculate(dices, selected);
		} else if (selected == THREE_OF_A_KIND || selected == FOUR_OF_A_KIND || selected == CHANCE) {
			return sum(dices);
		} else if (selected == FULL_HOUSE) {
			return 25;
		} else if (selected == SMALL_STRAIGHT) {
			return 30;
		} else if (selected == LARGE_STRAIGHT) {
			return 40;
		} else if (selected == YAHTZEE) {
			return 50;
		}
		return 0;
	}

	// calculate - this method calculate sum of selected number for categories
	// 1-6
	private int calculate(int[] dices, int selected) {
		int scr = 0;
		for (int i = 0; i < dices.length; i++) {
			if (dices[i] == selected) {
				scr += selected;
			}
		}
		return scr;
	}

	// sum - this method calculate sum of all the dices
	private int sum(int[] dices) {
		int sum = 0;
		for (int i = 0; i < dices.length; i++) {
			sum += dices[i];
		}
		return sum;
	}

	// chooseDices - this method choose dice values with randomizer
	private void chooseDices(int[] dices) {
		display.printMessage("Select the dice you wish to re-roll and click 'Roll again'.");
		display.waitForPlayerToSelectDice();
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				dices[i] = rgen.nextInt(1, 6);
			}
		}
		display.displayDice(dices);
	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
}
