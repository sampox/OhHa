/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.*;
import static org.junit.Assert.*;
import tallennus.Winners;

/**
 *
 * @author b4d
 */
public class PlayTest {
    Play play = new Play(new ArrayList<String>(),new Scanner(System.in));
    
    public PlayTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void getWinnersWorks() {
        Winners winner = new Winners();
        assertEquals(winner.lueTiedosto(),play.getWinners());
    }
    
    @Test
    public void playersHandAndCardsWorks() {
        assertEquals(play.playersHandAndCards(new Player("keke",new Hand())),"keke's cards are: []\nkeke's hand has value of: 0");
    }
    
}
