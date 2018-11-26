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

    String fileToLoadFrom;

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

        fileToLoadFrom = "";
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
        fileToLoadFrom = "";
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

    /** *********************************************************************
     Function Name: SetHumanPoints
     Purpose: To set the points for the human to what was passed in
     Parameters:
     @param passedPoints, int
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the humanPoints variable to what was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetHumanPoints(int passedPoints) {
        humanPoints = passedPoints;
    }

    /** *********************************************************************
     Function Name: SetComputerPoints
     Purpose: To set the points for the computer to what was passed in
     Parameters:
     @param passedPoints, int
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the computerPoints variable to what was passed in
     Assistance Received: none
      ********************************************************************* */
    void SetComputerPoints(int passedPoints) {
        computerPoints = passedPoints;
    }

    /** *********************************************************************
     Function Name: SetCurrentRound
     Purpose: To set the current round to what was passed in
     Parameters:
     @param passedRound, int
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the round variable to what was passed in
     Assistance Received: none
      ********************************************************************* */
    void SetCurrentRound(int passedRound) {
        round = passedRound;
    }

    /** *********************************************************************
     Function Name: GetHumanPoints
     Purpose: To retrieve the human's points from the tournament model
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Return the humanPoints variable
     Assistance Received: none
      ********************************************************************* */
    int GetHumanPoints() {
        return humanPoints;
    }

    /** *********************************************************************
     Function Name: GetComputerPoints
     Purpose: To retrieve the computer's points from the tournament model
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Return the computerPoints variable
     Assistance Received: none
     ********************************************************************* */
    int GetComputerPoints() {
        return computerPoints;
    }

    /** *********************************************************************
     Function Name: GetRound
     Purpose: To retrieve the round from the tournament model
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Return the round variable
     Assistance Received: none
     ********************************************************************* */
    int GetRound() {
        return round;
    }

    /** *********************************************************************
     Function Name: SetLastCapture
     Purpose: To set the player who captured last to what was passed in
     Parameters:
     @param passedCapture, String
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the lastCaptured variable to what was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetLastCapture(String passedCapture) {
        lastCaptured = passedCapture;
    }

    /** *********************************************************************
     Function Name: GetLastCapture
     Purpose: To retrieve the lastCapture player from the tournament model
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Return the lastCapture variable
     Assistance Received: none
     ********************************************************************* */
    String GetLastCapture() {
        return lastCaptured;
    }

    /** *********************************************************************
     Function Name: LoadGame
     Purpose: To gather all of the necessary information from a text file
     Parameters:
     @param path, String
     @param fileName, String
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Open the file that the user wants to load from
     2) While there are still words in the text file to read
     3) Then cycle through and look for the keywords to load the
     information from
     Assistance Received: none
     ********************************************************************* */
    void LoadGame(String path, String fileName) {

        String line = null;
        Card_Model tempCard = new Card_Model();

        try {
            FileInputStream fileIS = new FileInputStream(new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileIS);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            Scanner input = new Scanner(inputStreamReader);

            /*while( (line = bufferedReader.readLine()) != null) {
                Log.d("Line", line);
            }*/

            Log.d("In", "In the tournament function");

            // Reading the text file word by word
            while(input.hasNext()) {
                Log.d("Inside", "Inside of while loop");
                String currentWord = input.next();

                // Getting the round
                if(currentWord.equals("Round:")) {
                    currentWord = input.next();
                    loadGameRound = Integer.parseInt(currentWord);
                    Log.d("Round", Integer.toString(loadGameRound));
                }

                currentWord = input.next();
                try {
                    currentWord = input.next();
                }
                catch(Exception e) {
                    break;
                }

                // Getting the computer score
                if(currentWord.equals("Score:")) {
                    currentWord = input.next();
                    loadGameComputerScore = Integer.parseInt(currentWord);
                    Log.d("CompScore", Integer.toString(loadGameComputerScore));
                }

                currentWord = input.next();

                Vector<Card_Model> computerHandTempCards = new Vector<Card_Model>();
                // Getting the computer hand
                if(currentWord.equals("Hand:")) {
                    currentWord = input.next();
                    while(currentWord.length() == 2) {
                        tempCard.SetCard(currentWord);
                        loadGameComputerHand.add(new Card_Model(currentWord));
                        Log.d("CompHandCard", tempCard.GetCard());
                        currentWord = input.next();
                    }
                }

                // Getting the computer pile
                if(currentWord.equals("Pile:")) {
                    currentWord = input.next();
                    while(currentWord.length() == 2) {
                        tempCard.SetCard(currentWord);
                        loadGameComputerPile.add(new Card_Model(currentWord));
                        Log.d("CompPileCard", tempCard.GetCard());
                        currentWord = input.next();
                    }
                }

                currentWord = input.next();

                // Getting the human score
                if(currentWord.equals("Score:")) {
                    currentWord = input.next();
                    loadGameHumanScore = Integer.parseInt(currentWord);
                    Log.d("humanScore", Integer.toString(loadGameHumanScore));
                }

                currentWord = input.next();

                Vector<Card_Model> humanHandTempCards = new Vector<Card_Model>();
                // Getting the human score
                if(currentWord.equals("Hand:")) {
                    currentWord = input.next();
                    while(currentWord.length() == 2) {
                        loadGameHumanHand.add(new Card_Model(currentWord));
                        Log.d("HumanHand", tempCard.GetCard());
                        currentWord = input.next();
                    }
                }

                // Getting the human pile
                if(currentWord.equals("Pile:")) {
                    currentWord = input.next();
                    while(currentWord.length() == 2) {
                        tempCard.SetCard(currentWord);
                        Log.d("HumanPile", currentWord);
                        loadGameHumanPile.add(new Card_Model(currentWord));
                        currentWord = input.next();
                    }
                }

                Log.d("RightBeforeTable", currentWord);

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
                            loadGameTable.add(new Card_Model(currentWord));
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
                                loadGameBuildCards.add(new Card_Model(buildString));
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

                    Log.d("Current", currentWord);
                    loadGameLastCapture = currentWord;
                }

                currentWord = input.next();
                Log.d("Deck?", currentWord);
                //currentWord = input.next();

                Log.d("CurrentFirst", currentWord);
                if(currentWord.equals("Deck:")) {
                    currentWord = input.next();

                    Log.d("CurrentWord", currentWord);
                    if(!currentWord.equals("Next")) {
                        while(currentWord.length() == 2) {
                            tempCard.SetCard(currentWord);
                            loadGameDeck.add(new Card_Model(currentWord));
                            currentWord = input.next();
                        }
                    }

                    currentWord = input.next();
                }

                for(int i = 0; i < loadGameDeck.size(); i++) {
                    Log.d("Deck", loadGameDeck.get(i).GetCard());
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
    }

    /** *********************************************************************
     Function Name: GetLoadGameRound
     Purpose: To retrieve the loaded game round
     Parameters: None
     Return Value:
     @return int
     Local Variables: None
     Algorithm:
     1) Return the loadGameRound variable
     Assistance Received: none
     ********************************************************************* */
    int GetLoadGameRound() {
        return loadGameRound;
    }

    /** *********************************************************************
     Function Name: GetLoadGameComputerScore
     Purpose: To retrieve the loaded game computer score
     Parameters: None
     Return Value:
     @return int
     Local Variables: None
     Algorithm:
     1) Return the loadGameComputerScore variable
     Assistance Received: none
     ********************************************************************* */
    int GetLoadGameComputerScore() {
        return loadGameComputerScore;
    }

    /** *********************************************************************
     Function Name: GetLoadGameComputerHand
     Purpose: To retrieve the loaded game computer hand
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the loadGameComputerHand variable
     Assistance Received: none
     ********************************************************************* */
    Vector<Card_Model> GetLoadGameComputerHand() {
        return loadGameComputerHand;
    }

    /** *********************************************************************
     Function Name: GetLoadGameComputerPile
     Purpose: To retrieve the loaded game computer pile
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the loadGameComputerPile variable
     Assistance Received: none
     ********************************************************************* */
    Vector<Card_Model> GetLoadGameComputerPile() {
        return loadGameComputerPile;
    }

    /** *********************************************************************
     Function Name: GetLoadGameHumanScore
     Purpose: To retrieve the loaded game human score
     Parameters: None
     Return Value:
     @return int
     Local Variables: None
     Algorithm:
     1) Return the loadGameHumanScore variable
     Assistance Received: none
     ********************************************************************* */
    int GetLoadGameHumanScore() {
        return loadGameHumanScore;
    }

    /** *********************************************************************
     Function Name: GetLoadGameHumanHand
     Purpose: To retrieve the loaded game human hand
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the loadGameHumanHand variable
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetLoadGameHumanHand() {
        return loadGameHumanHand;
    }

    /** *********************************************************************
     Function Name: GetLoadGameHumanPile
     Purpose: To retrieve the loaded game human pile
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the loadGameHumanPile variable
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetLoadGameHumanPile() {
        return loadGameHumanPile;
    }

    /** *********************************************************************
     Function Name: GetLoadGameTable
     Purpose: To retrieve the loaded game table
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the loadGameTable variable
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetLoadGameTable() {
        return loadGameTable;
    }

    /** *********************************************************************
     Function Name: GetLoadGameBuildCards
     Purpose: To retrieve the loaded game build cards
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the loadGameBuildCards variable
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetLoadGameBuildCards() {
        return loadGameBuildCards;
    }

    /** *********************************************************************
     Function Name: GetLoadGameBuilds
     Purpose: To retrieve the loaded game builds
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the loadGameComputerBuilds variable
     Assistance Received: none
      ********************************************************************* */
    Vector<Build_Model> GetLoadGameBuilds() {
        return loadGameBuilds;
    }

    /** *********************************************************************
     Function Name: GetLoadGameLastCapture
     Purpose: To retrieve the loaded game last capture
     Parameters: None
     Return Value:
     @return String
     Local Variables: None
     Algorithm:
     1) Return the loadGameLastCapture variable
     Assistance Received: none
      ********************************************************************* */
    String GetLoadGameLastCapture() {
        return loadGameLastCapture;
    }

    /** *********************************************************************
     Function Name: GetLoadGameDeck
     Purpose: To retrieve the loaded game deck
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the loadGameDeck variable
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetLoadGameDeck() {
        return loadGameDeck;
    }

    /** *********************************************************************
     Function Name: GetLoadGameNextPlayer
     Purpose: To retrieve the loaded game next player
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the loadGameNectPlayer variable
     Assistance Received: none
      ********************************************************************* */
    String GetLoadGameNextPlayer() {
        return loadGameNextPlayer;
    }

    /** *********************************************************************
     Function Name: GetFileToLoadFrom
     Purpose: To retrieve the file to load from
     Parameters: None
     Return Value:
     @return String
     Local Variables: None
     Algorithm:
     1) Return the fileToLoadFrom variable
     Assistance Received: none
      ********************************************************************* */
    String GetFileToLoadFrom() {
        return fileToLoadFrom;
    }

    /** *********************************************************************
     Function Name: SetFileToLoadFrom
     Purpose: To set the fileToLoadFrom
     Parameters:
     @param file, String
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) To set the fileToLoadFrom variable to what was passed in
     Assistance Received: none
      ********************************************************************* */
    void SetFileToLoadFrom(String file) {
        fileToLoadFrom = file;
    }


}
