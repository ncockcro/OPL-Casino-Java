package edu.ramapo.ncockcro.casino;

import android.util.Log;

import java.util.Vector;

public class Computer_Model extends Player_Model {

    /** *********************************************************************
     Function Name: MakeMove
     Purpose: A virtual function for when the computer specifically is making a move
     Parameters:
     @param table, Vector<Card_Model> the cards on the table
     @param tableBuilds Vector<Card_Model> the cards that are builds on the table
     Return Value:
     @return char, the move the player is making
     Local Variables:None
     Algorithm:
     1) If the ai can make a build, return 'b'
     2) If the ai can make a capture, return 'c'
     3) If neither of the other options, return 't'
     Assistance Received: none
     ********************************************************************* */
    @Override public char MakeMove(Vector<Card_Model> table, Vector<Build_Model> tableBuilds) {
        //Check for build
        /*if(AICheckForBuild(table, tableBuilds)) {
            playerMove = 'b';
            return;
        }*/

        //Check for Capture
        if(AICheckForCapture(table, tableBuilds)) {
            AICheckForCapture(table, tableBuilds);
            return 'c';
        }

        //Check for trail
        AIMakeTrail();
        return 't';

    }
}
