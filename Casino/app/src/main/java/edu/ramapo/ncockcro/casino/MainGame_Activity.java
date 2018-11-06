package edu.ramapo.ncockcro.casino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainGame_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_);

        Tournament_View tournamentView = new Tournament_View();
        Round_View roundView = new Round_View(this);
        Card_View cardView = new Card_View();

        roundView.SetFourCardsHuman();

    }
}
