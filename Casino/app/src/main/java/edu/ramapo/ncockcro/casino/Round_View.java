package edu.ramapo.ncockcro.casino;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.Vector;

public class Round_View extends MainGame_Activity {

    private Round_Model roundModel = new Round_Model();
    private Player_Model playerModel = new Player_Model();
    private Card_View cardView = new Card_View();
    private Activity activity;
    private Vector<ImageButton> playerImageButtonHand = new Vector();
    private Vector<ImageView> computerImageViewHand = new Vector();
    private Vector<ImageButton> table = new Vector();
    private Vector<ImageView> deck = new Vector();
    private HorizontalScrollView tableHorizontalScrollView;
    private LinearLayout tableLinearLayout;
    private HorizontalScrollView deckHorizontalScrollView;
    private LinearLayout deckLinearLayout;
    private Button trailButton;

    private Vector<Card_View> playerCards = new Vector();
    private Vector<Card_View> computerCards = new Vector();
    private Vector<Card_View> tableCards = new Vector();
    private Card_View playerWantCard = new Card_View();
    private Vector<Card_View> deckCards = new Vector();
    private Card_View highlightedCard = new Card_View();

    // Default constructor
    Round_View() {

    }

    // Overloaded constructor where the activity for the current screen is passed through
    Round_View(Activity passedActivity) {
        this.activity = (Activity) passedActivity;
        playerImageButtonHand.add((ImageButton) activity.findViewById(R.id.playerHand1));
        playerImageButtonHand.add((ImageButton) activity.findViewById(R.id.playerHand2));
        playerImageButtonHand.add((ImageButton) activity.findViewById(R.id.playerHand3));
        playerImageButtonHand.add((ImageButton) activity.findViewById(R.id.playerHand4));

        computerImageViewHand.add((ImageView) activity.findViewById(R.id.computerHand1));
        computerImageViewHand.add((ImageView) activity.findViewById(R.id.computerHand2));
        computerImageViewHand.add((ImageView) activity.findViewById(R.id.computerHand3));
        computerImageViewHand.add((ImageView) activity.findViewById(R.id.computerHand4));

        tableHorizontalScrollView = (HorizontalScrollView) activity.findViewById(R.id.tableHorizontalView);
        tableLinearLayout = (LinearLayout) activity.findViewById(R.id.linearLayoutTableView);
        tableLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        deckHorizontalScrollView = (HorizontalScrollView) activity.findViewById(R.id.deckHorizontalView);
        deckLinearLayout = (LinearLayout) activity.findViewById(R.id.linearLayoutDeckView);
        tableLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        trailButton = (Button) activity.findViewById(R.id.trailButton);

    }

    /** *********************************************************************
    Function Name: DrawFourCardsHumandAndComputer
    Purpose: To draw four cards to the human and computer player's hands
    Parameters: None
    Return Value:
        @Void
    Local Variables: None
    Algorithm:
        1) Update the model by dealing four cards
        2) Get the four cards that were dealt and store them in the view
        3) Draw the four cards for the human's hand
        4) Draw the four cards for the computer's hand
    Assistance Received: none
    ********************************************************************* */
    public void DrawFourCardsHumanAndComputer() {

        roundModel.DealCardsToPlayer();
        playerCards = cardView.ConvertModelToView(roundModel.GetHand(0));
        computerCards = cardView.ConvertModelToView(roundModel.GetHand(1));

        //playerCards.get(0).DrawImageCard(playerHand1);
        playerCards.get(0).DrawImageCard(playerImageButtonHand.get(0));
        playerCards.get(1).DrawImageCard(playerImageButtonHand.get(1));
        playerCards.get(2).DrawImageCard(playerImageButtonHand.get(2));
        playerCards.get(3).DrawImageCard(playerImageButtonHand.get(3));

        computerCards.get(0).DrawImageViewCard(computerImageViewHand.get(0));
        computerCards.get(1).DrawImageViewCard(computerImageViewHand.get(1));
        computerCards.get(2).DrawImageViewCard(computerImageViewHand.get(2));
        computerCards.get(3).DrawImageViewCard(computerImageViewHand.get(3));


    }

    /** *********************************************************************
     Function Name: DrawFourCardsTable
     Purpose: To draw four cards to the table
     Parameters:
        @param context Context object, holds the current activity that this is accessing
     Return Value:
        @return void
     Local Variables: None
     Algorithm:
     1) Update the model by dealing cards to the table there
     2) Get the four cards that were dealt and store them in the view
     3) Add a new card and append it to the table horizontal view
     4) Draw the card that was dealt to the table at that specific card
     5) Repeat for four times
     Assistance Received: none
      ********************************************************************* */
    void DrawFourCardsTable(Context context) {


        table.clear();
        roundModel.DealCardsToTable();
        tableCards = cardView.ConvertModelToView(roundModel.GetTable());

        table.add(AddCardToTable(context));
        tableCards.get(0).DrawImageCard(table.get(0));
        table.add(AddCardToTable(context));
        tableCards.get(1).DrawImageCard(table.get(1));
        table.add(AddCardToTable(context));
        tableCards.get(2).DrawImageCard(table.get(2));
        table.add(AddCardToTable(context));
        tableCards.get(3).DrawImageCard(table.get(3));

    }

    void DrawCards(Context context) {

        table.clear();
        tableCards.clear();
        tableLinearLayout.removeAllViews();
        tableCards = cardView.ConvertModelToView(roundModel.GetTable());

        for(int i = 0; i < tableCards.size(); i++) {
            table.add(AddCardToTable(context));
            tableCards.get(i).DrawImageCard(table.get(i));
        }
    }

    void DrawDeck(Context context) {

        deck.clear();
        deckLinearLayout.removeAllViews();
        deckCards = cardView.ConvertModelToView(roundModel.GetDeck());


        for(int i = 0; i < deckCards.size(); i++) {
            deck.add(AddCardToDeck(context));
            deckCards.get(i).DrawImageViewCard(deck.get(i));
        }
        /*table.add(AddCardToTable(context));
        tableCards.get(0).DrawImageCard(table.get(0));
        table.add(AddCardToTable(context));
        tableCards.get(1).DrawImageCard(table.get(1));
        table.add(AddCardToTable(context));
        tableCards.get(2).DrawImageCard(table.get(2));
        table.add(AddCardToTable(context));
        tableCards.get(3).DrawImageCard(table.get(3));*/

    }

    /** *********************************************************************
     Function Name: AddCardToTable
     Purpose: To create a new image button to be added to the table horizontal scroll view
     Parameters:
     @param context Context object, holds the current activity that this is accessing
     Return Value:
     @return ImageButton
     Local Variables:
        button, a new image button which gets added to the table
     Algorithm:
     1) Create a new button
     2) Set the background to be a default background
     3) Set the size of the button
     4) Set the scale type to fit x and y coordinates
     5) Set the background to clear
     6) Add it to the linear layout for the table
     Assistance Received: none
      ********************************************************************* */
    ImageButton AddCardToTable(Context context) {
        ImageButton button = new ImageButton(context);
        button.setImageResource(R.drawable.blackback);
        button.setLayoutParams(new LinearLayout.LayoutParams(89, 125));
        button.setScaleType(ImageView.ScaleType.FIT_XY);
        button.setBackgroundColor(Color.TRANSPARENT);
        tableLinearLayout.addView(button);

        return button;
    }

    ImageView AddCardToDeck(Context context) {
        ImageView button = new ImageView(context);
        button.setImageResource(R.drawable.blackback);
        button.setLayoutParams(new LinearLayout.LayoutParams(89, 125));
        button.setScaleType(ImageView.ScaleType.FIT_XY);
        button.setBackgroundColor(Color.TRANSPARENT);
        deckLinearLayout.addView(button);

        return button;
    }

    /** *********************************************************************
     Function Name: HighlightCard
     Purpose: When a card in the player's hand is clicked, it will highlight the card and set it
     to be the card the player wants to use for a move
     Parameters:
     @param view View object, holds the button that the player clicked
     Return Value:
     @return Void
     Local Variables:
         tempCard, a card to hold the one that was clicked by the user
     Algorithm:
     1) Find out which button the user hit and highlight it red and set the tempCard to that card
     2) Then update the model with the card the player selected
     Assistance Received: none
      ********************************************************************* */
    void HighlightCard(View view) {

        //Card_View tempCard = new Card_View();

        if(view.getId() == R.id.playerHand1) {
            playerImageButtonHand.get(0).setColorFilter(0x40ff0000);
            highlightedCard = playerCards.get(0);
        }
        else if(view.getId() == R.id.playerHand2) {
            playerImageButtonHand.get(1).setColorFilter(0x40ff0000);
            highlightedCard = playerCards.get(1);
        }
        else if(view.getId() == R.id.playerHand3) {
            playerImageButtonHand.get(2).setColorFilter(0x40ff0000);
            highlightedCard = playerCards.get(2);
        }
        else if(view.getId() == R.id.playerHand4) {
            playerImageButtonHand.get(3).setColorFilter(0x40ff0000);
            highlightedCard = playerCards.get(3);
        }

        for(int i = 0; i < playerCards.size(); i++) {
            if(playerCards.get(i).GetCard() != highlightedCard.GetCard()) {
                playerImageButtonHand.get(i).setColorFilter(null);
            }
        }

        SetPlayerWantCard(highlightedCard);
    }

    /** *********************************************************************
     Function Name: SetPlayerWantCard
     Purpose: To set the card that the player wants to use for a move
     Parameters:
     @param passedCard Card_View object, holds the card the player tapped on
     Return Value:
     @return void
     Local Variables: None
     Algorithm:
        1) Set the playerWantCard variable to what was passed to it
        2) update the model with the card the player picked
     Assistance Received: none
      ********************************************************************* */
    void SetPlayerWantCard(Card_View passedCard) {
        playerWantCard = passedCard;
        playerModel.SetPlayerWantCard(passedCard.ConvertViewToModel(passedCard));

    }

    /** *********************************************************************
     Function Name: MakeTrail
     Purpose: Find which card is the highlighted card and call the model to see if the playe can trail
     Parameters: None
     Return Value:
     @return void
     Local Variables: None
     Algorithm:
     1) Cycle through the player's cards and find which one they clicked previously
     2) Then call the model and check if the player is able to trail
     3) Then, unhighlight all of the cards on the table
     Assistance Received: none
      ********************************************************************* */
    void MakeTrail() {

        // Checking to make sure the card the user selected is in the hand
        for(int i = 0; i < playerCards.size(); i++) {
            if(highlightedCard.GetCard() == playerCards.get(i).GetCard()) {

                // If the CheckTrail returned false, then we need to notify the user to make a different move
                if(roundModel.CheckTrail(cardView.ConvertViewToModel(playerCards.get(i))) == false) {
                    Toast.makeText(activity, "Can not trail with " + highlightedCard.GetCard() + " because " +
                            roundModel.GetErrorReason(), Toast.LENGTH_LONG).show();
                }
            }
        }

        // Once done, we need to unhighlight any other cards
        for(int i = 0; i < playerCards.size(); i++) {
            playerImageButtonHand.get(i).setColorFilter(null);
        }
    }

    /** *********************************************************************
     Function Name: UpdateScreen
     Purpose: To redraw all of the different piles, hand, table, ect since they may have changed since last time
     Parameters:
     @param context, Context object, holds the context
     Return Value:
     @return void
     Local Variables: None
     Algorithm:
     1) Cycle through the player's cards and find which one they clicked previously
     2) Then call the model and check if the player is able to trail
     3) Then, unhighlight all of the cards on the table
     Assistance Received: none
      ********************************************************************* */
    void UpdateScreen(Context context) {

        playerCards = cardView.ConvertModelToView(roundModel.GetHand(0));
        computerCards = cardView.ConvertModelToView(roundModel.GetHand(1));

        for(int i = 0; i < playerCards.size(); i++) {
            playerCards.get(i).DrawImageCard(playerImageButtonHand.get(i));
        }

        for(int i = playerCards.size(); i < 4; i++) {
            playerImageButtonHand.get(i).setVisibility(View.GONE);
        }
        for(int i = 0; i < computerCards.size(); i++) {
            computerCards.get(i).DrawImageViewCard(computerImageViewHand.get(i));
        }

        DrawCards(context);
        DrawDeck(context);

    }

    boolean CheckForDealingCards() {

        // If the player's hands are empty but not the deck, deal cards and return true
        if(roundModel.CheckIfPlayersHandEmpty() && roundModel.CheckIfDeckEmpty() == false) {
            roundModel.DealCardsToPlayer();
            return true;
        }
        // If the hands aren't empty and the deck isn't empty, return true
        else if(roundModel.CheckIfPlayersHandEmpty() == false && roundModel.CheckIfDeckEmpty() == false) {
            return true;
        }
        // Otherwise, return false, the round is over
        else {
            return false;
        }
    }

}
