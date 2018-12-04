package edu.ramapo.ncockcro.casino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Vector;

public class RoundEnd_Activity extends AppCompatActivity {

    private Vector<Card_View> playerPile = new Vector<Card_View>();
    private Vector<Card_View> computerPile = new Vector<Card_View>();
    private Tournament_View tournamentView;
    private Tournament_Model tournamentModel= new Tournament_Model();

    /** *********************************************************************
     Function Name: onCreate
     Purpose: When the activity is started, display the piles and scores
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Get the information from the round activity and pass it to the tournamentView
     to display all the necessary information
     Assistance Received: none
      ********************************************************************* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_end_);
        tournamentView = new Tournament_View(this, tournamentModel);

        this.findViewById(R.id.endGameButton).setVisibility(View.GONE);
        Intent intent = getIntent();

        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("PlayerPileSize")); i++) {
            playerPile.add(new Card_View(intent.getStringExtra("PlayerPile" + i)));
        }

        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("ComputerPileSize")); i++) {
            computerPile.add(new Card_View(intent.getStringExtra("ComputerPile" + i)));
        }

        tournamentModel.SetHumanPoints(Integer.parseInt(intent.getStringExtra("humanScore")));
        tournamentModel.SetComputerPoints(Integer.parseInt(intent.getStringExtra("computerScore")));
        tournamentModel.SetCurrentRound(Integer.parseInt(intent.getStringExtra("currentRound")));
        tournamentModel.SetLastCapture(intent.getStringExtra("lastCapture"));

        tournamentView.DrawResults(this, playerPile, computerPile);

        if(tournamentModel.GameWon() > 0){
            TextView gameWonText = this.findViewById(R.id.gameWonTextView);
            if(tournamentModel.GetWinnerColor().equals("Green")) {
                gameWonText.setTextColor(0xFF2ea54a);
            }
            else if(tournamentModel.GetWinnerColor().equals("Red")) {
                gameWonText.setTextColor(0xFFFF0000);
            }
            else {
                gameWonText.setTextColor(0xFFf4e842);
            }
            gameWonText.append(tournamentModel.GetWinnerMessage());
            this.findViewById(R.id.nextRoundButton).setVisibility(View.GONE);
            this.findViewById(R.id.endGameButton).setVisibility(View.VISIBLE);
        }


    }

    /** *********************************************************************
     Function Name: NextRoundButtonPressed
     Purpose: When the player wants to play the next round, this view will get triggered
     Parameters:
     @param view, View object
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Get information from the tournament model such as the player's scores
     and round to be passed on to the new round
     2) Start the new round by calling the main game activity
     Assistance Received: none
     ********************************************************************* */
    public void NextRoundButtonPressed(View view) {
        Intent intentRound = new Intent(this, MainGame_Activity.class);

        String humanScoreString = Integer.toString(tournamentModel.GetHumanPoints());
        String computerScoreString = Integer.toString(tournamentModel.GetComputerPoints());
        String roundString = Integer.toString(tournamentModel.GetRound());

        intentRound.putExtra("coinWinner", tournamentModel.GetLastCapture());
        Log.d("humanScore", humanScoreString);
        Log.d("computerScore", computerScoreString);
        intentRound.putExtra("humanScore", humanScoreString);
        intentRound.putExtra("computerScore", computerScoreString);
        intentRound.putExtra("currentRound", roundString);
        intentRound.putExtra("loadGame", "false");

        startActivity(intentRound);


    }

    /** *********************************************************************
     Function Name: EndGameButtonPressed
     Purpose: When the game is over, the user will press this button and be triggered
     Parameters:
     @param view, View object
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Exit out of the game
     Assistance Received: none
     ********************************************************************* */
    public void EndGameButtonPressed(View view) {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
