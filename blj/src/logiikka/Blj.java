/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

/**
 *
 * @author b4d
 */
public class Blj {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Deck v = new Deck();
        v.shuffle();
        Hand a = v.dealHand(2);
        System.out.println(a.toString());
    }
}
