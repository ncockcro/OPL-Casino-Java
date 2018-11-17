package edu.ramapo.ncockcro.casino;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class Round_View extends MainGame_Activity {

    private Round_Model roundModel;
    private Player_Model playerModel = new Player_Model();
    private Card_View cardView = new Card_View();
    private Activity activity;
    private Vector<ImageButton> playerImageButtonHand = new Vector<ImageButton>();
    private Vector<ImageView> computerImageViewHand = new Vector<ImageView>();
    private Vector<ImageButton> table = new Vector<ImageButton>();
    private Vector<ImageView> deck = new Vector<ImageView>();
    private Vector<ImageView> playerPile = new Vector<ImageView>();
    private Vector<ImageView> computerPile = new Vector<ImageView>();
    private TextView outputTextView;
    private HorizontalScrollView tableHorizontalScrollView;
    private LinearLayout tableLinearLayout;
    private HorizontalScrollView deckHorizontalScrollView;
    private LinearLayout deckLinearLayout;
    private HorizontalScrollView playerPileHorizontalScrollView;
    private LinearLayout playerPileLinearLayout;
    private HorizontalScrollView computerPileHorizontalScrollView;
    private LinearLayout computerPileLinearLayout;
    private Button trailButton;
    private Button computerMoveButton;
    private Button captureButton;
    private Button captureBuildButton;
    private Button captureSetButton;
    private Button captureDoneButton;

    private Vector<Card_View> playerCards = new Vector<Card_View>();
    private Vector<Card_View> computerCards = new Vector<Card_View>();
    private Vector<Card_View> tableCards = new Vector<Card_View>();
    private Card_View playerWantCard = new Card_View();
    private Vector<Card_View> deckCards = new Vector<Card_View>();
    private Vector<Card_View> playerPileCards = new Vector<Card_View>();
    private Vector<Card_View> computerPileCards = new Vector<Card_View>();
    private Card_View highlightedCard = new Card_View();
    private Vector<Card_View> highlightedTableCard = new Vector<Card_View>();
    private int tableIdCounter = 0;

    // Default constructor
    Round_View() {

    }

    // Overloaded constructor where the activity for the current screen is passed through
    Round_View(Activity passedActivity, Round_Model passedRoundModel) {
        this.activity = (Activity) passedActivity;
        roundModel = passedRoundModel;
        outputTextView = (TextView) activity.findViewById(R.id.outputTextView);
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

        playerPileHorizontalScrollView = (HorizontalScrollView) activity.findViewById(R.id.playerPileHorizontalView);
        playerPileLinearLayout = (LinearLayout) activity.findViewById(R.id.linearLayoutPlayerPileView);
        playerPileLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        computerPileHorizontalScrollView = (HorizontalScrollView) activity.findViewById(R.id.computerPileHorizontalView);
        computerPileLinearLayout = (LinearLayout) activity.findViewById(R.id.linearLayoutComputerPile);
        computerPileLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        trailButton = (Button) activity.findViewById(R.id.trailButton);
        computerMoveButton = (Button) activity.findViewById(R.id.computerMoveButton);
        captureButton = (Button) activity.findViewById(R.id.captureButton);
        captureBuildButton = (Button) activity.findViewById(R.id.captureBuildButton);
        captureSetButton = (Button) activity.findViewById(R.id.captureSetButton);
        captureDoneButton = (Button) activity.findViewById(R.id.captureDoneButton);

    }

    /** *********************************************************************
    Function Name: DrawFourCardsHumandAndComputer
    Purpose: To draw four cards to the human and computer player's hands
    Parameters: None
    Return Value: Void
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
     Return Value: Void
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

    /***********************************************************************
     Function Name: DrawCards
     Purpose: To draw the cards for the table
     Parameters:
     @param context
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Clear the table from what was previously in it
     2) Cycle through the table vector and add them to the view of the screen
     Assistance Received: none
     ********************************************************************* */
    void DrawTable(Context context) {

        table.clear();
        tableCards.clear();
        tableLinearLayout.removeAllViews();
        tableCards = cardView.ConvertModelToView(roundModel.GetTable());

        for(int i = 0; i < tableCards.size(); i++) {
            table.add(AddCardToTable(context));
            tableCards.get(i).DrawImageCard(table.get(i));
        }
    }

    /***********************************************************************
     Function Name: DrawDeck
     Purpose: To draw the deck to the screen
     Parameters:
     @param context
     Return Value: Void
     Local Variables:None
     Algorithm:
     1) Clear the current deck from the school
     2) Draw the new current deck from the model
     Assistance Received: none
     ********************************************************************* */
    void DrawDeck(Context context) {

        // Clear the deck from the screen
        deck.clear();
        deckLinearLayout.removeAllViews();
        deckCards = cardView.ConvertModelToView(roundModel.GetDeck());

        // Draw the new deck from the model
        for(int i = 0; i < deckCards.size(); i++) {
            deck.add(AddCardToDeck(context));
            deckCards.get(i).DrawImageViewCard(deck.get(i));
        }

    }

    void DrawPlayerPile(Context context) {

        playerPile.clear();
        playerPileLinearLayout.removeAllViews();
        playerPileCards = cardView.ConvertModelToView(roundModel.GetPlayerPile(0));

        for(int i = 0; i < playerPileCards.size(); i++) {
            playerPile.add(AddCardToPlayerPile(context));
            playerPileCards.get(i).DrawImageViewCard(playerPile.get(i));
        }
    }

    void DrawComputerPile(Context context) {

        computerPile.clear();
        computerPileLinearLayout.removeAllViews();
        computerPileCards = cardView.ConvertModelToView(roundModel.GetPlayerPile(1));

        for(int i = 0; i < computerPileCards.size(); i++) {
            computerPile.add(AddCardToComputerPile(context));
            computerPileCards.get(i).DrawImageViewCard(computerPile.get(i));
        }
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
        button.setId(tableIdCounter);
        tableIdCounter++;
        button.setLayoutParams(new LinearLayout.LayoutParams(89, 125));
        button.setScaleType(ImageView.ScaleType.FIT_XY);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setEnabled(false);
        tableLinearLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HighlightTableCard(view);
            }
        });

        return button;
    }

    /** *********************************************************************
     Function Name: AddCardToDeck
     Purpose: To create a new image view to be added to the deck horizontal scroll view
     Parameters:
     @param context Context object, holds the current activity that this is accessing
     Return Value:
     @return ImageView
     Local Variables:
     button, a new image button which gets added to the table
     Algorithm:
     1) Create a new image view
     2) Set the background to be a default background
     3) Set the size of the button
     4) Set the scale type to fit x and y coordinates
     5) Set the background to clear
     6) Add it to the linear layout for the table
     Assistance Received: none
      ********************************************************************* */
    ImageView AddCardToDeck(Context context) {
        ImageView button = new ImageView(context);
        button.setImageResource(R.drawable.blackback);
        button.setLayoutParams(new LinearLayout.LayoutParams(89, 125));
        button.setScaleType(ImageView.ScaleType.FIT_XY);
        button.setBackgroundColor(Color.TRANSPARENT);
        deckLinearLayout.addView(button);

        return button;
    }

    ImageView AddCardToPlayerPile(Context context) {
        ImageView button = new ImageView(context);
        button.setImageResource(R.drawable.redback);
        button.setLayoutParams(new LinearLayout.LayoutParams(89, 125));
        button.setScaleType(ImageView.ScaleType.FIT_XY);
        button.setBackgroundColor(Color.TRANSPARENT);
        playerPileLinearLayout.addView(button);

        return button;
    }

    ImageView AddCardToComputerPile(Context context) {
        ImageView button = new ImageView(context);
        button.setImageResource(R.drawable.redback);
        button.setLayoutParams(new LinearLayout.LayoutParams(89, 125));
        button.setScaleType(ImageView.ScaleType.FIT_XY);
        button.setBackgroundColor(Color.TRANSPARENT);
        computerPileLinearLayout.addView(button);
        return button;
    }

    /** *********************************************************************
     Function Name: HighlightCard
     Purpose: When a card in the player's hand is clicked, it will highlight the card and set it
     to be the card the player wants to use for a move
     Parameters:
     @param view View object, holds the button that the player clicked
     Return Value: Void
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
            if(!playerCards.get(i).GetCard().equals(highlightedCard.GetCard())) {
                playerImageButtonHand.get(i).setColorFilter(null);
            }
        }

        EnableMainInputButtons();
        SetPlayerWantCard(highlightedCard);
    }

    void HighlightTableCard(View view) {
        for(int i = 0; i < table.size(); i++) {
            if(view.getId() == table.get(i).getId()) {
                table.get(i).setColorFilter(0x40ff0000);
                highlightedTableCard.add(tableCards.get(i));
                table.get(i).setEnabled(false);
            }
        }

        captureBuildButton.setEnabled(true);
        captureSetButton.setEnabled(true);
        captureDoneButton.setEnabled(true);
    }

    /** *********************************************************************
     Function Name: SetPlayerWantCard
     Purpose: To set the card that the player wants to use for a move
     Parameters:
     @param passedCard Card_View object, holds the card the player tapped on
     Return Value: Void
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

    void SetCaptureInfo() {

        // Sending over the card the player wants to capture with
        for(int i = 0; i < playerCards.size(); i++) {
            if(highlightedCard.GetCard().equals(playerCards.get(i).GetCard())) {
                roundModel.SetPlayerCard(cardView.ConvertViewToModel(playerCards.get(i)));
            }
        }

        roundModel.SetTableCardsToBeCaptured(cardView.ConvertViewToModelVector(highlightedTableCard));

        for(int i = 0; i < playerCards.size(); i++) {
            playerImageButtonHand.get(i).setColorFilter(null);
        }
    }

    /** *********************************************************************
     Function Name: MakeTrail
     Purpose: Find which card is the highlighted card and call the model to see if the playe can trail
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Cycle through the player's cards and find which one they clicked previously
     2) Then call the model and check if the player is able to trail
     3) Then, unhighlight all of the cards on the table
     Assistance Received: none
      ********************************************************************* */
    void SetTrailInfo() {

        // Checking to make sure the card the user selected is in the hand
        for(int i = 0; i < playerCards.size(); i++) {
            if(highlightedCard.GetCard().equals(playerCards.get(i).GetCard())) {
                roundModel.SetPlayerMove('t');
                roundModel.SetPlayerCard(cardView.ConvertViewToModel(playerCards.get(i)));
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
     Return Value: Void
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
        playerPileCards = cardView.ConvertModelToView(roundModel.GetPlayerPile(0));

        for(int i = 0; i < playerCards.size(); i++) {
            playerCards.get(i).DrawImageCard(playerImageButtonHand.get(i));
        }

        for(int i = playerCards.size(); i < 4; i++) {
            playerImageButtonHand.get(i).setVisibility(View.GONE);
        }
        for(int i = 0; i < computerCards.size(); i++) {
            computerCards.get(i).DrawImageViewCard(computerImageViewHand.get(i));
        }

        for(int i = computerCards.size(); i < 4; i++) {
            computerImageViewHand.get(i).setVisibility(View.GONE);
        }


        DrawTable(context);
        DrawDeck(context);
        DrawPlayerPile(context);
        DrawComputerPile(context);

    }

    /** *********************************************************************
     Function Name: CheckForDealingCards
     Purpose: To check and see if we need to deal more cards to the players if they are out of cards
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Check if the player's hands are empty but not the deck, then we need to deal, return 1
     2) Then check if both the player's hands are empty and the deck is empty, then just keep playing
     3) If neither of those conditions were true, then the round is over
     Assistance Received: none
      ********************************************************************* */
    Integer CheckForDealingCards() {

        // If the player's hands are empty but not the deck, deal cards and return true
        if(roundModel.CheckIfPlayersHandEmpty() && !roundModel.CheckIfDeckEmpty()) {
            Log.d("Check", "Dealing cardssssss");
            roundModel.DealCardsToPlayer();
            return 1;
        }
        // If the hands aren't empty and the deck isn't empty, return true
        else if(!roundModel.CheckIfPlayersHandEmpty() && !roundModel.CheckIfDeckEmpty()) {
            return 2;
        }
        // Otherwise, return false, the round is over
        else {
            return 3;
        }
    }

    /** *********************************************************************
     Function Name: SetPlayerButtons
     Purpose: To show either the human buttons or the computer buttons
     Parameters: None
     Return Value: None
     Local Variables:
     button, a new image button which gets added to the table
     Algorithm:
     1) If the current player is 0, show the human buttons
     2) Otherwise, show the computer buttons
     Assistance Received: none
      ********************************************************************* */
    void SetPlayerButtons() {
        if(roundModel.GetCurrentPlayer() == 0) {
            ShowHumanButtons();
        }
        else {
            ShowComputerButtons();
        }
    }

    /** *********************************************************************
     Function Name: ShowHumanButtons
     Purpose: To hide all other buttons except the ones for the human moves
     Parameters: None
     Return Value: Void
     Local Variables:
     button, a new image button which gets added to the table
     Algorithm:
     1) Hide all of the buttons
     2) Set the trail button to be visible
     Assistance Received: none
      ********************************************************************* */
    void ShowHumanButtons() {
        HideAllButtons();
        trailButton.setVisibility(View.VISIBLE);
        captureButton.setVisibility(View.VISIBLE);

        DisableMainInputButtons();
    }

    /** *********************************************************************
     Function Name: ShowComputerButtons
     Purpose: To show only the computer buttons
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Hide all of the buttons
     2) Show the computer move button
     Assistance Received: none
      ********************************************************************* */
    void ShowComputerButtons() {
        HideAllButtons();
        computerMoveButton.setVisibility(View.VISIBLE);
    }

    void ShowCaptureButtons() {
        HideAllButtons();
        captureBuildButton.setVisibility(View.VISIBLE);
        captureBuildButton.setEnabled(false);
        captureSetButton.setVisibility(View.VISIBLE);
        captureSetButton.setEnabled(false);
        captureDoneButton.setVisibility(View.VISIBLE);
        captureDoneButton.setEnabled(false);
    }

    void EnableMainInputButtons() {
        trailButton.setEnabled(true);
        captureButton.setEnabled(true);
    }

    void DisableMainInputButtons() {
        trailButton.setEnabled(false);
        captureButton.setEnabled(false);
    }

    /** *********************************************************************
     Function Name: HideAllButtons
     Purpose: To hide all of the player input buttons for making moves
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Hide the various buttons that are used for making moves
     Assistance Received: none
     ********************************************************************* */
    void HideAllButtons() {

        trailButton.setVisibility(View.GONE);
        computerMoveButton.setVisibility(View.GONE);
        captureButton.setVisibility(View.GONE);
        captureBuildButton.setVisibility(View.GONE);
        captureSetButton.setVisibility(View.GONE);
        captureDoneButton.setVisibility(View.GONE);
    }

    /** *********************************************************************
     Function Name: PrintErrors
     Purpose: If there were any errors in making moves, this will output them to the text box
     Parameters: None
     Return Value:
     @return boolean, true if there was an error, false otherwise
     Local Variables: None
     Algorithm:
     1) If there was an error, output and return true
     2) Otherwise, show the computer buttons and output false
     Assistance Received: none
     ********************************************************************* */
    boolean PrintErrors() {
        if(!roundModel.GetErrorReason().equals("")) {
            outputTextView.append("Can not trail with " + highlightedCard.GetCard() + " because " +
                    roundModel.GetErrorReason());
            outputTextView.append("\n");
            return true;
        }
        else {
            ShowComputerButtons();
            return false;
        }
    }

    void EnableTableButtons() {
        for(int i = 0; i < table.size(); i++) {
            table.get(i).setEnabled(true);
        }
    }

    void DisableTableButtons() {
        for(int i = 0; i < table.size(); i++) {
            table.get(i).setEnabled(false);
        }
    }

}
