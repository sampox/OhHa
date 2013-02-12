/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tallennus;

import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author b4d
 */
public class WinnersTest {
    private Winners winners;
    
    public WinnersTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        winners = new Winners();
        
    }
    
    @After
    public void tearDown() {
       if(!winners.tiedosto.getName().equals("winners.txt"))
        winners.deleteFile();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testaaLukeminenJaPalauttaakoSisaltoa() {
        assertEquals(winners.lueTiedosto().isEmpty(),false);
    }
    @Test
    public void testaaKirjoitus() {
        String test = (int)(Math.random()*10000)+"\t\t3";
        winners.kirjoitaTiedostoon(test);
        assertEquals(winners.lueTiedosto().contains(test),true);
    }
    @Test
    public void testaaLukeminenUudellaTestitiedostolla() {
        winners = new Winners(""+Math.random()*10000);
        assertEquals(winners.lueTiedosto().isEmpty(),true);
    }
    @Test
    public void testaaKirjoitusUudellaTestitiedostolla() {
        winners = new Winners(""+Math.random()*10000);
        String test = (int)(Math.random()*10000)+"kekeg";
        winners.kirjoitaTiedostoon(test);
        assertEquals(winners.lueTiedosto().contains(test),true);
    }
}
