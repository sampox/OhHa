/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author b4d
 */
public class HandTest {
    private Hand testHand;
    
    public HandTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(1,"Spades")); cards.add(new Card(2,"Diamonds"));
        testHand = new Hand(cards);
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
    public void testInitialHandSize() {
        assertEquals(2,testHand.howManyCards());
    }
    @Test
    public void testLargeHandSize() {
        for(int i=0;i<10;i++) {
            testHand.addCard(new Card(i,"Hearts"));
        }
        assertEquals(12,testHand.howManyCards());
    }
    @Test
    public void testAddingCard() {
        testHand.addCard(new Card(13,"Diamonds"));
        assertEquals(3,testHand.getCards().size());
    }
    @Test
    public void testBlackjackValue() {
        testHand.addCard(new Card(13,"Diamonds"));
        assertEquals(13,testHand.getBlackjackValue());
    }
    
}
