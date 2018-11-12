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
    //private Vector<Card_Model> humanHand = new Vector();
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
    private String errorReason = new String();

    /** *********************************************************************
     Function Name: Round_Model
     Purpose: To initialize the players and built counter
     Parameters: None
     Return Value:
     @return None
     Local Variables: None
     Algorithm:
     1) Intialize the players and buildCounter
     Assistance Received: none
      ********************************************************************* */
    Round_Model() {
        player.add(p1);
        player.add(p2);

        buildCounter = 0;

    }

    /** *********************************************************************
     Function Name: SetRoundInfo
     Purpose: To set information outside of the round class to be used when saving the game
     Parameters:
     @param round int, the current round of the tournament
     @param humanScore, int, the current score of the human
     @param computerScore, int, the currentScore of the computer
     Return Value:
     @return void
     Local Variables: None
     Algorithm:
     1) If the round is greater than 0, set the round
     2) If the scores are greater or equal to zero, set the scores
     Assistance Received: none
      ********************************************************************* */
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

    /** *********************************************************************
     Function Name: DealCardsToPlayer
     Purpose: To deal four cards to the human, four cards to the computer, and remove the cards from the deck
     Parameters: None
     Return Value:
     @return void
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
     Return Value:
     @return void
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
     Return Value:
     @return void
     Local Variables: None
     Algorithm:
     1) Add the four cards to the table
     Assistance Received: none
      ********************************************************************* */
    void SetTable(Vector<Card_Model> passedFourCards) {

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
            if(player.get(currentPlayer).GetPlayerBuildCards().get(i).GetCard() == passedCard.GetCard()) {
                errorReason = "you need that card for a build.";
                return false;
            }
        }

        for(int i = 0; i < table.size(); i++) {
            for(int j = 0 ; j < playerHand.size(); j++) {
                if(table.get(i).GetNumber() == playerHand.get(j).GetNumber()) {
                    errorReason = "there is a card with the same value on the table.";
                    return false;
                }
            }
        }

        player.get(currentPlayer).RemoveCard(passedCard);
        player.get(0).RemoveCard(passedCard);

        Vector<Card_Model> trailCard = new Vector();
        trailCard.add(passedCard);
        AddCardsToTable(trailCard);

        return true;

    }

    void AddCardsToTable(Vector<Card_Model> passedCards) {

        for(int i = 0; i < passedCards.size(); i++) {
            table.add(passedCards.get(i));
        }
    }

    void GiveTableCards() {
        if(lastCapture == "Human") {
            player.get(0).AddToPile(table);
        }
        else if (lastCapture == "Computer") {
            player.get(1).AddToPile(table);
        }
        else {
            Log.d("ERROR", "Error, there is a problem giving the cards at the end of the round.");
        }
    }

    final String GetErrorReason() {
        return errorReason;
    }

    boolean CheckIfPlayersHandEmpty() {
        if(player.get(0).hand.isEmpty() && player.get(1).hand.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean CheckIfDeckEmpty() {
        if(deckOfCards.GetDeck().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }


}
