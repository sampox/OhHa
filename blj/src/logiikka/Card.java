/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

/**
 *
 * @author b4d
 */
public class Card {
    private int value;
    private String suit;
    private int blackjackValue;
    
    @Override
    public String toString() {
        return value + " of " + suit;
    }

    public int getBlackjackValue() {
        return blackjackValue;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
    
    public Card(int value, String suit) {
        this.value=value;
        this.suit=suit;
        calculateBlackjackValue();
    }
    private void calculateBlackjackValue() {
        if(this.value==1) {
            this.blackjackValue=11;
        }
        else if(this.value<11) {
            this.blackjackValue = this.value;
        } else if(this.value < 14) {
            this.blackjackValue=10;
        } 
    }
}
