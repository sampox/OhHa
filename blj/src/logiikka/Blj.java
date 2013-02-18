


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
 * Pelin Main-luokka, käynnistää ohjelman kutsumalla UI:ta.
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
            Logger.getLogger(Blj.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
