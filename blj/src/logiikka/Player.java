/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

/**
 * Luokka kuvaa pelaajaa, jolla on nimi ja käsi.
 * @author b4d
 */
public class Player {
    private Hand hand;
    private String name;
    
    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

  /**
 * Konstruktori luo uuden pelaajan jolla on nimi ja käsi.
 *
 * @param name pelaajan nimi
 * @param hand pelaajan käsi
 */    
    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }
}
