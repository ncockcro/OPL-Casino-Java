package edu.ramapo.ncockcro.casino;

import java.util.Vector;

public class Card_View {

    private String card;
    private char suit;
    private char number;

    Card_View() {

    }

    Card_View(Card_Model passedCard) {
        card = passedCard.GetCard();
        suit = passedCard.GetSuit();
        number = passedCard.GetSuit();
    }

    Vector<Card_View> ConvertModelToView(Vector<Card_Model> passedCards) {
        Vector<Card_View> tempCards = new Vector();

        for(int i = 0; i < passedCards.size(); i++) {
            tempCards.add(new Card_View(passedCards.get(i)));
        }

        return tempCards;
    }
}
