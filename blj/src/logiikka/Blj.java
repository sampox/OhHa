/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import UI.TUI;
import java.util.Scanner;

/**
 * Pelin Main-luokka, aloittaa pelin kutsumalla Play-luokkaa.
 * 
 * @author b4d
 */
public class Blj {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner lukija = new Scanner(System.in);
        TUI Tuitui = new TUI(lukija);
    }
}
