/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author b4d
 */
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    
    public Deck() {
        for(int i=1;i<14;i++) {
            deck.add(new Card(i,"Clubs"));
            deck.add(new Card(i,"Diamonds"));
            deck.add(new Card(i,"Spades"));
            deck.add(new Card(i,"Hearts"));
        }
    }
    public int howManyCardsLeft() {
        return deck.size();
    }
    public void shuffle() {
        Collections.shuffle(deck);
    }
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
