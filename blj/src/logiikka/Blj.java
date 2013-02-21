


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import UI.GUI;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Pelin Main-luokka, käynnistää ohjelman kutsumalla GUI:ta.
 * 
 * @author b4d
 */
public class Blj {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GUI freim = new GUI();
        try {
            GUI.main(args);
        } catch (IOException ex) {
            Logger.getLogger(Blj.class.getName()).log(Level.SEVERE, "SOMETHING WENT TERRIBLY WRONG WITH THE PROGRAM'S I/O! CONTACT GAME DEVELOPER AND SYSTEM ADMIN IMMEDIATELY!");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex1) {
                Thread.currentThread().interrupt();
            }
            System.exit(1);
        }
        
    }
}
