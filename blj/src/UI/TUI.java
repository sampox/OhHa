/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import logiikka.Play;

/**
 * TUI = Text UI, luokka toteuttaa tekstikäyttöliittymän pelille.
 * @author b4d
 */
public class TUI {
    /**
 * Scanner-olio käyttäjän syötteen lukemiseen.
 */
    private Scanner lukija;
    /**
 * Play-luokka pelin pelaamista varten.
 */
    public Play play = new Play(new ArrayList<String>(),lukija); // so that the method getWinners() can be used right away; 
    
    /**
 * Konstruktori kutsuu pelin päävalikkoa ja asettaa syötetyn Scanner olion luokan käyttöön.
 * 
 *
 * @param lukija Scanner-olio käyttäjän syötteen lukemiseen
 */
    public TUI(Scanner lukija) {
        this.lukija = lukija;
        mainMenu();
    }
    
        /**
 * Konstruktori GUI:lle, kutsuu pelin päävalikkoa ja asettaa syötetyn PipedOutputStream olion luokan käyttöön.
 * 
 * @see UI.GUI#main(java.lang.String[]) 
 * @param out PipedOutputStream-olio käyttäjän syötteen lukemiseen graafisesta käyttöliittymästä
 */
    public TUI(PipedOutputStream out)throws IOException {
        InputStream in = new PipedInputStream(out);
        lukija = new Scanner(in);
        mainMenu();
    }
         /**
 * Metodi näyttää pelin päävalikon.
 *
 * @see UI.TUI#TUI(java.util.Scanner)   
 */ 
    private void mainMenu() {
        while(true) {
                System.out.println("\n1 to play the game\n2 to show winners\n3 to quit\n");
                int choice = getChoiceNumber(3);
                    if(choice == 1) {
                        play = new Play(playerNames(howManyPlayers()),lukija);
                        play.gameOn();
                    }
                    else if(choice == 2) System.out.println(play.getWinners());
                    else System.exit(0);
        }
     
    }
    
         /**
 * Metodi tiedustelee pelaajalta kuinka monta pelaajaa halutaan tälle kierrokselle.
 *
 * @see UI.TUI#mainMenu() 
 * @return kuinka monta pelaajaa eli numero(int) 1-8
 *
 */ 
    private int howManyPlayers() {
        System.out.println("How many players? Enter a number from 1 to 8: ");
        return getChoiceNumber(8);
    }
         /**
 * Metodi tiedustelee pelaajalta pelaajien nimet.
 *
 * @see UI.TUI#mainMenu() 
 * @return listan pelaajien nimiä
 */ 
    private ArrayList<String> playerNames(int howManyPlayers) {
        ArrayList<String> players = new ArrayList<String>();
        for (int i = 1; i <= howManyPlayers; i++) {
            System.out.println("Give player " + i + " name: ");
            players.add(getProperName());
        }
        return players;
    }
    
             /**
 * Metodi kertoo syötteen olleen virheellistä
 *
 * @see UI.TUI#getProperName() 
 * @see UI.TUI#getChoiceNumber(int) 
 */ 
    private void invalidInput() {
        System.out.println("INVALID INPUT, try again!");
    }
    
             /**
 * Metodi varmistaa ettei pelaaja syötä tyhjää nimeä tai vain välilyöntejä nimeksi.
 *
 * @see UI.TUI#playerNames(int)   
 * @return kunnollinen nimi
 */ 
    private String getProperName() {
        while(true) {
            String playerName = lukija.nextLine();
            if (!playerName.isEmpty()) {
                if (playerName.trim().length() > 0){
                        System.out.println(playerName);
                        return playerName;
                }
                else invalidInput();
            } else invalidInput();
        }
    }
             /**
 * Metodi palauttaa käyttäjän valitseman vaihtoehdon.
 *
 * @param numberOfOptions valintojen lukumäärä
 * @see UI.TUI#mainMenu() 
 * @see UI.TUI#howManyPlayers() 
 */ 
    private int getChoiceNumber(int numberOfOptions) {
        while(true)
        try {
        int choice = Integer.parseInt(lukija.nextLine());
                if (choice > 0 && choice <= numberOfOptions) {
                    System.out.println(choice);
                    return choice;
                } else {
                    invalidInput();
                }
                } catch (NumberFormatException nfe) {
                    invalidInput();
            }
    }

}
