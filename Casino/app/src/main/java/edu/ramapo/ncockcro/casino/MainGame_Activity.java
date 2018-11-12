package edu.ramapo.ncockcro.casino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainGame_Activity extends AppCompatActivity {

    Tournament_View tournamentView;
    Round_View roundView;
    Card_View cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_);

        tournamentView = new Tournament_View();
        roundView = new Round_View(this);
        cardView = new Card_View();

        roundView.DrawFourCardsHumanAndComputer();
        roundView.DrawFourCardsTable(this);
        roundView.DrawDeck(this);

    }

    public void HumanCardPressed(View view) {
        roundView.HighlightCard(view);

    }

    public void TrailCardPressed(View view) {

        roundView.MakeTrail();
        roundView.UpdateScreen(this);
    }
}
