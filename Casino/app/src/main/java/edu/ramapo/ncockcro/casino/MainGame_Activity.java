package edu.ramapo.ncockcro.casino;

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

        roundView.DrawFourCardsHumanAndComputer();
        roundView.DrawFourCardsTable(this);
        roundView.DrawDeck(this);

        Intent intent = getIntent();
        coinTossWinner = intent.getStringExtra("coinWinner");
        roundModel.SetFirstPlayer(coinTossWinner);
        roundView.SetPlayerButtons();

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

        roundView.PrintTableToOutput();
        roundView.EnableTableButtons();
        roundModel.SetPlayerMove('b');
        roundView.ShowBuildButtons();
        roundView.DisableTableButtons();
    }

    public void NewBuildButtonPressed(View view) {
        roundModel.SetPlayerModelWantNewOrExisting('n');
        roundView.ShowNewOrExistingBuildButtons();
        roundView.EnableTableButtons();
    }

    public void ExistingBuildButtonPressed(View view) {
        roundModel.SetPlayerModelWantNewOrExisting('e');
        roundView.ShowNewOrExistingBuildButtons();
        roundView.EnableTableButtons();
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

        roundView.PrintTableToOutput();
        roundView.EnableTableButtons();
        roundModel.SetPlayerMove('c');
        roundView.ShowCaptureButtons();
    }

    public void CaptureBuildButtonPressed(View view) {

        roundModel.CheckForBuilds();
        if(!roundView.CaptureBuildPrintError()) {
            Log.d("Yes", "Sending yes to the model");
            roundModel.SetPlayerModelWantBuild('y');
            roundView.ShowCaptureBuildButtons();

        }
    }

    public void InsideCaptureBuildButtonPressed(View view) {

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

        if(roundModel.GetPlayerMove() == 'c') {
            roundView.SetCaptureInfo();
        }
        else {
            roundView.SetBuildInfo();
        }
        roundModel.PlayerMakeMove();

        if(!roundView.PrintErrors()) {
            roundView.DisableTableButtons();
            roundView.CheckForDealingCards(this);
            roundView.UpdateScreen(this);
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

        roundView.PrintTableToOutput();
        roundView.SetTrailInfo();
        roundModel.PlayerMakeMove();

        if(!roundView.PrintErrors()) {
            roundView.CheckForDealingCards(this);
            roundView.UpdateScreen(this);
        }
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

        roundView.PrintTableToOutput();

        roundModel.PlayerMakeMove();

        if(!roundView.PrintErrors()) {
            roundView.CheckForDealingCards(this);
            roundView.UpdateScreen(this);
            roundView.ShowHumanButtons();
        }

    }

    public void BackButtonPressed(View view) {
        roundView.ClearData();
    }

    public void SeeResultsButtonPressed(View view) {
        Intent intentResults = new Intent(this, RoundEnd_Activity.class);
        Vector<Card_Model> tempPlayerPile = roundModel.GetPlayerPile(0);
        Vector<Card_Model> tempComputerPile = roundModel.GetPlayerPile(1);


        for(int i = 0; i < tempPlayerPile.size(); i++) {
            intentResults.putExtra("PlayerPile" + i, tempPlayerPile.get(i).GetCard());
        }

        String playerPileSize = Integer.toString(tempPlayerPile.size());
        Log.d("playerPileSize", Integer.toString(tempPlayerPile.size()));
        //intentResults.putExtra("PlayerPileSize", tempPlayerPile.size());
        intentResults.putExtra("PlayerPileSize", playerPileSize);

        for(int i = 0; i < tempComputerPile.size(); i++) {
            intentResults.putExtra("ComputerPile" + i, tempComputerPile.get(i).GetCard());
        }

        String computerPileSize = Integer.toString(tempComputerPile.size());
        intentResults.putExtra("ComputerPileSize", computerPileSize);

        startActivity(intentResults);
    }
}
