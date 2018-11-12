package edu.ramapo.ncockcro.casino;

import android.util.Log;

import java.util.Vector;

public class Player_Model {

    protected Vector<Card_Model> hand = new Vector();
    private Vector<Card_Model> pile;
    private Vector<Card_Model> playerBuildCards = new Vector();
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

    void AddToPile(Vector<Card_Model> passedCards) {
        for(int i = 0; i < uniqueCards.size(); i++) {
            for(int j = 0; j < passedCards.size(); j++) {
                if(uniqueCards.get(i).GetCard() == passedCards.get(j).GetCard()) {
                    pile.add(passedCards.get(j));
                }
            }
        }
    }

    final Vector<Card_Model> GetPlayerBuildCards() {
        return playerBuildCards;
    }

    protected boolean AICheckForBuild(Vector<Card_Model> table, Vector<Build_Model> tableBuilds) {

        buildCards.clear();

        int handCardNumber;
        boolean skipCard = false;

        for(int i = 0; i < hand.size(); i++) {
            handCardNumber = CardNumber(hand.get(i).GetNumber());

            for(int j = 0; j < tableBuilds.size(); j++) {
                if(hand.get(i).GetCard().equals(tableBuilds.get(j).GetCaptureCardOfBuild().GetCard())) {
                    skipCard = true;
                }
            }

            if(skipCard) {
                skipCard = false;
                continue;
            }

            for(int j = 0; j < hand.size(); j++) {
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

        int buildValue;
        Vector<Integer> buildWithAddedCards = new Vector();

        for(int i = 0; i < tableBuilds.size(); i++) {

            buildValue = tableBuilds.get(i).GetCardValueOfBuild();
            printTableBuildCards = tableBuilds.get(i).GetBuildOfCards();

            if(tableBuilds.get(i).GetOwner() == 1) {
                continue;
            }

            for(int j = 0; j < hand.size(); j++) {

                buildWithAddedCards.add(buildValue + CardNumber(hand.get(j).GetNumber()));

                for(int k = 0; k < hand.size(); k++) {

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



}

