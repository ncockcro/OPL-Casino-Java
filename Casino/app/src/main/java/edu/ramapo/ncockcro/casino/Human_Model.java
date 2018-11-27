package edu.ramapo.ncockcro.casino;

import android.util.Log;

import java.util.Vector;

public class Human_Model extends Player_Model {

    /***********************************************************************
     Function Name: MakeMove
     Purpose: For the human specifically to make a move
     Parameters:
     @param table, Vector<Card_Model> the cards on the table
     @param buildTable, Vector<Build_Model> the cards that are in builds
     Return Value:
     @return char, the move the human wants to make
     Local Variables:None
     Algorithm:
     1) If the player move is 'b', return that
     2) If the player move is 'c', return that
     3) If the player move is 't', return that
     4) Else, output and error
     Assistance Received: none
     ********************************************************************* */
    @Override
    public char MakeMove(Vector<Card_Model> table, Vector<Build_Model> buildTable) {

        // Nothing to do in here, previously in C++ this was where user input was gathered
        if(playerMove == 'b') {
            return 'b';
        }
        else if(playerMove == 'c') {
            return 'c';
        }
        else if(playerMove == 't') {
            return 't';
        }
        else {
            Log.d("MyError", "Error in making move in the human model class.");
            return '0';
        }
    }

    @Override public void PrintMove() {
        //playerOutputMessages.clear();
    }

}
