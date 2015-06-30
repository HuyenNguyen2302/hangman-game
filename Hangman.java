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



public class Hangman extends ConsoleProgram {
	
	/* Constants */
	public static final int GUESS_NUM = 8;
	public static final int WIDTH = 700;
	public static final int HEIGHT = 800;
	
	/* Global variables */
	public static int lives;
	
	/* Add the canvas */
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		canvas.setSize(WIDTH, HEIGHT); // Set the size of the 
	}
	
	public void run() {
		setUp();
		
		/* 
		 * Repeat the game until 
		 * remaining lives is 0  the word has been guessed correctly.
		 */
		while (blankSpot() && lives != 0) {
			processGuess();
			checkAndUpdate();
		}
	}
	
	/* 
	 * Reset the canvas, print welcome message,
	 * choose a random word, and print it (using dashes only)
	 */
	public void setUp() {
		canvas.reset();
		println("Welcome to Hangman!");
		lives = GUESS_NUM; // Initialize number of lives
		wordNum = rgen.nextInt(0, (wordBank.getWordCount() - 1));
		arraySize = wordBank.getWord(wordNum).length();
		arrayWord = new String[arraySize];
		
		/* Initialize array with dashes */
		for (int i = 0; i < arraySize; i++)
			arrayWord[i] = "-";
		
		printArrayWord(); 
	}
	
	/*
	 * Print the array that keeps track of the word being guessed,
	 * including how it currently looks like, and how many lives are left
	 */
	public void printArrayWord() {
		String wordString = convertToString(arrayWord);
		println("The word now looks like this: " + wordString);
		canvas.displayWord(wordString);
		println("You have " + lives + " guesses left.");
	}
	
	/*
	 * Concatenate each element in the given array into a string
	 */
	public String convertToString(String[] array) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
		   result.append( array[i] );
		}
		return result.toString();
	}
	/* 
	 * Return true if there are letters that haven't been guessed correctly.
	 */
	public boolean blankSpot() {
		for (int i = 0; i < arraySize; i++) 
			if (arrayWord[i].equals("-")) 
				return true;
		return false;
	}
	
	/* 
	 * Take the guess of the player and turn it into upper-case letter if it is lower-case.
	 */
	public void processGuess() {
		String input = readLine("Your guess: ");
		letterUpper = input.toUpperCase();
	}
	
	/*
	 * Check the guess of the player,
	 * update arrayWord if the guess if true
	 * and print appropriate message to the console program
	 */
	public void checkAndUpdate() {
		boolean match = false;
		for (int i = 0; i < arraySize; i++) {
			char charAtPos = wordBank.getWord(wordNum).charAt(i);
			String stringAtPos = Character.toString(charAtPos);
			if (stringAtPos.equals(letterUpper)) {
				arrayWord[i] = letterUpper;
				match = true;
			}
		}
		
		/* Print different messages on the screen 
		 * If the player made a correct guess, print a winning message,
		 * Otherwise, print a losing message
		 */
		if (match) {
			println("That guess is correct.");
			if (blankSpot()) printArrayWord();
			else printWin();
		} else {
			println("There are no " + letterUpper + "'s in the word.");
			lives--;
			if (lives != 0) printArrayWord();
			else printLose();
			canvas.noteIncorrectGuess(letterUpper.charAt(0));
		}
	}
	
	public void printWin() {
		println("You guessed the word: " + wordBank.getWord(wordNum));
		println("You win! :)");
	}
	
	public void printLose()  {
		println("You're completely hung. :(");
		println("The word was: " + wordBank.getWord(wordNum));
		println("You lose.");
	}
	
	/* Instance variables */
	private HangmanLexicon wordBank = new HangmanLexicon();
	private String[] arrayWord;
	private int arraySize; // Size of the array that represents the randomly chosen word
	// private int lives; // Number of guesses left
	private String letterUpper; 
	private int wordNum; // the index of the randomly chosen word
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanCanvas canvas;
}
