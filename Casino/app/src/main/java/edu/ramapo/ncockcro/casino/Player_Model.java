package edu.ramapo.ncockcro.casino;

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

    public void SetHand(Vector<Card_Model> passedFourCards) {

        hand.add(passedFourCards.get(0));
        hand.add(passedFourCards.get(1));
        hand.add(passedFourCards.get(2));
        hand.add(passedFourCards.get(3));

    }

}

