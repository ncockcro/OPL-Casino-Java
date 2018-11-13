package edu.ramapo.ncockcro.casino;

import android.util.Log;

import java.util.Vector;

public class Player_Model {

    protected Vector<Card_Model> hand = new Vector();
    private Vector<Card_Model> pile;
    private Vector<Card_Model> playerBuildCards = new Vector();
    private Vector<Card_Model> playerSingleSetCards;
    private Vector<Set_Model> playerMultipleSetCards;
    private Deck_Model deck = new Deck_Model();
    private Vector<Card_Model> uniqueCards = deck.GetDeck();
    private boolean playerWantSave;
    private Card_Model playerCard;
    private Vector<Card_Model> buildCards;
    private char newOrExistingBuild;
    private Card_Model existingBuildCard;
    private char playerMove;
    private char playerWantBuild;
    private char printPlayerCaptureBuild;
    private char playerWantSet;
    private Vector<Card_Model> printTableBuildCards;
    private Vector<Card_Model> printTableCaptureCards;

    private Card_Model playerWantCard = new Card_Model();

    /** *********************************************************************
     Function Name: SetHand
     Purpose: To set four cards to the player's hand
     Parameters:
        @param passedFourCards Vector<Card_Model>, four cards to be set to the player
     Return Value:
        @return void
     Local Variables: None
     Algorithm:
     1) Add the four cards to the player's hand
     Assistance Received: none
      ********************************************************************* */
    public void SetHand(Vector<Card_Model> passedFourCards) {

        hand.add(passedFourCards.get(0));
        hand.add(passedFourCards.get(1));
        hand.add(passedFourCards.get(2));
        hand.add(passedFourCards.get(3));

    }

    /** *********************************************************************
    Function Name: AddCardsToHand
    Purpose: Adds any cards that are passed in to the function
    Parameters:
    @param cards, a vector of cards passed by value. It holds the cards to be added to a player's hand
    Return Value:
    @return Void
    Local Variables: None
    Algorithm:
    1) Add the cards to the player's hand
    Assistance Received: none
    ********************************************************************* */
    void AddCardsToHand(Vector<Card_Model> cards) {
        for(int i = 0; i < cards.size(); i++) {
            hand.add(cards.get(i));
        }
    }

    /** *********************************************************************
    Function Name: IsHandEmpty
    Purpose: To check if a player's hand is empty
    Parameters: None
    Return Value:
    @return boolean, Returns true if the player's hand is empty, false otherwise, a boolean value
    Local Variables: None
        Algorithm:
    1) If the hand vector is empty, return true
    2) Otherwise, return false
    Assistance Received: none
    ********************************************************************* */
    boolean IsHandEmpty() {

        if(hand.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    void SetPlayerWantCard(Card_Model passedCard) {
        playerWantCard = passedCard;
    }

    Vector<Card_Model> GetHand() {
        return hand;
    }

    void RemoveCard(Card_Model passedCard) {
        boolean notFound = false;

        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).GetCard().equals(passedCard.GetCard())) {
                Log.d("Yup", "Deleting card");
                hand.remove(i);
                notFound = true;
            }
        }

        if(!notFound) {
            Log.d("ERROR", "Error in removing a card in the player model class.");
        }
    }

    void AddToPile(Vector<Card_Model> passedCards) {
        for(int i = 0; i < uniqueCards.size(); i++) {
            for(int j = 0; j < passedCards.size(); j++) {
                if(uniqueCards.get(i).GetCard() == passedCards.get(j).GetCard()) {
                    pile.add(passedCards.get(j));
                }
            }
        }
    }

    Vector<Card_Model> GetPlayerBuildCards() {
        return playerBuildCards;
    }

    /** *********************************************************************
    Function Name: AICheckForBuild
    Purpose: Check if there is a build for a player's hand and suggest what to do
    Parameters:
     @param table, Vector<Card_Model> the cards currently on the table
    Return Value:
     @return boolean, If the computer can build or not, a boolean value
    Local Variables: None
    Algorithm:
    1) Cycle through the table and the player's hand for any cards that can be added together
    2) After the cards have been added together, check and see if there are any cards in the player's
    inventory that equal the new total sum
    3) It will also check if there are any existing builds that the computer can add to which it will build onto
    4) If there are, then the computer can capture
    5) If there aren't, the function will return false
    Assistance Received: none
    ********************************************************************* */
    protected boolean AICheckForBuild(Vector<Card_Model> table, Vector<Build_Model> tableBuilds) {

        buildCards.clear();
        // ****** For creating a new build ******

        int handCardNumber;
        boolean skipCard = false;


        // Cycling through the computer's hand and checking if there are cards that add up to that card
        for(int i = 0; i < hand.size(); i++) {
            handCardNumber = CardNumber(hand.get(i).GetNumber());

            // Cycling through the builds and if the card in the player's hand matches one of the cards
            // used to capture a build, then it will set skipCard to true and skip over that hand card
            for(int j = 0; j < tableBuilds.size(); j++) {
                if(hand.get(i).GetCard().equals(tableBuilds.get(j).GetCaptureCardOfBuild().GetCard())) {
                    skipCard = true;
                }
            }

            // Skipping over a hand card because it is needed in a build
            if(skipCard) {
                skipCard = false;
                continue;
            }

            // Now cycling through the computers hand and table, looking for a combination of a card from the hand and a card on
            // the table that will add up to another card in the player's hand
            for(int j = 0; j < hand.size(); j++) {
                // Skipping over the same exact computer card from the first for loop
                if(hand.get(i).GetCard().equals(hand.get(j).GetCard())) {
                    continue;
                }

                for(int k = 0; k < table.size(); k++) {
                    if((CardNumber(hand.get(j).GetNumber()) + CardNumber(table.get(k).GetNumber())) == handCardNumber) {
                        newOrExistingBuild = 'n';
                        playerCard = hand.get(j);
                        buildCards.add(table.get(k));
                        return true;
                    }
                }
            }
        }

        // **** Adding to an existing build ****
        int buildValue;
        Vector<Integer> buildWithAddedCards = new Vector();

        // Cycling through each of the builds on the table to see if any of the builds can be added to
        for(int i = 0; i < tableBuilds.size(); i++) {

            // Storing the value of a particular build into a variable
            buildValue = tableBuilds.get(i).GetCardValueOfBuild();
            printTableBuildCards = tableBuilds.get(i).GetBuildOfCards();

            // If the computer is the build owner, then it can not add to the build
            if(tableBuilds.get(i).GetOwner() == 1) {
                continue;
            }

            // Now cycling through the player's hand to gather different combinations of adding a card to a build
            for(int j = 0; j < hand.size(); j++) {

                buildWithAddedCards.add(buildValue + CardNumber(hand.get(j).GetNumber()));

                // Finally, with a card added, are there any other cards in a player's hand that equal up to that
                for(int k = 0; k < hand.size(); k++) {

                    // If there is, then save that and add it to the existing build
                    if(CardNumber(hand.get(i).GetNumber()) == buildWithAddedCards.get(j)) {
                        newOrExistingBuild = 'e';
                        playerCard = hand.get(j);
                        existingBuildCard = tableBuilds.get(i).GetBuildOfCards().lastElement();
                        return true;
                    }
                }
            }

        }

        return false;
    }

    /** *********************************************************************
    Function Name: AICheckForCapture
    Purpose: Check if there are any cards for a player to capture
    Parameters:
    @param table, a vector of cards passed by value. It holds current table cards
    @param tableBuilds, a vector of builds passed value. It holds the current builds on the table
    Return Value:
    @return boolean, If the computer cancapture or not, a boolean value
    Local Variables:
    currentBuild, a vector of cards, holds the current build while cycling through all the builds
    count, an int, holds the count of numbers in a build
    isCapturing, a boolean, false if not capturing, true otherwise
    tableCardToBeCaptured, a card value, holds the table card that will be captured
    tempCards, a vector of card object, a temporary holding for a card
    setOfCards, a vector of cards, holds a pair of cards that make up a set
    tempSet, a set object, temporarily holds a set object
    canCapture, a boolean, true if can capture, false otherwise
    Algorithm:
    1) First, cycle through any builds on the table and see if there are any to be captured
    2) If not, then check and see if there are any loose same value cards which can be captured
    3) Then check if there are any combination of cards to make up a set which can be captured
    4) If any of these are true, the function will return true
    5) Otherwise, it will return false
    Assistance Received: none
    ********************************************************************* */
    boolean AICheckForCapture(Vector<Card_Model> table, Vector<Build_Model> tableBuilds) {

        Vector<Card_Model> currentBuild;
        int count = 0;
        boolean isCapturing = false;
        playerWantSet = 'n';
        playerMultipleSetCards.clear();

        // Checking to see if any builds can be captured

        // First we need to cycle through each of the builds on the table
        for(int i = 0; i < tableBuilds.size(); i++) {
            currentBuild = tableBuilds.get(i).GetBuildOfCards();

            // Saving the current build of cards to be printed after the computer makes their move
            printTableBuildCards = tableBuilds.get(i).GetBuildOfCards();

            // Then cycle through all of the cards in a specific build and add up the value of the cards
            for(int j = 0; j < currentBuild.size(); j++) {
                count += CardNumber(currentBuild.get(j).GetNumber());
            }

            // Now we must go through the player's hand and see if any of the cards equal the sum of the build
            // If so, this function will return true and the build will be captured
            for(int j = 0; j < hand.size(); j++) {

                // Capturing the build with an ace
                if(hand.get(j).GetNumber() == 'A' && count == 14) {
                    playerWantBuild = 'y';
                    printPlayerCaptureBuild = 'y';
                    playerCard = hand.get(j);
                    existingBuildCard = currentBuild.lastElement();
                    return true;
                }

                // Capturing the build with any other card
                if(CardNumber(hand.get(j).GetNumber()) == count) {
                    playerWantBuild = 'y';
                    printPlayerCaptureBuild = 'y';
                    playerCard = hand.get(j);
                    existingBuildCard = currentBuild.lastElement();
                    return true;
                }
            }
        }

        Card_Model tableCardToBeCaptured = new Card_Model();

        // If there were no builds to capture, then we will check for any loose cards to capture
        // First cycling through the table
        for(int i = 0; i < table.size(); i++) {

            // Then through the player's hand
            for(int j = 0; j < hand.size(); j++) {

                // If any of them have the same card number value, the computer can capture the card
                if(CardNumber(hand.get(j).GetNumber()) == CardNumber(hand.get(j).GetNumber())) {

                    playerCard = hand.get(j);
                    tableCardToBeCaptured = table.get(i);
                    isCapturing = true;

                    // Saving the card that matched so it can be outputted later to show the computers move
                    printTableCaptureCards.add(table.get(i));

                    // Ensuring that there is no duplication of data in the cards to be printed
                    if(printTableCaptureCards.size() > 0 && printTableCaptureCards.lastElement().GetCard() != table.get(i).GetCard()) {
                        printTableCaptureCards.add(table.get(i));
                    }
                }
            }
        }

        // **** Set Checking ****
        Vector<Card_Model> tempCards = new Vector<Card_Model>();
        Vector<Card_Model> setOfCards = new Vector<Card_Model>();
        Set_Model tempSet = new Set_Model();
        boolean canCapture = true;

        // If the computer is capturing, maybe there are some sets to be captured aswell
        if(isCapturing) {

            // Next two for loops go through the table and we are looking for combinations of cards on the table
            // that add up to the card the computer is capturing with
            for(int i = 0; i < table.size(); i++) {

                // If the card that is going to be in a set is already going to be used to capture,
                // then we must skip over it
                if(table.get(i).GetCard().equals(tableCardToBeCaptured.GetCard())) {
                    continue;
                }

                for(int j = 0; j < table.size(); j++) {

                    // If it is the same card on both tables, then we need to skip it as you can not have a set of the same card
                    if(table.get(i).GetCard().equals(table.get(j).GetCard())) {
                        continue;
                    }

                    // If the card that is going to be in a set is already going to be used to capture,
                    // then we must skip over it
                    if(table.get(j).GetCard().equals(tableCardToBeCaptured.GetCard())) {
                        continue;
                    }

                    // If the card the player is capturing with is an ace, then we need to look for sets that add up to 14
                    if(playerCard.GetNumber() == 'A' && CardNumber(table.get(i).GetNumber()) + CardNumber(table.get(j).GetNumber()) == 14) {
                        tempCards.add(table.get(j));
                        tempCards.add(table.get(i));

                        // Before adding the cards to a set, we need to check if they were already placed in a set prior
                        // This for loop will cycle through each of the prior sets
                        for(int l = 0; l < playerMultipleSetCards.size(); l++) {
                            setOfCards = playerMultipleSetCards.get(l).GetCardOfSet();

                            // This for loop will cycle through a specific set of cards and determine if the cards
                            // were used in a set prior
                            for(int m = 0; m < setOfCards.size(); m++) {
                                if(setOfCards.get(m).GetCard().equals(tempCards.get(m).GetCard())) {
                                    canCapture = false;
                                }
                            }
                        }

                        tempCards.clear();

                        // If the cards are not already in the set vector, then add them to it
                        if(canCapture) {

                            // If the cards are not already in the set vector, then add them to it
                            playerWantSet = 'y';
                            tempCards.add(table.get(i));
                            tempCards.add(table.get(j));

                            // Then push these cards onto a set object and them to the vector of sets
                            tempSet.SetCardsOfSet(tempCards);
                            playerMultipleSetCards.add(tempSet);
                            tempCards.clear();
                        }
                    }

                    // Otherwise, were are just looking for any set that adds up to the card the player chose
                    if((CardNumber(table.get(i).GetNumber()) + CardNumber(table.get(j).GetNumber())) == CardNumber(playerCard.GetNumber())) {
                        tempCards.add(table.get(j));
                        tempCards.add(table.get(i));

                        // Before adding the cards to a set, we need to check if they were already placed in a set prior
                        // This for loop will cycle through each of the prior sets
                        for(int l = 0; l < playerMultipleSetCards.size(); l++) {
                            setOfCards = playerMultipleSetCards.get(l).GetCardOfSet();

                            // This for loop will cycle through a specific set of cards and determine if the cards
                            // were used in a set prior
                            for(int m = 0; m < setOfCards.size(); m++) {
                                if(setOfCards.get(m).GetCard().equals(tempCards.get(m).GetCard())) {
                                    canCapture = false;
                                }
                            }
                        }

                        tempCards.clear();

                        // If the cards are not already in the set vector, then add them to it
                        if(canCapture) {
                            playerWantSet = 'y';
                            tempCards.add(table.get(i));
                            tempCards.add(table.get(j));

                            // Then push these cards onto a set object and them to the vector of sets
                            tempSet.SetCardsOfSet(tempCards);
                            playerMultipleSetCards.add(tempSet);
                            tempCards.clear();
                        }
                    }
                }
            }
        }
        return isCapturing;
    }

    /** *********************************************************************
    Function Name: AIMakeTrail
    Purpose: To pick the lowest card the computer has and trail
    Parameters: None
    Return Value:
    @return Void
    Local Variables:
    lowestCard, a card object used to hold the lowest valued card in a player hand
    Algorithm:
    1) Cycle through a player's hand and find which card has the lowest value
    2) Add it to the playerCard and trail with it so maybe it can be incorporated into a build
    Assistance Received: none
    ********************************************************************* */
    void AIMakeTrail() {
        Card_Model lowestCard = new Card_Model();
        lowestCard.SetCard("CK");

        for(int i = 0; i < hand.size(); i++) {
            if(CardNumber(hand.get(i).GetNumber()) <= CardNumber(lowestCard.GetNumber())) {
                lowestCard = hand.get(i);
            }
        }

        playerCard = lowestCard;
    }

    /** *********************************************************************
     Function Name: CardNumber
     Purpose: Take a character as input and output the number that it represents.
     Parameters:
     @param number, a char, the number of a card (e.g. 2, X, J)
     Return Value:
     @return Int
     Local Variables:None
     Algorithm: Go through if, else if, and else statements til the corrent number is found.
     Assistance Received: none
     ********************************************************************* */
    int CardNumber(char number) {

        if(number == 'A') {
            return 1;
        }
        else if (number == '2') {
            return 2;
        }
        else if(number == '3') {
            return 3;
        }
        else if(number == '4') {
            return 4;
        }
        else if(number == '5') {
            return 5;
        }
        else if(number == '6') {
            return 6;
        }
        else if(number == '7') {
            return 7;
        }
        else if(number == '8') {
            return 8;
        }
        else if(number == '9') {
            return 9;
        }
        else if(number == 'X') {
            return 10;
        }
        else if(number == 'J') {
            return 11;
        }
        else if(number == 'Q') {
            return 12;
        }
        else if(number == 'K') {
            return 13;
        }
        else {
            Log.d("ERROR", "Error in finding card value in player model class.");
            return -1;
        }
    }

    /** *********************************************************************
     Function Name: GetNumberName
     Purpose: To get the actual name of a number (e.g. X for ten)
     Parameters:
     @param number, a char passed by value, the number of a card
     Return Value:
     @return The word value of a number, a string value
     Local Variables:
     actualNum, a string value, used if the number that was passed in is an actual number
     Algorithm:
     1) If the number passed in is X, J, Q, K, or A, will convert it to the word equivalent
     Assistance Received: none
     ********************************************************************* */
    String GetNumberName(char number) {
        if(number == 'X') {
            return "Ten";
        }
        else if(number == 'J') {
            return "Jack";
        }
        else if(number == 'Q') {
            return "Queen";
        }
        else if(number == 'K') {
            return "King";
        }
        else if(number == 'A') {
            return "Ace";
        }
        else {
            Log.d("ERROR", "Error in setting the number of card in player model class.");
            return "ERROR";
        }
    }

    /** *********************************************************************
     Function Name: GetSuitName
     Purpose: To get the actual name of a club (e.g. C for Club)
     Parameters:
     @param suit, a char passed by value, the suit of a card
     Return Value:
     @return String, The word value of a suit, a string value
     Local Variables: None
     Algorithm:
     1) If the suit passed in is C, D, H, or S, will convert it to the word equivalent
     Assistance Received: none
     ********************************************************************* */
    String GetSuitName(char suit) {
        if(suit == 'C') {
            return "Clubs";
        }
        else if(suit == 'D') {
            return "Diamonds";
        }
        else if(suit == 'H') {
            return "Hearts";
        }
        else if(suit == 'S') {
            return "Spades";
        }
        else {
            Log.d("ERROR", "Error in finding the suit in the player model class.");
            return "ERROR";
        }
    }

}

