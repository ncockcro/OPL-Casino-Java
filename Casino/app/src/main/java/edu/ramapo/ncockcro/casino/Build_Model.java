package edu.ramapo.ncockcro.casino;

import android.util.Log;

import java.util.Vector;

public class Build_Model {

    private int owner;
    private int cardValueOfBuild;
    private Vector<Card_Model> buildOfCards = new Vector<Card_Model>();
    private Card_Model captureCardOfBuild;
    private Deck_Model deck = new Deck_Model();
    private Vector<Card_Model> uniqueCards = deck.GetDeck();
    private Player_Model playerModel = new Player_Model();

    /** *********************************************************************
    Function Name: Build_Model
    Purpose: Deafult constructor for Build_Model
    Parameters: None
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) None
    Assistance Received: none
    ********************************************************************* */
    Build_Model() {

    }

    /** *********************************************************************
    Function Name: SetOwner
    Purpose: To set the owner of a build
    Parameters:
    @param passedOwner, an integer passed by value. It holds the passed in owner of a build
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Check and make sure the passed in owner is either 0 or 1
    2) Set the owner to be what was passed in
    Assistance Received: none
    ********************************************************************* */
    void SetOwner(int passedOwner) {
        if(passedOwner == 0 || passedOwner == 1) {
            owner = passedOwner;
        }
        else {
            Log.d("MyError", "Error in setting the owner of a build in the build class.");
        }
    }

    /** *********************************************************************
    Function Name: GetOwner
    Purpose: To retrieve the owner of a specific build
    Parameters: None
    Return Value:
    @return int, The owner of a build, a constant integer value
    Local Variables: None
    Algorithm:
    1) Return the owner
    Assistance Received: none
    ********************************************************************* */
    final int GetOwner()  {
        return owner;
    }

    /** *********************************************************************
    Function Name: SetBuildOfCards
    Purpose: Set the vector of cards of a build
    Parameters:
    @param buildCards, an vector of cards passed by value. It holds the cards to be made of a build
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Set the buildOfCards to be the set passed in
    Assistance Received: none
    ********************************************************************* */
    void SetBuildOfCards(Vector<Card_Model> buildCards) {

        for(int i = 0; i < buildCards.size(); i++) {
            for(int j = 0; j < uniqueCards.size(); j++) {
                if(buildCards.get(i).GetCard().equals(uniqueCards.get(j).GetCard())) {
                    buildOfCards.add(buildCards.get(i));
                }
            }
        }
    }

    /** *********************************************************************
    Function Name: GetBuildOfCards
    Purpose: To retrieve a build of cards
    Parameters: None
    Return Value:
    @return Vector<Card_Model> The vector of cards that make up a build, a vector of cards value
    Local Variables: None
    Algorithm:
    1) Return the buildOfCards
    Assistance Received: none
    ********************************************************************* */
    Vector<Card_Model> GetBuildOfCards() {
        return buildOfCards;
    }

    /** *********************************************************************
    Function Name: CheckAndAddCardInBuild
    Purpose: To verify if a card can be added to a build and add it if possible
    Parameters:
    @param cardToBeAdded, an card passed by value. It holds the card to be added
    @param cardInBuild, a card that is already in the build
    @param currentPlayer, an integer. It holds the current player that is playing
    @param playerHand, a vector of cards. It holds the hand of a player
    Return Value:
    @return boolean, True if the card can be added, false otherwise, a boolean value
    Local Variables:
    cardInBuild, a boolean used to track if a card is in a build or not
    tempBuild, a vector of cards used to see what it would be like if a card was added to it
    aceAs1, an integer to count numbers as if ace was 1
    aceAs14, an integer to count numbers as if ace was 14
    Algorithm:
    1) Check and see if the card the build they want to add to exists in this build
    2) If it is and the current player isnt the owner of the build, then try to add the card
    3) If the player has a card in their hand that equals what the value will be after added the card
    4) Add the card to the build
    5) Set the owner of the build to the current player
    Assistance Received: none
    ********************************************************************* */
    boolean CheckAndAddCardInBuild(Card_Model cardToBeAdded, Card_Model cardInBuild, int currentPlayer, Vector<Card_Model> playerHand) {
        boolean isCardInBuild = false;
        Vector<Card_Model> tempBuild = buildOfCards;
        int aceAs1 = 0;
        int aceAs14 = 0;

        Log.d("BuildOfCards", buildOfCards.get(0).GetCard());
        Log.d("cardInBuild", cardInBuild.GetCard());

        for(int i = 0; i < buildOfCards.size(); i++) {
            if(buildOfCards.get(i).GetCard().equals(cardInBuild.GetCard())) {
                isCardInBuild = true;
            }
        }

        Log.d("But", "In build class");

        // If the card the user wants to add to a build exists and they are not the owner...
        if(isCardInBuild && owner != currentPlayer) {

            // Push the card the player wants to add to the build onto a temporary copy of the build to
            // be added if the numbers correctly add up to a card in their hand
            tempBuild.add(cardToBeAdded);

            // Iterate through the build with the card added and see what number it adds up to
            for (int i = 0; i < tempBuild.size(); i++) {
                if (tempBuild.get(i).GetNumber() != 'A') {
                    aceAs1 += playerModel.CardNumber(tempBuild.get(i).GetNumber());
                    aceAs14 += playerModel.CardNumber(tempBuild.get(i).GetNumber());
                } else {
                    aceAs1++;
                    aceAs14 += 14;
                }
            }

            // Then iterate through the players hand and check and see with the card they want to add being added,
            // Does it equal one of the cards in their hand
            for (int i = 0; i < playerHand.size(); i++) {

                // If it does equal, update the build with the added card and return true
                if (playerModel.CardNumber(playerHand.get(i).GetNumber()) == aceAs1 ||
                        playerModel.CardNumber(playerHand.get(i).GetNumber()) == aceAs14 ||
                        playerHand.get(i).GetNumber() == 'A' && aceAs1 == 14) {
                    buildOfCards = tempBuild;
                    owner = currentPlayer;

                    if (playerHand.get(i).GetNumber() != 'A') {
                        cardValueOfBuild = playerModel.CardNumber(playerHand.get(i).GetNumber());
                    } else {
                        cardValueOfBuild = 14;
                    }

                    return true;
                }
            }

            // If we get out of the for loop then that means that it never added up to a card in the player's hand
            return false;
        }

        // You can not add to the existing build
        else {
            return false;
        }
    }

    /** *********************************************************************
    Function Name: CanCaptureBuildOfCards
    Purpose: To verify if a player can capture a build
    Parameters:
    @param cardToBeCaptured, a card passed by value. It holds the card that will capture the build
    @param cardInBuild, a card passed by value. It holds the card that is in the build already
    @param playerHand, a vector of cards. It holds the hand of a player
    Return Value:
    @return boolean, True if the build can be captured, false otherwise, a boolean value
    Local Variables:
    isCardInBuild, a boolean used to track if a card is in a build or not
    captureCardNum, an int to represent the value of the card the player is capturing with
    captureCardAce, an int to represent the value of an ace if the player is capturing with one
    Algorithm:
    1) Check and see if the build they want to capture exists
    2) If it is and the player is capturing with a card equivalent to the value of the build, then return true
    Assistance Received: none
    ********************************************************************* */
    boolean CanCaptureBuildOfCards(Card_Model cardToBeCaptured, Card_Model cardInBuild, Vector<Card_Model> playerHand) {
        boolean isCardInBuild = false;
        int captureCardNum = playerModel.CardNumber(cardToBeCaptured.GetNumber());
        int captureCardAce = 14;

        // First checking to see if the build the user wants to capture exists by looking for the card they provided
        for(int i = 0; i < buildOfCards.size(); i++) {
            if(buildOfCards.get(i).GetCard().equals(cardInBuild.GetCard())) {
                Log.d("Card", "Card is in the build");
                isCardInBuild = true;
            }
        }

        // If the card the user typed in is in the build and the card the user
        // wants to capture with is not an ace, then we need to make sure the card they are
        // capturing with is the same value as the total build
        if(isCardInBuild && cardToBeCaptured.GetNumber() != 'A') {
            Log.d("CardValueOfBuild", Integer.toString(cardValueOfBuild));
            Log.d("playerCard", Integer.toString(playerModel.CardNumber(cardToBeCaptured.GetNumber())));
            if(playerModel.CardNumber(cardToBeCaptured.GetNumber()) == cardValueOfBuild) {
                return true;
            }
        }
        // If the card is in the build and the card is an ace, then we treat the capturing
        // card as 14 and see if it matches the total build value
        else if(isCardInBuild) {
            if(captureCardAce == cardValueOfBuild) {
                return true;
            }
        }

        return false;
    }

    /* *********************************************************************
    Function Name: SetValueOfBuild
    Purpose: To set the numeric value of a build (what it adds up to)
    Parameters:
    @param value, an int passed by value. It holds the value of the build
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) If the value is greater than 0 and less than 15
    2) Set the value of the build to what was passed in
    Assistance Received: none
    ********************************************************************* */
    void SetValueOfBuild(int value) {

        // Value must be greater than 0 and less than 15 to remain in the size of a build
        if(value > 0 && value < 15) {
            cardValueOfBuild = value;
        }
        else {
            Log.d("ERROR", Integer.toString(value));
            Log.d("MyError", "Error in setting the value of a build in the build class.");
        }
    }

    /** *********************************************************************
    Function Name: GetValueOfBuild
    Purpose: To retrieve the added up value of a build
    Parameters: None
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Return the value of the build
    Assistance Received: none
    ********************************************************************* */
    int GetCardValueOfBuild() {
        return cardValueOfBuild;
    }

    /** *********************************************************************
    Function Name: SetCaptureCardOfBuild
    Purpose: To set the card that will be used to capture the build
    Parameters:
    @param card, a card object passed by value, the card that will be used to capture a build
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Check to make sure the card has an actual number value
    2) If it does, set the value
    3) Otherwise, print out an error message
    Assistance Received: none
    ********************************************************************* */
    void SetCaptureCardOfBuild(Card_Model card) {

        // If the card is an actual card being passed in, then set the value
        for(int i = 0; i < uniqueCards.size(); i++) {
            if(uniqueCards.get(i).GetCard().equals(card.GetCard())) {
                captureCardOfBuild = card;
                return;
            }
        }

        Log.d("MyError", "Error in setting the capture card of a build in build class.");
    }

    /** *********************************************************************
    Function Name: GetCaptureCardOfBuild
    Purpose: To retrieve the card that
    Paramters: None
    Return Value: the card that can capture a build, a card object
    Local Variables: None
    Algorithm:
    1) Return the capture card of a build
    Assistance Received: none
    ********************************************************************* */
    Card_Model GetCaptureCardOfBuild() {
        return captureCardOfBuild;
    }



}
