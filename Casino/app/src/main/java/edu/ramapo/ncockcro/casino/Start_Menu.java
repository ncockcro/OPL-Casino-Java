package edu.ramapo.ncockcro.casino;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.util.Vector;

public class Start_Menu extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.firstapp.MESSAGE";
    Tournament_View tournamentViewLG;
    Tournament_Model tournamentModelLG = new Tournament_Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__menu);
        tournamentViewLG = new Tournament_View(tournamentModelLG);

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

        /*for(File file : fList) {
            Log.d("File", file.toString());
            tournamentModelLG.LoadGame(this, this.getFilesDir().getAbsolutePath() + "/save", "/Test.txt");
        }*/

        final EditText loadEditText = new EditText(this);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Enter a file name to load from.");
        dialog.setView(loadEditText);
        final String newDirectory = directory.toString();
        dialog.setPositiveButton("Load", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                tournamentModelLG.SetFileToLoadFrom(loadEditText.getText().toString());
                Log.d("About", "About to call the function");
                tournamentModelLG.LoadGame(newDirectory, "/" + loadEditText.getText().toString() + ".txt");
                StartLoadedGame();
            }

        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }

        });

        dialog.create();
        dialog.show();
    }

    /** *********************************************************************
     Function Name: StartLoadedGame
     Purpose: To send over the information from loading in a text file and starting the round activity
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Cycle through the various information needed to load a game in and put them in the intent
     that will start the round activity
     Assistance Received: none
     ********************************************************************* */
    void StartLoadedGame() {
        Vector<Card_Model> loadGameComputerHand = new Vector<Card_Model>();
        Vector<Card_Model> loadGameComputerPile = new Vector<Card_Model>();
        int loadGameHumanScore;
        Vector<Card_Model> loadGameHumanHand = new Vector<Card_Model>();
        Vector<Card_Model> loadGameHumanPile = new Vector<Card_Model>();
        Vector<Card_Model> loadGameTable = new Vector<Card_Model>();
        Vector<Card_Model> loadGameBuildCards = new Vector<Card_Model>();
        Vector<Build_Model> loadGameBuilds = new Vector<Build_Model>();
        String loadGameLastCapture;
        Vector<Card_Model> loadGameDeck = new Vector<Card_Model>();
        String loadGameNextPlayer;

        Intent loadIntent = new Intent(this, MainGame_Activity.class);

        // Toggle the loadGame variable to yes since we are loading in a game
        loadIntent.putExtra("loadGame", "true");

        // Set the coinWinner (next player) to the person who captured last
        loadIntent.putExtra("coinWinner", tournamentModelLG.GetLoadGameLastCapture());

        // Load in the current round
        String stringLoadGameRound = Integer.toString(tournamentModelLG.GetLoadGameRound());
        loadIntent.putExtra("currentRound", stringLoadGameRound);

        // Load in the computer's score
        String stringLoadGameComputerScore = Integer.toString(tournamentModelLG.GetLoadGameComputerScore());
        loadIntent.putExtra("computerScore", stringLoadGameComputerScore);

        // Load in the computer's hand
        for(int i = 0; i < tournamentModelLG.GetLoadGameComputerHand().size(); i++) {
            loadIntent.putExtra("loadGameComputerHand" + i, tournamentModelLG.GetLoadGameComputerHand().get(i).GetCard());
        }
        String computerHandSize = Integer.toString(tournamentModelLG.GetLoadGameComputerHand().size());
        loadIntent.putExtra("loadGameComputerHandSize", computerHandSize);

        // Load in the computer's pile
        for(int i = 0; i < tournamentModelLG.GetLoadGameComputerPile().size(); i++) {
            loadIntent.putExtra("loadGameComputerPile" + i, tournamentModelLG.GetLoadGameComputerPile().get(i).GetCard());
        }
        String stringComputerPileSize = Integer.toString(tournamentModelLG.GetLoadGameComputerPile().size());
        Log.d("ComputerPileSize", stringComputerPileSize);
        loadIntent.putExtra("computerPileSize", stringComputerPileSize);

        // Load in the human's score
        String stringHumanScore = Integer.toString(tournamentModelLG.GetLoadGameHumanScore());
        loadIntent.putExtra("humanScore", stringHumanScore);

        // Load in the human's hand
        for(int i = 0; i < tournamentModelLG.GetLoadGameHumanHand().size(); i++) {
            loadIntent.putExtra("loadGameHumanHand" + i, tournamentModelLG.GetLoadGameHumanHand().get(i).GetCard());
            Log.d("LoadingHnd", tournamentModelLG.GetLoadGameHumanHand().get(i).GetCard());
        }

        String stringHumanHandSize = Integer.toString(tournamentModelLG.GetLoadGameHumanHand().size());
        loadIntent.putExtra("humanHandSize", stringHumanHandSize);

        // Load in the human's pile
        for(int i = 0; i < tournamentModelLG.GetLoadGameHumanPile().size(); i++) {
            loadIntent.putExtra("loadGameHumanPile" + i, tournamentModelLG.GetLoadGameHumanPile().get(i).GetCard());
        }

        String stringHumanPileSize = Integer.toString(tournamentModelLG.GetLoadGameHumanPile().size());
        loadIntent.putExtra("humanPileSize", stringHumanPileSize);

        // Load in the table
        for(int i = 0; i < tournamentModelLG.GetLoadGameTable().size(); i++) {
            loadIntent.putExtra("loadGameTable" + i, tournamentModelLG.GetLoadGameTable().get(i).GetCard());
        }

        String stringTableSize = Integer.toString(tournamentModelLG.GetLoadGameTable().size());
        loadIntent.putExtra("tableSize", stringTableSize);

        // Loading in the deck
        for(int i = 0; i < tournamentModelLG.GetLoadGameDeck().size(); i++) {
            loadIntent.putExtra("loadGameDeck" + i, tournamentModelLG.GetLoadGameDeck().get(i).GetCard());
        }

        String stringDeckSize = Integer.toString(tournamentModelLG.GetLoadGameDeck().size());
        loadIntent.putExtra("deckSize", stringDeckSize);

        // Loading in the builds
        Vector<Card_Model> tempCards = new Vector<Card_Model>();
        for(int i = 0; i < tournamentModelLG.GetLoadGameBuilds().size(); i++) {
            tempCards = tournamentModelLG.GetLoadGameBuilds().get(i).GetBuildOfCards();

            for(int j = 0; j < tempCards.size(); j++) {
                loadIntent.putExtra("loadGameBuilds" + i + j, tempCards.get(j).GetCard());
            }
            String stringTempCardsSize = Integer.toString(tempCards.size());
            loadIntent.putExtra("buildCardSize" + i, stringTempCardsSize);
        }

        String stringBuildsSize = Integer.toString(tournamentModelLG.GetLoadGameBuilds().size());
        loadIntent.putExtra("buildSize", stringBuildsSize);

        loadIntent.putExtra("nextPlayer", tournamentModelLG.GetLoadGameNextPlayer());

        startActivity(loadIntent);

    }
}
