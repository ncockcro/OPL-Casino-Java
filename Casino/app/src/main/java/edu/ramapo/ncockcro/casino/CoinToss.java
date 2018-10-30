package edu.ramapo.ncockcro.casino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class CoinToss extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_toss);


    }

    public void heads(View view) {

        Random random = new Random();

        // Get a random number, either 0 or 1
        int randomCoin = random.nextInt(2);

        // Capture the layout's TextViewand set the string as its text
        setContentView(R.layout.activity_coin_toss);
        TextView textView = (TextView) findViewById(R.id.CoinTossTextView);


        // If the user clicked heads button...
        if(view.getId() == R.id.HeadsButton) {

            // If the user's guess of heads was correct...
            if(randomCoin == 0) {
                // Output that the answer was correct by clicking on the heads
                textView.setText("Your guess was correct! Human goes first.");
            }

            else {
                // Output that the answer was wrong by clicking heads and the computer goes first
                textView.setText("Your guess was incorrect. Computer goes first.");
            }

        }
        else {

            if(randomCoin == 1) {
                // Output that the answer was correct by clicking on the tails
                textView.setText("Your guess was correct! Human goes first.");
            }
            else {
                // Output that the answer was incorrect by clicking on tails and the computer goes first
                textView.setText("Your guess was incorrect. Computer goes first.");
            }
        }

        // Disabling the buttons so the user can't click for another guess
        findViewById(R.id.HeadsButton).setEnabled(false);
        findViewById(R.id.TailsButton).setEnabled(false);



    }
}
