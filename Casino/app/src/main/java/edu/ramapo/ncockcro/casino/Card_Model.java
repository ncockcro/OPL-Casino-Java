package edu.ramapo.ncockcro.casino;

public class Card_Model {

    Card_Model() {

    }

    public void SetCard(String passedCard) {
        if(CheckSuit(passedCard.charAt(0)) && CheckNumber(passedCard.charAt(1))) {
            card = passedCard;
            suit = passedCard.charAt(0);
            number = passedCard.charAt(1);
        }
        else {
            // Output that it was an invalid card that was passed to it
        }
    }

    public final String GetCard() {
        return card;
    }

    public final char GetSuit() {
        return suit;
    }

    public final char GetNumber() {
        return number;
    }

    private boolean CheckSuit(char passedSuit) {
        if(passedSuit == 'C' || passedSuit == 'D' || passedSuit == 'H' || passedSuit == 'S') {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean CheckNumber(char passedNumber) {
        if(passedNumber == '2' || passedNumber == '3' || passedNumber == '4' || passedNumber == '5' || passedNumber == '6' ||
                passedNumber == '7' || passedNumber == '8' || passedNumber == '9' || passedNumber == 'X' || passedNumber == 'J' ||
                passedNumber == 'Q' || passedNumber == 'K') {
            return true;
        }
        else {
            return false;
        }
    }



    // Private variables of the class
    private String card;
    private char suit;
    private char number;
}
