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
 * Luokassa tapahtuu tekstikäyttöliittymätoiminnot ja pelin loogiset operaatiot.
 * @author b4d
 */
public class Play {

    Scanner lukija = new Scanner(System.in);
    ArrayList<Player> players = new ArrayList<Player>();
    Player HOUSE;
    Deck deck = new Deck();
    Winners winner = new Winners();

      /**
 * Konstruktori sekoittaa pakan ja jakaa annetulle määrälle pelaajia kädet.
 *
 * @param howManyPlayers pelaajien määrä
 */ 
    public Play(int howManyPlayers) {
        deck.shuffle();
        HOUSE = new Player("House", deck.dealHand(2));
        for (int i = 1; i <= howManyPlayers; i++) {
            System.out.println("Give player " + i + " name");
            String playerName = lukija.nextLine();
            players.add(new Player(playerName, deck.dealHand(2)));

        }
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
        playHouse();
    }

    ;
    private void playHouse() {
        while (HOUSE.getHand().getLegality()) {
            if (getTopPlayer() != null) {
                if (getTopPlayer().getHand().getBlackjackValue() >= HOUSE.getHand().getBlackjackValue()) {
                    HOUSE.getHand().addCard(deck.dealTopCard());
                    System.out.println("House has hand with value of " + HOUSE.getHand().getBlackjackValue());
                    System.out.println("House's cards are: " + HOUSE.getHand().getCards());
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        if (HOUSE.getHand().getLegality() == false) {
            System.out.println(getTopPlayer().getName() + " WINS with a hand value of " + getTopPlayer().getHand().getBlackjackValue());
            saveWinner(getTopPlayer().getName(), getTopPlayer().getHand().getBlackjackValue());
            askIfPlayAgain();
        } else {
            System.out.println("HOUSE WINS");
            System.out.println("House's winning cards are: " + HOUSE.getHand().getCards());
            askIfPlayAgain();
        }
    }
       /**
 * Metodi palauttaa pelaajan, jolla on korkein laillinen käsi.
 *
 * @see logiikka.Play#playHouse() 
 * @return pelaaja jolla korkein käsi
 */ 
    public Player getTopPlayer() {
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
        System.out.println("House has hand with value of " + HOUSE.getHand().getBlackjackValue());
        while (true) {
            try {
                System.out.println("1 to get more cards, 2 to stay, 3 to quit game");
                int choice = Integer.parseInt(lukija.nextLine());
                if (choice > 0 && choice < 4) {
                    return choice;
                } else {
                    System.out.println("INVALID INPUT, try again!");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("INVALID INPUT, try again!");
            }
        }
    }
    private void askIfPlayAgain() {
        while (true) {
            try {
                System.out.println("Play again? 1 to play again, 2 to show winners, 3 to quit");
                int choice = Integer.parseInt(lukija.nextLine());
                if (choice > 0 && choice < 4) {
                    if(choice == 1) playAgain();
                    else if(choice == 2) getWinners();
                    else System.exit(0);
                } else {
                    System.out.println("INVALID INPUT, try again!");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("INVALID INPUT, try again!");
            }
        }
        
    }
    private void playAgain() {
            Play play = new Play(1);
            play.gameOn();
    }

    private void twentyOne(Player player) {
        if (player.getHand().getBlackjackValue() == 21) {
            System.out.println("21!!! " + player.getName() + " WINS!!");
            System.out.println(player.getName() + "'s winning cards are: " + player.getHand().getCards());
            saveWinner(getTopPlayer().getName(), getTopPlayer().getHand().getBlackjackValue());
            askIfPlayAgain();
        }
    }

    private boolean playARound() {
        boolean continueRounds = false;
        for (Iterator<Player> it = players.iterator(); it.hasNext();) { //Iterate players and see what they want to do
            Player pleijer = it.next();
            if (pleijer.getHand().getLegality()) {
                twentyOne(pleijer); // Check for blackjack
                System.out.println(pleijer.getName() + " hand has value of: " + pleijer.getHand().getBlackjackValue());
                System.out.println(pleijer.getName() + "'s cards are: " + pleijer.getHand().getCards());
                int choice = askPlayer();

                if (choice == 1) {
                    pleijer.getHand().addCard(deck.dealTopCard());
                    continueRounds = true;
                    System.out.println(pleijer.getName() + " hand has value of: " + pleijer.getHand().getBlackjackValue() + " now");
                    System.out.println(pleijer.getName() + "'s cards are: " + pleijer.getHand().getCards());
                    twentyOne(pleijer); //Check for blackjack again after hand has changed
                } else if (choice == 2) {
                } else if (choice == 3) {
                    System.exit(0);
                }


            }
        }
        return continueRounds;
    }

    ;
    
    private void saveWinner(String playerName, int handValue) {
        winner.kirjoitaTiedostoon(playerName + "\t\t" + handValue);
    }

    private void getWinners() {
        String winners = winner.lueTiedosto();
        System.out.println(winners);
    }
}
