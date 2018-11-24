package edu.ramapo.ncockcro.casino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;

import java.util.Random;
import java.util.Vector;

public class CoinToss extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_toss);


    }
    String coinTossWinner;

    /** *********************************************************************
     Function Name: heads
     Purpose: If the user clicks heads or tails, this function will determine who goes first
     Parameters:
        @param view, View, the view for the button that was clicked
     Return Value: Void
     Local Variables: None
     Algorithm:
        1) Generate a random number between 0 and 1 as the "coin"
        2) If the user's guess matched the random number, output they were correct and pass "Human"
        3) Otherwise, output they were wrong and pass "Computer"
     Assistance Received: none
      ********************************************************************* */
    public void heads(View view) {

        Random random = new Random();

        // Get a random number, either 0 or 1
        int randomCoin = random.nextInt(2);

        // Capture the layout's TextView and set the string as its text
        setContentView(R.layout.activity_coin_toss);
        TextView textView = (TextView) findViewById(R.id.CoinTossTextView);



        // If the user clicked heads button...
        if(view.getId() == R.id.HeadsButton) {

            // If the user's guess of heads was correct...
            if(randomCoin == 0) {
                // Output that the answer was correct by clicking on the heads
                textView.setTextColor(0xFF2ea54a);
                textView.setText("Your guess was correct! Human goes first.");
                coinTossWinner = "Human";
            }

            else {
                // Output that the answer was wrong by clicking heads and the computer goes first
                textView.setTextColor(0xFFFF0000);
                textView.setText("Your guess was incorrect. Computer goes first.");
                coinTossWinner = "Computer";
            }
        }
        else {

            if(randomCoin == 1) {
                // Output that the answer was correct by clicking on the tails
                textView.setTextColor(0xFF2ea54a);
                textView.setText("Your guess was correct! Human goes first.");
                coinTossWinner = "Human";
            }
            else {
                // Output that the answer was incorrect by clicking on tails and the computer goes first
                textView.setTextColor(0xFFFF0000);
                textView.setText("Your guess was incorrect. Computer goes first.");
                coinTossWinner = "Computer";
            }
        }

        // Disabling the buttons so the user can't click for another guess
        findViewById(R.id.HeadsButton).setEnabled(false);
        findViewById(R.id.TailsButton).setEnabled(false);
        findViewById(R.id.PlayButton).setVisibility(View.VISIBLE);



    }

    /** *********************************************************************
     Function Name: Play
     Purpose: When the user clicks the play button, it will send them to the main game screen
     Parameters:
        @param view, View, the view of the button that was pressed
     Return Value: Void
     Local Variables: None
     Algorithm:
        1) Start up the main game activity
     Assistance Received: none
      ********************************************************************* */
    public void Play(View view) {
        Intent intent = new Intent(this, MainGame_Activity.class);
        intent.putExtra("coinWinner", coinTossWinner);
        intent.putExtra("humanScore", "0");
        intent.putExtra("computerScore", "0");
        intent.putExtra("currentRound", "0");
        startActivity(intent);
    }
}
