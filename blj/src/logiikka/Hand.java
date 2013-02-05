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
    private int blackjackValue = 0;
    private boolean handLegal = true;

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

    public boolean getLegality() {
        return handLegal;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Hand() {
        this.cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
        updateBlackjackValue(card);
        checkLegality();
    }
    private void updateBlackjackValue(Card card) {
        if (card.getBlackjackValue() == 11) {
            if (this.blackjackValue + 11 > 21) {
                this.blackjackValue += 1;
            } else {
                this.blackjackValue += card.getBlackjackValue();
            }
        } else {
            this.blackjackValue += card.getBlackjackValue();
        }
    }
    private void checkLegality() {
        if (this.blackjackValue > 21) {
            handLegal = false;
        }
    }
}
