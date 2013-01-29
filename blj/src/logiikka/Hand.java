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
    private int aces=0;
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
        if(card.getBlackjackValue()==11) {
            if(aces>0 || this.blackjackValue+11 > 21) { aces++; this.blackjackValue+=1;}
            else {aces++; this.blackjackValue+=card.getBlackjackValue(); }
        }
        else{
        this.blackjackValue+=card.getBlackjackValue();
        }
        
        if(this.blackjackValue > 21) handLegal = false;
    }
 /*   private void calculateBlackjackValue() {
        this.blackjackValue=0;
        for(Card card : this.cards) {
            this.blackjackValue += card.getBlackjackValue();
        }
    }*/
}
