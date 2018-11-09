package edu.ramapo.ncockcro.casino;

import android.util.Log;

import java.util.Collections;
import java.util.Vector;

public class Deck_Model {

    private Vector<Card_Model> deckOfCards = new Vector();

    /** *********************************************************************
     Function Name: Deck_Model
     Purpose: The constructor for the Deck_Model class
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Create all of the 52 cards and store them in the vector of cards
     2) Shuffle the deck
     Assistance Received: none
      ********************************************************************* */
    Deck_Model() {

        int count = 0;

        for(int i = 2; i < 10; i++) {
            deckOfCards.add(new Card_Model());
            deckOfCards.get(count).SetCard("C" + Integer.toString(i));
            count++;
            deckOfCards.add(new Card_Model());
            deckOfCards.get(count).SetCard("D" + Integer.toString(i));
            count++;
            deckOfCards.add(new Card_Model());
            deckOfCards.get(count).SetCard("H" + Integer.toString(i));
            count++;
            deckOfCards.add(new Card_Model());
            deckOfCards.get(count).SetCard("S" + Integer.toString(i));
            count++;
        }

        // Tens
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("CX");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("DX");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("HX");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("SX");
        count++;

        // Jacks
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("CJ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("DJ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("HJ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("SJ");
        count++;

        // Queens
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("CQ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("DQ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("HQ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("SQ");
        count++;

        // Kings
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("CK");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("DK");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("HK");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("SK");
        count++;

        // Tens
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("CA");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("DA");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("HA");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("SA");
        count++;

        ShuffleCards();


    }

    /** *********************************************************************
     Function Name: IsEmpty
     Purpose: To check if the deck is empty
     Parameters: None
     Return Value:
     @return boolean
     Local Variables: None
     Algorithm:
     1) Returns true if the deck is empty
     2) Otherwise, it returns false
     Assistance Received: none
      ********************************************************************* */
    boolean IsEmpty() {
        if(deckOfCards.isEmpty() == true) {
            return true;
        }
        else {
            return false;
        }
    }

    /** *********************************************************************
     Function Name: GetDeck
     Purpose: To retrieve the deck
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the deck of cards
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetDeck() {
        return deckOfCards;
    }

    /** *********************************************************************
     Function Name: RemoveFourCards
     Purpose: To remove four cards from the deck
     Parameters: None
     Return Value:
     @return void
     Local Variables: None
     Algorithm:
     1) Remove four cards from the first index of the deck four times
     Assistance Received: none
      ********************************************************************* */
    void RemoveFourCards() {
        deckOfCards.remove(0);
        deckOfCards.remove(0);
        deckOfCards.remove(0);
        deckOfCards.remove(0);

    }

    /** *********************************************************************
     Function Name: ShuffleCards
     Purpose: To shuffle the deck of cards
     Parameters: None
     Return Value:
     @return void
     Local Variables: None
     Algorithm:
     1) Shuffle the deck of cards
     Assistance Received: none
      ********************************************************************* */
    private void ShuffleCards() {

        Collections.shuffle(deckOfCards);

    }
}
