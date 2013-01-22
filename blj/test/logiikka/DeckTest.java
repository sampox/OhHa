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
public class DeckTest {
    private Deck testDeck;
    
    public DeckTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        testDeck = new Deck();
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
    public void testDeckHasAllCards() {
        assertEquals(52,testDeck.howManyCardsLeft());
    }
    @Test
    public void testTakingOneCard() {
        testDeck.dealTopCard();
        assertEquals(51,testDeck.howManyCardsLeft());
    }
    @Test
    public void testTakingCards() {
        testDeck.dealHand(3);
        assertEquals(49,testDeck.howManyCardsLeft());
    }
    @Test
    public void doNotDealFromEmptyDeck() {
        testDeck.dealHand(52);
        assertEquals(null,testDeck.dealTopCard());
    }
    @Test
    public void doNotDealOverDeckSize() {
        testDeck.dealHand(53);
        assertEquals(0,testDeck.howManyCardsLeft());
    }

}
