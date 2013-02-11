/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.ArrayList;
import java.util.Scanner;
import logiikka.Play;

/**
 * TUI = Text UI, luokka toteuttaa tekstikäyttöliittymän pelille.
 * @author b4d
 */
public class TUI {
    private Scanner lukija;
    private Play play = new Play(new ArrayList<String>(),lukija); // so that the method getWinners() can be used right away
    
    public TUI(Scanner lukija) {
        this.lukija = lukija;
        mainMenu();
    }
    
    private void mainMenu() {
        while(true) {
                System.out.println("1 to play the game\n2 to show winners\n3 to quit");
                int choice = getChoiceNumber(3);
                    if(choice == 1) {
                        play = new Play(playerNames(howManyPlayers()),lukija);
                        play.gameOn();
                    }
                    else if(choice == 2) play.getWinners();
                    else System.exit(0);
        }
     
    }
    

    private int howManyPlayers() {
        System.out.println("How many players? Enter a number from 1 to 8");
        return getChoiceNumber(8);
    }

    private ArrayList<String> playerNames(int howManyPlayers) {
        ArrayList<String> players = new ArrayList<String>();
        for (int i = 1; i <= howManyPlayers; i++) {
            System.out.println("Give player " + i + " name");
            players.add(getProperName());
        }
        return players;
    }
    
    
    private void invalidInput() {
        System.out.println("INVALID INPUT, try again!");
    }
    
    private String getProperName() {
        while(true) {
            String playerName = lukija.nextLine();
            if (!playerName.isEmpty()) {
                if (playerName.trim().length() > 0){
                        return playerName;
                }
                else invalidInput();
            } else invalidInput();
        }
    }
    
    private int getChoiceNumber(int numberOfOptions) {
        while(true)
        try {
        int choice = Integer.parseInt(lukija.nextLine());
                if (choice > 0 && choice <= numberOfOptions) {
                    return choice;
                } else {
                    invalidInput();
                }
                } catch (NumberFormatException nfe) {
                    invalidInput();
            }
    }
    

}
