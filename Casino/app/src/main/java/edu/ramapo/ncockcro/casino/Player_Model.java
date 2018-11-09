package edu.ramapo.ncockcro.casino;

import android.util.Log;

import java.util.Vector;

public class Player_Model {

    protected Vector<Card_Model> hand = new Vector();
    private Vector<Card_Model> pile;
    private Vector<Card_Model> playerBuildCards;
    private Vector<Card_Model> playerSingleSetCards;
    //private Vector<Set_Model> playerMultipleSetCards;
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

    void SetPlayerWantCard(Card_Model passedCard) {
        playerWantCard = passedCard;
    }

    Vector<Card_Model> GetHand() {
        return hand;
    }

    void RemoveCard(Card_Model passedCard) {
        boolean notFound = false;

        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).GetCard() == passedCard.GetCard()) {
                Log.d("Yup", "Deleting card");
                hand.remove(i);
                notFound = true;
            }
        }

        if(notFound == false) {
            Log.d("ERROR", "Error in removing a card in the player model class.");
        }
    }

}

