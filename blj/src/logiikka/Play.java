/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import tallennus.Winners;

/**
 * Luokassa tapahtuu pelin loogiset operaatiot.
 * @author b4d
 */
public class Play {

    Scanner lukija;
    ArrayList<Player> players = new ArrayList<Player>();
    public Player HOUSE;
    Deck deck = new Deck();
    Winners winner = new Winners();
    private boolean BLACKJACK = false;

      /**
 * Konstruktori sekoittaa pakan ja jakaa annetulle määrälle pelaajia kädet.
 *
 * @param howManyPlayers pelaajien määrä
 */ 
    public Play(ArrayList<String> playerNames, Scanner lukija) {
        this.lukija=lukija;
        deck.shuffle();
        HOUSE = new Player("House", deck.dealHand(2));
        for(String playerName : playerNames)
            players.add(new Player(playerName, deck.dealHand(2)));

        
    }
      /**
 * Metodi käynnistää pelin, pelaajien vuoro ensin, sitten talon.
 *
 * @see logiikka.Blj#main(java.lang.String[]) 
 */ 

    public void gameOn() {
        while (true) {
            if (!playARound()) {
                break;
            }
        }
        if(getTopPlayer()!=null) { // if all the players hands aren't illegal
            if(!BLACKJACK){ //if there isn't a player with hand of 21
                playHouse();
        }} else playHouse();
    }

    ;
    private void playHouse() {
        while (HOUSE.getHand().getLegality()) {
            if (getTopPlayer() != null) {
                if (getTopPlayer().getHand().getBlackjackValue() >= HOUSE.getHand().getBlackjackValue()) {
                   dealACardAndShowHand(HOUSE);
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        if (HOUSE.getHand().getLegality() == false) {
            playerWins(getTopPlayer());
        } else {
            playerWins(HOUSE);
        }
    }
    
    private void playerWins(Player player) {
        playersHandAndCards(player);
        System.out.println(player.getName() + " WINS");
        if(!player.getName().equals("House")) 
            saveWinner(player.getName(), player.getHand().getBlackjackValue());
       
    }
       /**
 * Metodi palauttaa pelaajan, jolla on korkein laillinen käsi.
 *
 * @see logiikka.Play#playHouse() 
 * @return pelaaja jolla korkein käsi
 */ 
    private Player getTopPlayer() {
        int value = 0;
        Player top = null;
        for (Player topPlayer : players) {
            if (topPlayer.getHand().getLegality()) {
                if (topPlayer.getHand().getBlackjackValue() > value) {
                    value = topPlayer.getHand().getBlackjackValue();
                    top = topPlayer;
                }
            }
        }
        return top;

    }

    private int askPlayer() {
        playersHandAndCards(HOUSE);
        while (true) {
            try {
                System.out.println("1 to get more cards\n2 to stay");
                int choice = Integer.parseInt(lukija.nextLine());
                if (choice > 0 && choice < 3) {
                    return choice;
                } else {
                    invalidInput();
                }
            } catch (NumberFormatException nfe) {
                invalidInput();
            }
        }
    }
    private void invalidInput() {
        System.out.println("INVALID INPUT, try again!");
    }
    
    private boolean twentyOne(Player player){
        if (player.getHand().getBlackjackValue() == 21) {
            System.out.println("21!!! " + player.getName() + " WINS!! The winning hand:");
            playersHandAndCards(player);
            saveWinner(getTopPlayer().getName(), getTopPlayer().getHand().getBlackjackValue());
            BLACKJACK = true;
            return true;
        }
        return false;
    }

    private boolean playARound() {
        boolean continueRounds = false;
        for (Player pleijer : players) {
            if (pleijer.getHand().getLegality()) { // If the hand is legal
                if(twentyOne(pleijer)) return false; // Check for blackjack
                playersHandAndCards(pleijer);
                int choice = askPlayer();

                if (choice == 1) {
                    dealACardAndShowHand(pleijer);
                    continueRounds = true;
                    if(twentyOne(pleijer)) return false; //Check for blackjack again after hand has changed
                }
            }
        }
        return continueRounds;
    }

    private void dealACardAndShowHand(Player pleijer) {
                    pleijer.getHand().addCard(deck.dealTopCard());
                    playersHandAndCards(pleijer);
    }
    private void playersHandAndCards(Player pleijer) {
        System.out.println(pleijer.getName() + "'s cards are: " + pleijer.getHand().getCards());
        System.out.println(pleijer.getName() + "'s hand has value of: " + pleijer.getHand().getBlackjackValue());
    }
    
    private void saveWinner(String playerName, int handValue) {
        winner.kirjoitaTiedostoon(playerName + "\t\t" + handValue);
    }

    public void getWinners() {
        String winners = winner.lueTiedosto();
        System.out.println(winners);
    }
}
