package edu.ramapo.ncockcro.casino;

import android.util.Log;

import java.util.Vector;

public class Player_Model {

    protected Vector<Card_Model> hand = new Vector<Card_Model>();
    private Vector<Card_Model> pile = new Vector<Card_Model>();
    private Vector<Card_Model> playerBuildCards = new Vector<Card_Model>();
    private Vector<Card_Model> playerSingleSetCards = new Vector<Card_Model>();
    private Vector<Set_Model> playerMultipleSetCards = new Vector<Set_Model>();
    private int multipleSetCardsCount = 0;
    private Deck_Model deck = new Deck_Model();
    private Vector<Card_Model> uniqueCards = deck.GetDeck();
    private boolean playerWantSave;
    private Card_Model playerCard;
    private Vector<Card_Model> buildCards = new Vector<Card_Model>();
    private char newOrExistingBuild;
    private Card_Model existingBuildCard;
    protected char playerMove;
    private char playerWantBuild;
    private char printPlayerCaptureBuild;
    private char playerWantSet;
    private Vector<Card_Model> printTableBuildCards = new Vector<Card_Model>();
    private Vector<Card_Model> printTableCaptureCards = new Vector<Card_Model>();

    private Vector<Card_Model> tableCardsToBeCaptured = new Vector<Card_Model>();

    private Card_Model playerWantCard;

    /** *********************************************************************
     Function Name: SetHand
     Purpose: To set four cards to the player's hand
     Parameters:
     @param table Vector<Card_Model>, the cards on the table
     @param tableBuilds Vector<Build_Model>, the builds in the game
     Return Value:
     @return char, return the move type
     Local Variables: None
     Algorithm:
     1) Virtual function, just returns a null value, the actual functions in the human
     and computer classes return the player's move
     Assistance Received: none
      ********************************************************************* */
    public char MakeMove(Vector<Card_Model> table, Vector<Build_Model> tableBuilds) {
        return '0';
    }

    /** *********************************************************************
     Function Name: SetHand
     Purpose: To set four cards to the player's hand
     Parameters:
        @param passedFourCards Vector<Card_Model>, four cards to be set to the player
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Add the four cards to the player's hand
     Assistance Received: none
      ********************************************************************* */
    void SetHand(Vector<Card_Model> passedFourCards) {

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
    Return Value: Void
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

    /** *********************************************************************
     Function Name: SetPlayerWantCard
     Purpose: To set
     Parameters:
     @param passedCard Card_Model, the card the player wants to use
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Set the playerWantCard variable
     Assistance Received: none
      ********************************************************************* */
    void SetPlayerWantCard(Card_Model passedCard) {
        playerWantCard = passedCard;
    }

    /** *********************************************************************
     Function Name: SetHand
     Purpose: To set four cards to the player's hand
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Retrieve the player's hand
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetHand() {
        return hand;
    }

    /** *********************************************************************
     Function Name: AddToPile
     Purpose: To add cards to a player's pile
     Parameters:
     @param passedCards Vector<Card_Model>, cards to be added to the pile
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) if the cards are an actual card, add them to the pile
     Assistance Received: none
      ********************************************************************* */
    void AddToPile(Vector<Card_Model> passedCards) {
        for(int i = 0; i < uniqueCards.size(); i++) {
            for(int j = 0; j < passedCards.size(); j++) {
                if(uniqueCards.get(i).GetCard().equals(passedCards.get(j).GetCard())) {
                    Log.d("LOL", passedCards.get(j).GetCard());
                    pile.add(passedCards.get(j));
                }
            }
        }
    }

    /** *********************************************************************
     Function Name: GetNewOrExistingBuild
     Purpose: To get the newOrExistingBuild variable
     Parameters: None
     Return Value:
     @return char
     Local Variables: None
     Algorithm:
     1) return the newOrExistingBuild variable
     Assistance Received: none
      ********************************************************************* */
    char GetNewOrExistingBuild() {
        return newOrExistingBuild;
    }

    /** *********************************************************************
     Function Name: GetExistingBuildCard
     Purpose: Get the existing build card
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Retrieve the existingBuildCard variable
     Assistance Received: none
      ********************************************************************* */
    Card_Model GetExistingBuildCard() {
        return existingBuildCard;
    }

    /** *********************************************************************
     Function Name: GetPlayerCard
     Purpose: To return the player's card
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Return the playerCard
     Assistance Received: none
      ********************************************************************* */
    Card_Model GetPlayerCard() {
        return playerCard;
    }

    /** *********************************************************************
     Function Name: SetHand
     Purpose: To remove a card from the player's hand
     Parameters:
     @param passedCard Card_Model, a card to be removed from the player's hand
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) If the card is in the player's hand, delete the card
     Assistance Received: none
      ********************************************************************* */
    void RemoveCard(Card_Model passedCard) {
        boolean notFound = false;

        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).GetCard().equals(passedCard.GetCard())) {
                hand.remove(i);
                notFound = true;
            }
        }

        if(!notFound) {
            Log.d("MyError", "Error in removing a card in the player model class.");
        }
    }

    /** *********************************************************************
     Function Name: SetPile
     Purpose: To set a player's pile
     Parameters:
     @param passedPile Vector<Card_Model>, a player's pile
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) if the cards in the pile are valid cards, place them into the player's pile
     Assistance Received: none
      ********************************************************************* */
    void SetPile(Vector<Card_Model> passedPile) {
        int count = 0;
        for(int i = 0; i  <uniqueCards.size(); i++) {
            for(int j = 0; j < passedPile.size(); j++) {
                if(uniqueCards.get(i).GetCard().equals(passedPile.get(j).GetCard())) {
                    count++;
                }
            }
        }

        if(count == passedPile.size()) {
            pile = passedPile;
        }
    }

    /** *********************************************************************
    Function Name: AddToPile
    Purpose: To add a certain amount of cards to a player's pile
    Parameters:
    @param passedPile, a vector of cards passed by value. It holds the pile to be added to a player's existing pile
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Iterate through the 52 cards deck and through the passed in pile
    2) If there is a match, add the card to the existing pile
    Assistance Received: none
    ********************************************************************* */
    void AddtoPile(Vector<Card_Model> passedPile) {

        for(int i = 0; i < uniqueCards.size(); i++) {
            for(int j = 0; j < passedPile.size(); j++) {
                if(uniqueCards.get(i).GetCard().equals(passedPile.get(j).GetCard())) {
                    pile.add(passedPile.get(j));
                }
            }
        }
    }

    /** *********************************************************************
    Function Name: GetPile
    Purpose: To retrieve a player's pile
    Parameters: None
    Return Value: The pile of a players pile, a constant vector of cards value
    Local Variables: None
    Algorithm:
    1) Returns a player's pile
    Assistance Received: none
    ********************************************************************* */
    Vector<Card_Model> GetPile() {
        return pile;
    }

    /** *********************************************************************
    Function Name: AddToPlayerBuildCards
    Purpose: To add a card to the list of build cards a player must put down at some point to
    complete a build
    Parameters:
    @param buildCard, A build card to be added to the vector, a card type
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Cycle through and ensure the card passed in is a valid card
    2) Push the card to the back of the vector of build cards
    Assistance Received: none
    ********************************************************************* */
    void AddToPlayerBuildCards(Card_Model buildCard) {
        for(int i = 0; i < uniqueCards.size(); i++) {
            if(buildCard.GetCard().equals(uniqueCards.get(i).GetCard())) {
                playerBuildCards.add(buildCard);
            }
        }
    }

    /** *********************************************************************
     Function Name: GetBuildCards
     Purpose: Retrieve the build cards
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Return the buildCards
     Assistance Received: none
     ********************************************************************* */
    Vector<Card_Model> GetBuildCards() {
        return buildCards;
    }


    /** *********************************************************************
    Function Name: GetPlayerBuildCards
    Purpose: To retrieve the cards the player must use for a build
    Parameters: None
    Return Value:
    @return Vector<Card_Model>, the vector of cards the player must use to build, a vector of cards
    Local Variables: None
    Algorithm:
    1) Return the vector of cards the player must build with
    Assistance Received: none
    ********************************************************************* */
    Vector<Card_Model> GetPlayerBuildCards() {
        return playerBuildCards;
    }

    /** *********************************************************************
    Function Name: RemovePlayerBuildCard
    Purpose: To remove a card from the list of cards the player has tied to a build
    Parameters:
    @param card, a card object passed by value, the card to be removed from a build
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Cycle through the list of cards tied to a build and if the one that is passed in matches
    2) Remove it from the vector
    Assistance Received: none
    ********************************************************************* */
    void RemovePlayerBuildCard(Card_Model card) {

        // Cycle through the vector of cards and make sure what was passed in is a valid card
        for(int i = 0; i < playerBuildCards.size(); i++) {

            // If it is, erase the card from the list of build cards
            if(card.GetCard().equals(playerBuildCards.get(i).GetCard())) {
                playerBuildCards.remove(playerBuildCards.get(i));
                return;
            }
        }
    }

    /** *********************************************************************
    Function Name: GetPlayerWantBuild
    Purpose: To retrieve the character indicating if a player wants to capture a set or not
    Parameters: None
    Return Value: The character either being 'y' or 'n', a constant char value
    Local Variables: None
    Algorithm:
    1) Return the playerWantBuild variable
    Assistance Received: none
    ********************************************************************* */
    char GetPlayerWantBuild() {
        return playerWantBuild;
    }

    /** *********************************************************************
    Function Name: SetPlayerWantBuild
    Purpose: To retrieve the character indicating if a player wants to capture a set or not
    Parameters:
    @param choice, a char variable, used for holding the choice if a player wants to build or not
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Check if the choice variable passed in is either a 'y' or 'n'
    Assistance Received: none
    ********************************************************************* */
    void SetPlayerWantBuild(char choice) {
        if(Character.toLowerCase(choice) == 'y' || Character.toLowerCase(choice) == 'n') {
            playerWantBuild = choice;
        }
        else {
            Log.d("MyError", "Error in setting player want build in player model class.");
        }
    }

    /** *********************************************************************
    Function Name: GetPlayerWantSet
    Purpose: To retrieve the character indicating if a player wants to make a set or not
    Parameters: None
    Return Value:
     @return char, The character either being 'y' or 'n', a constant char value
    Local Variables: None
    Algorithm:
    1) Return the playerWantSet variable
    Assistance Received: none
    ********************************************************************* */
    char GetPlayerWantSet() {
        return playerWantSet;
    }

    /** *********************************************************************
    Function Name: GetPlayerSingleSetCards
    Purpose: To retrieve the cards the player wants to use for a set
    Parameters: None
    Return Value:
     @return Vector<Card_Model>The cards used for a set, a vector of cards value
    Local Variables: None
    Algorithm:
    1) Return the playerSetCards variable
    Assistance Received: none
    ********************************************************************* */
    Vector<Card_Model> GetPlayerSingleSetCards() {
        return playerSingleSetCards;
    }

    /** *********************************************************************
    Function Name: GetPlayerMultipleSetCards
    Purpose: To retrieve the cards the player wants to use for a set
    Parameters: None
    Return Value:
    @return Vector<Set_Model> The sets the player wants to capture, a vector of sets value
    Local Variables: None
    Algorithm:
    1) Return the playerOfSetCards variable
    Assistance Received: none
    ********************************************************************* */
    Vector<Set_Model> GetPlayerMultipleSetCards() {
        return playerMultipleSetCards;
    }

    /** *********************************************************************
    Function Name: GetPlayerWantSave
    Purpose: To retrieve the bool indicating if the player wants to save or not
    Parameters: None
    Return Value:
    @return boolean, The bool either being true or false, a constant boolean value
    Local Variables: None
    Algorithm:
    1) Return the playerWantSave variable
    Assistance Received: none
    ********************************************************************* */
    boolean GetPlayerWantSave() {
        return playerWantSave;
    }

    /** *********************************************************************
    Function Name: SetPrintTableBuildCards
    Purpose: To record any cards that were used in a build from the table
    Parameters:
    @param cards, a vector of cards passed by value, the cards that were from the table used for a build
    Return Value: Void
    Local Variables: None
        Algorithm:
    1) Iterate through the 52 card deck and see if the passed in card matches one of them
    2) If it does, put it into the vector
    Assistance Received: none
    ********************************************************************* */
    void SetPrintTableBuildCards(Vector<Card_Model> cards) {

        // Cycle through the cards being passed in
        for(int i = 0; i < uniqueCards.size(); i++) {

            // Cycle through the list of 52 cards and make sure the cards match one of them before putting it into the vector
            for(int j = 0; j < cards.size(); j++) {
                if(uniqueCards.get(i).GetCard().equals(cards.get(j).GetCard())) {
                    printTableBuildCards.add(cards.get(j));
                }
            }
        }
    }

    /** *********************************************************************
    Function Name: SetPrintTableCaptureCards
    Purpose: To record any cards from the table used in a capture
    Parameters:
    @param cards, a vector of cards passed by value, the cards to be recorder
    Return Value: Void
    Local Variables: None
    Algorithm:
    1) Iterate through the 52 card deck and see if the passed in card matches one of them
    2) If it does, return true, otherwise, return false
    Assistance Received: none
    ********************************************************************* */
    void SetPrintTableCaptureCards(Vector<Card_Model> cards) {

        // Cycle through the cards being passed in
        for(int i = 0; i < uniqueCards.size(); i++) {

            // Cycle through the list of 52 cards and make sure the cards match one of them before putting it into the vector
            for(int j = 0; j < cards.size(); j++) {
                if(uniqueCards.get(i).GetCard().equals(cards.get(j).GetCard())) {
                    printTableCaptureCards.add(cards.get(j));
                }
            }
        }
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
        Log.d("AI", "AI trying to make a build");


        // Cycling through the computer's hand and checking if there are cards that add up to that card
        for(int i = 0; i < hand.size(); i++) {
            handCardNumber = CardNumber(hand.get(i).GetNumber());

            Log.d("AIBuild", "In AI trying to make a build");
            // Cycling through the builds and if the card in the player's hand matches one of the cards
            // used to capture a build, then it will set skipCard to true and skip over that hand card
            for(int j = 0; j < tableBuilds.size(); j++) {
                Log.d("AIHand", hand.get(i).GetCard());
                Log.d("CaptureCard", tableBuilds.get(j).GetCaptureCardOfBuild().GetCard());
                if(hand.get(i).GetCard().equals(tableBuilds.get(j).GetCaptureCardOfBuild().GetCard())) {
                    Log.d("Skip", "Setting skipCard to true");
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
                boolean skipCard2 = false;

                // Skipping over the same exact computer card from the first for loop
                if (hand.get(i).GetCard().equals(hand.get(j).GetCard())) {
                    continue;
                }

                for (int l = 0; l < tableBuilds.size(); l++) {
                    if (hand.get(j).GetCard().equals(tableBuilds.get(l).GetCaptureCardOfBuild().GetCard())) {
                        skipCard2 = true;
                    }
                }

                if (!skipCard2) {
                    for (int k = 0; k < table.size(); k++) {
                        if ((CardNumber(hand.get(j).GetNumber()) + CardNumber(table.get(k).GetNumber())) == handCardNumber) {
                            Log.d("Success", "Actually creating the new build!");
                            newOrExistingBuild = 'n';
                            playerCard = hand.get(j);
                            buildCards.add(table.get(k));
                            return true;
                        }
                    }
                }
            }
        }

        // **** Adding to an existing build ****
        int buildValue;
        Vector<Integer> buildWithAddedCards = new Vector<Integer>();

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
        Log.d("AI", "Ai is capturing");

        tableCardsToBeCaptured.clear();
        Vector<Card_Model> currentBuild;
        int count = 0;
        boolean isCapturing = false;
        playerWantSet = 'n';
        playerMultipleSetCards.clear();

        // Checking to see if any builds can be captured

        Log.d("In AI", Integer.toString(tableBuilds.size()));

        // First we need to cycle through each of the builds on the table
        for(int i = 0; i < tableBuilds.size(); i++) {
            currentBuild = tableBuilds.get(i).GetBuildOfCards();
            Log.d("AICaptureBuild", "Inside of capturing a build in AI");

            // Saving the current build of cards to be printed after the computer makes their move
            printTableBuildCards = tableBuilds.get(i).GetBuildOfCards();

            // Then cycle through all of the cards in a specific build and add up the value of the cards
            Log.d("BuildSize", Integer.toString(currentBuild.size()));
            for(int j = 0; j < currentBuild.size(); j++) {
                Log.d("Value of card", Integer.toString(CardNumber(currentBuild.get(j).GetNumber())));
                count += CardNumber(currentBuild.get(j).GetNumber());
            }

            Log.d("CountBefore", Integer.toString(count));
            // Now we must go through the player's hand and see if any of the cards equal the sum of the build
            // If so, this function will return true and the build will be captured
            for(int j = 0; j < hand.size(); j++) {

                Log.d("IncaptureBuild", hand.get(j).GetCard());
                Log.d("Count", Integer.toString(count));

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

            count = 0;
        }

        Card_Model tableCardToBeCaptured = new Card_Model();

        // If there were no builds to capture, then we will check for any loose cards to capture
        // First cycling through the table
        for(int i = 0; i < table.size(); i++) {

            // Then through the player's hand
            for(int j = 0; j < hand.size(); j++) {

                // If any of them have the same card number value, the computer can capture the card
                if(CardNumber(hand.get(j).GetNumber()) == CardNumber(table.get(i).GetNumber())) {

                    //playerCard = hand.get(j);
                    tableCardToBeCaptured = table.get(i);

                    if(tableCardsToBeCaptured.size() > 0) {
                        for (int k = 0; k < tableCardsToBeCaptured.size(); k++) {
                            Log.d("CaptureCards", tableCardsToBeCaptured.get(k).GetCard());
                            if (CardNumber(tableCardsToBeCaptured.get(k).GetNumber()) == CardNumber(table.get(i).GetNumber()) &&
                                    !tableCardsToBeCaptured.get(k).GetCard().equals(table.get(i).GetCard())) {
                                playerCard = hand.get(j);
                                tableCardsToBeCaptured.add(table.get(i));
                            }
                        }
                    }
                    else {
                        playerCard = hand.get(j);
                        tableCardsToBeCaptured.add(table.get(i));
                    }
                    //tableCardsToBeCaptured.add(table.get(i));
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

            Log.d("AI", "AI is trying to get a set");
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
                            Log.d("We", "We are at the point of adding to the sets");
                            Vector<Card_Model> cardsForSetsAce = new Vector<Card_Model>();
                            cardsForSetsAce.add(table.get(i));
                            cardsForSetsAce.add(table.get(j));

                            // If the cards are not already in the set vector, then add them to it
                            playerWantSet = 'y';
                            tempCards.add(table.get(i));
                            tempCards.add(table.get(j));

                            // Then push these cards onto a set object and them to the vector of sets
                            //tempSet.SetCardsOfSet(tempCards);
                            tempSet.SetCardsOfSet(cardsForSetsAce);

                            playerMultipleSetCards.add(tempSet);
                            playerMultipleSetCards.get(multipleSetCardsCount).SetCardsOfSet(tempSet.GetCardOfSet());
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

                                Log.d("falsing", setOfCards.get(m).GetCard());
                                Log.d("tempFalsing", tempCards.get(m).GetCard());
                                if(setOfCards.get(m).GetCard().equals(tempCards.get(m).GetCard())) {
                                    Log.d("false", "Turning false");

                                    canCapture = false;
                                }
                            }
                        }

                        tempCards.clear();

                        // If the cards are not already in the set vector, then add them to it
                        if(canCapture) {
                            Vector<Card_Model> cardsForSets = new Vector<Card_Model>();
                            cardsForSets.add(table.get(i));
                            cardsForSets.add(table.get(j));
                            playerWantSet = 'y';
                            tempCards.add(table.get(i));
                            tempCards.add(table.get(j));

                            // Then push these cards onto a set object and them to the vector of sets
                            //tempSet.SetCardsOfSet(tempCards);
                            //playerMultipleSetCards.add(tempSet);
                            tempSet.SetCardsOfSet(cardsForSets);
                            playerMultipleSetCards.add(tempSet);
                            //tempCards.clear();
                        }
                    }
                }
            }
        }

        if(playerMultipleSetCards.size() == 0) {
            Log.d("This is not", "Size is still 0");
        }
        if(playerMultipleSetCards.size() > 0) {
            for (int i = 0; i < playerMultipleSetCards.get(0).GetCardOfSet().size(); i++) {
                Log.d("Crds in set", playerMultipleSetCards.get(0).GetCardOfSet().get(i).GetCard());
            }
        }

        return isCapturing;
    }

    /** *********************************************************************
    Function Name: AIMakeTrail
    Purpose: To pick the lowest card the computer has and trail
    Parameters: None
    Return Value: Void
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
            Log.d("MyError", "Error in finding card value in player model class.");
            return -1;
        }
    }

    /** *********************************************************************
     Function Name: AskForHelp
     Purpose: To find what the best move would be using the ai's capabilities
     Parameters:
     @param table, Vector<Card_Model> the cards on the table
     @param tableBuilds Vector<Card_Model> the cards on table that are builds
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Use the AI's ability to check for a build and if it can, it will display the cards to use
     2) If it can't build, use the AI's ability to check for a capture and if it can, display the cards to capture
     3) If the AI can't do either of those, then trail and output a card for the player to trail with
     Assistance Received: none
     ********************************************************************* */
    void AskForHelp(Vector<Card_Model> table, Vector<Build_Model> tableBuilds) {

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
            Log.d("MyError", "Error in setting the number of card in player model class.");
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
            Log.d("MyError", "Error in finding the suit in the player model class.");
            return "ERROR";
        }
    }

    /***********************************************************************
     Function Name: SetPlayerMove
     Purpose: To set the move of the player
     Parameters:
     @param passedMove, char the player's move
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Set the move that was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetPlayerMove(char passedMove) {
        playerMove = passedMove;
    }

    /***********************************************************************
     Function Name: SetPlayerCard
     Purpose: To set the card the player wants to use
     Parameters:
     @param passedPlayerCard, Card_Model the card the player wants to use
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Set the card that was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetPlayerCard(Card_Model passedPlayerCard) {

        for(int i = 0; i < uniqueCards.size(); i++) {
            if(uniqueCards.get(i).GetCard().equals(passedPlayerCard.GetCard())) {
                playerCard = passedPlayerCard;
            }
        }
    }

    /** *********************************************************************
     Function Name: SetTableCardToBeCaptured
     Purpose: To set the table cards the player wants to capture
     Parameters:
     @param passedCards Vector of card_model, holds the cards to be captured from the table
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Clear what was in the vector to begin with
     2) Make sure the cards passed in were valid and add them to the variable to be used
     when checking if the capture was valid in the round_model
     Assistance Received: none
      ********************************************************************* */
    void SetTableCardToBeCaptured(Vector<Card_Model> passedCards) {

        tableCardsToBeCaptured.clear();

        for(int i = 0; i < uniqueCards.size(); i++) {
            for(int j = 0; j < passedCards.size(); j++) {
                if(uniqueCards.get(i).GetCard().equals(passedCards.get(j).GetCard())) {
                    tableCardsToBeCaptured.add(passedCards.get(j));
                }
            }
        }
    }

    /** *********************************************************************
     Function Name: GetTableCardsToBeCaptured
     Purpose: To retrive the table cards that are meant to be captured
     Parameters: None
     Return Value:
     @return Vector<Card_Model>
     Local Variables: None
     Algorithm:
     1) Return the tableCardsToBeCaptured variable
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> GetTableCardsToBeCaptured() {
        return tableCardsToBeCaptured;
    }

    /** *********************************************************************
     Function Name: SetPlayerWantSet
     Purpose: To set the playerWantSet variable either 'y' or 'n'
     Parameters:
     @param choice a char, holds the character for capturing a set
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) If the choice passed in was 'y' or 'n', set the variable to what was passed in
     2) Otherwise, report an error to the log
     Assistance Received: none
      ********************************************************************* */
    void SetPlayerWantSet(char choice) {
        if(choice == 'y' || choice == 'n') {
            playerWantSet = choice;
        }
        else {
            Log.d("MyError", "Error in setting the playerWantSet in the player model class.");
        }
    }

    /** *********************************************************************
     Function Name: AddSetToPlayer
     Purpose: To add a set to the vector of sets to be captured
     Parameters:
     @param setCards Set_Model object, holds the set of two cards to be added
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Add the set object to the vector of set objects
     Assistance Received: none
      ********************************************************************* */
    void AddSetToPlayer(Set_Model setCards) {
        playerMultipleSetCards.add(setCards);
    }

    /** *********************************************************************
     Function Name: ClearPlayerMultipleSets
     Purpose: To empty everything out of the vector of set objects
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Clear the playerMultipleSetCards vector
     Assistance Received: none
      ********************************************************************* */
    void ClearPlayerMultipleSets() {
        playerMultipleSetCards.clear();
    }

    /** *********************************************************************
     Function Name: SetPlayerWantNewOrExisting
     Purpose: Set whether a player wants a new or existing build
     Parameters:
     @param choice, a char which holds the choice of the player
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) If the choice is 'n' or 'e', set the choice
     2) Otherwise, report an error
     Assistance Received: none
      ********************************************************************* */
    void SetPlayerWantNewOrExisting(char choice) {

        if(choice == 'n' || choice == 'e') {
            newOrExistingBuild = choice;
        }
        else {
            Log.d("MyError", "Error in setting the new or existing variable in player model class.");
        }
    }

    /** *********************************************************************
     Function Name: GetPlayerMove
     Purpose: To retrieve a player's move choice
     Parameters: None
     Return Value: char
     Local Variables:None
     Algorithm:
     1) Return the playerMove variable
     Assistance Received: none
      ********************************************************************* */
    char GetPlayerMove() {
        return playerMove;
    }

    /** *********************************************************************
     Function Name: SetPlayerBuildCards
     Purpose: To set the playerBuildCards variable to what was passed in
     Parameters:
     @param passedBuildCards, Vector<Card_Model>, the build cards a player wants to use
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) If all of the cards in the vector are valid cards, set the buildCards to what was
     passed in
     Assistance Received: none
      ********************************************************************* */
    void SetPlayerBuildCards(Vector<Card_Model> passedBuildCards) {

        // Cycling through to make sure each card is valid
        int count = 0;
        for(int i = 0; i < uniqueCards.size(); i++) {
            for(int j = 0; j < passedBuildCards.size(); j++) {
                if(uniqueCards.get(i).GetCard().equals(passedBuildCards.get(j).GetCard())) {
                    count++;
                }
            }
        }

        // If the count matches the size of the vector, then all of the cards were valid
        if(count == passedBuildCards.size()) {
            playerBuildCards = passedBuildCards;
        }
    }

    /** *********************************************************************
     Function Name: SetBuildCards
     Purpose: To set the buildCards variable to what was passed in
     Parameters:
     @param passedBuildCards, Vector<Card_Model>
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) If all of the cards in the vector are valid cards, set the buildCards to what was
     passed in
     Assistance Received: none
      ********************************************************************* */
    void SetBuildCards(Vector<Card_Model> passedBuildCards) {

        // Cycling through the cards to make sure they are all valid
        int count = 0;
        for(int i = 0; i < uniqueCards.size(); i++) {
            for(int j = 0; j < passedBuildCards.size(); j++) {
                if(uniqueCards.get(i).GetCard().equals(passedBuildCards.get(j).GetCard())) {
                    count++;
                }
            }
        }

        // If the count matches the size of the vector passed in, then they were all valid
        if(count == passedBuildCards.size()) {
            buildCards = passedBuildCards;
        }
    }

    /** *********************************************************************
     Function Name: SSetExistingbuildCard
     Purpose: To set the existingBuildCard to what was passed in
     Parameters:
     @param passedCard, Card_Model
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) If the card that was passed in is valid, set the existingBuildCard variable
     passed in
     Assistance Received: none
      ********************************************************************* */
    void SetExistingBuildCard(Card_Model passedCard) {

        for(int i = 0; i < uniqueCards.size(); i++) {
            if(uniqueCards.get(i).GetCard().equals(passedCard.GetCard())) {
                existingBuildCard = passedCard;
            }
        }
    }

    /** *********************************************************************
     Function Name: RemoveFromPlayerBuildCards
     Purpose: To remove a card from a player's build cards
     Parameters:
     @param cardValue, int
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Cycle through the build cards and if there is a card with a value
     that matches what was passed in, remove it
     Assistance Received: none
      ********************************************************************* */
    void RemoveFromPlayerBuildCards(int cardValue) {

        for(int i = 0; i < playerBuildCards.size(); i++) {
            if(cardValue == CardNumber(playerBuildCards.get(i).GetNumber())) {
                playerBuildCards.remove(i);
            }
        }
    }


}

