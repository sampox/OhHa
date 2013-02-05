/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;

/**
 * Luokka kuvaa pelaajan kättä, eli kädessä olevia kortteja ja niiden arvoa. 
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
  /**
 * Konstruktori lue uuden käsi-olion. (Alustaa uuden ArrayList<Card> )
 *
 * 
 */
    public Hand() {
        this.cards = new ArrayList<Card>();
    }
    /**
 * Metodi lisää annetun (pakasta otetun) kortin käteen.
 * @see logiikka.Play#playARound() 
 * @see logiikka.Play#playHouse() 
 * @param card lisättävä kortti
 */

    public void addCard(Card card) {
        this.cards.add(card);
        updateBlackjackValue(card);
        checkLegality();
    }
    /**
 * Metodi päivittää käden arvon kortin lisäyksen jälkeen.
 *
 */
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
    /**
 * Metodi tarkistaa onko käsi blackjack käsi ts onko arvo 21 tai alle.
 *
 */
    private void checkLegality() {
        if (this.blackjackValue > 21) {
            handLegal = false;
        }
    }
}
