package edu.ramapo.ncockcro.casino;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
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
    String loadGameLastCapture;
    Vector<Card_Model> loadGameDeck = new Vector<Card_Model>();
    String loadGameNextPlayer;

    String mostCardsMessage;
    String mostSpadesMessage;
    String tenOfDiamondsMessage;
    String twoOfSpadesMessage;
    Vector<String> numAcesMessage = new Vector<String>();
    String winnerMessage;

    String mostCardsColor;
    String mostSpadesColor;
    String tenOfDiamondsColor;
    String twoOfSpadesColor;
    String winnerColor;

    /** *********************************************************************
     Function Name: Tournament_Model
     Purpose: Default constructor
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Default constructor
     Assistance Received: none
      ********************************************************************* */
    Tournament_Model() {

    }

    /** *********************************************************************
     Function Name: Tournament_Model
     Purpose: Overloaded default constructor with the activity
     Parameters:
     @param passedActivity, Activity object of the current activity were in
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the private activity to what was passed in
     Assistance Received: none
      ********************************************************************* */
    Tournament_Model(Activity passedActivity) {
        activity = (Activity) passedActivity;
    }


    /** *********************************************************************
     Function Name: InrementRound
     Purpose: To increment the round by 1
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Increment the round by 1
     Assistance Received: none
      ********************************************************************* */
    void IncrementRound() {
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

        // Cycling through the player's pile...
        for (int i = 0; i < passedHumanPile.size(); i++) {

            // If the card is a Spade, increase the player's Spade count
            if (passedHumanPile.get(i).GetSuit() == 'S') {
                humanSpadeCount++;
            }

            // If the player has the S2, increment the count
            if (passedHumanPile.get(i).GetCard().equals("S2")) {
                twoOfSpadesColor = "Green";
                twoOfSpadesMessage = "The human had the 2 of Spades, plus a point.";
                humanRoundPoints++;
            }

            // If the player has the DX, increment the count
            if (passedHumanPile.get(i).GetCard().equals("DX")) {
                tenOfDiamondsColor = "Green";
                tenOfDiamondsMessage = "The human had the 10 of Diamonds, plus two points.";
                humanRoundPoints += 2;
            }

            // If the player has an ace in their pile, increment their score
            if (passedHumanPile.get(i).GetNumber() == 'A') {
                humanAceCount++;
                humanRoundPoints++;
            }

        }

        // Cycling through the computer's pile...
        for (int i = 0; i < passedComputerPile.size(); i++) {

            // If the card is a Spade, increment the computer's Spade count
            if (passedComputerPile.get(i).GetSuit() == 'S') {
                computerSpadeCount++;
            }

            // If the computer has the S2, increment their score
            if (passedComputerPile.get(i).GetCard().equals("S2")) {
                twoOfSpadesColor = "Red";
                twoOfSpadesMessage = "The computer had the 2 of Spades, plus a point.";
                computerRoundPoints++;
            }

            // If the computer has the DX, increment their score
            if (passedComputerPile.get(i).GetCard().equals("DX")) {
                tenOfDiamondsColor = "Red";
                tenOfDiamondsMessage = "The computer had the 10 of Diamons, plus two points,";
                computerRoundPoints += 2;
            }

            // If the computer has any aces, increment their score
            if (passedComputerPile.get(i).GetNumber() == 'A') {
                computerAceCount++;
                computerRoundPoints++;
            }
        }

        // See if the player or computer has the most cards
        if (passedHumanPile.size() > passedComputerPile.size()) {
            mostCardsColor = "Green";
            mostCardsMessage = "The human had the most cards!";
            humanRoundPoints += 3;
        }
        else if (passedHumanPile.size() < passedComputerPile.size()) {
            mostCardsColor = "Red";
            mostCardsMessage = "The computer had the most cards.";
            computerRoundPoints += 3;
        }
        else {
            mostCardsColor = "Yellow";
            mostCardsMessage = "Neither player had more cards!";
        }

        // Checking to see if either player has more Spades
        if (humanSpadeCount > computerSpadeCount) {
            mostSpadesColor = "Green";
            mostSpadesMessage = "The human had the most Spades!";
            humanRoundPoints++;
        } else if (humanSpadeCount < computerSpadeCount) {
            mostSpadesColor = "Red";
            mostSpadesMessage = "The computer had the most Spades.";
            computerSpadeCount++;
        }
        else {
            mostSpadesColor = "Yellow";
            mostSpadesMessage = "Neither player had more Spades!";
        }

        numAcesMessage.add("You had " + Integer.toString(humanAceCount) + " Aces. ");
        numAcesMessage.add("The computer had " + Integer.toString(computerAceCount) + " Aces.");

        // Set the overall scores to what was calculated by the round
        humanPoints += humanRoundPoints;
        computerPoints += computerRoundPoints;
    }

    /** *********************************************************************
     Function Name: GameWon
     Purpose: To check if the game has finished or not
     Parameters: None
     Return Value:
     @return int, 1 if the human won, 2 if the computer won, or 3 if neither won
     Local Variables: None
     Algorithm:
     1) If the human has more points, output they won
     2) If the computer has more points, output they won
     3) If neither has more points, output it was a tie
     Assistance Received: none
      ********************************************************************* */
    int GameWon() {

        // Human won
        if(humanPoints >= 21 && computerPoints < 21) {
            winnerColor = "Green";
            winnerMessage = "You won!";
            return 1;
        }

        // Computer won
        else if(humanPoints < 21 && computerPoints >= 21) {
            winnerColor = "Red";
            winnerMessage = "The computer won.";
            return 2;
        }

        // It was a tie
        else {
            winnerColor = "Yellow";
            winnerMessage = "It was a tie!";
            return 3;
        }
    }

    /** *********************************************************************
     Function Name: GetMostCardsMessage
     Purpose: To retrieve the mostCardsMessage
     Parameters: None
     Return Value:
     @return String, the variable
     Local Variables: None
     Algorithm:
     1) Return mostCardsMessage
     Assistance Received: none
      ********************************************************************* */
    String GetMostCardsMessage() {
        return mostCardsMessage;
    }

    /** *********************************************************************
     Function Name: GetMostSpadesMessage
     Purpose: To retrieve the mostSpadesMessage
     Parameters: None
     Return Value:
     @return String, the variable
     Local Variables: None
     Algorithm:
     1) Return mostSpadesMessage
     Assistance Received: none
      ********************************************************************* */
    String GetMostSpadesMessage() {
        return mostSpadesMessage;
    }

    /** *********************************************************************
     Function Name: GetTenOfDiamondsMessage
     Purpose: To retrieve the tenOfDiamondsMessage
     Parameters: None
     Return Value:
     @return String, the variable
     Local Variables: None
     Algorithm:
     1) Return tenOfDiamondsMessage
     Assistance Received: none
      ********************************************************************* */
    String GetTenOfDiamondsMessage() {
        return tenOfDiamondsMessage;
    }

    /** *********************************************************************
     Function Name: GetTwoOfSpadesMessage
     Purpose: To retrieve the twoOfSpadesMessage
     Parameters: None
     Return Value:
     @return String, the variable
     Local Variables: None
     Algorithm:
     1) Return twoOfSpadesMessage
     Assistance Received: none
      ********************************************************************* */
    String GetTwoOfSpadesMessage() {
        return twoOfSpadesMessage;
    }

    /** *********************************************************************
     Function Name: GetNumAcesMessage
     Purpose: To retrieve the numAcesMessage
     Parameters: None
     Return Value:
     @return Vector<String>, the variable
     Local Variables: None
     Algorithm:
     1) Return numAcesMessage
     Assistance Received: none
      ********************************************************************* */
    Vector<String> GetNumAcesMessage() {
        return numAcesMessage;
    }

    /** *********************************************************************
     Function Name: GetWinnerMessage
     Purpose: To retrieve the winnerMessage
     Parameters: None
     Return Value:
     @return String, the variable
     Local Variables: None
     Algorithm:
     1) Return winnerMessage
     Assistance Received: none
      ********************************************************************* */
    String GetWinnerMessage() {
        return winnerMessage;
    }

    /** *********************************************************************
     Function Name: GetMostCardsColor
     Purpose: To retrieve the mostCardsColor
     Parameters: None
     Return Value:
     @return String, the variable
     Local Variables: None
     Algorithm:
     1) Return mostCardsColor
     Assistance Received: none
      ********************************************************************* */
    String GetMostCardsColor() {
        return mostCardsColor;
    }

    /** *********************************************************************
     Function Name: GetMostSpadesColor
     Purpose: To retrieve the mostSpadesColor
     Parameters: None
     Return Value:
     @return String, the variable
     Local Variables: None
     Algorithm:
     1) Return mostSpadesColor
     Assistance Received: none
      ********************************************************************* */
    String GetMostSpadesColor() {
        return mostSpadesColor;
    }

    /** *********************************************************************
     Function Name: GetTenOfDiamondsColor
     Purpose: To retrieve the tenOfDiamondsColor
     Parameters: None
     Return Value:
     @return String, the variable
     Local Variables: None
     Algorithm:
     1) Return tenOfDiamondsColor
     Assistance Received: none
      ********************************************************************* */
    String GetTenOfDiamondsColor() {
        return tenOfDiamondsColor;
    }

    /** *********************************************************************
     Function Name: GetTwoOfSpadesColor
     Purpose: To retrieve the twoOfSpadesColor
     Parameters: None
     Return Value:
     @return String, the variable
     Local Variables: None
     Algorithm:
     1) Return twoOfSpadesColor
     Assistance Received: none
      ********************************************************************* */
    String GetTwoOfSpadesColor() {
        return twoOfSpadesColor;
    }

    /** *********************************************************************
     Function Name: GetWinnerColor
     Purpose: To retrieve the winnerColor
     Parameters: None
     Return Value:
     @return String, the variable
     Local Variables: None
     Algorithm:
     1) Return winnerColor
     Assistance Received: none
      ********************************************************************* */
    String winnerColor() {
        return winnerColor;
    }

    void SetHumanPoints(int passedPoints) {
        humanPoints = passedPoints;
    }

    void SetComputerPoints(int passedPoints) {
        computerPoints = passedPoints;
    }

    void SetCurrentRound(int passedRound) {
        round = passedRound;
    }

    int GetHumanPoints() {
        return humanPoints;
    }

    int GetComputerPoints() {
        return computerPoints;
    }

    int GetRound() {
        return round;
    }

    void SetLastCapture(String passedCapture) {
        lastCaptured = passedCapture;
    }

    String GetLastCapture() {
        return lastCaptured;
    }

    void LoadGame(Context context, String path, String fileName) {

        String line = null;
        Card_Model tempCard = new Card_Model();

        try {
            FileInputStream fileIS = new FileInputStream(new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileIS);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            Scanner input = new Scanner(inputStreamReader);

            while( (line = bufferedReader.readLine()) != null) {
                Log.d("Line", line);
            }

            // Reading the text file word by word
            while(input.hasNext()) {
                String currentWord = input.next();

                // Getting the round
                if(currentWord.equals("Round:")) {
                    currentWord = input.next();
                    loadGameRound = Integer.parseInt(currentWord);
                    Log.d("Round", Integer.toString(loadGameRound));
                }

                currentWord = input.next();
                currentWord = input.next();

                // Getting the computer score
                if(currentWord.equals("Score:")) {
                    currentWord = input.next();
                    loadGameComputerScore = Integer.parseInt(currentWord);
                }

                currentWord = input.next();

                // Getting the computer hand
                if(currentWord.equals("Hand:")) {
                    currentWord = input.next();
                    while(currentWord.length() == 2) {
                        tempCard.SetCard(currentWord);
                        loadGameComputerHand.add(tempCard);
                        currentWord = input.next();
                    }
                }

                // Getting the computer pile
                if(currentWord.equals("Pile:")) {
                    currentWord = input.next();
                    while(currentWord.length() == 2) {
                        tempCard.SetCard(currentWord);
                        loadGameComputerPile.add(tempCard);
                        currentWord = input.next();
                    }
                }

                currentWord = input.next();

                // Getting the human score
                if(currentWord.equals("Score:")) {
                    currentWord = input.next();
                    loadGameHumanScore = Integer.parseInt(currentWord);
                }

                currentWord = input.next();

                // Getting the human score
                if(currentWord.equals("Hand:")) {
                    currentWord = input.next();
                    while(currentWord.length() == 2) {
                        tempCard.SetCard(currentWord);
                        loadGameHumanHand.add(tempCard);
                        currentWord = input.next();
                    }
                }

                // Getting the human pile
                if(currentWord.equals("Pile:")) {
                    currentWord = input.next();
                    while(currentWord.length() == 2) {
                        tempCard.SetCard(currentWord);
                        loadGameHumanPile.add(tempCard);
                        currentWord = input.next();
                    }
                }

                // Getting the table
                if(currentWord.equals("Table:")) {
                    currentWord = input.next();
                    while(!currentWord.equals("Build") && !currentWord.equals("Last")) {
                        if(currentWord.equals("[")) {
                            while(!currentWord.equals("]")) {
                                currentWord = input.next();
                            }
                        }
                        if(currentWord.length() == 2) {
                            tempCard.SetCard(currentWord);
                            loadGameTable.add(tempCard);
                        }
                        currentWord = input.next();
                    }
                }

                Vector<String> tempVector = new Vector<String>();
                String tempString = new String();
                String buildString = new String();
                Build_Model tempBuild = new Build_Model();

                // If the build keyword is in the text file, then we need to parse it for a build
                do {
                    if(currentWord.equals("Build")) {
                        currentWord = input.next();

                        // If the string is not a computer or human, then we know there is some
                        // text of the build we need to parse
                        while(!currentWord.equals("Computer") && !currentWord.equals("Human")) {
                            currentWord = input.next();

                            for(int i = 0; i < currentWord.length(); i++) {

                                // Checking for a suit
                                if(currentWord.charAt(i) == 'C' || currentWord.charAt(i) == 'D' ||
                                        currentWord.charAt(i) == 'H' || currentWord.charAt(i) == 'S') {
                                    buildString += currentWord.charAt(i);
                                }

                                // Checking for a number
                                if(currentWord.charAt(i) == '2' || currentWord.charAt(i) == '3' ||
                                        currentWord.charAt(i) == '4' || currentWord.charAt(i) == '5' ||
                                        currentWord.charAt(i) == '6' || currentWord.charAt(i) == '7' ||
                                        currentWord.charAt(i) == '8' || currentWord.charAt(i) == '9' ||
                                        currentWord.charAt(i) == 'X' || currentWord.charAt(i) == 'J' ||
                                        currentWord.charAt(i) == 'Q' || currentWord.charAt(i) == 'K' ||
                                        currentWord.charAt(i) == 'A') {
                                    buildString += currentWord.charAt(i);
                                }
                            }

                            // So long as what we parsed is a valid card, push it into a card and
                            // add it to the vector of cards to make up a build
                            if(buildString.length() == 2) {
                                tempCard.SetCard(buildString);
                                loadGameBuildCards.add(tempCard);
                            }

                            buildString = "";
                        }

                        tempBuild.SetBuildOfCards(loadGameBuildCards);

                        if(currentWord.equals("Computer")) {
                            tempBuild.SetOwner(1);
                        }
                        else if(currentWord.equals("Human")) {
                            tempBuild.SetOwner(0);
                        }
                        else {
                            Log.d("MyError", "Error in setting the build owner in tournament model");
                        }

                        loadGameBuilds.add(tempBuild);
                        loadGameBuildCards.clear();
                        currentWord = input.next();
                    }

                    tempBuild = new Build_Model();
                } while(currentWord.equals("Build"));

                if(currentWord.equals("Last")) {
                    currentWord = input.next();
                    currentWord = input.next();

                    loadGameLastCapture = currentWord;
                }

                currentWord = input.next();
                currentWord = input.next();

                if(currentWord.equals("Deck:")) {
                    currentWord = input.next();

                    if(currentWord != "Next") {
                        while(currentWord.length() == 2) {
                            tempCard.SetCard(currentWord);
                            loadGameDeck.add(tempCard);
                            currentWord = input.next();
                        }
                    }

                    currentWord = input.next();
                }

                if(currentWord.equals("Player:")) {
                    currentWord = input.next();

                    loadGameNextPlayer = currentWord;
                }


                Log.d("Comp", Integer.toString(loadGameComputerScore));
            }

        }
        catch (FileNotFoundException e) {

            Log.d("In", "In exception");
        }
        catch (IOException e) {

            Log.d("In", "In exception");
        }

    }


}
