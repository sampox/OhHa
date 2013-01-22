/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;

/**
 *
 * @author b4d
 */
public class Hand {
    private ArrayList<Card> cards;
    int blackjackValue = 0;

    public int getBlackjackValue() {
        return blackjackValue;
    }

    @Override
    public String toString() {
        return "Hand{" + "cards=" + cards + '}';
    }
    public int howManyCards() {
        return cards.size();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public Hand(ArrayList<Card> cards) {
        this.cards = cards;
        calculateBlackjackValue();
    }
    public void addCard(Card card) {
        this.cards.add(card);
        calculateBlackjackValue();
    }
    private void calculateBlackjackValue() {
        this.blackjackValue=0;
        for(Card card : this.cards) {
            this.blackjackValue += card.getBlackjackValue();
        }
    }
}
