/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas {

	private GCompound hungedMan;
	private GOval head;
	private GLine body;
	private GCompound completeLeftArm;
	private GCompound completeRightArm;
	private GCompound completeLeftLeg;
	private GCompound completeRightLeg;
	private GLine leftFoot;
	private GLine rightFoot;

	private GLabel secretWordLabel;
	private GLabel failCharsLabel;
	private String failList;

	int incorrectGuessCounter = 0;

/** Resets the display so that only the scaffold appears */
	public void reset() {
		drawHungedMan();
		hideMan();
		drawInitialInfoLabels();
		repositionHangedMan(); /* Vertical reposition according to labels height */
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		secretWordLabel.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		incorrectGuessCounter++;
		displayNextBodyPart(incorrectGuessCounter);
		displayFailChars(letter);
	}

	private void drawHungedMan() {
		hungedMan = new GCompound();
		GCompound scaffold = getScaffold();
		GCompound man = getMan();
		hungedMan.add(scaffold);
		hungedMan.add(man, BEAM_LENGTH, ROPE_LENGTH);
		add(hungedMan, getWidth() / 2 - BEAM_LENGTH, 0);
	}

	private void hideMan() {
		head.setVisible(false);
		body .setVisible(false);
		completeLeftArm.setVisible(false);
		completeRightArm.setVisible(false);
		completeLeftLeg.setVisible(false);
		completeRightLeg.setVisible(false);
		leftFoot.setVisible(false);
		rightFoot.setVisible(false);
	}

	private void drawInitialInfoLabels() {
		drawInitialSecretWordLabel();
		drawInitialFailLabel();
	}

	private void drawInitialSecretWordLabel() {
		secretWordLabel = new GLabel("");
		secretWordLabel.setFont(SECRET_WORD_LABEL_FONT);
		double y = 	hungedMan.getY()
					+ hungedMan.getHeight()
					+ SECRET_WORD_LABEL_OFFSET_FROM_HANGED_MAN;
		add(secretWordLabel, hungedMan.getX(), y);
	}

	private void drawInitialFailLabel() {
		failCharsLabel = new GLabel("");
		failCharsLabel.setFont(FAIL_LABEL_FONT);
		double y = secretWordLabel.getY() + FAIL_LABEL_OFFSET_FROM_SECRET_WORD;
		add(failCharsLabel, hungedMan.getX(), y);
	}

	private void repositionHangedMan() {
		double compleElementsWidth = hungedMan.getHeight()
									+ SECRET_WORD_LABEL_OFFSET_FROM_HANGED_MAN
									+ FAIL_LABEL_OFFSET_FROM_SECRET_WORD;
		double yHungedMan = (getHeight() - compleElementsWidth) / 2;
		hungedMan.setLocation(hungedMan.getX(), yHungedMan);
	}

	private GCompound getMan() {
		GCompound completeMan = new GCompound();

		head = new GOval(-HEAD_RADIUS, 0, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		completeMan.add(head);

		body = new GLine (0, 2 * HEAD_RADIUS, 0, 2 * HEAD_RADIUS + BODY_LENGTH);
		completeMan.add(body);

		completeLeftArm = new GCompound();
		GLine leftArm = new GLine (-UPPER_ARM_LENGTH, 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, 0, 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		completeLeftArm.add(leftArm);
		GLine leftLowerArm = new GLine (-UPPER_ARM_LENGTH, 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, -UPPER_ARM_LENGTH, 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		completeLeftArm.add(leftLowerArm);
		completeMan.add(completeLeftArm);

		completeRightArm = new GCompound();
		GLine rightArm = new GLine (0, 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, UPPER_ARM_LENGTH, 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		completeRightArm.add(rightArm);
		GLine rightLowerArm = new GLine (UPPER_ARM_LENGTH, 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, UPPER_ARM_LENGTH, 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		completeRightArm.add(rightLowerArm);
		completeMan.add(completeRightArm);

		completeLeftLeg = new GCompound();
		GLine leftLegHip = new GLine (-HIP_WIDTH, 2 * HEAD_RADIUS + BODY_LENGTH, 0, 2 * HEAD_RADIUS + BODY_LENGTH);
		completeLeftLeg.add(leftLegHip);
		GLine leftLeg = new GLine (-HIP_WIDTH, 2 * HEAD_RADIUS + BODY_LENGTH, -HIP_WIDTH, 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		completeLeftLeg.add(leftLeg);
		completeMan.add(completeLeftLeg);

		completeRightLeg = new GCompound();
		GLine rightHip = new GLine (0, 2 * HEAD_RADIUS + BODY_LENGTH, HIP_WIDTH, 2 * HEAD_RADIUS + BODY_LENGTH);
		completeRightLeg.add(rightHip);
		GLine rightLeg = new GLine (HIP_WIDTH, 2 * HEAD_RADIUS + BODY_LENGTH, HIP_WIDTH, 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		completeRightLeg.add(rightLeg);
		completeMan.add(completeRightLeg);

		leftFoot = new GLine (-HIP_WIDTH - FOOT_LENGTH, 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, -HIP_WIDTH, 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		completeMan.add(leftFoot);

		rightFoot = new GLine (HIP_WIDTH, 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, HIP_WIDTH + FOOT_LENGTH, 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		completeMan.add(rightFoot);

		return completeMan;
	}

	private GCompound getScaffold() {
		GCompound completeScaffold = new GCompound();

		GLine post = new GLine(0, SCAFFOLD_HEIGHT, 0, 0);
		completeScaffold.add(post);

		GLine beam = new GLine(0, 0, BEAM_LENGTH, 0);
		completeScaffold.add(beam);

		GLine rope = new GLine(BEAM_LENGTH, 0, BEAM_LENGTH, ROPE_LENGTH);
		completeScaffold.add(rope);

		return completeScaffold;
	}

	private void displayNextBodyPart(int bodyPartIndex) {
		switch (bodyPartIndex) {
		case  1: head.setVisible(true); break;
		case  2: body.setVisible(true); break;
		case  3: completeLeftArm.setVisible(true); break;
		case  4: completeRightArm.setVisible(true); break;
		case  5: completeLeftLeg.setVisible(true); break;
		case  6: completeRightLeg.setVisible(true); break;
		case  7: leftFoot.setVisible(true); break;
		case  8: rightFoot.setVisible(true); break;
		default: throw new ErrorException("displayNextBodyPart: Illegal bodyPartIndex");
		}
	}

	private void displayFailChars(char letter) {
		failList = (failList == null) ? "" + letter : failList + letter;
		failCharsLabel.setLabel(failList);
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

	private static final int SECRET_WORD_LABEL_OFFSET_FROM_HANGED_MAN = 70;
	private static final double FAIL_LABEL_OFFSET_FROM_SECRET_WORD = 20;
	private static final String SECRET_WORD_LABEL_FONT = "Serif-24";
	private static final String FAIL_LABEL_FONT = "Serif-14";

}
