package edu.ramapo.ncockcro.casino;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.Vector;

public class MainGame_Activity extends AppCompatActivity {

    Round_View roundView;
    Round_Model roundModel = new Round_Model();
    Intent intent = getIntent();
    String coinTossWinner;


    /** *********************************************************************
     Function Name: onCreate
     Purpose: To initialize the main screen of the game when it is initialized
     Parameters:
     @param savedInstanceState
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Initialize the roundView object
     2) Deal cards to the human and draw them to the screen
     3) Deal cards to the computer and draw them to the screen
     4) Get the coin toss winner to know how plays first
     Assistance Received: none
     ********************************************************************* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_);

        roundView = new Round_View(this, roundModel);

        Intent intent = getIntent();
        coinTossWinner = intent.getStringExtra("coinWinner");

        // Loading in the scores and the current round
        roundView.SetHumanScore(Integer.parseInt(intent.getStringExtra("humanScore")));
        roundView.SetComputerScore(Integer.parseInt(intent.getStringExtra("computerScore")));
        roundView.SetRound(Integer.parseInt(intent.getStringExtra("currentRound")));
        roundModel.SetFirstPlayer(coinTossWinner);

        // If we aren't loading in a game...
        if(intent.getStringExtra("loadGame").equals("false")) {
            roundView.DrawFourCardsHumanAndComputer();
            roundView.DrawFourCardsTable(this);
            roundView.DrawDeck(this);
        }

        // Otherwise, we are loading from a save
        else if(intent.getStringExtra("loadGame").equals("true")) {

        roundModel.SetComputerPoints(intent.getStringExtra("computerScore"));

        // Loading in the computer's hand
        Vector<Card_Model> computerHandTempCards = new Vector<Card_Model>();
        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("loadGameComputerHandSize")); i++) {
            computerHandTempCards.add(new Card_Model(intent.getStringExtra("loadGameComputerHand" + i)));
        }
        roundModel.SetPlayerHand(1, computerHandTempCards);

        // Loading in the computer's pile
        Vector<Card_Model> computerPileTempCards = new Vector<Card_Model>();
        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("computerPileSize")); i++) {
            computerPileTempCards.add(new Card_Model(intent.getStringExtra("loadGameComputerPile" + i)));
        }

        roundModel.SetPlayerPile(1, computerPileTempCards);

        // Loading in the human's score
        roundModel.SetHumanPoints(intent.getStringExtra("humanScore"));

        // Loading in the human's hand
        Vector<Card_Model> humanHandTempCards = new Vector<Card_Model>();
        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("humanHandSize")); i++) {
            humanHandTempCards.add(new Card_Model(intent.getStringExtra("loadGameHumanHand" + i)));
        }

        roundModel.SetPlayerHand(0, humanHandTempCards);

        // Loading in the human's pile
        Vector<Card_Model> humanPileTempCards = new Vector<Card_Model>();
        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("humanPileSize")); i++) {
            humanPileTempCards.add(new Card_Model(intent.getStringExtra("loadGameHumanPile" + i)));
        }

        roundModel.SetPlayerPile(0, humanPileTempCards);

        // Loading in the table
        Vector<Card_Model> tableTempCards = new Vector<Card_Model>();
        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("tableSize")); i++) {
            tableTempCards.add(new Card_Model(intent.getStringExtra("loadGameTable" + i)));
        }

        roundModel.LoadTable(tableTempCards);

        // Loading in the deck
        Vector<Card_Model> deckTempCards = new Vector<Card_Model>();
        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("deckSize")); i++) {
            deckTempCards.add(new Card_Model(intent.getStringExtra("loadGameDeck" + i)));
        }
        Deck_Model tempDeck = new Deck_Model();
        tempDeck.SetDeck(deckTempCards);
        roundModel.SetDeck(tempDeck);

        // Loading in the builds
        Vector<Card_Model> tempCards = new Vector<Card_Model>();
        Vector<Build_Model> tempBuilds = new Vector<Build_Model>();
        Build_Model tempBuildSingle = new Build_Model();
        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("buildSize")); i++) {

            roundModel.CreateNewBuildOnTable();

            for(int j = 0; j < Integer.parseInt(intent.getStringExtra("buildCardSize" + i)); j++) {
                //tempCards.add(new Card_Model(intent.getStringExtra("loadGameBuilds" + i + j)));
                roundModel.AddCardToBuild(i, new Card_Model(intent.getStringExtra("loadGameBuilds" + i + j)));
            }
            tempBuildSingle.SetBuildOfCards(tempCards);
            tempBuilds.add(tempBuildSingle);

        }

        //roundModel.SetTableBuilds(tempBuilds);
        roundModel.SetBuildCounter(Integer.parseInt(intent.getStringExtra("buildSize")));

        // Setting the owners of each build
        Vector<String> buildOwners = new Vector<String>();
        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("buildOwnerSize")); i++) {
            buildOwners.add(intent.getStringExtra("buildOwner" + i));
        }

        roundModel.SetBuildOwners(buildOwners);
        roundModel.SetCaptureCardForBuilds();

        // Loading in the current player
        roundModel.SetCurrentPlayer(intent.getStringExtra("nextPlayer"));

        }

        roundView.SetPlayerButtons();
        roundView.UpdateScreen(this);

        // Disabling the human's cards if the computer is going first
        if(intent.getStringExtra("loadGame").equals("true")) {
            if (intent.getStringExtra("nextPlayer").equals("Computer")) {
                roundView.DisableHumanCards();
            }
        }

    }

    /***********************************************************************
     Function Name: HumanCardPressed
     Purpose: When a card from the human hand is pressed, highlight it
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Call roundView to highlight the card that was clicked
     Assistance Received: none
     ********************************************************************* */
    public void HumanCardPressed(View view) {
        roundView.HighlightCard(view);

    }

    /** *********************************************************************
     Function Name: BuildButtonPressed
     Purpose: When the player wants to build, it sets the move and changes the buttons
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Set the build info for the card the player is building with
     2) Enable the build buttons for further gameplay
     Assistance Received: none
      ********************************************************************* */
    public void BuildButtonPressed(View view) {

        // Enable the table cards to be clicked, set the player's move to 'b', and show the build buttons
        roundView.EnableTableButtons();
        roundModel.SetPlayerMove('b');
        roundView.ShowBuildButtons();
        roundView.DisableTableButtons();
    }

    /** *********************************************************************
     Function Name: NewBuildButtonPressed
     Purpose: When the player wants to make a new build, this sets the necessary parameters for that
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Set the build info to 'n' for wanting a new build
     2) Show the build buttons to capture the build
     Assistance Received: none
      ********************************************************************* */
    public void NewBuildButtonPressed(View view) {
        roundModel.SetPlayerModelWantNewOrExisting('n');
        roundView.ShowNewOrExistingBuildButtons();
        roundView.EnableTableButtons();
        roundView.DisableBuildButtons();
    }

    /** *********************************************************************
     Function Name: ExistingBuildButtonPressed
     Purpose: When the player wants to add to an existing build, this sets the necessary parameters for that
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Set the build info to 'e' for wanting an existing build
     2) Show the builds buttons to add to an existing build
     Assistance Received: none
      ********************************************************************* */
    public void ExistingBuildButtonPressed(View view) {
        roundModel.SetPlayerModelWantNewOrExisting('e');
        roundView.EnableBuildButtons();
        roundView.ShowNewOrExistingBuildButtons();
        roundView.DisableTableButtons();
        roundView.EnableBuildButtons();
    }

    /** *********************************************************************
     Function Name: CaptureButtonPressed
     Purpose: When the player wants to capture, it sets the move and changes the buttons
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Set the trail info for the card the player is trailing with
     2) Have the player try and make the move
     3) If the move was successful, update the screen to reflect the changes from the trail
     Assistance Received: none
      ********************************************************************* */
    public void CaptureButtonPressed(View view) {

        roundView.EnableTableButtons();
        roundView.DisableBuildButtons();
        roundModel.SetPlayerMove('c');
        roundView.ShowCaptureButtons();
    }

    /** *********************************************************************
     Function Name: CaptureBuildButtonPressed
     Purpose: When the player wants to capture a build, it sets the move and changes the buttons
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Check and make sure there are actual builds on the table for the player to capture...
     2) If so, set the move and show the appropriate buttons
     Assistance Received: none
      ********************************************************************* */
    public void CaptureBuildButtonPressed(View view) {

        roundModel.CheckForBuilds();
        roundView.SetCapturingBuild(true);
        if(!roundView.CaptureBuildPrintError()) {
            roundModel.SetPlayerModelWantBuild('y');
            roundView.EnableBuildButtons();
            roundView.ShowCaptureBuildButtons();

        }
    }

    /** *********************************************************************
     Function Name: InsideCaptureBuildButtonPressed
     Purpose: To make the move for capturing a build
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Make the move in the model
     2) If there no errors, switch the buttons on the table and update the screen
     Assistance Received: none
      ********************************************************************* */
    public void InsideCaptureBuildButtonPressed(View view) {

        roundView.SendTableCaptureCards();
        roundModel.PlayerMakeMove();

        if(!roundView.PrintErrors()) {
            roundView.DisableTableButtons();
            roundView.CheckForDealingCards(this);
            roundView.UpdateScreen(this);
        }
    }

    /** *********************************************************************
     Function Name: CaptureSetButtonPressed
     Purpose: Let the model know the user is capturing a set and show the capture buttons
     Parameters:
     @param view View object, holds the button that was pressed
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Set the playerWantSet to 'y' in the model
     2) Show the capture buttons for gettings sets
     Assistance Received: none
      ********************************************************************* */
    public void CaptureSetButtonPressed(View view) {
        roundModel.SetPlayerModelWantSet('y');
        roundView.ShowCaptureSetButtons();
        roundView.DisableBuildButtons();
    }

    /** *********************************************************************
     Function Name: CaptureDoneButtonPressed
     Purpose: To make a capture move when the capture done button is pressed
     Parameters:
     @param view View object, holds the button that was pressed
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Send the capture info over to the model
     2) Have the player make a move
     3) If there were no errors, then switch the UI for a computer move and check for
     dealing cards
     Assistance Received: none
      ********************************************************************* */
    public void DoneButtonPressed(View view) {

        // If the done button was pressed while capturing, set the capture info
        if(roundModel.GetPlayerMove() == 'c') {
            roundView.SetCaptureInfo();
        }
        // Otheriwse, the done button had to be pressed for a build
        else {
            roundView.SetBuildInfo();
        }

        roundModel.PlayerMakeMove();
        roundView.SetCapturingBuild(false);

        if(!roundView.PrintErrors()) {
            roundView.DisableTableButtons();
            roundView.CheckForDealingCards(this);
            roundView.UpdateScreen(this);
        }

        roundView.UpdateScreen(this);

        for(int i = 0; i < roundModel.GetPlayerPile(0).size(); i++) {
            Log.d("AfterPile", roundModel.GetPlayerPile(0).get(i).GetCard());
        }
    }

    /** *********************************************************************
     Function Name: ConvertModelToView
     Purpose: To convert a vector of card_model's to card_view's
     Parameters:
     @param view View object, holds the button that was pressed
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Add the two cards the user clicked and send them to the model
     Assistance Received: none
      ********************************************************************* */
    public void AddSetButtonPressed(View view) {
        roundView.AddSetToPlayer();
    }

    /** *********************************************************************
     Function Name: TrailCardPressed
     Purpose: When the player wants to trail, it sets the trail info and calls for the player to make move
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Set the trail info for the card the player is trailing with
     2) Have the player try and make the move
     3) If the move was successful, update the screen to reflect the changes from the trail
     Assistance Received: none
     ********************************************************************* */
    public void TrailCardPressed(View view) {

        roundView.SetTrailInfo();
        roundModel.PlayerMakeMove();

        if(!roundView.PrintErrors()) {
            roundView.CheckForDealingCards(this);
            roundView.UpdateScreen(this);
        }
        roundView.UpdateScreen(this);
    }

    /***********************************************************************
     Function Name: ComputerMoveButtonPressed
     Purpose: To have the computer make a move when the button is pressed
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Have the ai make a move
     2) Update the screen
     Assistance Received: none
     ********************************************************************* */
    public void ComputerMoveButtonPressed(View view) {

        roundModel.PlayerMakeMove();
        roundView.OutputPlayerMessages();

        if(!roundView.PrintErrors()) {
            int result = roundView.CheckForDealingCards(this);
            roundView.UpdateScreen(this);

            // If the result equals 3, then the round is over and we shouldnt show the human buttons
            if(result != 3) {
                roundView.ShowHumanButtons();
            }
        }

    }

    /** *********************************************************************
     Function Name: BackButtonPressed
     Purpose: When the player wants to go back, this clears all the information they made
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Clear all of the information the player made with what they were doing
     Assistance Received: none
      ********************************************************************* */
    public void BackButtonPressed(View view) {
        roundView.ClearData();
        roundView.UpdateScreen(this);
    }

    /** *********************************************************************
     Function Name: SeeResultsButtonPressed
     Purpose: When the round is over, this button appears to see the end of round results
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Create a new intent
     2) Put all of the necessary information about the player's scores into the intent
     3) Then start the activity for the RoundEnd
     Assistance Received: none
      ********************************************************************* */
    public void SeeResultsButtonPressed(View view) {

        // Creating a new intent
        Intent intentResults = new Intent(this, RoundEnd_Activity.class);
        Vector<Card_Model> tempPlayerPile = roundModel.GetPlayerPile(0);
        Vector<Card_Model> tempComputerPile = roundModel.GetPlayerPile(1);

        // Pushing the player's pile into the intent
        for(int i = 0; i < tempPlayerPile.size(); i++) {
            intentResults.putExtra("PlayerPile" + i, tempPlayerPile.get(i).GetCard());
        }

        // Pushing the size of the player's pile into the intent
        String playerPileSize = Integer.toString(tempPlayerPile.size());
        intentResults.putExtra("PlayerPileSize", playerPileSize);

        // Pushing the computer's pile onto the intent
        for(int i = 0; i < tempComputerPile.size(); i++) {
            intentResults.putExtra("ComputerPile" + i, tempComputerPile.get(i).GetCard());
        }

        // Pushing the computer's pile size onto the intent
        String computerPileSize = Integer.toString(tempComputerPile.size());
        intentResults.putExtra("ComputerPileSize", computerPileSize);

        // Passed the current scores of the player's
        String humanScoreString = Integer.toString(roundView.GetHumanScore());
        String computerScoreString = Integer.toString(roundView.GetComputerScore());
        String roundString = Integer.toString(roundView.GetRound());

        intentResults.putExtra("humanScore", humanScoreString);
        intentResults.putExtra("computerScore", computerScoreString);
        intentResults.putExtra("currentRound", roundString);
        intentResults.putExtra("lastCapture", roundModel.GetLastCapture());

        // Starting the activity
        startActivity(intentResults);
    }

    /** *********************************************************************
     Function Name: SaveButtonPressed
     Purpose: When the player wants to save, this will open a prompt for them to type in a
     name for their save file
     Parameters:
     @param view
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Prompt the user with an input field to save
     Assistance Received: none
      ********************************************************************* */
    public void SaveButtonPressed(View view) {
        roundView.ShowSaveTextInput(this);
    }

    /** *********************************************************************
     Function Name: HelpButtonPressed
     Purpose: When the help button is pressed, generate a help statement for the player
     Parameters:
     @param view, View object
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Call the model to generate the help messages
     2) Call the view to show the help messages
     Assistance Received: none
      ********************************************************************* */
    public void HelpButtonPressed(View view) {

        // So long as the current player is the human...
        if(roundModel.GetCurrentPlayer() == 0) {
            roundModel.GetPlayerHelpOutputMessages();
            roundView.OutputHelpMessages();
        }
    }
}
