/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import tallennus.Winners;

/**
 * Luokassa tapahtuu pelin loogiset operaatiot.
 * @author b4d
 */
public class Play {
    /**
 * Scanner-olio käyttäjän syötteen lukemiseen.
 */
    Scanner lukija;
    /**
 * Lista (ihmis)pelaajista.
 */
    ArrayList<Player> players = new ArrayList<Player>();
    /**
 * "jakaja" eli talo, tietokonepelaaja, jota vastaan ihmispelaajat pelaavat.
 */
    public Player HOUSE;
    /**
 * 52-korttinen pakka jota pelissä käytetään.
 */
    Deck deck = new Deck();
    /**
 * Voittajien tallenus hoidetaan tämän olion avulla.
 */
    Winners winner = new Winners();
    /**
 * boolean arvo joka kertoo onko jokin pelaaja saavuttanut kädellään arvon 21.
 */
    private boolean BLACKJACK = false;

      /**
 * Konstruktori sekoittaa pakan ja jakaa jokaiselle pelaajalle käden.
 *
 * @see UI.TUI#play
 * @param playerNames pelaajien nimet ArrayListinä
 * @param lukija luokalle välitetty Scanner-olio
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
 * @see UI.TUI#mainMenu()  
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

     /**
 * Metodi pelaa talon vuoron. Lisää kortteja talon(HOUSE) käteen jos jollakin 
 * pelaajalla on taloa korkeampi käsi. Lopuksi tarkistaa voittiko talo vai pelaaja.
 *
 * @see logiikka.Play#gameOn()   
 */ 
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
     /**
 * Metodi ilmaisee voittajan ja kutsuu voittajantallenusmetodia jos voittaja ei ole talo.
 *
 * @see logiikka.Play#playHouse() 
 * @see logiikka.Play#twentyOne(logiikka.Player) 
 */ 
    private void playerWins(Player player) {
        System.out.println("");
        System.out.println(playersHandAndCards(player));
        System.out.println(player.getName() + " WINS");
        if(!player.getName().equals("House")) 
            saveWinner(player.getName(), player.getHand().getBlackjackValue());
       
    }
       /**
 * Metodi palauttaa pelaajan, jolla on korkein laillinen käsi.
 * HUOM. jos kahdella pelaajalla samanarvoinen käsi, se joka saavutti sen ensin 
 * on topPlayer.
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
     /**
 * Metodi kysyy pelaajalta haluaako hän lisää kortteja vai pitäytyä kädessään.
 * Näyttää ensin HOUSE:n käden pelaajalle, jotta hän voi tehdä informoidun päätöksen.
 * 
 * @see logiikka.Play#playARound() 
 * @return pelaajan valinnan numero
 */ 
    private int askPlayer() {
        System.out.println(playersHandAndCards(HOUSE));
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
         /**
 * Metodi ilmaisee virheellisen syötteen.
 *
 * @see logiikka.Play#askPlayer() 
 */ 
    private void invalidInput() {
        System.out.println("INVALID INPUT, try again!");
    }
         /**
 * Metodi kertoo, onko pelaaja saavuttanut käden arvoltaan 21. Jos on, tallennetaan voittaja
 * ja palautetaan asetetaan BLACKJACK=true ja palautetaan true.
 *
 * @see logiikka.Play#playARound() 
 * @return saavutettiinko blackjack true/false
 */ 
    private boolean twentyOne(Player player){
        if (player.getHand().getBlackjackValue() == 21) {
            System.out.println("21!!! " + player.getName() + " WINS!! The winning hand:");
            System.out.println(playersHandAndCards(player));
            saveWinner(getTopPlayer().getName(), getTopPlayer().getHand().getBlackjackValue());
            BLACKJACK = true;
            return true;
        }
        return false;
    }

         /**
 * Metodissa pelataan pelikierroksia kunnes saavutetaan blackjack, kaikkien pelaajien 
 * kädet ovat laittomia tai pelaajat ovat päättäneet pitäytyä käsissään.
 *
 * @see logiikka.Play#gameOn() 
 * @return tarvitseeko kierroksia jatkaa true/false
 */ 
    private boolean playARound() {
        boolean continueRounds = false;
        for (Player pleijer : players) {
            if (pleijer.getHand().getLegality()) { // If the hand is legal
                if(twentyOne(pleijer)) return false; // Check for blackjack
                System.out.println(playersHandAndCards(pleijer));
                int choice = askPlayer();

                if (choice == 1) {
                    dealACardAndShowHand(pleijer);
                    System.out.println("");
                    continueRounds = true;
                    if(twentyOne(pleijer)) return false; //Check for blackjack again after hand has changed
                } else System.out.println("");
            }
        }
        return continueRounds;
    }

         /**
 * Metodi lisää pelaajan käteen pakan päällimmäisen kortin ja näyttää pelaajan päivitetyn käden.
 *
 * @param pleijer pelaaja jonka kättä käsitellään
 * @see logiikka.Play#playARound() 
 * @see logiikka.Play#playHouse()
 */ 
    private void dealACardAndShowHand(Player pleijer) {
                    pleijer.getHand().addCard(deck.dealTopCard());
                    System.out.println(playersHandAndCards(pleijer));
    }
         /**
 * Metodi näyttää pelaajan käden arvon, siinä olevat kortit ja käden laillisuuden.
 *
 * @param pleijer pelaaja jonka kättä käsitellään
 * @see logiikka.Play#playARound() 
 * @see logiikka.Play#dealACardAndShowHand(logiikka.Player);
 */ 
   public String playersHandAndCards(Player pleijer) {
      String legality = "legal.";
      if(!pleijer.getHand().getLegality()) {
          legality = "ILLEGAL. " + pleijer.getName() +" is out of the game!";
      }
        return pleijer.getName() + "'s cards are: " + pleijer.getHand().getCards() + "\n" +
        pleijer.getName() + "'s hand has value of: " + pleijer.getHand().getBlackjackValue() +"\n" +
        pleijer.getName() +"'s hand is " +legality;
    }
         /**
 * Metodi hoitaa voitokkaan pelaajan ja hänen kätensä arvon tallentamisen.  
 *
 * @param playerName voittaneen pelaajan nimi
 * @param handValue voittaneen käden arvo
 * @see logiikka.Play#twentyOne(logiikka.Player)  
 * @see logiikka.Play#playHouse()
 */ 
    private void saveWinner(String playerName, int handValue) {
        winner.kirjoitaTiedostoon(playerName + "\t\t" + handValue);
    }
     /**
 * Metodi lukee voittajat sisältävän tiedoston ja palauttaa sen sisällön.
 * 
 * @see UI.TUI#mainMenu() 
 */ 
    public String getWinners() {
        return winner.lueTiedosto();
    }
 
            
}
