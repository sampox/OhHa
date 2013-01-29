/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author b4d
 */
public class Play {
    Scanner lukija = new Scanner(System.in);
    ArrayList<Player> players = new ArrayList<Player>();
    Player HOUSE;
    Deck deck = new Deck();
        
    public Play(int howManyPlayers) {
        deck.shuffle();
        HOUSE = new Player("House",deck.dealHand(2));
        for(int i=1;i<=howManyPlayers;i++) {
            System.out.println("Give player "+i+ " name");
            String playerName = lukija.nextLine();
            players.add(new Player(playerName,deck.dealHand(2)));
            
        }
        gameOn();
    }
    public void gameOn() {
        while(true) {
            if(!playARound()) break;
        }
        playHouse();
    };
    public void playHouse() {
        while(HOUSE.getHand().getLegality()) {
        if(getTopPlayer() != null) {
            HOUSE.getHand().addCard(deck.dealTopCard());
            System.out.println("House has hand with value of "+HOUSE.getHand().getBlackjackValue());
        } else {
            break;
        }
        }
        if(HOUSE.getHand().getLegality()==false)
        System.out.println(getTopPlayer().getName() +" WINS");
        else
            System.out.println("HOUSE WINS");
    }
    public Player getTopPlayer() {
        int value = 0;
        Player top = null;
        for(Player topPlayer : players) {
            if(topPlayer.getHand().getLegality()) {
            if (topPlayer.getHand().getBlackjackValue() > value) {
                value = topPlayer.getHand().getBlackjackValue();
                top = topPlayer;
            }
        }
        }
        return top;
        
    }
    public int askPlayer() {
        System.out.println("House has hand with value of " +HOUSE.getHand().getBlackjackValue());
        while(true) {
        System.out.println("1 to get more cards, 2 to stay, 3 to quit game");
        int choice = lukija.nextInt();
        if (choice >0 && choice <4) {
            return choice;
        } else {
            System.out.println("INVALID INPUT, try again!");
        }}
    }
    public boolean playARound() {
        boolean continueRounds = false;
        for (Iterator<Player> it = players.iterator(); it.hasNext();) {
            Player pleijer = it.next();
            if(pleijer.getHand().getLegality()) {
                System.out.println(pleijer.getName() +" hand has value of: " +pleijer.getHand().getBlackjackValue());
            int choice = askPlayer();
            
            if(choice==1) { pleijer.getHand().addCard(deck.dealTopCard());
            continueRounds=true;
                System.out.println(pleijer.getName()+ "hand has value of: " +pleijer.getHand().getBlackjackValue() + " now");
            }
            else if(choice==2) {}
            else if(choice==3) System.exit(choice);

            
            } }
        return continueRounds;
    };
}
