package edu.ramapo.ncockcro.casino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class Start_Menu extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.firstapp.MESSAGE";
    Tournament_Model tournamentModelLG = new Tournament_Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__menu);

    }

    /** *********************************************************************
     Function Name: newGame
     Purpose: When the new game button is pressed, this will direct the game to the coin toss screen
     Parameters:
        @param view View, the view of the button that was pressed
     Return Value:
         @return void
     Local Variables: None
     Algorithm:
     1) Start a new activity with the intent of the CoinToss class
     Assistance Received: none
      ********************************************************************* */
    public void newGame(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, CoinToss.class);
        startActivity(intent);
    }

    public void loadGame(View view) {

        Log.d("Path", this.getFilesDir().getAbsolutePath().toString());

        File directory = new File(this.getFilesDir().getAbsolutePath() + "/save");

        File[] fList = directory.listFiles();

        for(File file : fList) {
            Log.d("File", file.toString());
            tournamentModelLG.LoadGame(this, this.getFilesDir().getAbsolutePath() + "/save", "/Test.txt");
        }
    }
}
