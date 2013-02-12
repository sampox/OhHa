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
        testHand = new Hand();
        testHand.addCard(new Card(1,"Spades"));
        testHand.addCard(new Card(1,"Diamonds"));
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
    public void testEmptyHandSize() {
        testHand = new Hand();
        assertEquals(0,testHand.howManyCards());
    }
    @Test
    public void testLargeHandSize() {
        for(int i=0;i<10;i++) {
            testHand.addCard(new Card(i,"Hearts"));
        }
        assertEquals(12,testHand.howManyCards());
    }
    @Test
    public void testRightAmountofCardsAfterAddingACard() {
        testHand.addCard(new Card(5,"Diamonds"));
        assertEquals(3,testHand.getCards().size());
        
    }
    @Test
    public void testRightAmountofCardsAfterAddingTwoCards() {
        testHand.addCard(new Card(3,"Spades"));
        testHand.addCard(new Card(6,"Diamonds"));
        assertEquals(4,testHand.getCards().size());
    }
    @Test
    public void testCardInHandAfterAddingIt() {
        Card pataJatka = new Card(11,"Spades");
        testHand.addCard(pataJatka);
        assertEquals(testHand.getCards().contains(pataJatka),true);
    }
    @Test
    public void testCardInHandAfterAddingIt2() {
        Card ruutuKunkku = new Card(13,"Diamonds");
        testHand.addCard(ruutuKunkku);
        assertEquals(testHand.getCards().contains(ruutuKunkku),true);
    }
    @Test
    public void testBlackjackValueWithLowCards() {
        testHand = new Hand();
        testHand.addCard(new Card(2,"Spades"));
        testHand.addCard(new Card(3,"Spades"));
        testHand.addCard(new Card(6,"Spades"));
        assertEquals(11,testHand.getBlackjackValue());
    }
    @Test
    public void testBlackjackValueMixedCards() {
        testHand.addCard(new Card(2,"Diamonds"));
        assertEquals(14,testHand.getBlackjackValue());
    }
    @Test
    public void testBlackjackValueWithMiddleCards() {
        testHand = new Hand();
        testHand.addCard(new Card(8,"Spades"));
        testHand.addCard(new Card(7,"Spades"));
        testHand.addCard(new Card(6,"Spades"));
        assertEquals(21,testHand.getBlackjackValue());
    }
    @Test
    public void testBlackjackValueWithAllAces() {
        testHand.addCard(new Card(1,"Hearts"));
        testHand.addCard(new Card(1,"Clubs"));
        assertEquals(14,testHand.getBlackjackValue());
    }
    @Test
    public void testBlackjackValueWithLargeCards() {
        testHand = new Hand();
        testHand.addCard(new Card(10,"Spades"));
        testHand.addCard(new Card(11,"Spades"));
        testHand.addCard(new Card(12,"Spades"));
        assertEquals(30,testHand.getBlackjackValue());
    }
    @Test
    public void testLegalityWhenIllegal() {
        testHand.addCard(new Card(10,"Diamonds"));
        assertEquals(false,testHand.getLegality());
    }
    @Test
    public void testLegalityWhenLegal() {
        assertEquals(true,testHand.getLegality());
    }
    @Test
    public void testLegalityAtTwentyOne() {
        testHand.addCard(new Card(9,"Diamonds"));
        assertEquals(true,testHand.getLegality());
    }
}
