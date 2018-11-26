package edu.ramapo.ncockcro.casino;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button DoneButton;
    private Button captureAddSetButton;
    private Button backButton;
    private Button buildButton;
    private Button newBuildButton;
    private Button existingBuildButton;
    private Button insideCaptureBuildButton;
    private Button seeResultsButton;

    private Vector<Card_View> playerCards = new Vector<Card_View>();
    private Vector<Card_View> computerCards = new Vector<Card_View>();
    private Vector<Card_View> tableCards = new Vector<Card_View>();
    private Card_View playerWantCard = new Card_View();
    private Vector<Card_View> deckCards = new Vector<Card_View>();
    private Vector<Card_View> playerPileCards = new Vector<Card_View>();
    private Vector<Card_View> computerPileCards = new Vector<Card_View>();
    private Card_View highlightedCard = new Card_View();
    private Vector<Card_View> highlightedTableCard = new Vector<Card_View>();
    private Vector<Card_View> setHighlightedTableCard = new Vector<Card_View>();
    private int tableIdCounter = 0;
    private int setCardPickedCounter = 0;
    private Vector<Card_View> buildHighlightedTableCards = new Vector<Card_View>();
    private Vector<Build_Model> multipleBuildCards = new Vector<Build_Model>();
    private Vector<Card_View> singleBuildCards = new Vector<Card_View>();
    private Card_View currentBuildCard;

    private int humanScore;
    private int computerScore;
    int round;

    /** *********************************************************************
     Function Name: Round_View
     Purpose: Default constructor for the round_view class
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Nothing as of right now
     Assistance Received: none
     ********************************************************************* */
    Round_View() {

    }

    /** *********************************************************************
     Function Name: Round_View
     Purpose: Default constructor with overloaded parameters
     Parameters:
     @param passedActivity, Activity, the main activity of the screen
     @param passedRoundModel, Round_Model, the roundModel that the MainGame_Activity is using
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Initialize all of the buttons that are on the main screen and get their ID's
     Assistance Received: none
     ********************************************************************* */
    Round_View(Activity passedActivity, Round_Model passedRoundModel) {
        this.activity = (Activity) passedActivity;
        roundModel = passedRoundModel;
        outputTextView = (TextView) activity.findViewById(R.id.outputTextView);
        outputTextView.setMovementMethod(new ScrollingMovementMethod());
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
        DoneButton = (Button) activity.findViewById(R.id.doneButton);
        captureAddSetButton = (Button) activity.findViewById(R.id.addSetButton);
        backButton = (Button) activity.findViewById(R.id.backButton);
        buildButton = (Button) activity.findViewById(R.id.buildButton);
        newBuildButton = (Button) activity.findViewById(R.id.buildNewButton);
        existingBuildButton = (Button) activity.findViewById(R.id.existingBuildButton);
        insideCaptureBuildButton = (Button) activity.findViewById(R.id.insideCaptureBuildButton);
        seeResultsButton = (Button) activity.findViewById(R.id.seeResultsButton);

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

        // Deal cards in the game's logic
        roundModel.DealCardsToPlayer();

        // Get the cards for the human and the computer
        playerCards = cardView.ConvertModelToView(roundModel.GetHand(0));
        computerCards = cardView.ConvertModelToView(roundModel.GetHand(1));

        // Draw the images for the player's cards
        playerCards.get(0).DrawImageCard(playerImageButtonHand.get(0));
        playerCards.get(1).DrawImageCard(playerImageButtonHand.get(1));
        playerCards.get(2).DrawImageCard(playerImageButtonHand.get(2));
        playerCards.get(3).DrawImageCard(playerImageButtonHand.get(3));

        // Draw the images for the computer's cards
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

        // Clear what is on the screen and get the current table cards
        table.clear();
        roundModel.DealCardsToTable();
        tableCards = cardView.ConvertModelToView(roundModel.GetTable());

        // Dynamically add a card to the table
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

        // Clear what is on the table and get the current table cards
        table.clear();
        tableCards.clear();
        singleBuildCards.clear();
        tableLinearLayout.removeAllViews();
        highlightedTableCard.clear();

        Log.d("InUpdate", Integer.toString(roundModel.GetTableBuilds().size()));
        tableCards = cardView.ConvertModelToView(roundModel.GetTable());

        for(int i = 0; i < tableCards.size(); i++) {
            Log.d("TableCards", tableCards.get(i).GetCard());
        }

        // Get the builds from the model
        multipleBuildCards = roundModel.GetTableBuilds();

        Log.d("tableBuilds", Integer.toString(multipleBuildCards.size()));

        // Start a counter at 0 to keep track of how many cards are on the table
        int numOfBuildCards = 0;

        // Cycle through the build objects...
        for(int i = 0; i < multipleBuildCards.size(); i++) {

            Log.d("multiBuild", "Hello");
            // Get the build cards for the specific build object
            singleBuildCards = cardView.ConvertModelToView(multipleBuildCards.get(i).GetBuildOfCards());
            Log.d("singleSize", Integer.toString(singleBuildCards.size()));

            // Cycle through the cards and display them on the table
            for(int j = 0; j < singleBuildCards.size(); j++) {
                Log.d("buildInView", singleBuildCards.get(j).GetCard());
                table.add(AddCardToTable(context));
                singleBuildCards.get(j).DrawImageCard(table.get(numOfBuildCards));

                // Alternating between green and blue to distinguish different builds
                if(i % 2 == 0) {
                    table.get(numOfBuildCards).setColorFilter(0x4033FF42);
                }
                else {
                    table.get(numOfBuildCards).setColorFilter(0x403336FF);
                }
                numOfBuildCards++;
            }
        }

        // Then outputting the cards that are actually on the table
        int tableCount = 0;
        for(int i = numOfBuildCards; i < tableCards.size() + numOfBuildCards; i++) {
            table.add(AddCardToTable(context));
            tableCards.get(tableCount).DrawImageCard(table.get(i));
            tableCount++;
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

    /** *********************************************************************
     Function Name: DrawPlayerPile
     Purpose: To dynamically draw the cards that are currently in the player's pile
     Parameters:
     @param context, Context object, the context of the activity we are in
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Clear what is in the player pile horizontal scroll view
     2) Re draw the new updated player pile
     Assistance Received: none
     ********************************************************************* */
    void DrawPlayerPile(Context context) {

        // Clear what is on the table for the player's pile and get the current cards in the pile
        playerPile.clear();
        playerPileLinearLayout.removeAllViews();
        playerPileCards = cardView.ConvertModelToView(roundModel.GetPlayerPile(0));

        // Dynamically add the cards to the player's pile and draw the correct image
        for(int i = 0; i < playerPileCards.size(); i++) {
            playerPile.add(AddCardToPlayerPile(context));
            playerPileCards.get(i).DrawImageViewCard(playerPile.get(i));
        }
    }

    /** *********************************************************************
     Function Name: DrawComputerPile
     Purpose: To dynamically draw the computer's pile
     Parameters:
     @param context, Context object of the current activity
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Remove the cards that were in the computer pile horizontal scroll view already
     2) Re draw the current computer's pile to the screen
     Assistance Received: none
     ********************************************************************* */
    void DrawComputerPile(Context context) {

        // Clear the cards that are currently on the screen for the computer's pile
        computerPile.clear();
        computerPileLinearLayout.removeAllViews();
        computerPileCards = cardView.ConvertModelToView(roundModel.GetPlayerPile(1));

        // Re draw the current cards that are in the computer's pile
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

        // When a button from the table is clicked, this is the on click listener that handles what
        // to do with that button
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

    /** *********************************************************************
     Function Name: AddCardToPlayerPile
     Purpose: To create a new image view to be added to the player pile horizontal scroll view
     Parameters:
     @param context, Context object, the current activity that were in
     Return Value:
     @return ImageView, the new image view that was created
     Local Variables: None
     Algorithm:
     1) Create a new image view
     2) Set the image background to the default red back
     3) Set the parameters of the button to fit on the screen
     4) Add the image view to the player pile linear layout
     5) Return the button
     Assistance Received: none
     ********************************************************************* */
    ImageView AddCardToPlayerPile(Context context) {
        ImageView button = new ImageView(context);
        button.setImageResource(R.drawable.redback);
        button.setLayoutParams(new LinearLayout.LayoutParams(89, 125));
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

        // Highlight the card that the user picked red
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

        // Unhighlight any other cards that might have been highlighted previously
        for(int i = 0; i < playerCards.size(); i++) {
            if(!playerCards.get(i).GetCard().equals(highlightedCard.GetCard())) {
                playerImageButtonHand.get(i).setColorFilter(null);
            }
        }

        // Enabled the main buttons (build, capture, trail) to be accessible to the player
        EnableMainInputButtons();
        SetPlayerWantCard(highlightedCard);
    }

    /** *********************************************************************
     Function Name: HighlightTableCard
     Purpose: To highlight a table card when clicked
     Parameters:
     @param view, View object, the card that was clicked
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) If the player is capturing a set, highlight only the first two cards
     they pick cyan
     2) If it is a regular table card the player is picking, it highlights red
     3) If the player is capturing a set, it sets the appropriate information for the set
     4) Otherwise, it just turns on the buttons for capturing builds or sets
     Assistance Received: none
     ********************************************************************* */
    void HighlightTableCard(View view) {

        for(int i = 0; i < multipleBuildCards.size(); i++) {

            //singleBuildCards = multipleBuildCards.get(i).GetBuildOfCards()
        }

        // If the player is capturing a set...
        if(roundModel.GetPlayerModelWantSet() == 'y') {

            // If they haven't clicked on two cards yet...
            if(setCardPickedCounter < 2) {

                // Highlight the card the player picked cyan and add the card to the setHighlightedTableCard
                for (int i = 0; i < table.size(); i++) {
                    if (view.getId() == table.get(i).getId()) {
                        table.get(i).setColorFilter(0x4000ffff);
                        setHighlightedTableCard.add(tableCards.get(i));
                        table.get(i).setEnabled(false);
                    }
                }
            }
        }

        // If the player is making a new build...
        else if(roundModel.GetPlayerMove() == 'b' && roundModel.GetPlayerModelWantNewOrExisting() != 'e') {

            int numOfBuilds = 0;

            // Cycle through the builds and highlight them to the correct colors
            for(int i = 0; i < multipleBuildCards.size(); i++) {

                singleBuildCards = cardView.ConvertModelToView(multipleBuildCards.get(i).GetBuildOfCards());

                for(int j = 0; j < singleBuildCards.size(); j++) {

                    if(i % 2 == 0) {
                        table.get(numOfBuilds).setColorFilter(0x4033FF42);
                    }
                    else {
                        table.get(numOfBuilds).setColorFilter(0x403336FF);
                    }

                    numOfBuilds++;
                }
            }

            // Then cycle through the actual table cards
            int tableCount = 0;
            for(int i = numOfBuilds; i < table.size(); i++) {
                if(view.getId() == table.get(i).getId()) {
                    table.get(i).setColorFilter(0x40ff0000);
                    buildHighlightedTableCards.add(tableCards.get(tableCount));
                    table.get(i).setEnabled(false);
                }
                tableCount++;
            }
        }

        // If the player is capturing a build or adding to an existing build...
        else if(roundModel.GetPlayerModelWantCaptureBuild() == 'y' || roundModel.GetPlayerModelWantNewOrExisting() == 'e') {

            int numOfBuilds = 0;
            Log.d("InBuilds", "in the builds");
            // Cycle through the builds...
            for(int i = 0; i < multipleBuildCards.size(); i++) {
                singleBuildCards = cardView.ConvertModelToView(multipleBuildCards.get(i).GetBuildOfCards());
                for(int j = 0; j < singleBuildCards.size(); j++) {
                    if(view.getId() == table.get(numOfBuilds).getId()) {

                        // Once the card is found that the user clicked, highlight all of the cards
                        // in that build
                        HighlightBuild(singleBuildCards);
                    }
                    numOfBuilds++;
                }
            }

        }



        // Otherwise, we just highlight the card the player chose in red
        else {
            int numOfBuilds = 0;
            for(int i = 0; i < multipleBuildCards.size(); i++) {
                singleBuildCards = cardView.ConvertModelToView(multipleBuildCards.get(i).GetBuildOfCards());

                for(int j = 0; j < singleBuildCards.size(); j++) {
                    numOfBuilds++;
                    Log.d("numOfBuilds", Integer.toString(numOfBuilds));
                }


            }

            Log.d("In highlight", "We are here");
            int tableCount = 0;
            for (int i = numOfBuilds; i < tableCards.size() + numOfBuilds; i++) {
                if (view.getId() == table.get(i).getId()) {
                    table.get(i).setColorFilter(0x40ff0000);
                    highlightedTableCard.add(tableCards.get(tableCount));
                    table.get(i).setEnabled(false);
                }
                tableCount++;
            }

            for(int i = 0; i < highlightedTableCard.size(); i++) {
                Log.d("Highlighted", highlightedTableCard.get(i).GetCard());
            }
        }

        // If the player is capturing sets,
        if(roundModel.GetPlayerModelWantSet() == 'y') {

            // Increase the card picked counter
            setCardPickedCounter++;

            // Once the user has clicked two cards, allow them to press the buttons to add the set
            if(setCardPickedCounter > 1) {
                captureAddSetButton.setEnabled(true);
                DoneButton.setEnabled(true);
            }
        }
        else if(roundModel.GetPlayerMove() == 'b') {
            DoneButton.setEnabled(true);
        }
        // Otherwise, enable the capture buttons
        else {
            captureBuildButton.setEnabled(true);
            captureSetButton.setEnabled(true);
            DoneButton.setEnabled(true);
        }
    }

    /** *********************************************************************
     Function Name: HighlightBuild
     Purpose: To highlight all of the cards in a build and disable them
     Parameters:
     @param buildCards, the cards of the build
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Cycle through all the builds on the table
     2) Once the card of a build matchs with the card of the build we want to highlight,
     then it will highlight and disable that particular build
     Assistance Received: none
      ********************************************************************* */
    void HighlightBuild(Vector<Card_View> buildCards) {

        Vector<Card_View> tempCards;
        int numOfBuilds = 0;

        // Cycling through all the build objects on the table...
        for(int i = 0; i < multipleBuildCards.size(); i++) {

            // Getting the specific cards of a build
            tempCards = cardView.ConvertModelToView(multipleBuildCards.get(i).GetBuildOfCards());

            // Cycling through a particular build
            for(int j = 0; j < tempCards.size(); j++) {

                // Cycling through the build we want highlighted
                for(int k = 0; k < buildCards.size(); k++) {

                    // If the cards from a particular build match with the cards we want
                    // highlighted, then highlight and disable those cards
                    if (tempCards.get(j).GetCard().equals(buildCards.get(k).GetCard())) {
                        Log.d("Color", buildCards.get(k).GetCard());
                        table.get(numOfBuilds).setColorFilter(0x40ff0000);
                        table.get(numOfBuilds).setEnabled(false);
                        currentBuildCard = buildCards.get(k);
                        insideCaptureBuildButton.setEnabled(true);
                    }

                }
                numOfBuilds++;
            }
        }

        // Update the buttons
        roundModel.SetPlayerModelExistingBuildCard(cardView.ConvertViewToModel(currentBuildCard));
        roundModel.SetPlayerCard(cardView.ConvertViewToModel(highlightedCard));
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

    /** *********************************************************************
     Function Name: SetBuildInfo
     Purpose: To set the necessary information for a build
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) If the player is making a new build, then we need to send the card
     the player originally highlighted
     2) Send a card from the table they highlighted
     3) Unhighlight the cards in the player's hand
     Assistance Received: none
      ********************************************************************* */
    void SetBuildInfo() {

        if(roundModel.GetPlayerModelWantNewOrExisting() == 'n') {

            // Sending over the card from the player's hand they selected
            for(int i = 0; i < playerCards.size(); i++) {
                if(highlightedCard.GetCard().equals(playerCards.get(i).GetCard())) {
                    roundModel.SetPlayerCard(cardView.ConvertViewToModel(playerCards.get(i)));
                }
            }

            // Sending over the card from the table the player selected
            for(int i = 0; i < buildHighlightedTableCards.size(); i++) {
                Log.d("Build", Character.toString(buildHighlightedTableCards.get(i).GetNumber()));
            }
            roundModel.SetPlayerModelBuildCards(cardView.ConvertViewToModelVector(buildHighlightedTableCards));

            // Unhighlighting the rest of the player's cards
            for(int i = 0; i < playerCards.size(); i++) {
                playerImageButtonHand.get(i).setColorFilter(null);
            }
        }
    }

    /** *********************************************************************
     Function Name: SetCaptureInfo
     Purpose: To set the player card and table card to be captured to the model
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Cycle through the player's cards and find the one that they chose and send it over to model
     2) Send over the table cards they selected to the model as well
     3) Unhighlight the cards from the player's hand
     Assistance Received: none
     ********************************************************************* */
    void SetCaptureInfo() {

        // Sending over the card the player wants to capture with
        for(int i = 0; i < playerCards.size(); i++) {
            if(highlightedCard.GetCard().equals(playerCards.get(i).GetCard())) {
                roundModel.SetPlayerCard(cardView.ConvertViewToModel(playerCards.get(i)));
            }
        }

        // Sending over the table cards the player selected
        roundModel.SetTableCardsToBeCaptured(cardView.ConvertViewToModelVector(highlightedTableCard));

        // Setting the player's hand cards to be unhighlighted
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

        // Get the latest version of the player's cards
        playerCards = cardView.ConvertModelToView(roundModel.GetHand(0));
        computerCards = cardView.ConvertModelToView(roundModel.GetHand(1));
        playerPileCards = cardView.ConvertModelToView(roundModel.GetPlayerPile(0));

        // Draw the player's hand
        for(int i = 0; i < playerCards.size(); i++) {
            Log.d("Player", playerCards.get(i).GetCard());
            playerCards.get(i).DrawImageCard(playerImageButtonHand.get(i));
        }

        // Any cards the player used isnt being displayed
        for(int i = playerCards.size(); i < 4; i++) {
            playerImageButtonHand.get(i).setVisibility(View.GONE);
        }

        // Draw the computer's hand
        for(int i = 0; i < computerCards.size(); i++) {
            computerCards.get(i).DrawImageViewCard(computerImageViewHand.get(i));
        }

        // Any cards the computer used isnt being displayed
        for(int i = computerCards.size(); i < 4; i++) {
            computerImageViewHand.get(i).setVisibility(View.GONE);
        }

        Log.d("update", "In updating screen");
        // Draw the rest of the content to the screen
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
    Integer CheckForDealingCards(Context context) {

        Log.d("Check", "Checking for dealing cards");

        for(int i = 0; i < roundModel.GetHand(0).size(); i++) {
            Log.d("humanCards", roundModel.GetHand(0).get(i).GetCard());
        }

        Log.d("HandEmpty", Boolean.toString(roundModel.CheckIfPlayersHandEmpty()));
        Log.d("DeckEmpty", Boolean.toString(roundModel.CheckIfDeckEmpty()));

        // If the player's hands are empty but not the deck, deal cards and return true
        if(roundModel.CheckIfPlayersHandEmpty() && !roundModel.CheckIfDeckEmpty()) {
            Log.d("Choice", "Returning 1");
            roundModel.DealCardsToPlayer();
            SetPlayersHandsVisible();
            UpdateScreen(context);
            return 1;
        }
        // If the hands aren't empty and the deck isn't empty, return true
        else if(!roundModel.CheckIfPlayersHandEmpty() && !roundModel.CheckIfDeckEmpty()) {
            Log.d("Choice", "returning 2");
            return 2;
        }
        // Otherwise, return false, the round is over
        else if(roundModel.CheckIfPlayersHandEmpty() && roundModel.CheckIfDeckEmpty()){
            Log.d("Choice", "returning 3");

            roundModel.GiveTableCards();
            HideAllButtons();
            seeResultsButton.setVisibility(View.VISIBLE);

            return 3;
        }

        return 0;
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

        // Clear the error (if any) and show just the buttons for the human to press
        roundModel.ClearErrorReason();
        HideAllButtons();
        trailButton.setVisibility(View.VISIBLE);
        captureButton.setVisibility(View.VISIBLE);
        buildButton.setVisibility(View.VISIBLE);

        // Disable the main input buttons
        DisableMainInputButtons();

        // Enabled the player's hand buttons
        for(int i = 0; i < playerCards.size(); i++) {
            playerImageButtonHand.get(i).setEnabled(true);
        }
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

        // Hide all the buttons except for the one for the computer to make a move
        HideAllButtons();
        computerMoveButton.setVisibility(View.VISIBLE);

        // Disable the human hand's buttons so they cant click on them
        for(int i = 0; i < playerCards.size(); i++) {
            playerImageButtonHand.get(i).setEnabled(false);
        }
    }

    /** *********************************************************************
     Function Name: ShowCaptureButtons
     Purpose: Show just the buttons needed for capturing
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Hide all the buttons
     2) Show just the buttons for capturing but disable all of them
     Assistance Received: none
     ********************************************************************* */
    void ShowCaptureButtons() {

        HideAllButtons();
        captureBuildButton.setVisibility(View.VISIBLE);
        captureBuildButton.setEnabled(true);
        captureSetButton.setVisibility(View.VISIBLE);
        captureSetButton.setEnabled(true);
        DoneButton.setVisibility(View.VISIBLE);
        DoneButton.setEnabled(false);

    }

    /** *********************************************************************
     Function Name: ShowCaptureSetButtons
     Purpose: To show just the buttons needed for capturing a set
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Hide all of the buttons
     2) Just show the buttons for adding a set and being done for a capture
     Assistance Received: none
     ********************************************************************* */
    void ShowCaptureSetButtons() {

        HideAllButtons();
        captureAddSetButton.setVisibility(View.VISIBLE);
        captureAddSetButton.setEnabled(false);
        DoneButton.setVisibility(View.GONE);
        DoneButton.setEnabled(false);
        backButton.setVisibility(View.VISIBLE);
    }

    /** *********************************************************************
     Function Name: ShowBuildButtons
     Purpose: To show just the buttons needed making a new or existing build
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Hide all of the buttons
     2) Just show the buttons for adding making a new or existing build
     Assistance Received: none
     ********************************************************************* */
    void ShowBuildButtons() {
        HideAllButtons();
        newBuildButton.setVisibility(View.VISIBLE);
        existingBuildButton.setVisibility(View.VISIBLE);
    }

    /** *********************************************************************
     Function Name: ShowNewOrExistingBuildButtons
     Purpose: To show just the buttons needed for finishing a new or existing build
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Hide all of the buttons
     2) Just show the buttons for finishing a new or existing build
     Assistance Received: none
     ********************************************************************* */
    void ShowNewOrExistingBuildButtons() {
        HideAllButtons();
        backButton.setVisibility(View.VISIBLE);
        DoneButton.setVisibility(View.VISIBLE);
        DoneButton.setEnabled(false);
    }

    /** *********************************************************************
     Function Name: ShopwCaptureBuildButtons
     Purpose: To show just the buttons needed for capturing a build
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Hide all of the buttons
     2) Just show the buttons for adding a build
     Assistance Received: none
     ********************************************************************* */
    void ShowCaptureBuildButtons() {

        HideAllButtons();
        backButton.setVisibility(View.VISIBLE);
        insideCaptureBuildButton.setVisibility(View.VISIBLE);
        insideCaptureBuildButton.setEnabled(false);
    }

    /** *********************************************************************
     Function Name: EnableManInputButtons
     Purpose: To enable the main buttons (build, capture, trail) for the player to use after
     they clicked on one of their cards
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Enable the trail, capture, and build buttons
     Assistance Received: none
     ********************************************************************* */
    void EnableMainInputButtons() {

        trailButton.setEnabled(true);
        captureButton.setEnabled(true);
        buildButton.setEnabled(true);
    }

    /** *********************************************************************
     Function Name: DisableMainInputButtons
     Purpose: To disable the main input buttons at the start of the human's move
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Disable the trail, capture, and build buttons
     Assistance Received: none
     ********************************************************************* */
    void DisableMainInputButtons() {
        trailButton.setEnabled(false);
        captureButton.setEnabled(false);
        buildButton.setEnabled(false);
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
        DoneButton.setVisibility(View.GONE);
        captureAddSetButton.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);
        buildButton.setVisibility(View.GONE);
        newBuildButton.setVisibility(View.GONE);
        existingBuildButton.setVisibility(View.GONE);
        insideCaptureBuildButton.setVisibility(View.GONE);
        seeResultsButton.setVisibility(View.GONE);

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

        // resetting variables that are used for capturing sets
        setCardPickedCounter = 0;
        setHighlightedTableCard.clear();
        roundModel.SetPlayerModelWantSet('n');
        roundModel.PlayerModelClearPlayerMultipleSets();

        // If there was an error reason...
        if(!roundModel.GetErrorReason().equals("None")) {
            Log.d("err", "inside of print errors");

            // Output the error reason to the text view
            outputTextView.append(roundModel.GetErrorReason());
            outputTextView.append("\n");
            outputTextView.append("\n");

            // Clear the reason and have the human play again
            roundModel.ClearErrorReason();
            HideAllButtons();
            ShowHumanButtons();

            int numOfBuildCards = 0;

            for(int i = 0; i < multipleBuildCards.size(); i++) {

                singleBuildCards = cardView.ConvertModelToView(multipleBuildCards.get(i).GetBuildOfCards());

                for(int j = 0; j < singleBuildCards.size(); j++) {
                    singleBuildCards.get(j).DrawImageCard(table.get(numOfBuildCards));

                    if(i % 2 == 0) {
                        table.get(numOfBuildCards).setColorFilter(0x4033FF42);
                    }
                    else {
                        table.get(numOfBuildCards).setColorFilter(0x403336FF);
                    }
                    numOfBuildCards++;
                }
            }

            // Unhighlight the cards on the table and re enable them
            for(int i = numOfBuildCards; i < tableCards.size(); i++) {
                table.get(i).setColorFilter(null);
                highlightedTableCard.clear();
                table.get(i).setEnabled(true);
            }

            return true;
        }

        // Otherwise, switch to the computer's button for their move
        else {
            Log.d("CurrentPlayer", Integer.toString(roundModel.GetCurrentPlayer()));
            Log.d("Else", "switching to computer buttons");
            ShowComputerButtons();
            return false;
        }
    }

    /** *********************************************************************
     Function Name: CaptureBuildPrintError
     Purpose: To show any errors that might happen when trying to capture a build
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) If there was an error, output it and send the human back to the main buttons
     2) Otherwise, just return false
     Assistance Received: none
     ********************************************************************* */
    boolean CaptureBuildPrintError() {

        // If there was an error...
        if(!roundModel.GetErrorReason().equals("None")) {
            // Output the error reason to the text view
            outputTextView.append(roundModel.GetErrorReason());
            outputTextView.append("\n");
            outputTextView.append("\n");

            // Clear the reason and have the human play again
            roundModel.ClearErrorReason();
            HideAllButtons();
            ShowHumanButtons();

            ClearData();

            return true;
        }
        // Otherwise, just return false
        else {
            return false;
        }
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
    void EnableTableButtons() {

        Log.d("TableSize", Integer.toString(table.size()));
        for(int i = 0; i < table.size(); i++) {
            table.get(i).setEnabled(true);
        }
    }

    /** *********************************************************************
     Function Name: DisableTableButtons
     Purpose: Disable the image buttons on the table when the user isn't allowed to click them
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Cycle through the table cards and disable each one
     Assistance Received: none
      ********************************************************************* */
    void DisableTableButtons() {
        for(int i = 0; i < table.size(); i++) {
            table.get(i).setEnabled(false);
        }
    }

    /** *********************************************************************
     Function Name: SetPlayersHandsVisible
     Purpose: Set both of the player's hands visible when both player's use all of their cards
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Cycle through the human's cards and set visible
     2) Cycle through the computer's cards and set visible
     Assistance Received: none
      ********************************************************************* */
    void SetPlayersHandsVisible() {

        // Human's cards
        for(int i = 0; i < 4; i++) {
            playerImageButtonHand.get(i).setVisibility(View.VISIBLE);
        }

        // Computer's cards
        for(int i = 0; i < 4; i++) {
            computerImageViewHand.get(i).setVisibility(View.VISIBLE);
        }
    }

    /** *********************************************************************
     Function Name: PrintTableToOutput
     Purpose: Print the table to the output text view
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Print the table to the output text view
     Assistance Received: none
      ********************************************************************* */
    void PrintTableToOutput() {

        outputTextView.append("TABLE: ");
        for(int i = 0; i < tableCards.size(); i++) {
            outputTextView.append(tableCards.get(i).GetCard() + " ");
        }
        outputTextView.append("\n");
    }

    /** *********************************************************************
     Function Name: AddSetToPlayer
     Purpose: Take the set cards the user entered earlier, put them in a set, and add to the player
     Parameters: None
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Put the sets cards into a set object
     2) Send the set object to the player classed to be added
     Assistance Received: none
      ********************************************************************* */
    void AddSetToPlayer() {
        Set_Model tempSet = new Set_Model();
        tempSet.SetCardsOfSet(cardView.ConvertViewToModelVector(setHighlightedTableCard));
        roundModel.SetPlayerModelAddSet(tempSet);
        DoneButton.setVisibility(View.VISIBLE);

    }

    /** *********************************************************************
     Function Name: ClearData
     Purpose: To clear everything that the player may have altered during their move
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Unhighlight all of the player's cards
     2) Unhighlight all of the table's cards
     3) Clear various vectors and set variables to 'n'
     Assistance Received: none
     ********************************************************************* */
    void ClearData() {

        HideAllButtons();
        ShowHumanButtons();

        //Unhighlight player's cards
        for(int i = 0; i < playerCards.size(); i++) {
            playerImageButtonHand.get(i).setColorFilter(null);
        }

        int numOfBuildCards = 0;


        for(int i = 0; i < multipleBuildCards.size(); i++) {

            singleBuildCards = cardView.ConvertModelToView(multipleBuildCards.get(i).GetBuildOfCards());

            for(int j = 0; j < singleBuildCards.size(); j++) {
                Log.d("buildInView", singleBuildCards.get(j).GetCard());
                singleBuildCards.get(j).DrawImageCard(table.get(numOfBuildCards));

                if(i % 2 == 0) {
                    table.get(numOfBuildCards).setColorFilter(0x4033FF42);
                }
                else {
                    table.get(numOfBuildCards).setColorFilter(0x403336FF);
                }
                numOfBuildCards++;
            }
        }

        // Unhighlight table cards
        for(int i = numOfBuildCards; i < tableCards.size(); i++) {
            table.get(i).setColorFilter(null);
        }
        roundModel.PlayerModelClearPlayerMultipleSets();
        setCardPickedCounter = 0;
        roundModel.SetPlayerModelWantSet('n');
        roundModel.SetPlayerModelWantBuild('n');

        setHighlightedTableCard.clear();
        buildHighlightedTableCards.clear();
    }

    /** *********************************************************************
     Function Name: SetHumanScore
     Purpose: Set the score for the human to what was passed in
     Parameters:
     @param score, int
     Return Value: None
     Local Variables: None
     Algorithm:
     1) To set the humanScore to what was passed in
     Assistance Received: none
     ********************************************************************* */
    void SetHumanScore(int score) {
        humanScore = score;
    }

    /** *********************************************************************
     Function Name: SetComputerScore
     Purpose: Set the score for the computer to what was passed in
     Parameters:
     @param score, int
     Return Value: None
     Local Variables: None
     Algorithm:
     1) To set the computerScore to what was passed in
     Assistance Received: none
      ********************************************************************* */
    void SetComputerScore(int score) {
        computerScore = score;
    }

    /** *********************************************************************
     Function Name: SetRound
     Purpose: Set the round to what was passed in
     Parameters:
     @param passedRound, int
     Return Value: None
     Local Variables: None
     Algorithm:
     1) To set the round to what was passed in
     Assistance Received: none
      ********************************************************************* */
    void SetRound(int passedRound) {
        round = passedRound;
    }

    /** *********************************************************************
     Function Name: GetHumanScore
     Purpose: To retrieve the human's score to what was passed in
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Return the humanScore variable
     Assistance Received: none
      ********************************************************************* */
    int GetHumanScore() {
        return humanScore;
    }

    /** *********************************************************************
     Function Name: GetComputerScore
     Purpose: To retrieve the computer's score to what was passed in
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Return the computerScore variable
     Assistance Received: none
     ********************************************************************* */
    int GetComputerScore() {
        return computerScore;
    }

    /** *********************************************************************
     Function Name: GetRound
     Purpose: To retrieve the round
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Return the round variable
     Assistance Received: none
     ********************************************************************* */
    int GetRound() {
        return round;
    }

    /** *********************************************************************
     Function Name: ShowSaveTextInput
     Purpose: To prompt the user to type in a name for their save file
     Parameters:
     @param context, Context object
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Create an input box for the player to type in a name for their save file
     and then save the game under that file name
     Assistance Received: none
     ********************************************************************* */
    void ShowSaveTextInput(Context context) {
        final EditText saveEditText = new EditText(context);
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Save to a file");
        dialog.setMessage("Enter a file name for your save.");
        dialog.setView(saveEditText);
        final Context innerContext = context;
        dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                roundModel.SaveGame(innerContext, saveEditText.getText().toString());
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