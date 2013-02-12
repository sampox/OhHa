/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

/** 
 * Luokka kuvaa yksittäistä korttia, eli maa+arvo yhdistelmää. Kortille lasketaan
 * blackjack-arvo erikseen.
 * 
 * @author b4d
 */
public class Card {
        /**
 * Kortin oikea arvo (1-13)
 */
    private int value;
        /**
 * Kortin maa
 */
    private String suit;
        /**
 * Kortin blackjackissä käytettävä arvo, 2-11 (ässä erikoistapauksissa 1, mutta siitä ei välitetä tässä luokassa)
 */
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
    
 /**
 * Konstruktori luo annetun arvon ja maan omaavan kortin ja kutsuu sen
 * blackjack-arvon laskevaa metodia
 * 
 * @see logiikka.Deck#Deck() 
 * @param value kortin arvo
 * @param suit  kortin maa  
 *
 */
    public Card(int value, String suit) {
        this.value=value;
        this.suit=suit;
        calculateBlackjackValue();
    }
    
 /**
 * Metodi laskee kortin Blackjackissa käytettävän arvon
 *
 * @see logiikka.Card#Card(int, java.lang.String) 
 */
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
