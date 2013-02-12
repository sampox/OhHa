/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author b4d
 */
public class CardTest {
    
    public CardTest() {
    }
    
    Card card;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        card = new Card(1,"Diamonds");
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
    public void testaaSuitOikein() {
        assertEquals("Diamonds",card.getSuit());
    }
    
    @Test
    public void testaaSuitOikein2() {
        card = new Card(2,"Spades");
        assertEquals("Spades",card.getSuit());
    }
      
    @Test
    public void testaaValueOikein() {
        assertEquals(1,card.getValue());
    }
    
    @Test
    public void testaaValueOikein2() {
        card = new Card(12,"Hearts");
        assertEquals(12,card.getValue());
    }
    
    @Test
    public void testaaKonstruktoriJa_toString1() {
        assertEquals("1 of Diamonds",card.toString());
    }
    @Test
    public void testaaKonstruktoriJa_toString2() {
        card = new Card(8,"Spades");
        assertEquals("8 of Spades",card.toString());
    }
    @Test
    public void testaaKonstruktoriJa_toString3() {
        card = new Card(11,"Clubs");
        assertEquals("11 of Clubs",card.toString());
    }
    @Test
    public void testaaBlackjackValueOnOikeinAce() {
        assertEquals(11,card.getBlackjackValue());
    }
    @Test
    public void testaaBlackjackValueOikeinAlleKymmenen() {
        card = new Card(8,"Spades");
        assertEquals(8,card.getBlackjackValue());
    }
    @Test
    public void testaaBlackjackValueOikeinYliKymmenen() {
        card = new Card(11,"Spades");
        assertEquals(10,card.getBlackjackValue());
    }
}
