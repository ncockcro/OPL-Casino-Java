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
    @Override public char MakeMove(Vector<Card_Model> table, Vector<Build_Model> tableBuilds, int currentPlayer) {

        //Check for build
        if(AICheckForBuild(table, tableBuilds, currentPlayer)) {
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

        // Clear the output messages vector in case anything was in it from before
        playerOutputMessages.clear();

        // If the computer made a build...
        if(playerMove == 'b') {

            playerOutputMessages.add("The computer chose to play a " + GetNumberName(playerCard.GetNumber()) + " of " + GetSuitName(playerCard.GetSuit()));

            // If they made a new build...
            if(newOrExistingBuild == 'n') {
                playerOutputMessages.add(" to make a build with ");

                // Cycle through and get the table cards the computer made a build with
                for(int i = 0; i < printTableBuildCards.size(); i++) {
                    if(i > 1) {
                        playerOutputMessages.add(" and ");
                    }

                    playerOutputMessages.add(GetNumberName(printTableBuildCards.get(i).GetNumber()) + " of " + GetSuitName(printTableBuildCards.get(i).GetSuit()));
                }

                playerOutputMessages.add(". It wanted to be able to capture more cards later.");
            }

            // If the computer added to an existing build...
            else if(newOrExistingBuild == 'e') {
                playerOutputMessages.add(" to add to an existing build with the ");

                // Cycle through the table cards the existing build consisted of
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

        // If the computer captured...
        else if(playerMove == 'c') {
            playerOutputMessages.add("The computer chose to play a " + GetNumberName(playerCard.GetNumber()) + " of " + GetSuitName(playerCard.GetSuit()) + " to capture the ");

            // If the computer captured a build...
            if(printPlayerCaptureBuild == 'y') {

                // Cycle through the cards from the build they captured
                for(int i = 0; i < printTableBuildCards.size(); i++) {
                    if(i > 0) {
                        playerOutputMessages.add(" and ");
                    }

                    playerOutputMessages.add(GetNumberName(printTableBuildCards.get(i).GetNumber()) + " of " + GetSuitName(printTableBuildCards.get(i).GetSuit()));
                }
            }

            // Cycle through the cards the computer captured
            for(int i = 0; i < printTableCaptureCards.size(); i++) {
                if(i > 1) {
                    playerOutputMessages.add(" and ");
                }
                playerOutputMessages.add(GetNumberName(printTableCaptureCards.get(i).GetNumber()) + " of " + GetSuitName(printTableCaptureCards.get(i).GetSuit()));

            }

            // If the computer captured a set...
            if(playerWantSet == 'y') {
                playerOutputMessages.add(" and the set of ");

                // Cycle through the set cards that the computer captured
                for(int i = 0; i < playerMultipleSetCards.size(); i++) {
                    for(int j = 0; j < playerMultipleSetCards.get(i).GetCardOfSet().size(); j++) {
                        if(i > 0) {
                            playerOutputMessages.add(" and ");
                        }

                        playerOutputMessages.add(GetNumberName(playerMultipleSetCards.get(i).GetCardOfSet().get(j).GetNumber()) + " of " + GetSuitName(playerMultipleSetCards.get(i).GetCardOfSet().get(j).GetSuit()));

                    }
                }
            }

            playerOutputMessages.add(". It wanted to add more cards to its pile.");
        }

        // If the computer trailed, output the card they trailed with
        else if(playerMove == 't') {
            playerOutputMessages.add("The computer chose to trail with the " + GetNumberName(playerCard.GetNumber()) + " of " + GetSuitName(playerCard.GetSuit()));
            playerOutputMessages.add(". It had no other moves to make.");
        }
        else {
            Log.d("MyError", "Error in setting the computer's print statement in computer model.");
        }

    }
}
