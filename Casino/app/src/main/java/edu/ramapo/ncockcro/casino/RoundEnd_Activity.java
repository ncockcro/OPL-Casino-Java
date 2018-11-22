package edu.ramapo.ncockcro.casino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

        Intent intent = getIntent();

        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("PlayerPileSize")); i++) {
            Log.d("Player at end", intent.getStringExtra("PlayerPile" + i));
            playerPile.add(new Card_View(intent.getStringExtra("PlayerPile" + i)));
        }

        for(int i = 0; i < Integer.parseInt(intent.getStringExtra("ComputerPileSize")); i++) {
            computerPile.add(new Card_View(intent.getStringExtra("ComputerPile" + i)));
        }

        tournamentView.DrawResults(this, playerPile, computerPile);


    }
}
