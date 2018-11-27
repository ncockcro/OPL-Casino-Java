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
        Log.d("In computer", Integer.toString(tableBuilds.size()));
        if(AICheckForBuild(table, tableBuilds)) {
            playerMove = 'b';
            return 'b';
        }

        //Check for Capture
        if(AICheckForCapture(table, tableBuilds)) {
            return 'c';
        }

        //Check for trail
        AIMakeTrail();
        return 't';

    }

    /** *********************************************************************
     Function Name: PrintMove
     Purpose: Overrided player function for printing the computer's move to the screen
     Parameters: None
     Return Value: None
     Local Variables: None
     Algorithm:
     1)
     Assistance Received: none
     ********************************************************************* */
    @Override public void PrintMove() {
        Log.d("Printing", "Printing in the computer");

        playerOutputMessages.clear();

        if(playerMove == 'b') {

            playerOutputMessages.add("The computer chose to play a " + GetNumberName(playerCard.GetNumber()) + " of " + GetSuitName(playerCard.GetSuit()));

            if(newOrExistingBuild == 'n') {
                playerOutputMessages.add(" to make a build with ");

                for(int i = 0; i < buildCards.size(); i++) {
                    if(i > 1) {
                        playerOutputMessages.add(" and ");
                    }

                    playerOutputMessages.add(GetNumberName(buildCards.get(i).GetNumber()) + " of " + GetSuitName(buildCards.get(i).GetNumber()));
                }

                playerOutputMessages.add("It wanted to be able to capture more cards later.");
            }

            else if(newOrExistingBuild == 'e') {
                playerOutputMessages.add(" to add to an existing build with the ");

                for(int i = 0; i < printTableBuildCards.size(); i++) {
                    if(i > 1) {
                        playerOutputMessages.add(" and ");
                    }

                    playerOutputMessages.add(GetNumberName(printTableBuildCards.get(i).GetNumber()) + " of " + GetSuitName(printTableBuildCards.get(i).GetSuit()));
                }
                playerOutputMessages.add(". It wanted to steal the opponents cards.");
            }
            else {
                Log.d("MyError", "Error in printing computer's builds in computer model class.");
            }
        }

        else if(playerMove == 'c') {
            playerOutputMessages.add("The computer chose to play a " + GetNumberName(playerCard.GetNumber()) + " of " + GetSuitName(playerCard.GetSuit()) + " to capture the ");

            if(printPlayerCaptureBuild == 'y') {
                for(int i = 0; i < printTableBuildCards.size(); i++) {
                    if(i > 1) {
                        playerOutputMessages.add(" and ");
                    }

                    playerOutputMessages.add(GetNumberName(printTableBuildCards.get(i).GetNumber()) + " of " + GetSuitName(printTableBuildCards.get(i).GetSuit()));
                }
            }

            for(int i = 0; i < printTableCaptureCards.size(); i++) {
                if(i > 1) {
                    playerOutputMessages.add(" and ");
                }
                playerOutputMessages.add(GetNumberName(printTableCaptureCards.get(i).GetNumber()) + " of " + GetSuitName(printTableCaptureCards.get(i).GetSuit()));

            }

            if(playerWantSet == 'y') {
                playerOutputMessages.add(" and the set of ");

                for(int i = 0; i < playerMultipleSetCards.size(); i++) {
                    for(int j = 0; j < playerMultipleSetCards.get(i).GetCardOfSet().size(); j++) {
                        if(i > 0) {
                            playerOutputMessages.add(" and ");
                        }

                        playerOutputMessages.add(GetNumberName(playerMultipleSetCards.get(i).GetCardOfSet().get(j).GetNumber()) + " of " + GetSuitName(playerMultipleSetCards.get(i).GetCardOfSet().get(j).GetNumber()));

                    }
                }
            }

            playerOutputMessages.add("It wanted to add more cards to its pile.");
        }

        else if(playerMove == 't') {
            playerOutputMessages.add("The computer chose to trail witht the " + GetNumberName(playerCard.GetNumber()) + " of " + GetSuitName(playerCard.GetSuit()));
            playerOutputMessages.add("It had no other moves to make.");
        }
    }
}
