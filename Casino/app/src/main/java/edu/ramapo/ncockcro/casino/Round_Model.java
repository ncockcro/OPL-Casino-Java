package edu.ramapo.ncockcro.casino;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class Round_Model {

    // Private variables for the Round class
    private Vector<Card_Model> computerPile = new Vector<Card_Model>();
    private int humanScore;
    private Vector<Card_Model> humanPile = new Vector<Card_Model>();
    private Vector<Card_Model> table = new Vector<Card_Model>();
    private Vector<Card_Model> buildCards = new Vector<Card_Model>();
    private Vector<Build_Model> builds = new Vector<Build_Model>();
    private Vector<Card_Model> deck = new Vector<Card_Model>();
    private String nextPlayer;
    private String lastCapture;
    private int currentPlayer = 1;
    private Deck_Model deckOfCards = new Deck_Model();
    private Human_Model p1 = new Human_Model();
    private Computer_Model p2 = new Computer_Model();
    private Vector<Player_Model> player = new Vector<Player_Model>();
    private Card_Model playerHandBuildCard;
    private Vector<Card_Model> playerTableBuildCards = new Vector<Card_Model>();
    private Vector<Build_Model> tableBuilds = new Vector<Build_Model>();
    private int buildCounter;
    private Card_Model playerHandCaptureCard;
    private Vector<Card_Model> playerTableCaptureCards;
    private Card_Model playerHandTrailCard;
    private int currentRound;
    private int humanPoints;
    private int computerPoints;
    private boolean loadGame;
    private Round_View roundView;
    private String errorReason;

    /** *********************************************************************
     Function Name: Round_Model
     Purpose: To initialize the players and built counter
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Intialize the players and buildCounter
     Assistance Received: none
      ********************************************************************* */
    Round_Model() {
        player.add(p1);
        player.add(p2);

        buildCounter = 0;
        errorReason = "None";

    }

    /** *********************************************************************
     Function Name: SetRoundInfo
     Purpose: To set information outside of the round class to be used when saving the game
     Parameters:
     @param round int, the current round of the tournament
     @param humanScore, int, the current score of the human
     @param computerScore, int, the currentScore of the computer
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) If the round is greater than 0, set the round
     2) If the scores are greater or equal to zero, set the scores
     Assistance Received: none
      ********************************************************************* */
    private void SetRoundInfo(int round, int humanScore, int computerScore) {

        // So long as it is not a negative round or 0, set the currentRound to be that round
        if(round > 0) {
            currentRound = round;
        }
        else {
            Log.d("MyError", "Error in setting the round in the round class");
        }

        // So long as the human and computer scores are 0 or greater, set them
        if(humanScore >= 0 && computerScore >= 0) {
            humanPoints = humanScore;
            computerPoints = computerScore;
        }
        else {
            Log.d("MyError", "Error in setting the scores in the round class");
        }
    }

    /** *********************************************************************
     Function Name: DealCardsToPlayer
     Purpose: To deal four cards to the human, four cards to the computer, and remove the cards from the deck
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Deal four cards to the human
     2) Remove four cards from the deck
     3) Deal four cards to the computer
     4) Remove four cards from the deck
     Assistance Received: none
      ********************************************************************* */
    void DealCardsToPlayer() {

        player.get(0).SetHand(deckOfCards.GetDeck());
        deckOfCards.RemoveFourCards();

        player.get(1).SetHand(deckOfCards.GetDeck());
        deckOfCards.RemoveFourCards();

    }

    /** *********************************************************************
     Function Name: DealCardsToTable
     Purpose: To deal cards to the table at the beginning of a round
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Add the four cards to the table
     2) Remove four cards from the table
     Assistance Received: none
      ********************************************************************* */
    void DealCardsToTable() {

        SetTable(deckOfCards.GetDeck());

        deckOfCards.RemoveFourCards();
    }

    /** *********************************************************************
     Function Name: SetTable
     Purpose: To set four cards to the table at the start of a round
     Parameters:
     @param passedFourCards Vector<Card_Model>, four cards to be set to the table
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Add the four cards to the table
     Assistance Received: none
      ********************************************************************* */
    void SetTable(Vector<Card_Model> passedFourCards) {

        // Add four cards to the table
        table.add(passedFourCards.get(0));
        table.add(passedFourCards.get(1));
        table.add(passedFourCards.get(2));
        table.add(passedFourCards.get(3));

    }

    /** *********************************************************************
     Function Name: GetHand
     Purpose: To retrieve the hand of the player passed in
     Parameters:
     @param passedPlayer int, the integer representing a player
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the hand of the player passed in
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetHand(int passedPlayer) {
        return player.get(passedPlayer).hand;
    }

    /** *********************************************************************
     Function Name: GetTable
     Purpose: To retrieve the table
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the table
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetTable() {
        return table;
    }

    /** *********************************************************************
     Function Name: GetDeck
     Purpose: To retrieve the current deck for the round
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the deck for the round
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetDeck() {
        return deckOfCards.GetDeck();
    }

    /** *********************************************************************
    Function Name: RemoveTableCards
    Purpose: Remove a series of cards that are passed in from the table
    Parameters:
    @param cards, a vector of cards passed by value. It holds the cards to be removed
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Iterate through the table and the cards to be removed...
    2) If the card to be removed matches with the one from the table, it will be erased and the vector will shift everything over
    Assistance Received: none
    ********************************************************************* */
    void RemoveTableCards(Vector<Card_Model> cards) {

        // First go through the table vector, then through the cards vector that was passed in...
        for(int i = 0; i < cards.size(); i++) {
            for(int j = 0; j < table.size(); j++) {

                // If there is a match, erase the card from the vector
                if(cards.get(i).GetCard().equals(table.get(j).GetCard())) {
                    table.remove(j);
                }
            }
        }
    }

    /** *********************************************************************
    Function Name: CheckBuild
    Purpose: To check if the user is able to make a build based on the cards they entered
    Parameters: None
    Return Value:
     @return boolean, Whether the player was able to build or not, a boolean value
    Local Variables:
    buildSize, an integer used for holding how many cards the user wants to build with
    count, an integer used for making sure the cards the user entered are on the table
    addExistingBuildSuccessful, a boolean for seeing if the player can add to a build or not
    Algorithm:
    1) Get the player's hand card used for the build and the cards from the table they want to build with
    2) Go through the table cards and make sure the cards they entered are on the table
    3) Have an if statement to check and make sure that if the cards add up to be able to make a build
    4) If the cards are able to make a build, call another function to handle removing cards from the
    player's hand and table and add them to the player's pile
    Assistance Received: none
    ********************************************************************* */
    boolean CheckBuild() {

        // Getting the player card that they want to put on the build
        playerHandBuildCard = player.get(currentPlayer).GetPlayerCard();

        // Getting the card on the table that the player wants to use for a build
        playerTableBuildCards = player.get(currentPlayer).GetBuildCards();

        int buildSize = playerTableBuildCards.size();
        int count = 0;
        boolean addExistingBuildSuccessful = false;

        // If the player is creating a new build...
        if(Character.toLowerCase(player.get(currentPlayer).GetNewOrExistingBuild()) == 'n') {

            if(playerTableBuildCards.size() < 1) {
                errorReason = "No cards selected from a build.";
                return false;
            }

            // This is checking to make sure that the cards the user entered in to make a build are actually on the table
            for(int i = 0; i < table.size(); i++) {
                for(int j = 0; j < playerTableBuildCards.size(); j++) {
                    if(table.get(i).GetCard().equals(playerTableBuildCards.get(j).GetCard())) {
                        count++;
                    }
                }
            }

            // If one of the cards the player entered did not match any on the table, they entered a wrong card
            if(count != buildSize) {
                errorReason = "You can not make a build with those cards.";
                return false;
            }

            // If the cards are suitable for a build , then the build will be created
            if(CheckBuildNumbers(playerHandBuildCard, playerTableBuildCards)) {
                CreatePlayerBuild();
            }
            else {
                errorReason = "You can not make a build with the cards you tried using.";
                return false;
            }
            return true;
        }
        // If the player wants to add to an existing build, then we will go through each build to find which one
        // they want to add to and if it is possible
        else if(Character.toLowerCase(player.get(currentPlayer).GetNewOrExistingBuild()) == 'e') {

            for(int i = 0; i < tableBuilds.size(); i++) {

                try {
                    if (playerHandBuildCard.GetCard().equals(tableBuilds.get(i).GetCaptureCardOfBuild().GetCard())) {
                        errorReason = "You can not add to that existing build because the card you want to add is needed for another build.";
                        return false;
                    }
                }
                catch (Exception e) {
                    Log.d("MyError", "Error, tried to get the capture card of a build but it did not have one.");
                }
            }
            int valueOfBuild = 0;

            for(int i = 0; i < tableBuilds.size(); i++) {
                valueOfBuild = tableBuilds.get(i).GetCardValueOfBuild();

                // If this function that is called returns true, then it was successful in validating the new build with the
                // card added and created the new build already so all that is left to do is return true
                // Here, we are also passing into the function the card to be added, the existing build, the current player,
                // and the current player's hand
                if(tableBuilds.get(i).CheckAndAddCardInBuild(playerHandBuildCard, player.get(currentPlayer).GetExistingBuildCard(),
                currentPlayer, player.get(currentPlayer).GetHand())) {

                    // Removing the card the player added from the player's hand
                    //player.get(currentPlayer).SetPrintTableBuildCards(tableBuilds.get(i).GetBuildOfCards());
                    player.get(currentPlayer).RemoveCard(playerHandBuildCard);
                    addExistingBuildSuccessful = true;

                    // After adding to a build, we must remove the build card from the other player
                    if(currentPlayer == 0) {
                        player.get(1).RemoveFromPlayerBuildCards(valueOfBuild);
                    }
                    else {
                        player.get(0).RemoveFromPlayerBuildCards(valueOfBuild);
                    }
                }
            }

            // If addExistingBuildSuccessful is true, then that means the card was added to the build successfully
            if(addExistingBuildSuccessful) {
                return true;
            }

            // Otherwise, they were not able to add the card to the build they specified
            else {
                errorReason = "There are no builds you can add with those cards.";
                return false;
            }
        }

        // Error handling in case something really got messed up and the program can't determine
        // if the user wants a new or existing build
        else {
            Log.d("MyError", "Error, don't know if it is a new or existing build in round class.");
            return false;
        }
    }

    /** *********************************************************************
     Function Name: CheckBuildNumbers
     Purpose: To figure out if the cards that a player wants to use to build adds up to something in their deck and
     if those cards they chose to build with are on the table.
     Parameters:
     @param playerCard, a card object, the card the player wants to build with
     @param playerBuildCards, a vector of cards, card(s) the player wants to build with from the table
     Return Value:
     @return Boolean
     Local Variables:
     aceAs1, an int for counting cards when ace is 1
     aceAs14, an int for counts cards when ace is 14
     playerHand, a vector of cards which holds the current cards in a players hand
     hasRightCards, a boolean which is true if everything is valid, false otherwise
     Algorithm:
     Still working on!
     Assistance Received: none
     ********************************************************************* */
    boolean CheckBuildNumbers(Card_Model playerCard, Vector<Card_Model> playerBuildCards) {

        // Local variables
        int aceAs1 = 0;
        int aceAs14 = 0;
        Vector<Card_Model> playerHand = player.get(currentPlayer).GetHand();
        boolean hasRightCards = false;
        Vector<Card_Model> tempPlayerBuildCards = new Vector<Card_Model>();

        playerBuildCards.add(playerCard);


        // Iterate through the cards the player wants to build with and count their values
        for(int i = 0; i < playerBuildCards.size(); i++) {

            // If the card is an ace, we must increment the two different counts with 1 and 14 as the ace card
            if(playerBuildCards.get(i).GetNumber() == 'A') {
                aceAs1++;
                aceAs14 += 14;
            }

            // Otherwise, just increment with whatever the number is
            else {
                aceAs1 += player.get(currentPlayer).CardNumber(playerBuildCards.get(i).GetNumber());
                aceAs14 += player.get(currentPlayer).CardNumber(playerBuildCards.get(i).GetNumber());
            }
        }

        // If the player has a card that equals the total value of the build, then return true
        for(int i = 0; i < playerHand.size(); i++) {

            boolean skipBuildCard = false;

            if(player.get(currentPlayer).CardNumber(playerHand.get(i).GetNumber()) == aceAs1 ||
                    player.get(currentPlayer).CardNumber(playerHand.get(i).GetNumber()) == aceAs14) {


                // If there is a build on the table that needs that capture card, return false since
                // they cant make a new build with that card
                for(int j = 0; j < tableBuilds.size(); j++) {
                    try {
                        if (playerHand.get(i).GetCard().equals(tableBuilds.get(j).GetCaptureCardOfBuild().GetCard())) {

                            skipBuildCard = true;
                        }
                    }
                    catch (Exception e) {
                        Log.d("MyError", "Failed because there was no capture card of the build.");
                    }
                }

                if(skipBuildCard) {
                    continue;
                }

                hasRightCards = true;
                player.get(currentPlayer).AddToPlayerBuildCards(playerHand.get(i));
                playerTableBuildCards.remove(playerTableBuildCards.size() - 1);
                return hasRightCards;
            }

            // If the player is creating a build with an ace as the card they will use to capture it with later...
            if(playerHand.get(i).GetNumber() == 'A' && aceAs14 == 14 || aceAs1 == 14) {

                // If the card is not an ace card, return false because they can't make a build with that
                if(playerHand.get(i).GetNumber() != 'A') {
                    return false;
                }

                // If there is a build on the table that needs that capture card, return false since
                // they cant make a new build with that card
                for(int j = 0; j < tableBuilds.size(); j++) {
                    try {
                        if (playerHand.get(i).GetCard().equals(tableBuilds.get(j).GetCaptureCardOfBuild().GetCard())) {
                            return false;
                        }
                    }
                    catch(Exception e) {
                        Log.d("MyError", "Failed because there was no capture card of the build.");
                    }
                }

                hasRightCards = true;
                player.get(currentPlayer).AddToPlayerBuildCards(playerHand.get(i));
                playerTableBuildCards.remove(playerTableBuildCards.size() - 1);
                return hasRightCards;
            }
        }

        return hasRightCards;
    }

    /** *********************************************************************
    Function Name: CreatePlayerBuild
    Purpose: To remove the cards from the table and player and incorporate them into a build object
    Parameters: None
    Return Value: Void
    Local Variables:
     playersBuildCards, a Vector<Card_Model>, for holding the builds cards part of a build
     lastAddedCard, a char, the number of the last added card
     handCards, a Vector<Card_Model>, the player's hand cards
     countOfBuild, an int, holds the number value of a build
    Algorithm:
    1) Set the owner of the build based on the player currently playing
    2) Add the cards to the build object and remove them from the table and player hand
    Assistance Received: none
    ********************************************************************* */
    void CreatePlayerBuild() {

        Vector<Card_Model> playersBuildCards = player.get(currentPlayer).GetPlayerBuildCards();
        Vector<Card_Model> handCards = new Vector<Card_Model>();

        // Initialize a build
        tableBuilds.add(new Build_Model());

        // Setting the owner of a particular build
        if(currentPlayer == 0) {
            tableBuilds.get(buildCounter).SetOwner(0);
        }
        else {
            tableBuilds.get(buildCounter).SetOwner(1);
        }

        // This is used for counting up the total value of a build and setting it right after to the specific build
        int countOfBuild = 0;
        for(int i = 0; i < playerTableBuildCards.size(); i++) {
            countOfBuild += player.get(currentPlayer).CardNumber(playerTableBuildCards.get(i).GetNumber());
        }

        countOfBuild += player.get(currentPlayer).CardNumber(playerHandBuildCard.GetNumber());
        tableBuilds.get(buildCounter).SetValueOfBuild(countOfBuild);

        // This is for setting the card that is needed for capturing the build
        handCards = player.get(currentPlayer).GetHand();
        for(int i = 0; i < handCards.size(); i++) {
            if(player.get(currentPlayer).CardNumber(handCards.get(i).GetNumber()) == countOfBuild) {
                tableBuilds.get(buildCounter).SetCaptureCardOfBuild(handCards.get(i));
                break;
            }
        }

        // Saving the build cards to be outputted after the move is done
        player.get(currentPlayer).SetPrintTableBuildCards(playerTableBuildCards);

        // Adding the card from the player's hand onto the build that way I can send all the cards to be added to build object
        playerTableBuildCards.add(playerHandBuildCard);

        // With all the cards being used for a build, we push them onto the vector of builds
        tableBuilds.get(buildCounter).SetBuildOfCards(playerTableBuildCards);

        // Then remove the cards from the player's hand and the table since those cards are now part of a build
        RemoveTableCards(playerTableBuildCards);
        player.get(currentPlayer).RemoveCard(playerHandBuildCard);

        buildCounter++;
        playerTableBuildCards.clear();

    }


    /** *********************************************************************
    Function Name: CheckCapture
    Purpose: To check if the player is elegible to make a capture based on what they entered
    Parameters: None
    Return Value:
    @return boolean, Whether the player can make a capture or not, a boolean value
    Local Variables: None
    Algorithm:
    1) Get the card the player wants to capture with and the player's hand
    2) Check and see if the player has any cards on the table to capture or any sets
    Assistance Received: none
    ********************************************************************* */
    boolean CheckCapture() {

        // Getting the player card that they want to put on the build and the current player's hand
        playerHandCaptureCard = player.get(currentPlayer).GetPlayerCard();
        Vector<Card_Model> playerHand = player.get(currentPlayer).GetHand();
        Vector<Card_Model> tableCardsForCapturing = player.get(currentPlayer).GetTableCardsToBeCaptured();

        // Local variables for capturing
        int number = player.get(currentPlayer).CardNumber(playerHandCaptureCard.GetNumber());
        Vector<Card_Model> pile = new Vector<Card_Model>();
        Vector<Card_Model> removeTableCards = new Vector<Card_Model>();
        pile.add(playerHandCaptureCard);
        boolean canCapture = false;
        boolean looseCardsCapture = false;

        // Local variables for sets
        Vector<Card_Model> setCards = new Vector<Card_Model>();
        int count = 0;
        int aceAs1Count = 0;
        int aceAs14Count = 0;

        // If the player said they wanted to make a set, then we will check those cards with the table cards first
        // to make sure they are on the table and add up to the capture card
        if(player.get(currentPlayer).GetPlayerWantSet() == 'y') {
            Vector<Set_Model> playerSets = new Vector<Set_Model>();
            playerSets = player.get(currentPlayer).GetPlayerMultipleSetCards();
            Vector<Card_Model> cardsOfSet = new Vector<Card_Model>();

            // Cycling through all of the sets that the player wants to capture
            for(int i = 0; i < playerSets.size(); i++) {

                cardsOfSet = playerSets.get(i).GetCardOfSet();


                // For each set, we must check and make sure that the cards are actually on the table
                for(int j = 0; j < table.size(); j++) {
                    for(int k = 0; k < cardsOfSet.size(); k++) {

                        // If the card is on the table, push it onto the pile vector to be added later
                        if(table.get(j).GetCard().equals(cardsOfSet.get(k).GetCard())) {

                            pile.add(table.get(j));
                            removeTableCards.add(table.get(j));

                            // Special handling if the card is an ace or not
                            if(cardsOfSet.get(k).GetNumber() == 'A') {
                                aceAs1Count += player.get(currentPlayer).CardNumber(cardsOfSet.get(k).GetNumber());
                                aceAs14Count += 14;
                            }
                            else {
                                aceAs1Count += player.get(currentPlayer).CardNumber(cardsOfSet.get(k).GetNumber());
                            }
                            count++;
                        }
                    }
                }

                // If the set card's numbers add up to the capture card, then they can make the set
                if(playerHandCaptureCard.GetNumber() == 'A' && aceAs1Count == 14) {
                    // Left blank intentionally so the function wouldn't return false in the else if statement
                }
                else if(aceAs1Count != player.get(currentPlayer).CardNumber(playerHandCaptureCard.GetNumber()) && aceAs14Count != player.get(currentPlayer).CardNumber(playerHandCaptureCard.GetNumber()) ) {
                    errorReason = "Card numbers did not add up to what you were capturing with. Try again.";
                    return false;
                }

                aceAs1Count = 0;
                aceAs14Count = 0;
                canCapture = true;
            }
        }

        // If the player wanted to build, then this function will check if it is possible based on
        // the information the user provided
        if(player.get(currentPlayer).GetPlayerWantBuild() == 'y') {

            for(int i = 0; i < tableCardsForCapturing.size(); i++) {
                if(player.get(currentPlayer).CardNumber(tableCardsForCapturing.get(i).GetNumber()) == number) {
                    pile.add(tableCardsForCapturing.get(i));
                    removeTableCards.add(tableCardsForCapturing.get(i));
                    canCapture = true;
                    looseCardsCapture = true;
                }
                else {
                    errorReason = "The loose cards you chose are not the same value as the card used for build.";
                    return false;
                }
            }

            if(CheckIfPlayerCanCaptureBuild(playerHandCaptureCard, playerHand)) {

                canCapture = true;

                // Decrement the build counter since the player is capturing the build
                buildCounter--;
                // Resetting this variable so the player doesn't always want a build
                player.get(currentPlayer).SetPlayerWantBuild('n');

                // Removing the first index which is the player's capture card since it was already
                // removed from capturing the build
                pile.remove(0);
                player.get(currentPlayer).RemoveCard(playerHandCaptureCard);
                RemoveTableCards(removeTableCards);

                player.get(currentPlayer).AddToPile(pile);
            }
        }
        else {
            boolean partOfBuild = false;

            // Checking to see if there are any cards on the table that match the card the player wants to capture with the same value
            for(int i = 0; i < tableCardsForCapturing.size(); i++) {



                if(tableBuilds.size() == 0) {
                    if(player.get(currentPlayer).CardNumber(tableCardsForCapturing.get(i).GetNumber()) == number) {
                        pile.add(tableCardsForCapturing.get(i));
                        removeTableCards.add(tableCardsForCapturing.get(i));
                        canCapture = true;
                        looseCardsCapture = true;
                    }
                    else {
                        errorReason = "One of the cards selected can not be captured.";
                        return false;
                    }
                }

                // Checking to make sure the card the player is capturing with is not needed for a build
                for(int j = 0; j < tableBuilds.size(); j++) {

                    // If the card the player is capturing with equals the build and they are the owner
                    if(tableBuilds.get(j).GetCardValueOfBuild() == number && tableBuilds.get(j).GetOwner() == currentPlayer) {
                        partOfBuild = true;
                        continue;
                    }

                    // Same thing but if they are using an ace
                    if(number == 1 && tableBuilds.get(j).GetCardValueOfBuild() == 14 && tableBuilds.get(j).GetOwner() == currentPlayer) {
                        partOfBuild = true;
                        continue;
                    }

                    if(player.get(currentPlayer).CardNumber(tableCardsForCapturing.get(i).GetNumber()) == number) {
                        pile.add(tableCardsForCapturing.get(i));
                        removeTableCards.add(tableCardsForCapturing.get(i));
                        canCapture = true;
                        looseCardsCapture = true;
                    }
                }
            }

            if(partOfBuild) {
                errorReason = "You can not capture with that card because it is needed for a build.";
                return false;
            }

            if(looseCardsCapture) {
                player.get(currentPlayer).SetPrintTableCaptureCards(removeTableCards);
            }

            // If everything is correct, add the cards and remove them properly
            if(canCapture) {

                if(player.get(currentPlayer).GetPlayerWantBuild() != 'y' && tableCardsForCapturing.size() == 0) {
                    // Checking to make sure the card the player is capturing with is not needed for a build
                    for(int j = 0; j < tableBuilds.size(); j++) {

                        // If the card the player is capturing with equals the build and they are the owner
                        if (tableBuilds.get(j).GetCardValueOfBuild() == number && tableBuilds.get(j).GetOwner() == currentPlayer) {
                            errorReason = "Can not capture set because the card is needed for a build";
                            return false;
                        }

                        // Same thing but if they are using an ace
                        if (number == 1 && tableBuilds.get(j).GetCardValueOfBuild() == 14 && tableBuilds.get(j).GetOwner() == currentPlayer) {
                            errorReason = "Can not capture set because the card is needed for a build";
                            return false;
                        }
                    }
                }


                // Checking to make sure there are no duplicated cards that are about to be added to the player's pile
                int dupeCard = 0;
                Vector<Card_Model> duplicatedCards = new Vector<Card_Model>();
                boolean skipCard = false;

                for(int i = 0; i < pile.size(); i++) {
                    for(int j = 0; j < pile.size(); j++) {
                        if(pile.get(i).GetCard().equals(pile.get(j).GetCard())) {
                            dupeCard++;
                        }

                        if(dupeCard == 2) {
                            for(int k = 0; k < duplicatedCards.size(); k++) {
                                if(pile.get(j).GetCard().equals(duplicatedCards.get(k).GetCard())) {
                                    skipCard = true;
                                }
                            }

                            if(!skipCard) {
                                duplicatedCards.add(pile.get(j));
                                break;
                            }
                            skipCard = false;
                        }
                    }
                    dupeCard = 0;
                }

                for(int i = 0; i < duplicatedCards.size(); i++) {
                    for(int j = 0; j < pile.size(); j++) {
                        if(pile.get(j).GetCard().equals(duplicatedCards.get(i).GetCard())) {
                            pile.remove(j);
                        }
                    }
                }


                player.get(currentPlayer).RemoveCard(playerHandCaptureCard);
                RemoveTableCards(removeTableCards);

                player.get(currentPlayer).AddToPile(pile);

            }
        }

        // Set lastCapture to whoever the current player is
        if(currentPlayer == 0) {
            lastCapture = "Human";
        }
        else {
            lastCapture = "Computer";
        }

        if(!canCapture) {
            errorReason = "You can not capture any cards on the table with that capture card.";
            return false;
        }

        return canCapture;
    }

    /** *********************************************************************
    Function Name: CheckIfPlayerCanCaptureBuild
    Purpose: To check if the player is elegible to capture a build
    Parameters:
    @param playerHandCaptureCard, a card passed by value. It holds the card the player wants to capture with
    @param playerHand, a vector of cards. It refers to the player's hand
    Return Value:
    @return boolean, Whether the player can make a capture or not, a boolean value
    Local Variables:
    existingBuildCard, a card used to store the card the player wants to add to a build
    tempPile, a vector of cards used to add the cards to a player's pile
    Algorithm:
    1) Get the card the player wants to capture with and the player's hand
    2) Check and see if the player has any cards on the table to capture or any sets
    Assistance Received: none
    ********************************************************************* */
    boolean CheckIfPlayerCanCaptureBuild(Card_Model playerHandCaptureCard, Vector<Card_Model> playerHand) {

        Card_Model existingBuildCard = player.get(currentPlayer).GetExistingBuildCard();
        Vector<Card_Model> tempPile = new Vector<Card_Model>();

        // Iterate through each of the builds on the table and check if any of them can be captured
        // based on the specifications the user entered in
        for(int i = 0; i < tableBuilds.size(); i++) {

            // If there was a build that can be successfully captured after checking if possible, move the cards
            // from the build element into player's pile, move the card used for capture to player pile, and
            // erase this build
            if(tableBuilds.get(i).CanCaptureBuildOfCards(playerHandCaptureCard, existingBuildCard, playerHand)) {
                tempPile = tableBuilds.get(i).GetBuildOfCards();
                tempPile.add(playerHandCaptureCard);
                player.get(currentPlayer).AddToPile(tempPile);

                player.get(currentPlayer).RemovePlayerBuildCard(playerHandCaptureCard);

                // If the player that captured the build is not the current owner, then we need to cycle through the owner of the build's
                // card's and remove it from their build cards that way they can discard that card again
                if(tableBuilds.get(i).GetOwner() != currentPlayer) {
                    int buildOwner = tableBuilds.get(i).GetOwner();
                    Vector<Card_Model> buildOwnerHand = new Vector<Card_Model>();
                    buildOwnerHand = player.get(buildOwner).GetHand();

                    for(int j = 0; j < buildOwnerHand.size(); j++) {
                        player.get(buildOwner).RemovePlayerBuildCard(buildOwnerHand.get(j));
                    }
                }

                tableBuilds.remove(tableBuilds.get(i));
                player.get(currentPlayer).RemoveCard(playerHandCaptureCard);
                return true;
            }
        }

        return false;
    }
    /** *********************************************************************
     Function Name: CheckTrail
     Purpose: Given a card, check to see if the player can trail with it
     Parameters:
     @param passedCard Card_Model, the card the player wants to trail with
     Return Value:
     @return boolean, true if player can trail, false otherwise
     Local Variables: None
     Algorithm:
     1) Add the four cards to the player's hand
     Assistance Received: none
      ********************************************************************* */
    boolean CheckTrail(Card_Model passedCard) {

        Vector<Card_Model> playerHand = player.get(currentPlayer).GetHand();

        // Iterating through the cards the player can not put down because the card is needed to complete a build
        for(int i = 0; i < player.get(currentPlayer).GetPlayerBuildCards().size(); i++) {
            if(player.get(currentPlayer).GetPlayerBuildCards().get(i).GetCard().equals(passedCard.GetCard())) {
                errorReason = "You can not trail with " + passedCard.GetCard() + " because you need that card for a build.";
                return false;
            }
        }

        // Checking if the player is able to capture with a same value card
        for(int i = 0; i < table.size(); i++) {
            for(int j = 0 ; j < playerHand.size(); j++) {
                if(table.get(i).GetNumber() == playerHand.get(j).GetNumber()) {
                    errorReason = "You can not trail with " + passedCard.GetCard() + " because there is a card with the same value on the table." + table.get(i).GetCard();
                    return false;
                }
            }
        }

        int tempOwner;
        Vector<Card_Model> cardsFromBuild = new Vector<Card_Model>();
        for(int i = 0; i < tableBuilds.size(); i++) {
            tempOwner = tableBuilds.get(i).GetOwner();

            if(currentPlayer == tempOwner) {
                errorReason = "You can not trail because there is a build you can capture.";
                return false;
            }
        }

        player.get(currentPlayer).RemoveCard(passedCard);

        Log.d("TrailCard", passedCard.GetCard());
        Vector<Card_Model> trailCard = new Vector<Card_Model>();
        trailCard.add(passedCard);

        // Adding the trail card to the table
        AddCardsToTable(trailCard);
        return true;

    }

    /** *********************************************************************
    Function Name: AddCardsToTable
    Purpose: To add any cards that were passed into it to the table
    Parameters:
    @param passedCards, a vector of cards passed by value. It holds cards to be added to the table
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Iterate through the vector of cards passed in and add them to the table
    Assistance Received: none
    ********************************************************************* */
    void AddCardsToTable(Vector<Card_Model> passedCards) {

        for(int i = 0; i < passedCards.size(); i++) {
            table.add(passedCards.get(i));
        }
    }

    /* *********************************************************************
    Function Name: SwitchPlayer
    Purpose: If the player is human, switches to computer and vice versa
    Parameters: None
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) If currentPlayer is 0 (human), switch it to 1 (computer) and vice versa
    Assistance Received: none
    ********************************************************************* */
    void SwitchPlayer() {
        if(currentPlayer == 0) {
            currentPlayer = 1;
        }
        else {
            currentPlayer = 0;
        }
    }

    /** *********************************************************************
    Function Name: GiveTableCards
    Purpose: At the end of the round, the player that captured last gets the table cards
    Parameters: None
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) If the human captured last, they get the cards
    2) If the computer captured last, they get the cards
    Assistance Received: none
    ********************************************************************* */
    void GiveTableCards() {
        if(lastCapture.equals("Human")) {
            player.get(0).AddToPile(table);
        }
        else if (lastCapture.equals("Computer")) {
            player.get(1).AddToPile(table);
        }
        else {
            Log.d("MyError", "Error, there is a problem giving the cards at the end of the round.");
        }
    }

    /** *********************************************************************
    Function Name: GetErrorReason
    Purpose: To return the reason for an error
    Parameters: None
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Return the errorReason variable
    Assistance Received: none
    ********************************************************************* */
    String GetErrorReason() {
        return errorReason;
    }

    /** *********************************************************************
    Function Name: CheckIfPlayersHandEmpty
    Purpose: Checks both of the player's hands to see if they are empty
    Parameters: None
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) If the human and computer's hands are empty, return true
    2) Otherwise, return false
    Assistance Received: none
    ********************************************************************* */
    boolean CheckIfPlayersHandEmpty() {
        if(player.get(0).hand.isEmpty() && player.get(1).hand.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    /** *********************************************************************
    Function Name: CheckIfDeckEmpty
    Purpose: Checks if the deck is empty or not
    Parameters: None
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Returns true if if the deck is empty
    2) False otherwise
    Assistance Received: none
    ********************************************************************* */
    boolean CheckIfDeckEmpty() {
        if(deckOfCards.GetDeck().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    /** *********************************************************************
     Function Name: PlayerMakeMove
     Purpose: To have the current player to make a move
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Current player makes a move
     2) Check the move to make sure it is valid
     3) Then switch the player
     Assistance Received: none
      ********************************************************************* */
    void PlayerMakeMove() {
        char move;

        //player.get(currentPlayer).ClearPrintMessages();

        // Clearing the error reason from before
        errorReason = "None";
        move = player.get(currentPlayer).MakeMove(table, tableBuilds, currentPlayer);

        // Builds
        if(move == 'b') {
            if(CheckBuild()) {
                SwitchPlayer();
            }
        }

        // Captures
        else if(move == 'c') {
            if(CheckCapture()) {
                SwitchPlayer();
            }
        }

        // Trailing
        else if(move == 't') {

            if(CheckTrail(player.get(currentPlayer).GetPlayerCard())) {
                SwitchPlayer();
            }
        }
        else {
            Log.d("MyError", "Error in making a move in the round model class.");
        }

    }

    /** *********************************************************************
    Function Name: SetFirstPlayer
    Purpose: To set who is the first player at the beginning of a round
    Parameters:
    @param passedFirstPlayer, the string that is the first player
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) If the passed player is human, set the current player to 0
    2) If the passed player is computer, set the current player to 1
    3) If the passed player is anything else, output an error
    Assistance Received: none
    ********************************************************************* */
    void SetFirstPlayer(String passedFirstPlayer) {

        if(passedFirstPlayer.equals("Human")) {
            currentPlayer = 0;
        }
        else if(passedFirstPlayer.equals("Computer")) {
            currentPlayer = 1;
        }
        else {
            Log.d("MyError", "Error in setting the first player in round model.");
        }
    }

    /** *********************************************************************
    Function Name: GetCurrentPlayer
    Purpose: To retrieve the current player
    Parameters: None
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Return the currentPlayer variable
    Assistance Received: none
    ********************************************************************* */
    Integer GetCurrentPlayer() {
        return currentPlayer;
    }

    /** *********************************************************************
    Function Name: SetPlayerMove
    Purpose: To set a player's move choice
    Parameters:
    @param passedMove, a char which has the player's move
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) If the player move is 'b', 'c', or 't', set the move
    2) Otherwise, output an error
    Assistance Received: none
    ********************************************************************* */
    void SetPlayerMove(char passedMove) {
        if(passedMove == 'b' || passedMove == 'c' || passedMove == 't') {
            player.get(currentPlayer).SetPlayerMove(passedMove);
        }
        else {
            Log.d("MyError", "Error in setting the player move in the round model.");
        }
    }

    /** *********************************************************************
    Function Name: SetPlayerCard
    Purpose: Set the player card for the current player
    Parameters:
    @param passedPlayerCard, a card object which has the card the player wants to use from their hand
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Set the player card for the current player
    Assistance Received: none
    ********************************************************************* */
    void SetPlayerCard(Card_Model passedPlayerCard) {
        player.get(currentPlayer).SetPlayerCard(passedPlayerCard);
    }

    /** *********************************************************************
     Function Name: SetTableCardsToBeCaptured
     Purpose: To pass the table cards to be captured to the player class
     Parameters:
     @param passedCards Vector of card_model, holds the cards to be captured
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Send the passed cards to the player class
     Assistance Received: none
      ********************************************************************* */
    void SetTableCardsToBeCaptured(Vector<Card_Model> passedCards) {
        player.get(currentPlayer).SetTableCardToBeCaptured(passedCards);
    }

    /** *********************************************************************
     Function Name: GetPlayerPile
     Purpose: To get the player's pile, whoever was passed in
     Parameters:
     @param passedPlayer, an integer, the player
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) To retrieve a player's pile
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetPlayerPile(int passedPlayer) {
        return player.get(passedPlayer).GetPile();
    }

    /** *********************************************************************
     Function Name: ClearErrorReason
     Purpose: To clear the error reason to "None"
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Set the errorReason to "None"
     Assistance Received: none
     ********************************************************************* */
    void ClearErrorReason() {
        errorReason = "None";
    }

    /** *********************************************************************
     Function Name: SetPlayerModelWantSet
     Purpose: To set if the player wants a set or not
     Parameters:
     @param choice, a char, either 'y' or 'n' for if the player wants a set
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Send the player's choice to the player class
     Assistance Received: none
     ********************************************************************* */
    void SetPlayerModelWantSet(char choice) {
        player.get(currentPlayer).SetPlayerWantSet(choice);
    }

    /** *********************************************************************
     Function Name: GetPlayerModelWantSet
     Purpose: Retrieve if the player wants a set or not
     Parameters: None
     Return Value:
     @return char, the player's choice for wanting a set
     Local Variables: None
     Algorithm:
     1) Retrieve the playerWantSet variable from the player class
     Assistance Received: none
     ********************************************************************* */
    char GetPlayerModelWantSet() {
        return player.get(currentPlayer).GetPlayerWantSet();
    }

    /** *********************************************************************
     Function Name: SetPlayerModelAddSet
     Purpose: To add a set object to the vector of set ojects in the player class
     Parameters:
     @param set, Set_Model oject, a set of two cards
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Pass the set object to the player class to be added to the player class
     Assistance Received: none
     ********************************************************************* */
    void SetPlayerModelAddSet(Set_Model set) {
        player.get(currentPlayer).AddSetToPlayer(set);
    }

    /** *********************************************************************
     Function Name: PlayerModelClearPlayerMultipleSets
     Purpose: To clear the multiple sets in the player model
     Parameters: Void
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) ClearPlayerMultipleSets in the player class
     Assistance Received: none
      ********************************************************************* */
    void PlayerModelClearPlayerMultipleSets() {
        player.get(currentPlayer).ClearPlayerMultipleSets();
    }

    /** *********************************************************************
     Function Name: SetPlayerModelWantNewOrExisting
     Purpose: To set the new or existing variable in the player class
     Parameters:
     @param choice, char
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Set the playerWantNewOrExisting variable in the player class
     Assistance Received: none
      ********************************************************************* */
    void SetPlayerModelWantNewOrExisting(char choice) {
        player.get(currentPlayer).SetPlayerWantNewOrExisting(choice);
    }

    /** *********************************************************************
     Function Name: GetPlayerModelWantNewOrExisting
     Purpose: To retrieve the newOrExisting variable from the player class
     Parameters: None
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Retrieve the newOrExisting variable from the player class
     Assistance Received: none
      ********************************************************************* */
    char GetPlayerModelWantNewOrExisting() {
        return player.get(currentPlayer).GetNewOrExistingBuild();
    }

    /** *********************************************************************
     Function Name: GetPlayerMove
     Purpose: To retrieve the playerMove variable from the class
     Parameters:= None
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Retrieve the playerMove variable from the player class
     Assistance Received: none
      ********************************************************************* */
    char GetPlayerMove() {
        return player.get(currentPlayer).GetPlayerMove();
    }

    /** *********************************************************************
     Function Name: SetPlayerModelBuildCards
     Purpose: To set the buildCards in the player class to what was passed in
     Parameters:
     @param passedBuildCards, Vector<Card_Model>
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Set the build cards in the player class with what was passed in
     Assistance Received: none
      ********************************************************************* */
    void SetPlayerModelBuildCards(Vector<Card_Model> passedBuildCards) {
        player.get(currentPlayer).SetBuildCards(passedBuildCards);
    }

    /** *********************************************************************
     Function Name: GetTableBuilds
     Purpose: To retrieve the tableBuilds to whatever is calling it
     Parameters: None
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Return the tableBuilds to whatever called it
     Assistance Received: none
      ********************************************************************* */
    Vector<Build_Model> GetTableBuilds() {
        return tableBuilds;
    }

    /** *********************************************************************
     Function Name: CheckForBuilds
     Purpose: To return true or false depending on if there are builds on the table
     Parameters: None
     Return Value:
     @return boolean
     Local Variables:None
     Algorithm:
     1) Returns true if there are builds on the table
     2) False otherwise
     Assistance Received: none
      ********************************************************************* */
    boolean CheckForBuilds() {
        if(tableBuilds.size() > 0) {
            return true;

        }
        else {
            errorReason = "There are no builds on the table to capture.";
            return false;
        }
    }

    /** *********************************************************************
     Function Name: SetPlayerModelWantBuild
     Purpose: To set the playerWantBuild variable to what was passed in for the player class
     Parameters:
     @param choice, char
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Call the function in the player class to set the choice that was passed in
     Assistance Received: none
      ********************************************************************* */
    void SetPlayerModelWantBuild(char choice) {
        player.get(currentPlayer).SetPlayerWantBuild(choice);
    }

    /** *********************************************************************
     Function Name: GetPlayerModelWantCaptureBuild
     Purpose: To retrieve the playerWantBuild variable from the player class
     Parameters: None
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Retrieve the playerWantBuild variable from the player class
     Assistance Received: none
      ********************************************************************* */
    char GetPlayerModelWantCaptureBuild() {
        return player.get(currentPlayer).GetPlayerWantBuild();
    }

    /** *********************************************************************
     Function Name: SetPlayerModelExistingBuildCard
     Purpose: To set the existingBuildCard variable in the player class to what was passed in
     Parameters:
     @param passedCard, Card_Model
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Call the function in the player class to set the existingBuildCard
     Assistance Received: none
      ********************************************************************* */
    void SetPlayerModelExistingBuildCard(Card_Model passedCard) {
        player.get(currentPlayer).SetExistingBuildCard(passedCard);
    }

    /** *********************************************************************
     Function Name: GetLastCapture
     Purpose: To get the player that captured last
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Retrieve the lastCapture variable
     Assistance Received: none
     ********************************************************************* */
    String GetLastCapture() {
        return lastCapture;
    }

    /** *********************************************************************
     Function Name: SaveGame
     Purpose: To save all of the information from the instance of the game
     Parameters:
     @param context, Context object, the current activity were in
     @param fileName, String, the name of the file were saving to
     Return Value: None
     Local Variables: None
     Algorithm:
     1) If the "save" folder doesn't exist, create it
     2) If the file the user wants to save to doesn't exist, create it
     3) Then go through and save the various information such as the player's
     hands, piles, table, ect
     Assistance Received: none
     ********************************************************************* */
    void SaveGame(Context context, String fileName) {

        //File saveFolder = new File(context.getFilesDir() + "/save");
        File saveFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "CasinoSave");
        File saveFile = new File(saveFolder.getAbsolutePath() + "/" + fileName + ".txt");

        // If the save folder doesn't exist, then make the directory
        if(!saveFolder.exists()) {
            saveFolder.mkdir();
        }

        // If the save file doesn't exist, then make the file
        if(!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            }
            catch(Exception e) {

            }
        }

        FileOutputStream fileOS = null;
        try {
            fileOS = new FileOutputStream(saveFile);

            // Write the round to the text file
            fileOS.write(("Round: " + Integer.toString(currentRound) + System.getProperty("line.separator") + System.getProperty("line.separator")).getBytes());

            // Write the computer's score, hand, and pile to the text file
            fileOS.write(("Computer: " + System.getProperty("line.separator")).getBytes());
            fileOS.write(("\t Score: " + Integer.toString(computerPoints) + System.getProperty("line.separator")).getBytes());
            fileOS.write("\t Hand: ".getBytes());

            for(int i = 0; i < player.get(1).GetHand().size(); i++) {
                fileOS.write((player.get(1).GetHand().get(i).GetCard() + " ").getBytes());
            }

            fileOS.write(System.getProperty("line.separator").getBytes());

            fileOS.write("\t Pile: ".getBytes());

            for(int i = 0; i < player.get(1).GetPile().size(); i++) {
                fileOS.write((player.get(1).GetPile().get(i).GetCard() + " ").getBytes());
            }

            fileOS.write(System.getProperty("line.separator").getBytes());
            fileOS.write(System.getProperty("line.separator").getBytes());

            // Write the human's score, hand, and pile to the text file
            fileOS.write(("Human: " + System.getProperty("line.separator")).getBytes());

            fileOS.write(("\t Score: " + Integer.toString(humanPoints) + System.getProperty("line.separator")).getBytes());

            fileOS.write("\t Hand: ".getBytes());

            for(int i = 0; i < player.get(0).GetHand().size(); i++) {
                fileOS.write((player.get(0).GetHand().get(i).GetCard() + " ").getBytes());
            }

            fileOS.write(System.getProperty("line.separator").getBytes());

            fileOS.write(("\t Pile: ").getBytes());
            for(int i = 0; i < player.get(0).GetPile().size(); i++) {
                fileOS.write(((player.get(0).GetPile().get(i).GetCard()) + " ").getBytes());
            }

            fileOS.write(System.getProperty("line.separator").getBytes());
            fileOS.write(System.getProperty("line.separator").getBytes());

            // Write the table to the text file
            fileOS.write("Table: ".getBytes());

            // Writing the builds first for the table
            for(int i = 0; i < tableBuilds.size(); i++) {
                Vector<Card_Model> tempCards = tableBuilds.get(i).GetBuildOfCards();
                fileOS.write("[ ".getBytes());
                for(int j = 0; j < tempCards.size(); j++) {
                    fileOS.write((tempCards.get(j).GetCard() + " ").getBytes());
                }
                fileOS.write("] ".getBytes());
            }

            // Then writing the plain cards to the table
            for(int i = 0; i < table.size(); i++) {
                fileOS.write((table.get(i).GetCard() + " ").getBytes());
            }

            fileOS.write(System.getProperty("line.separator").getBytes());
            fileOS.write(System.getProperty("line.separator").getBytes());

            // Then write the build owners to the text file
            if(tableBuilds.size() > 0) {
                for(int i = 0; i < tableBuilds.size(); i++) {
                    fileOS.write("Build Owner: ".getBytes());
                    Vector<Card_Model> tempBuild = tableBuilds.get(i).GetBuildOfCards();
                    fileOS.write("[ ".getBytes());

                    for(int j = 0; j < tempBuild.size(); j++) {
                        fileOS.write((tempBuild.get(j).GetCard() + " ").getBytes());
                    }
                    fileOS.write("] ".getBytes());

                    if(tableBuilds.get(i).GetOwner() == 1) {
                        fileOS.write("Computer".getBytes());
                    }
                    else if(tableBuilds.get(i).GetOwner() == 0) {
                        fileOS.write("Human".getBytes());
                    }

                    if(tableBuilds.size() > 1) {
                        fileOS.write(System.getProperty("line.separator").getBytes());
                    }
                }

                fileOS.write(System.getProperty("line.separator").getBytes());
            }

            fileOS.write(System.getProperty("line.separator").getBytes());

            // If there was no last capture yet in the game, it will default to saving
            // the last capture player to be the human
            if(lastCapture == null) {
                lastCapture = "Human";
            }

            // Write the person who captured last to the text file
            fileOS.write(("Last Capturer: " + lastCapture).getBytes());
            fileOS.write(System.getProperty("line.separator").getBytes());
            fileOS.write(System.getProperty("line.separator").getBytes());

            // Write the deck to the text file
            fileOS.write("Deck: ".getBytes());

            Vector<Card_Model> tempDeck = deckOfCards.GetDeck();
            for(int i = 0; i < deckOfCards.GetDeck().size(); i++) {
                fileOS.write((tempDeck.get(i).GetCard() + " ").getBytes());
            }

            fileOS.write(System.getProperty("line.separator").getBytes());
            fileOS.write(System.getProperty("line.separator").getBytes());

            // Write the next player to the text file
            if(currentPlayer == 1) {
                fileOS.write("Next Player: Computer".getBytes());
            }
            else if(currentPlayer == 0) {
                fileOS.write("Next Player: Human".getBytes());
            }
            else {
                Log.d("MyError", "Error in saving the next player in round model.");
            }

            fileOS.close();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /** *********************************************************************
     Function Name: SetComputerPoints
     Purpose: To set the points for the computer player to what was passed in
     Parameters:
     @param points, String the points that were passed in
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the computerPoints to what was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetComputerPoints(String points) {
        computerPoints = Integer.parseInt(points);
    }

    /** *********************************************************************
     Function Name: SetHumanPoints
     Purpose: To set the points for the human player to what was passed in
     Parameters:
     @param points, String, the points for the human player
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the human's points to what was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetHumanPoints(String points) {
        humanPoints = Integer.parseInt(points);
    }

    /** *********************************************************************
     Function Name: SetPlayerHand
     Purpose: To set the player's hand to what was passed in, used for loading in a game
     Parameters:
     @param passedPlayer, int
     @param handCards, Vector<Card_Model>
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Call the player class and set the cards to the player that was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetPlayerHand(int passedPlayer, Vector<Card_Model> handCards) {
        player.get(passedPlayer).LoadHand(handCards);
    }

    /** *********************************************************************
     Function Name: SetPlayerPile
     Purpose: To set the player's pile to what was passed in, used for loading in a game
     Parameters:
     @param passedPlayer, int
     @param pileCards, Vector<Card_Model>
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Call the player class and set the cards to the player that was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetPlayerPile(int passedPlayer, Vector<Card_Model> pileCards) {
        player.get(passedPlayer).SetPile(pileCards);
    }

    /** *********************************************************************
     Function Name: SetTableBuilds
     Purpose: To set the builds on the table to what was passed in, used when loading a game
     Parameters:
     @param builds, Vector<Build_Model>
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the tableBuilds to what was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetTableBuilds(Vector<Build_Model> builds) {
        tableBuilds = builds;
    }

    /** *********************************************************************
     Function Name: SetDeck
     Purpose: To set the deck of the round to what was passed in
     Parameters:
     @param passedDeck, DeckM=_Model
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the deck for the round to what was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetDeck(Deck_Model passedDeck) {
        deckOfCards = passedDeck;
    }

    /** *********************************************************************
     Function Name: LoadTable
     Purpose: To load in the table from a save file
     Parameters:
     @param passedTable, Vector<Card_Model>
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the table to what was passed in
     Assistance Received: none
     ********************************************************************* */
    void LoadTable(Vector<Card_Model> passedTable) {
        table = passedTable;
    }

    /** *********************************************************************
     Function Name: SetCurrentPlayer
     Purpose: To set the current player to what was passed in
     Parameters:
     @param passedCurrentPlayer, String
     Return Value: None
     Local Variables: None
     Algorithm:
     1) If the passed string is "Human" set the current player to 0
     2) If the passed string is "Computer" set the current player to 1
     Assistance Received: none
     ********************************************************************* */
    void SetCurrentPlayer(String passedCurrentPlayer) {
        if(passedCurrentPlayer.equals("Human")) {
            currentPlayer = 0;
        }
        else if(passedCurrentPlayer.equals("Computer")) {
            currentPlayer = 1;
        }
    }

    /** *********************************************************************
     Function Name: GetPlayerHelpOutputMessages
     Purpose: To retrieve the help output messages from the player class
     Parameters: None
     Return Value:
     @return Vector<String>
     Local Variables: None
     Algorithm:
     1) Return the helpOutputMessages variable from the player class
     Assistance Received: none
      ********************************************************************* */
    Vector<String> GetPlayerHelpOutputMessages() {
        player.get(currentPlayer).AskForHelp(table, tableBuilds, currentPlayer);
        return player.get(currentPlayer).GetHelpOutputMessages();
    }

    /** *********************************************************************
     Function Name: GetPlayerOutputMessages
     Purpose: To retrieve the output messages for the players after they make a move
     Parameters: None
     Return Value:
     @return Vector<String>
     Local Variables: None
     Algorithm:
     1) Return the playerOutputMessages variable from the player class
     Assistance Received: none
      ********************************************************************* */
    Vector<String> GetPlayerOutputMessages() {

        // Getting the computer output messages
        if(currentPlayer == 0 ) {
            player.get(1).PrintMove();
            return player.get(1).GetPlayerOutputMessages();
        }
        // Getting the human output messages
        else {
            player.get(0).PrintMove();
            return player.get(0).GetPlayerOutputMessages();
        }
    }

    /** *********************************************************************
     Function Name: SetBuildOwners
     Purpose: To set the owners of the builds after being loaded in
     Parameters:
     @param passedOwners Vector<String>
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Cycle through the owners passed in and set the owner to the corresponding build
     Assistance Received: none
      ********************************************************************* */
    void SetBuildOwners(Vector<String> passedOwners) {

        for(int i = 0; i < passedOwners.size(); i++) {
            if(passedOwners.get(i).equals("Computer")) {
                tableBuilds.get(i).SetOwner(1);
            }
            else {
                tableBuilds.get(i).SetOwner(0);
            }
        }
    }

    /** *********************************************************************
     Function Name: SetCaptureCardForBuilds
     Purpose: To set the capture cards for all the builds after being loaded in
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Cycle through the builds and get the value of the cards in each build
     2) Then cycle through the player's hand and see if any cards match the value of the build
     Assistance Received: none
      ********************************************************************* */
    void SetCaptureCardForBuilds() {

        Vector<Card_Model> playersHand = new Vector<Card_Model>();
        boolean setValue = false;

        // Cycling through the builds...
        for(int i = 0; i < tableBuilds.size(); i++) {
            if(tableBuilds.get(i).GetOwner() == 1) {
                playersHand = player.get(1).GetHand();
            }
            else {
                playersHand = player.get(0).GetHand();
            }

            int count = 0;
            // Cycling through the specific cards of a build and getting the count
            for(int j = 0; j < tableBuilds.get(i).GetBuildOfCards().size(); j++) {
                count += player.get(currentPlayer).CardNumber(tableBuilds.get(i).GetBuildOfCards().get(j).GetNumber());
            }

            // Cycling through the build owner's hand...
            for(int j = 0; j < playersHand.size(); j++) {

                // If the player has an ace, treat it as a 14
                if(playersHand.get(j).GetNumber() == 'A' && count == 14) {
                    setValue = true;
                    tableBuilds.get(i).SetCaptureCardOfBuild(playersHand.get(j));
                    tableBuilds.get(i).SetValueOfBuild(count);
                }
                // Otherwise, just see if the card value matches the value of the build
                else if(player.get(currentPlayer).CardNumber(playersHand.get(j).GetNumber()) == count) {
                    setValue = true;
                    tableBuilds.get(i).SetCaptureCardOfBuild(playersHand.get(j));
                    tableBuilds.get(i).SetValueOfBuild(count);
                }
            }

            setValue = false;
        }
    }

    /** *********************************************************************
     Function Name: SetBuildCounter
     Purpose: To set the build counter after builds have been loaded in
     Parameters:
     @param passedCount, int
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Set the build counter to what was passed in
     Assistance Received: none
      ********************************************************************* */
    void SetBuildCounter(int passedCount) {
        buildCounter = passedCount;
    }

    /** *********************************************************************
     Function Name: CreateNewBuildOnTable
     Purpose: To create a new build in the tableBuilds vector
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Add a new build_model object to the tableBuilds vector
     Assistance Received: none
      ********************************************************************* */
    void CreateNewBuildOnTable() {
        tableBuilds.add(new Build_Model());
    }

    /** *********************************************************************
     Function Name: AddCardToBuild
     Purpose: To add a card to a particular build
     Parameters:
     @param index, int
     @param passedCard, Card_Model
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Add the card that was passed in to the function to the tableBuilds
     Assistance Received: none
      ********************************************************************* */
    void AddCardToBuild(int index, Card_Model passedCard) {
        tableBuilds.get(index).AddCardToBuild(passedCard);
    }

    /** *********************************************************************
     Function Name: SetRound
     Purpose: To set the current round to what was passed in
     Parameters:
     @param passedRound, int
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Set the current round to what was passed in
     Assistance Received: none
      ********************************************************************* */
    void SetRound(int passedRound) {
        currentRound = passedRound;
    }
}
