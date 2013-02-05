/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tallennus;

/**
 * Rajapinta voittajien tallentamiselle ja lukemiselle.
 * 
 * @author b4d
 */
public interface IO {
    String lueTiedosto();
    void kirjoitaTiedostoon(String rivi);
}
