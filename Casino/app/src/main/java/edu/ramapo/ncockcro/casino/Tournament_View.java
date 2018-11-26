package edu.ramapo.ncockcro.casino;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.Vector;

public class Tournament_View {

    private HorizontalScrollView playerPileHorizontal;
    private LinearLayout playerPileLinearLayout;
    private HorizontalScrollView computerPileHorizontal;
    private LinearLayout computerPileLinearLayout;
    private TextView mostCardsView;
    private TextView mostSpadesView;
    private TextView tenOfDiamondsView;
    private TextView twoOfSpadesView;
    private TextView mostAcesView;
    private TextView humanScore;
    private TextView computerScore;

    Vector<ImageView> playerPile = new Vector<ImageView>();
    Vector<ImageView> computerPile = new Vector<ImageView>();

    private Tournament_Model tournamentModel;
    private Card_View cardView = new Card_View();

    /** *********************************************************************
     Function Name: Tournament_View
     Purpose: Default constructor
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Default constructor
     Assistance Received: none
      ********************************************************************* */
    Tournament_View() {

    }

    Tournament_View(Tournament_Model passedTournamentModel) {
        tournamentModel = passedTournamentModel;
    }

    /** *********************************************************************
     Function Name: Tournament_View
     Purpose: Overloaded default constructor
     Parameters:
     @param activity, Activity object of the current activity were in
     @param passedTournamentModel, Tournament_Model object, the model from the activity
     were using for the current screen
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Initialize the textviews and horizontal views to the id's
     Assistance Received: none
      ********************************************************************* */
    Tournament_View(Activity activity, Tournament_Model passedTournamentModel) {

        // Player pile
        playerPileHorizontal = (HorizontalScrollView) activity.findViewById(R.id.tableHorizontalView);
        playerPileLinearLayout = (LinearLayout) activity.findViewById(R.id.linearLayoutHumanPile);
        playerPileLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Computer pile
        computerPileHorizontal = (HorizontalScrollView) activity.findViewById(R.id.tableHorizontalView);
        computerPileLinearLayout = (LinearLayout) activity.findViewById(R.id.linearLayoutComputerPile);
        computerPileLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Rest of the textview's on the screen
        mostCardsView = (TextView) activity.findViewById(R.id.mostCardsTextView);
        mostSpadesView = (TextView) activity.findViewById(R.id.mostSpadesTextView);
        tenOfDiamondsView = (TextView) activity.findViewById(R.id.tenOfDiamondsTextView);
        twoOfSpadesView = (TextView) activity.findViewById(R.id.twoOfSpadesTextView);
        mostAcesView = (TextView) activity.findViewById(R.id.mostAcesTextView);

        humanScore = (TextView) activity.findViewById(R.id.humanScore);
        computerScore = (TextView) activity.findViewById(R.id.computerScore);

        tournamentModel = passedTournamentModel;
    }

    /** *********************************************************************
     Function Name: DrawResults
     Purpose: To create a new image view to be added to the computer's pile horizontal scroll view
     Parameters:
     @param context, Context object of the current activity were in
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Draw the player's piles and the scorings of the players
     Assistance Received: none
      ********************************************************************* */
    void DrawResults(Context context, Vector<Card_View> HumanPile, Vector<Card_View> ComputerPile) {

        // Draw the human and computer pile
        DrawPiles(context, HumanPile, ComputerPile);

        // Calculate the scores in the model
        tournamentModel.CalculatePoints(cardView.ConvertViewToModelVector(HumanPile), cardView.ConvertViewToModelVector(ComputerPile));

        // Player with the most cards
        if(tournamentModel.GetMostCardsColor().equals("Green")) {
            mostCardsView.setTextColor(0xFF2ea54a);
        }
        else {
            mostCardsView.setTextColor(0xFFFF0000);
        }
        mostCardsView.append(tournamentModel.GetMostCardsMessage());

        // Player with the most Spades
        if(tournamentModel.GetMostSpadesColor().equals("Green")) {
            mostSpadesView.setTextColor(0xFF2ea54a);
        }
        else {
            mostSpadesView.setTextColor(0xFFFF0000);
        }
        mostSpadesView.append(tournamentModel.GetMostSpadesMessage());

        // Player with the ten of Diamonds
        if(tournamentModel.GetTenOfDiamondsColor().equals("Green")) {
            tenOfDiamondsView.setTextColor(0xFF2ea54a);
        }
        else {
            tenOfDiamondsView.setTextColor(0xFFFF0000);
        }
        tenOfDiamondsView.append(tournamentModel.GetTenOfDiamondsMessage());

        // Player with the Two of Spades
        if(tournamentModel.GetTwoOfSpadesColor().equals("Green")) {
            twoOfSpadesView.setTextColor(0xFF2ea54a);
        }
        else {
            twoOfSpadesView.setTextColor(0xFFFF0000);
        }
        twoOfSpadesView.append(tournamentModel.GetTwoOfSpadesMessage());

        // How many Aces each of the player's had
        mostAcesView.setTextColor(0xFF2ea54a);
        mostAcesView.append(tournamentModel.GetNumAcesMessage().get(0));

        mostAcesView.setTextColor(0xFFFF0000);
        mostAcesView.append(tournamentModel.GetNumAcesMessage().get(1));

        humanScore.append(Integer.toString(tournamentModel.GetHumanPoints()));
        computerScore.append(Integer.toString(tournamentModel.GetComputerPoints()));

    }

    /** *********************************************************************
     Function Name: DrawPiles
     Purpose: To draw the human and computer's piles to the screen
     Parameters:
     @param context, Context object of the current activity were in
     @param passedPlayerPile, Vector<Card_View> the human's pile
     @param passedComputerPile Vector<Card_View> the computer's pile
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Cycle through the human's pile and create the image views to the screen
     2) Cycle through the computer's pike and create the image views to the screen
     Assistance Received: none
      ********************************************************************* */
    void DrawPiles(Context context, Vector<Card_View> passedPlayerPile, Vector<Card_View> passedComputerPile) {

        // Clear the deck from the screen
        playerPileLinearLayout.removeAllViews();
        computerPileLinearLayout.removeAllViews();

        // Draw the new player's pile from the model
        for(int i = 0; i < passedPlayerPile.size(); i++) {
            playerPile.add(AddCardToPlayerPile(context));
            passedPlayerPile.get(i).DrawImageViewCard(playerPile.get(i));
        }

        // Draw the new computer's pile from the model
        for(int i = 0; i < passedComputerPile.size(); i++) {
            computerPile.add(AddCardToComputerPile(context));
            passedComputerPile.get(i).DrawImageViewCard(computerPile.get(i));
        }

    }

    /** *********************************************************************
     Function Name: AddCardToPlayerPile
     Purpose: To dynamically add an image view to the player's pile linear layout
     Parameters:
     @param context, Context object of the current activity were in
     Return Value:
     @return ImageView, the image view that was created
     Local Variables: None
     Algorithm:
     1) Create a new image view
     2) Set the image background to the default red back
     3) Set the parameters of the image view to fit on the screen
     4) Return the image view
     Assistance Received: none
      ********************************************************************* */
    ImageView AddCardToPlayerPile(Context context) {
        ImageView button = new ImageView(context);
        button.setImageResource(R.drawable.blackback);
        button.setLayoutParams(new LinearLayout.LayoutParams(89, 69));
        button.setScaleType(ImageView.ScaleType.FIT_XY);
        button.setBackgroundColor(Color.TRANSPARENT);
        playerPileLinearLayout.addView(button);

        return button;
    }

    /** *********************************************************************
     Function Name: AddCardToComputerPile
     Purpose: To create a new image view to be added to the computer's pile horizontal scroll view
     Parameters:
     @param context, Context object of the current activity were in
     Return Value:
     @return ImageView, the image view that was created
     Local Variables: None
     Algorithm:
     1) Create a new image view
     2) Set the image background to the default red back
     3) Set the parameters of the image view to fit on the screen
     4) Return the image view
     Assistance Received: none
      ********************************************************************* */
    ImageView AddCardToComputerPile(Context context) {
        ImageView button = new ImageView(context);
        button.setImageResource(R.drawable.blackback);
        button.setLayoutParams(new LinearLayout.LayoutParams(89, 69));
        button.setScaleType(ImageView.ScaleType.FIT_XY);
        button.setBackgroundColor(Color.TRANSPARENT);
        computerPileLinearLayout.addView(button);

        return button;
    }

    /** *********************************************************************
     Function Name: ShowLoadTextInput
     Purpose: To prompt the user to type in a file name to load from
     Parameters:
     @param context, Context object
     @param directory, File object
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Create an input text box for the user to type in a file name
     2) Once they hit load, this will load in all the information from the text file
     and start the round
     Assistance Received: none
      ********************************************************************* */
    void ShowLoadTextInput(Context context, File directory) {
        final EditText loadEditText = new EditText(context);
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Loading");
        dialog.setMessage("Enter a file name to load from.");
        dialog.setView(loadEditText);
        final Context innerContext = context;
        final String newDirectory = directory.toString();
        dialog.setPositiveButton("Load", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                tournamentModel.SetFileToLoadFrom(loadEditText.getText().toString());
                tournamentModel.LoadGame(newDirectory, "/" + loadEditText.getText().toString() + ".txt");
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


}
