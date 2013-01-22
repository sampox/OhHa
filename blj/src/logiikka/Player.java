/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

/**
 *
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

    
    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }
}
