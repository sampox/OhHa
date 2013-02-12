/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tallennus;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Voittajat voidaan tallentaa tiedostoon ja ko tiedosto voidaan sitten lukea.
 * 
 * @author b4d
 */
public class Winners implements IO {
    /**
 * File-olio jota luokan metodit käsittelevät.
 */
    File tiedosto;
        /**
 * String-olio joka sisältää tiedostopolun tiedostoon johon kirjataan/josta luetaan voittajat.
 */
    private final String winnersFile = "winners.txt";
    
      /**
 * Konstruktori asettaa luokassa käsiteltäväksi tiedostoksi winners.txt tiedoston.
 *
 */
    public Winners() {
        tiedosto = new File(winnersFile);
    }
      /**
 * Tämä konstruktori asettaa parametrina annetun tiedostonnimen luokassa käsiteltäväksi tiedostoksi,
 * winners.txt tiedoston sijaan. Testauskäyttöä varten.
 *
 */
    public Winners(String fileName) {
        tiedosto = new File(fileName);
        try {
            tiedosto.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Winners.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
      /**
 * Metodi lukee tiedoston winners.txt. Tai minkä tahansa muunkin tiedoston toisen konstruktorin tapauksessa.
 *
 * @return tiedoston sisältö
 */
    @Override
    public String lueTiedosto() {
        String sisalto= "";
        try {
            Scanner lukija = new Scanner(tiedosto);
            while(lukija.hasNextLine()) {
            sisalto+=lukija.nextLine()+"\n";
            }
            lukija.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Winners.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sisalto;
    }
  /**
 * Metodi kirjoittaa pelin voittajan nimen ja käden arvon tiedostoon. 
 * Tai minkä tahansa muunkin String-olion testauskäytössä.
 *
 * @param rivi voittajan nimi ja käden arvo
 */
    @Override
    public void kirjoitaTiedostoon(String rivi) {
        try {
            Writer output;
            output = new BufferedWriter(new FileWriter(tiedosto, true));
            output.append(rivi+"\n");
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Winners.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      /**
 * Metodi poistaa tiedoston jota luokka käsittelee, tarvitaan testitiedostojen 
 * poistoon testien jälkeen. 
 *
 */
    public void deleteFile() {
        tiedosto.delete();
    }
    
}
