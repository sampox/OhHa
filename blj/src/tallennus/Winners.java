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
 *
 * @author b4d
 */
public class Winners implements IO {

    public Winners() {}
    
    File tiedosto = new File("winners.txt");
    
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
    
}
