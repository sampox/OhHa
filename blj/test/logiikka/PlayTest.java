/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.io.ByteArrayInputStream;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author b4d
 */
public class PlayTest {
    
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
    public void getTopPlayerWorks() {
        ByteArrayInputStream in = new ByteArrayInputStream("keke".getBytes());
        System.setIn(in);
        Play play = new Play(1);

        assertEquals("keke",play.getTopPlayer().getName());
    }
}
