package edu.ramapo.ncockcro.casino;

import android.util.Log;

public class Card_Model {

    // Private variables of the class
    private String card;
    private char suit;
    private char number;

    Card_Model() {

    }

    /** *********************************************************************
     Function Name: SetCard
     Purpose: To set a card to the value that was passed in
     Parameters:
     @param passedCard String, holds the value of a card
     Return Value:
     @return void
     Local Variables: None
     Algorithm:
     1) If the passed in card has a valid suit and valid number...
     2) Then set the card, suit, and number to what was passed in
     3) Otherwise, log an error to the log
     Assistance Received: none
      ********************************************************************* */
    void SetCard(String passedCard) {
        if(CheckSuit(passedCard.charAt(0)) && CheckNumber(passedCard.charAt(1))) {
            card = passedCard;
            suit = passedCard.charAt(0);
            number = passedCard.charAt(1);
        }
        else {
            Log.d("ERROR", "Error in setting the card in the card_model class.");
        }
    }

    /** *********************************************************************
     Function Name: GetCard
     Purpose: To draw four cards to the table
     Parameters: None
     Return Value:
     @return String
     Local Variables: None
     Algorithm:
     1) Return the string card value
     Assistance Received: none
      ********************************************************************* */
    final String GetCard() {
        return card;
    }

    /** *********************************************************************
     Function Name: GetSuit
     Purpose: To retrieve the suit of a card
     Parameters: None
     Return Value:
     @return char
     Local Variables: None
     Algorithm:
     1) Return the suit of a card
     Assistance Received: none
      ********************************************************************* */
    final char GetSuit() {
        return suit;
    }

    /** *********************************************************************
     Function Name: GetNumber
     Purpose: To retrieve the number of a card
     Parameters: None
     Return Value:
     @return char
     Local Variables: None
     Algorithm:
     1) Return the number of a card
     Assistance Received: none
      ********************************************************************* */
    final char GetNumber() {
        return number;
    }

    /** *********************************************************************
     Function Name: CheckSuit
     Purpose: To check if a suit of a card is valid
     Parameters:
     @param passedSuit char, holds the suit of a card
     Return Value:
     @return boolean
     Local Variables: None
     Algorithm:
     1) If the suit is valid, return true
     2) Otherwise, return false
     Assistance Received: none
      ********************************************************************* */
    private boolean CheckSuit(char passedSuit) {
        if(passedSuit == 'C' || passedSuit == 'D' || passedSuit == 'H' || passedSuit == 'S') {
            return true;
        }
        else {
            return false;
        }
    }

    /** *********************************************************************
     Function Name: CheckNumber
     Purpose: To check if a number passed in is valid for a card
     Parameters:
     @param passedNumber char, holds the number of a card
     Return Value:
     @return boolean
     Local Variables: None
     Algorithm:
     1) If the number is valid, return true
     2) Otherwise, return false
     Assistance Received: none
      ********************************************************************* */
    private boolean CheckNumber(char passedNumber) {
        if(passedNumber == 'A' || passedNumber == '2' || passedNumber == '3' || passedNumber == '4' || passedNumber == '5' ||
                passedNumber == '6' || passedNumber == '7' || passedNumber == '8' || passedNumber == '9' || passedNumber == 'X' ||
                passedNumber == 'J' || passedNumber == 'Q' || passedNumber == 'K') {
            return true;
        }
        else {
            return false;
        }
    }
}
