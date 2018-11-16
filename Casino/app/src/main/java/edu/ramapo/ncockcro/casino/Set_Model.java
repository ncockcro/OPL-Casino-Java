package edu.ramapo.ncockcro.casino;

import java.util.Vector;

public class Set_Model {
    private Vector<Card_Model> cardOfSet = new Vector<Card_Model>();

    /** *********************************************************************
    Function Name: SetCardsOfSet
    Purpose: To set a vector of cards into a set
    Parameters:
    @param cards, a vector of cards. It refers to the cards which make up a set
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Set the cardsOfSet variable to what was passed in
    Assistance Received: none
    ********************************************************************* */
    void SetCardsOfSet(Vector<Card_Model> cards) {
        cardOfSet = cards;
    }

    /** *********************************************************************
    Function Name: GetCardsOfSet
    Purpose: To retrieve the vector of cards of a set
    Parameters: None
    Return Value:
     @return Vector<Card_Model>The cards of a set, a vector of cards value
    Local Variables: None
    Algorithm:
    1) Return the cardsOfSet
    Assistance Received: none
    ********************************************************************* */
    Vector<Card_Model> GetCardOfSet() {
        return cardOfSet;
    }
}
