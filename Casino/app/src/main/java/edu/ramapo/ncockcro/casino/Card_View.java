package edu.ramapo.ncockcro.casino;

import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Vector;

public class Card_View {

    private String card;
    private char suit;
    private char number;

    /** *********************************************************************
     Function Name: Card_View
     Purpose: Default constructor
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Default constructor
     Assistance Received: none
      ********************************************************************* */
    Card_View() {

    }

    /** *********************************************************************
     Function Name: Card_View
     Purpose: Overloaded default constructor
     Parameters:
     @param passedCard String, holds the value of a card
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the card, suit, and number of a card to what was passed in
     Assistance Received: none
      ********************************************************************* */
    Card_View(String passedCard) {
        card = passedCard;
        suit = passedCard.charAt(0);
        number = passedCard.charAt(1);
    }

    /** *********************************************************************
     Function Name: DrawFourCardsTable
     Purpose: To draw four cards to the table
     Parameters:
     @param passedCard card_model object, a card that is passed in
     Return Value: None
     Local Variables: None
     Algorithm:
     1) Set the card, suit, and number to what the card passed in was
     Assistance Received: none
      ********************************************************************* */
    Card_View (Card_Model passedCard) {
        card = passedCard.GetCard();
        suit = passedCard.GetSuit();
        number = passedCard.GetNumber();
    }

    /** *********************************************************************
     Function Name: GetCard
     Purpose: To return the card
     Parameters: None
     Return Value:
     @return String
     Local Variables: None
     Algorithm:
     1) Return the card value
     Assistance Received: none
      ********************************************************************* */
    String GetCard() {
        return card;
    }

    /** *********************************************************************
     Function Name: GetSuit
     Purpose: To retrive the suit of a card
     Parameters:
     Return Value:
     @return char
     Local Variables: None
     Algorithm:
     1) Return the suit of a card
     Assistance Received: none
      ********************************************************************* */
    char GetSuit() {
        return suit;
    }

    /** *********************************************************************
     Function Name: GetNumber
     Purpose: To retrieve the number of a card
     Parameters: None
     Return Value:
     @return char
     Local Variables: None
     Algorithm:
     1) To return the number of a card
     Assistance Received: none
      ********************************************************************* */
    char GetNumber() {
        return number;
    }

    /** *********************************************************************
     Function Name: ConvertModelToView
     Purpose: To convert a vector of card_model's to card_view's
     Parameters:
     @param passedCards Vector of card_model, holds the cards to be converted to card_view
     Return Value:
     @return Vector<Card_View>
     Local Variables:
        tempCards, the Vector of card_view's which is what will get returned
     Algorithm:
     1) Cycle through the list of cards passed in and create new card_view's for each card
     2) Return the vector of card_view cards
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_View> ConvertModelToView(Vector<Card_Model> passedCards) {
        Vector<Card_View> tempCards = new Vector<Card_View>();

        for(int i = 0; i < passedCards.size(); i++) {
            tempCards.add(new Card_View(passedCards.get(i)));
        }

        return tempCards;
    }

    /** *********************************************************************
     Function Name: ConvertViewToModel
     Purpose: To convect a view object card to a model object card
     Parameters:
     @param passedCard card_model, holds the cards to be converted to card_view
     Return Value:
     @return Card_Model
     Local Variables:
     modelCard, a card_model object that gets returned
     Algorithm:
     1) Set the model card object to what was in the card view object
     2) Return that model object
     Assistance Received: none
      ********************************************************************* */
    Card_Model ConvertViewToModel(Card_View passedCard) {
        Card_Model modelCard = new Card_Model();

        modelCard.SetCard(passedCard.GetCard());
        return modelCard;
    }

    /** *********************************************************************
     Function Name: ConvertViewToModelVector
     Purpose: To convert a vector of card_view's to a vector of card_model's
     Parameters:
     @param passedCards Vector of card_view, holds the cards to be converted to card_model
     Return Value:
     @return Vector<Card_Model>
     Local Variables:
     modelCards, the Vector of card_model's which is what will get returned
     Algorithm:
     1) Cycle through the card view's passed in and store them in the vector of card_model
     Assistance Received: none
      ********************************************************************* */
    Vector<Card_Model> ConvertViewToModelVector(Vector<Card_View> passedCards) {
        Vector<Card_Model> modelCards = new Vector<Card_Model>();

        for(int i = 0; i < passedCards.size(); i++) {
            modelCards.add(ConvertViewToModel(passedCards.get(i)));
        }

        return modelCards;
    }

    /** *********************************************************************
     Function Name: DrawImageCard
     Purpose: To find which card to draw to the screen
     Parameters:
     @param imageButton Imagebutton, the button to have the image drawn onto
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Cycle through the possible card values and once found, draw the card to the button passed in
     2) If not found, report an error message
     Assistance Received: none
      ********************************************************************* */
    void DrawImageCard(ImageButton imageButton) {

        switch (card) {
            case "CA":
                imageButton.setImageResource(R.drawable.ca);
                break;
            case "C2":
                imageButton.setImageResource(R.drawable.c2);
                break;
            case "C3":
                imageButton.setImageResource(R.drawable.c3);
                break;
            case "C4":
                imageButton.setImageResource(R.drawable.c4);
                break;
            case "C5":
                imageButton.setImageResource(R.drawable.c5);
                break;
            case "C6":
                imageButton.setImageResource(R.drawable.c6);
                break;
            case "C7":
                imageButton.setImageResource(R.drawable.c7);
                break;
            case "C8":
                imageButton.setImageResource(R.drawable.c8);
                break;
            case "C9":
                imageButton.setImageResource(R.drawable.c9);
                break;
            case "CX":
                imageButton.setImageResource(R.drawable.cx);
                break;
            case "CJ":
                imageButton.setImageResource(R.drawable.cj);
                break;
            case "CQ":
                imageButton.setImageResource(R.drawable.cq);
                break;
            case "CK":
                imageButton.setImageResource(R.drawable.ck);
                break;
            case "DA":
                imageButton.setImageResource(R.drawable.da);
                break;
            case "D2":
                imageButton.setImageResource(R.drawable.d2);
                break;
            case "D3":
                imageButton.setImageResource(R.drawable.d3);
                break;
            case "D4":
                imageButton.setImageResource(R.drawable.d4);
                break;
            case "D5":
                imageButton.setImageResource(R.drawable.d5);
                break;
            case "D6":
                imageButton.setImageResource(R.drawable.d6);
                break;
            case "D7":
                imageButton.setImageResource(R.drawable.d7);
                break;
            case "D8":
                imageButton.setImageResource(R.drawable.d8);
                break;
            case "D9":
                imageButton.setImageResource(R.drawable.d9);
                break;
            case "DX":
                imageButton.setImageResource(R.drawable.dx);
                break;
            case "DJ":
                imageButton.setImageResource(R.drawable.dj);
                break;
            case "DQ":
                imageButton.setImageResource(R.drawable.dq);
                break;
            case "DK":
                imageButton.setImageResource(R.drawable.dk);
                break;
            case "HA":
                imageButton.setImageResource(R.drawable.ha);
                break;
            case "H2":
                imageButton.setImageResource(R.drawable.h2);
                break;
            case "H3":
                imageButton.setImageResource(R.drawable.h3);
                break;
            case "H4":
                imageButton.setImageResource(R.drawable.h4);
                break;
            case "H5":
                imageButton.setImageResource(R.drawable.h5);
                break;
            case "H6":
                imageButton.setImageResource(R.drawable.h6);
                break;
            case "H7":
                imageButton.setImageResource(R.drawable.h7);
                break;
            case "H8":
                imageButton.setImageResource(R.drawable.h8);
                break;
            case "H9":
                imageButton.setImageResource(R.drawable.h9);
                break;
            case "HX":
                imageButton.setImageResource(R.drawable.hx);
                break;
            case "HJ":
                imageButton.setImageResource(R.drawable.hj);
                break;
            case "HQ":
                imageButton.setImageResource(R.drawable.hq);
                break;
            case "HK":
                imageButton.setImageResource(R.drawable.hk);
                break;
            case "SA":
                imageButton.setImageResource(R.drawable.sa);
                break;
            case "S2":
                imageButton.setImageResource(R.drawable.s2);
                break;
            case "S3":
                imageButton.setImageResource(R.drawable.s3);
                break;
            case "S4":
                imageButton.setImageResource(R.drawable.s4);
                break;
            case "S5":
                imageButton.setImageResource(R.drawable.s5);
                break;
            case "S6":
                imageButton.setImageResource(R.drawable.s6);
                break;
            case "S7":
                imageButton.setImageResource(R.drawable.s7);
                break;
            case "S8":
                imageButton.setImageResource(R.drawable.s8);
                break;
            case "S9":
                imageButton.setImageResource(R.drawable.s9);
                break;
            case "SX":
                imageButton.setImageResource(R.drawable.sx);
                break;
            case "SJ":
                imageButton.setImageResource(R.drawable.sj);
                break;
            case "SQ":
                imageButton.setImageResource(R.drawable.sq);
                break;
            case "SK":
                imageButton.setImageResource(R.drawable.sk);
                break;
            default:
                Log.d("MyError", "Error in drawing the card to the screen in the round_view class.");
                break;
        }

    }

    /** *********************************************************************
     Function Name: DrawImageViewCard
     Purpose: To draw a specific card to a specific image view
     Parameters:
     @param imageView ImageView object, the image view to be drawn to
     Return Value: Void
     Local Variables: None
     Algorithm:
     1) Cycle through the list of cards and once found, draw that card to the image view
     2) Otherwise, print an error message to the log
     Assistance Received: none
      ********************************************************************* */
    void DrawImageViewCard(ImageView imageView) {

        switch (card) {
            case "CA":
                imageView.setImageResource(R.drawable.ca);
                break;
            case "C2":
                imageView.setImageResource(R.drawable.c2);
                break;
            case "C3":
                imageView.setImageResource(R.drawable.c3);
                break;
            case "C4":
                imageView.setImageResource(R.drawable.c4);
                break;
            case "C5":
                imageView.setImageResource(R.drawable.c5);
                break;
            case "C6":
                imageView.setImageResource(R.drawable.c6);
                break;
            case "C7":
                imageView.setImageResource(R.drawable.c7);
                break;
            case "C8":
                imageView.setImageResource(R.drawable.c8);
                break;
            case "C9":
                imageView.setImageResource(R.drawable.c9);
                break;
            case "CX":
                imageView.setImageResource(R.drawable.cx);
                break;
            case "CJ":
                imageView.setImageResource(R.drawable.cj);
                break;
            case "CQ":
                imageView.setImageResource(R.drawable.cq);
                break;
            case "CK":
                imageView.setImageResource(R.drawable.ck);
                break;
            case "DA":
                imageView.setImageResource(R.drawable.da);
                break;
            case "D2":
                imageView.setImageResource(R.drawable.d2);
                break;
            case "D3":
                imageView.setImageResource(R.drawable.d3);
                break;
            case "D4":
                imageView.setImageResource(R.drawable.d4);
                break;
            case "D5":
                imageView.setImageResource(R.drawable.d5);
                break;
            case "D6":
                imageView.setImageResource(R.drawable.d6);
                break;
            case "D7":
                imageView.setImageResource(R.drawable.d7);
                break;
            case "D8":
                imageView.setImageResource(R.drawable.d8);
                break;
            case "D9":
                imageView.setImageResource(R.drawable.d9);
                break;
            case "DX":
                imageView.setImageResource(R.drawable.dx);
                break;
            case "DJ":
                imageView.setImageResource(R.drawable.dj);
                break;
            case "DQ":
                imageView.setImageResource(R.drawable.dq);
                break;
            case "DK":
                imageView.setImageResource(R.drawable.dk);
                break;
            case "HA":
                imageView.setImageResource(R.drawable.ha);
                break;
            case "H2":
                imageView.setImageResource(R.drawable.h2);
                break;
            case "H3":
                imageView.setImageResource(R.drawable.h3);
                break;
            case "H4":
                imageView.setImageResource(R.drawable.h4);
                break;
            case "H5":
                imageView.setImageResource(R.drawable.h5);
                break;
            case "H6":
                imageView.setImageResource(R.drawable.h6);
                break;
            case "H7":
                imageView.setImageResource(R.drawable.h7);
                break;
            case "H8":
                imageView.setImageResource(R.drawable.h8);
                break;
            case "H9":
                imageView.setImageResource(R.drawable.h9);
                break;
            case "HX":
                imageView.setImageResource(R.drawable.hx);
                break;
            case "HJ":
                imageView.setImageResource(R.drawable.hj);
                break;
            case "HQ":
                imageView.setImageResource(R.drawable.hq);
                break;
            case "HK":
                imageView.setImageResource(R.drawable.hk);
                break;
            case "SA":
                imageView.setImageResource(R.drawable.sa);
                break;
            case "S2":
                imageView.setImageResource(R.drawable.s2);
                break;
            case "S3":
                imageView.setImageResource(R.drawable.s3);
                break;
            case "S4":
                imageView.setImageResource(R.drawable.s4);
                break;
            case "S5":
                imageView.setImageResource(R.drawable.s5);
                break;
            case "S6":
                imageView.setImageResource(R.drawable.s6);
                break;
            case "S7":
                imageView.setImageResource(R.drawable.s7);
                break;
            case "S8":
                imageView.setImageResource(R.drawable.s8);
                break;
            case "S9":
                imageView.setImageResource(R.drawable.s9);
                break;
            case "SX":
                imageView.setImageResource(R.drawable.sx);
                break;
            case "SJ":
                imageView.setImageResource(R.drawable.sj);
                break;
            case "SQ":
                imageView.setImageResource(R.drawable.sq);
                break;
            case "SK":
                imageView.setImageResource(R.drawable.sk);
                break;
            default:
                Log.d("MyError", "Error in drawing the card to the screen in the round_view class.");
                break;
        }

    }
}
