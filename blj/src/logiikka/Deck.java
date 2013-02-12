/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Luokka kuvaa korttipakkaa ArrayListinä Card-olioita. Pakan voi sekoittaa,
 * siitä voi jakaa yhden tai useampia kortteja ja tarkastaa kuinka monta korttia
 * on jäljellä.
 *
 * @author b4d
 * 
 *
 */
public class Deck {
        /**
 * Pakka koostuu listasta Card-olioita
 */
    private ArrayList<Card> deck = new ArrayList<Card>();
    
    /**
 * Konstruktori luo 52-korttia sisältävän pakan, jokaista kortti-maa yhdistelmää 1kpl.
 *
 */
    public Deck() {
        for(int i=1;i<14;i++) {
            deck.add(new Card(i,"Clubs"));
            deck.add(new Card(i,"Diamonds"));
            deck.add(new Card(i,"Spades"));
            deck.add(new Card(i,"Hearts"));
        }
    }
        /**
 * Metodi kertoo pakassa olevien korttien määrän.
 *
 */
    public int howManyCardsLeft() {
        return deck.size();
    }
    /**
 * Metodi sekoittaa korttipakan.
 *
 */
    public void shuffle() {
        Collections.shuffle(deck);
    }
    /**
 * Metodi jakaa pelaajalle käden pakasta.
 *
 * @see logiikka.Play#Play(java.util.ArrayList, java.util.Scanner) 
 * @param korttienMaara kuinka monta korttia pelaajalle jaetaan (2)
 * @return jaettu käsi tai null jos pakka on tyhjä
 */
    public Hand dealHand(int korttienMaara) {
        if (deck.isEmpty()) {
            return null;
        }
        Hand newHand = new Hand();
        if (korttienMaara > deck.size()) {
            korttienMaara = deck.size();
        }
        for (int i=0;i<korttienMaara;i++) {
            newHand.addCard(dealTopCard());
            }
        return newHand;
    }
    
    /**
 * Metodi jakaa pakan päällimmäisen kortin
 * 
 * @see logiikka.Play#dealACardAndShowHand(logiikka.Player)  
 * @return päällimmäinen kortti tai null jos pakka on tyhjä
 */
    public Card dealTopCard() {
        if(!deck.isEmpty()) {
           Card topCard = deck.get(deck.size()-1);
           deck.remove(deck.size()-1);
           return topCard;
        }
        else {
            return null;
        }
    }
}
