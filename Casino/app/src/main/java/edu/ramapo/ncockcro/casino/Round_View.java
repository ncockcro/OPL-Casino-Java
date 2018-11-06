package edu.ramapo.ncockcro.casino;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Vector;

public class Round_View extends MainGame_Activity {

    private Round_Model roundModel = new Round_Model();
    private Activity activity;
    private ImageButton playerHand1;
    private ImageButton playerHand2;
    private ImageButton playerHand3;
    private ImageButton playerHand4;
    private ImageView computerHand1;
    private ImageView computerHand2;
    private ImageView computerHand3;
    private ImageView computerHand4;
    private ImageButton tableCard1;
    private ImageButton tableCard2;
    private ImageButton tableCard3;
    private ImageButton tableCard4;

    Round_View() {

    }

    Round_View(Activity passedActivity) {
        this.activity = (Activity) passedActivity;
        playerHand1 = activity.findViewById(R.id.playerHand1);
        playerHand2 = activity.findViewById(R.id.playerHand2);
        playerHand3 = activity.findViewById(R.id.playerHand3);
        playerHand4 = activity.findViewById(R.id.playerHand4);
        computerHand1 = activity.findViewById(R.id.computerHand1);
        computerHand2 = activity.findViewById(R.id.computerHand2);
        computerHand3 = activity.findViewById(R.id.computerHand3);
        computerHand4 = activity.findViewById(R.id.computerHand4);
    }

    public void SetFourCardsHuman() {


        roundModel.DealCardsToPlayer();
        Vector<Card_Model> playerCards = roundModel.GetHumanHand(0);

        GetImageCard(playerCards.get(0), playerHand1);
        GetImageCard(playerCards.get(1), playerHand2);
        GetImageCard(playerCards.get(2), playerHand3);
        GetImageCard(playerCards.get(3), playerHand4);
    }

    public void SetFourCardsComputer(Vector <Card_Model> passedCards) {
        GetImageViewCard(passedCards.get(0), computerHand1);
        GetImageViewCard(passedCards.get(1), computerHand2);
        GetImageViewCard(passedCards.get(2), computerHand3);
        GetImageViewCard(passedCards.get(3), computerHand4);
    }

    public void SetFourCardsTable(Vector<Card_Model> passedCards) {

        GetImageCard(passedCards.get(0), tableCard1);
        GetImageViewCard(passedCards.get(1), tableCard2);
        GetImageViewCard(passedCards.get(2), tableCard3);
        GetImageViewCard(passedCards.get(3), tableCard4);
    }

    private void GetImageCard(Card_Model card, ImageButton imageButton) {

        switch (card.GetCard()) {
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
                Log.d("ERROR", "Error in drawing the card to the screen in the round_view class.");
                break;
        }

    }

    private void GetImageViewCard(Card_Model card, ImageView imageView) {

        switch (card.GetCard()) {
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
                Log.d("ERROR", "Error in drawing the card to the screen in the round_view class.");
                break;
        }

    }
}
