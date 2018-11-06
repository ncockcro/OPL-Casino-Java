package edu.ramapo.ncockcro.casino;

import android.app.Activity;
import android.content.Context;

public class Tournament_Model {

    private String initialDecision;
    private int humanPoints;
    private int computerPoints;
    private String firstPlayer;
    private int round;
    private String lastCaptured;
    private Tournament_View tournamentView;
    private Activity activity;

    Tournament_Model() {

    }

    Tournament_Model(Activity passedActivity) {
        activity = (Activity) passedActivity;
    }

    public void PlayTournament() {
    }


    private void IncrementRound() {
        round++;
    }
}
