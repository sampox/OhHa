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
public class PlayerTest {
    
    public PlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testName() {
        Player testPlayer = new Player("Nakki",new Deck().dealHand(5));
        assertEquals("Nakki",testPlayer.getName());
    }
    @Test
    public void handIsCorrect() {
        Hand hand = new Hand();
        hand.addCard(new Card(2,"diamonds"));
        hand.addCard(new Card(3,"clubs"));
        Player testPlayer = new Player("Nakki",hand);
        assertEquals(hand,testPlayer.getHand());
    }
}
