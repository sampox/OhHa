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
 * Luokka hoitaa ohjelman tiedostonkäsittelytarpeet, lähinnä voittajien tallenuksen ja lukemisen.
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
 * Konstruktori asettaa luokassa käsiteltäväksi tiedostoksi winners.txt tiedoston. Jos tiedostoa ei ole olemassa, se luodaan.
 *
 * @see logiikka.Play#winner
 */
    public Winners() {
        tiedosto = new File(winnersFile);
        if(!tiedosto.exists())
            try {
            tiedosto.createNewFile();
            kirjoitaTiedostoon("Player:\t\tHand value:");
        } catch (IOException ex) {
            handleException("");
        }
    
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
            handleException("");
        }
    }
    

    
      /**
 * Metodi lukee tiedoston winners.txt. Tai minkä tahansa muunkin tiedoston toisen konstruktorin tapauksessa.
 *
 * @see logiikka.Play#getWinners() 
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
            handleException("SPECIFIC PROBLEM: FILE NOT FOUND");
        }
        return sisalto;
    }
  /**
 * Metodi kirjoittaa pelin voittajan nimen ja käden arvon tiedostoon. 
 * Tai minkä tahansa muunkin String-olion testauskäytössä.
 *
 * @see logiikka.Play#saveWinner(java.lang.String, int) 
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
            handleException("");
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
    
    /**
    * Metodi virheiden käsittelyä varten, käyttäjä saa virheilmoituksen, ohjelma odottaa 4 sek ja sulkeutuu.
    *
    * @see tallennus.Winners#Winners() 
    * @see tallennus.Winners#Winners(java.lang.String) 
    * @see tallennus.Winners#kirjoitaTiedostoon(java.lang.String) 
    * @see tallennus.Winners#lueTiedosto() 
    * @param message String olio jossa on viesti jos virhe ei ole normaali IOException 
    */
    private void handleException(String message) {
        Logger.getLogger(Winners.class.getName()).log(Level.SEVERE, "EXCEPTION - SOMETHING IS WRONG WITH THE FILE: {0} !!!", tiedosto.getName());
        System.out.println(message);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.exit(1);
    }
}
