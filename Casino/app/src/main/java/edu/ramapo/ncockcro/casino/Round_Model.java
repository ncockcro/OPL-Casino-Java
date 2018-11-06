package edu.ramapo.ncockcro.casino;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.util.Vector;

public class Round_Model {

    // Private variables for the Round class
    private Vector<Card_Model> computerPile = new Vector();
    private int humanScore;
    private Vector<Card_Model> humanHand = new Vector();
    private Vector<Card_Model> humanPile = new Vector();
    private Vector<Card_Model> table = new Vector();
    private Vector<Card_Model> buildCards = new Vector();
    private Vector<Build_Model> builds = new Vector();
    private Vector<Card_Model> deck = new Vector();
    private String nextPlayer = new String();
    private String lastCapture;
    private int currentPlayer;
    private Deck_Model deckOfCards = new Deck_Model();
    private Human_Model p1 = new Human_Model();
    private Computer_Model p2 = new Computer_Model();
    private Vector<Player_Model> player = new Vector();
    private Card_Model playerHandBuiltCard;
    private Vector<Card_Model> playerTableBuildCards;
    private Vector<Build_Model> tableBuids;
    private int buildCounter;
    private Card_Model playerHandCaptureCard;
    private Vector<Card_Model> playerTableCaptureCards;
    private Card_Model playerHandTrailCard;
    private int currentRound;
    private int humanPoints;
    private int computerPoints;
    private boolean loadGame;
    private Round_View roundView;

    Round_Model() {
        player.add(p1);
        player.add(p2);

        buildCounter = 0;

    }


    private void SetRoundInfo(int round, int humanScore, int computerScore) {

        if(round > 0) {
            currentRound = round;
        }
        else {
            Log.d("ERROR", "Error in setting the round in the round class");
        }

        if(humanScore >= 0 && computerScore >= 0) {
            humanPoints = humanScore;
            computerPoints = computerScore;
        }
        else {
            Log.d("ERROR", "Error in setting the scores in the round class");
        }
    }

    void LoadRound(Vector<Card_Model> loadComputerHand, Vector<Card_Model> loadComputerPile, Vector<Card_Model> loadHumanHand,
                           Vector<Card_Model> loadHumanPile, Vector<Card_Model> loadTable, Vector<Build_Model> loadBuilds,
                           Vector<Card_Model> loadDeck) {

    }

    void PlayRound(String passedFirstPlayer) {

        if(passedFirstPlayer == "Human") {
            currentPlayer = 0;
        }
        else {
            currentPlayer = 1;
        }

        if(loadGame == false) {
            DealCardsToPlayer();
            DealCardsToTable();
        }
    }

    void DealCardsToPlayer() {

        player.get(0).SetHand(deckOfCards.GetDeck());
        deckOfCards.RemoveFourCards();

        player.get(1).SetHand(deckOfCards.GetDeck());
        deckOfCards.RemoveFourCards();

    }

    void DealCardsToTable() {

        SetTable(deckOfCards.GetDeck());

        deckOfCards.RemoveFourCards();
    }

    void SetTable(Vector<Card_Model> deckOfCards) {

        table.add(deckOfCards.get(0));
        table.add(deckOfCards.get(1));
        table.add(deckOfCards.get(2));
        table.add(deckOfCards.get(3));

    }

    Vector<Card_Model> GetHumanHand(int passedPlayer) {
        return player.get(passedPlayer).hand;
    }

}
