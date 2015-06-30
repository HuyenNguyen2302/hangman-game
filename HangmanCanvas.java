/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;

import acm.graphics.*;



public class HangmanCanvas extends GCanvas {
	
	// Constants
	public static final int WIDTH = 500;
	public static final int HEIGHT = 600;
	public static final int MID_X = WIDTH / 2;
	public static final int MID_Y = HEIGHT / 2;
	
	/** Resets the display so that only the scaffold appears */
	public void reset() {
				
		/* Draw the Hangman */
		
		GLine scaffold = new GLine(MID_X - BEAM_LENGTH, MID_Y + Y_OFFSET_FROM_MID_Y,
				MID_X - BEAM_LENGTH, MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT);
		add(scaffold);
		
		GLine beam = new GLine(MID_X, MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT,
				MID_X - BEAM_LENGTH, MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT);
		add(beam);
		
		GLine rope = new GLine(MID_X, MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT,
				MID_X, MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT + ROPE_LENGTH);
		add(rope);
	}
	
	/**
	 * Updates the word on the screen to correspond to the current
	 * state of the game.  The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		// Coordinates of the word label on the canvas
		
		int x = 100 - ROPE_LENGTH;
		int y = 100 + 400;
		
		// Check to see if a word label has been displayed
		// If true, then delete the word before adding a new one
		// If false, then just add the label to the canvas
		
		if (getElementAt(x, y) != null) 
			remove(getElementAt(x, y));
		
		GLabel wordLabel = new GLabel (word, x, y);
		wordLabel.setFont(font);
		add(wordLabel);
	}
	
	/**
	 * Updates the display to correspond to an incorrect guess by the
	 * user.  Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {
		/* Draw the next body part of the hang man */
		switch(Hangman.lives) {
			case 7:	
				drawHead();
				break;
			case 6:
				drawBody();
				break;
			case 5:
				drawLeftArm();
				break;
			case 4:
				drawRightArm();
				break;
			case 3:
				drawLeftLeg();
				break;
			case 2:
				drawRightLeg();
				break;
			case 1:
				drawLeftFoot();
				break;
			case 0:
				drawRightFoot();
				break;
		}
		
		/* Add the given letter to the list of incorrect guesses */
		String str = Character.toString(letter);
		GLabel wordLabel = new GLabel(str, xWord, yWord);
		wordLabel.setFont(font);
		add(wordLabel);
		xWord += SPACE;
	}
		
		
	/**
	 * Draw the right leg (including the hip)
	 */
        private void drawRightLeg() {
        	// Draw the right hip
        	int xStart = MID_X;
        	int yStart = MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT 
        			+ ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
	        int xEnd = MID_X + HIP_WIDTH;
	        GLine rightHip = new GLine(xStart, yStart, xEnd, yStart);
	        add(rightHip);
	        
	        // Draw the right leg
	        int yEndLeg = yStart + LEG_LENGTH;
	        GLine rightLeg = new GLine(xEnd, yStart, xEnd, yEndLeg);
	        add(rightLeg);   
        }

	/**
	 * Draw the right foot of the hang man
	 */
        private void drawRightFoot() {
        	int xStart = MID_X + HIP_WIDTH;
	        int yStart = MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT + 
        			ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
	        int xEnd = xStart + FOOT_LENGTH;
	        GLine rightFoot = new GLine(xStart, yStart, xEnd, yStart);
	        add(rightFoot);
        }

	/**
	 *  Draw the left foot of the hang man
	 */
        private void drawLeftFoot() {
	        int xStart = MID_X - HIP_WIDTH;
	        int yStart = MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT + 
        			ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
	        int xEnd = xStart - FOOT_LENGTH;
	        GLine leftFoot = new GLine(xStart, yStart, xEnd, yStart);
	        add(leftFoot);
        }

	/**
	 * Draw the left leg (including the hip)
	 */
        private void drawLeftLeg() {
        	// Draw the left hip
        	int xStart = MID_X;
        	int yStart = MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT + 
        			ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
	        int xEnd = MID_X - HIP_WIDTH;
	        GLine leftHip = new GLine(xStart, yStart, xEnd, yStart);
	        add(leftHip);
	        
	        // Draw the left leg
	        int yEndLeg = yStart + LEG_LENGTH;
	        GLine leftLeg = new GLine(xEnd, yStart, xEnd, yEndLeg);
	        add(leftLeg);
        }

	/**
	 * Draw the right arm of the hang man
	 */
        private void drawRightArm() {
        	int xStart = MID_X;
 	        int yStart = MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS 
 	        		+ ARM_OFFSET_FROM_HEAD;
 	        int xEnd = xStart + UPPER_ARM_LENGTH;
 	        int yEnd = yStart;
 	        GLine rightArm = new GLine(xStart, yStart, xEnd, yEnd);
 	        add(rightArm);    
 	        
 	       int yHandEnd = yEnd + LOWER_ARM_LENGTH;
	       GLine rightHand = new GLine(xEnd, yEnd, xEnd, yHandEnd);
	       add(rightHand);
        }

	/**
	 * Draw the left arm of the hang man
	 */
        private void drawLeftArm() {
	        int xStart = MID_X;
	        int yStart = MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS 
	        		+ ARM_OFFSET_FROM_HEAD;
	        int xEnd = xStart - UPPER_ARM_LENGTH;
	        int yEnd = yStart;
	        GLine leftArm = new GLine(xStart, yStart, xEnd, yEnd);
	        add(leftArm);
	        
	        int yHandEnd = yEnd + LOWER_ARM_LENGTH;
	        GLine leftHand = new GLine(xEnd, yEnd, xEnd, yHandEnd);
	        add(leftHand);
        }

	/**
	 * Draw body of the hang man
	 */
        private void drawBody() {
        	int xStart = MID_X;
        	int yStart = MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT + ROPE_LENGTH + 2 * HEAD_RADIUS;
        	int xEnd = xStart;
        	int yEnd = yStart + BODY_LENGTH;
        	GLine body = new GLine(xStart, yStart, xEnd, yEnd);
        	add(body);
        }

	
	
	/**
	 * Draw the head of the hang man
	 */
        private void drawHead() {
        	int x = MID_X - HEAD_RADIUS;
        	int y = MID_Y + Y_OFFSET_FROM_MID_Y - SCAFFOLD_HEIGHT + ROPE_LENGTH;
	        GOval head = new GOval(x, y, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
	        add(head);
        }
        
        

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	private static final int Y_OFFSET_FROM_MID_Y = 100;
	private static final int SPACE = 20; // Space between letters to be displayed on the canvas
	
	/* Instance variables */
	private int xWord = 100 - ROPE_LENGTH;
	private int yWord = getHeight() / 2 + 550;
	private Font font = new Font( "Monospaced", Font.PLAIN, 25);
}
