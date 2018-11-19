package edu.ramapo.ncockcro.casino;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.Vector;

public class Tournament_Model {

    private String initialDecision;
    private int humanPoints;
    private int computerPoints;
    private String firstPlayer;
    private int round;
    private String lastCaptured;
    private Tournament_View tournamentView;
    private Activity activity;

    int loadGameRound;
    int loadGameComputerScore;
    Vector<Card_Model> loadGameComputerHand = new Vector<Card_Model>();
    Vector<Card_Model> loadGameComputerPile = new Vector<Card_Model>();
    int loadGameHumanScore;
    Vector<Card_Model> loadGameHumanHand = new Vector<Card_Model>();
    Vector<Card_Model> loadGameHumanPile = new Vector<Card_Model>();
    Vector<Card_Model> loadGameTable = new Vector<Card_Model>();
    Vector<Card_Model> loadGameBuildCards = new Vector<Card_Model>();
    Vector<Build_Model> loadGameBuilds = new Vector<Build_Model>();
    Vector<Card_Model> loadGameDeck = new Vector<Card_Model>();
    String nextPlayer;
    Vector<String> scoreOutputHuman = new Vector<String>();
    Vector<String> scoreOutputComputer = new Vector<String>();

    Tournament_Model() {

    }

    Tournament_Model(Activity passedActivity) {
        activity = (Activity) passedActivity;
    }

    public void PlayTournament() {
    }


    private void IncrementRound() {
        round++;
    }

    /** *********************************************************************
    Function Name: SaveLastCapture
    Purpose: At the end of the round, save the player who got the last capture so they go first next round
    Parameters:
    @param capturer, the player that captured last, a string value
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Set the last capturer
    Assistance Received: none
    ********************************************************************* */
    void SaveLastCaptured(String capturer) {

        if (capturer.equals("Human") || capturer.equals("Computer")) {
            lastCaptured = capturer;
        }
        else {
            Log.d("MyError", "Error in setting the capturer in the tournament model class.");
        }
    }


    /** *********************************************************************
    Function Name: CalculatePoints
    Purpose: At the end of each round, this function calculates the points being made from the round
    Parameters:
     @param passedHumanPile, Vector<Card_Model>
     @param passedComputerPile, Vector<Card_Model>
    Return Value: Void
    Local Variables:
    humanPile, a vector of cards which holds the humans pile
    computerPile, a vector of cards which holds the computers pile
    humanRoundPoints, an integer which holds the amount of points a player earned from the round
    computerRoundPoints, an integer which holds the amount of points a computer earned from the round
    humanSpadeCount, an integer which holds the count of the number of spades the human has
    computerSpadeCount, an integer which holds the count of the number of spades the computer has
    Algorithm:
    1) First go through the human and calculate their points
    2) Prompt the user for what they think the coin is
    3) If they are right, return with the string "Human"
    4) Otherwise, return with the string "Computer"
    Assistance Received: none
    ********************************************************************* */
    void CalculatePoints(Vector<Card_Model> passedHumanPile, Vector<Card_Model> passedComputerPile) {

        int humanRoundPoints = 0;
        int computerRoundPoints = 0;
        int humanSpadeCount = 0;
        int computerSpadeCount = 0;
        int humanAceCount = 0;
        int computerAceCount = 0;

        for (int i = 0; i < passedHumanPile.size(); i++) {

            if (passedHumanPile.get(i).GetSuit() == 'S') {
                humanSpadeCount++;
            }
            if (passedHumanPile.get(i).GetCard().equals("S2")) {
                scoreOutputHuman.add("The human had the 2 of Spades, plus a point.");
                humanRoundPoints++;
            }
            if (passedHumanPile.get(i).GetCard().equals("DX")) {
                scoreOutputHuman.add("The human had the 10 of Diamonds, plus two points.");
                humanRoundPoints += 2;
            }
            if (passedHumanPile.get(i).GetNumber() == 'A') {
                humanAceCount++;
                humanRoundPoints++;
            }

        }

        for (int i = 0; i < passedComputerPile.size(); i++) {

            if (passedComputerPile.get(i).GetSuit() == 'S') {
                computerSpadeCount++;
            }
            if (passedComputerPile.get(i).GetCard().equals("S2")) {
                scoreOutputComputer.add("The computer had the 2 of Spades, plus a point.");
                computerRoundPoints++;
            }
            if (passedComputerPile.get(i).GetCard().equals("DX")) {
                scoreOutputComputer.add("The computer had the 10 of Diamonds, plus two points.");
                computerRoundPoints += 2;
            }
            if (passedComputerPile.get(i).GetNumber() == 'A') {
                computerAceCount++;
                computerRoundPoints++;
            }
        }

        if (passedHumanPile.size() > passedComputerPile.size()) {
            humanRoundPoints += 3;
        } else if (passedHumanPile.size() < passedComputerPile.size()) {
            computerRoundPoints += 3;
        }

        if (humanSpadeCount > computerSpadeCount) {
            humanRoundPoints++;
        } else if (humanSpadeCount < computerSpadeCount) {
            computerSpadeCount++;
        }
    }
}
