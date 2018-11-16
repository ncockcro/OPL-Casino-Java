package edu.ramapo.ncockcro.casino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        roundModel.SetPlayerMove('c');
        roundView.ShowCaptureButtons();
        roundModel.SetCaptureInfo();
    }

    public void CaptureDoneButtonPressed(View view) {

        roundModel.PlayerMakeMove();
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
        roundModel.PlayerMakeMove();
        roundView.UpdateScreen(this);
        roundView.ShowHumanButtons();

    }
}
