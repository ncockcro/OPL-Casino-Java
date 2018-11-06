package edu.ramapo.ncockcro.casino;

import android.util.Log;

import java.util.Collections;
import java.util.Vector;

public class Deck_Model {

    private Vector<Card_Model> deckOfCards = new Vector();

    Deck_Model() {

        int count = 0;

        for(int i = 2; i < 10; i++) {
            deckOfCards.add(new Card_Model());
            deckOfCards.get(count).SetCard("C" + Integer.toString(i));
            count++;
            deckOfCards.add(new Card_Model());
            deckOfCards.get(count).SetCard("D" + Integer.toString(i));
            count++;
            deckOfCards.add(new Card_Model());
            deckOfCards.get(count).SetCard("H" + Integer.toString(i));
            count++;
            deckOfCards.add(new Card_Model());
            deckOfCards.get(count).SetCard("S" + Integer.toString(i));
            count++;
        }

        // Tens
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("CX");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("DX");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("HX");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("SX");
        count++;

        // Jacks
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("CJ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("DJ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("HJ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("SJ");
        count++;

        // Queens
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("CQ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("DQ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("HQ");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("SQ");
        count++;

        // Kings
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("CK");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("DK");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("HK");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("SK");
        count++;

        // Tens
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("CA");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("DA");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("HA");
        count++;
        deckOfCards.add(new Card_Model());
        deckOfCards.get(count).SetCard("SA");
        count++;

        ShuffleCards();


    }

    public boolean isEmpty() {
        if(deckOfCards.isEmpty() == true) {
            return true;
        }
        else {
            return false;
        }
    }

    public Vector<Card_Model> GetDeck() {
        return deckOfCards;
    }

    public void RemoveFourCards() {
        deckOfCards.remove(0);
        deckOfCards.remove(0);
        deckOfCards.remove(0);
        deckOfCards.remove(0);

    }

    private void ShuffleCards() {

        //Collections.shuffle(deckOfCards);

        /*for(int i = 0; i < deckOfCards.size(); i++) {
            Log.d("Rawr", deckOfCards.get(i).GetCard());
        }*/
    }
}
