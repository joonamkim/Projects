/**
 * 
 */
package flashCards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Joo-nam Kim
 */
public class ItemTest {

    /**
     * @throws java.lang.Exception
     */
	
	
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link flashCards.Item#setStimulus(java.lang.String)} and
     * {@link flashCards.Item#getStimulus()} (combined).
     */
    @Test
    public final void testSetAndGetStimulus() {
    	
    	Item item = new Item("life is", "good");
    	Item item2 = new Item("Programming", "is fun");
    	Item item3 = new Item("Java", "is amazing");
    	Item item4 = new Item("Can't think of", "more examples");
    	
    	assertEquals("life is", item.getStimulus());
    	assertEquals("Programming", item2.getStimulus());
    	item3.setStimulus("nono");
    	item4.setStimulus("yesyes");
    	assertEquals("nono", item3.getStimulus());
    	assertEquals("yesyes", item4.getStimulus());
    	
    }
    
    /**
     * Test method for {@link flashCards.Item#setResponse(java.lang.String)} and
     * {@link flashCards.Item#getResponse()} (combined).
     */
    @Test
    public final void testSetAndGetResponse() {
    	
    	Item item = new Item("life is", "good");
    	Item item2 = new Item("Programming", "is fun");
    	Item item3 = new Item("Java", "is amazing");
    	Item item4 = new Item("Can't think of", "more examples");
    	
    	assertEquals("good", item.getResponse());
    	assertEquals("is fun", item2.getResponse());
    	item3.setResponse("nono");
    	item4.setResponse("yesyes");
    	assertEquals("nono", item3.getResponse());
    	assertEquals("yesyes", item4.getResponse());
    	
    }

    /**
     * Test method for {@link flashCards.Item#setTimesCorrect(int)} and
     * {@link flashCards.Item#getTimesCorrect()} (combined).
     */
    @Test
    public final void testSetAndGetTimesCorrect() {
    	
    	Item item = new Item("life is", "good");
    	Item item2 = new Item("Programming", "is fun");
    	Item item3 = new Item("Java", "is amazing");
    	Item item4 = new Item("Can't think of", "more examples");
    	
    	item.setTimesCorrect(5); 
    	assertEquals(5, item.getTimesCorrect());
    	item.setTimesCorrect(4);
    	assertEquals(4, item.getTimesCorrect());
    	
    	item2.setTimesCorrect(6);
    	assertEquals(6, item2.getTimesCorrect());
    	
    	item3.setTimesCorrect(12);
    	assertNotEquals(5, item3.getTimesCorrect());
    	
    }

}
