/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tallennus;

import org.junit.*;
import static org.junit.Assert.*;

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
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testaaLukeminenJaPalauttaakoSisaltoa() {
        String test = winners.lueTiedosto();
        assertEquals(test.isEmpty(),false);
    }
    @Test
    public void testaaKirjoitus() {
        String test = (int)(Math.random()*10000)+"\t\t3";
        winners.kirjoitaTiedostoon(test);
        String tiedostonSisalto = winners.lueTiedosto();
        assertEquals(tiedostonSisalto.contains(test),true);
    }
}
