/**
 * 
 */
package flashCards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Joo-nam Kim & Ted Fujimoto
 */
public class StudyListTest {

    /**
     * @throws java.lang.Exception
     */
	StudyList studyList = new StudyList();
	
    @Before
    public void setUp() throws Exception {
    
    }

    /**
     * Test method for {@link flashCards.StudyList#add(flashCards.Item)}.
     */
    @Test
    public final void testAdd() {
    	
        ArrayList<Item> addArray = new ArrayList<Item>();
        
        Item itemAdd = new Item("Joo-nam", "Kim");
        Item ted = new Item("Ted", "Fujimoto");
        Item card2 = new Item("Function within class", "Method");
    	addArray.add(itemAdd);
    	addArray.add(ted);
    	addArray.add(card2);
    	
        assertEquals(itemAdd, addArray.get(0));
        assertEquals(ted, addArray.get(1));
        assertEquals(card2, addArray.get(2));
       
    }

    /**
     * Test method for {@link flashCards.StudyList#find(java.lang.String)}.
     */
    @Test
    public final void testFind() {
    	
    	StudyList stToTestFind = new StudyList();
    	
    	Item item = new Item("life is", "good");
    	Item item2 = new Item("Programming", "is fun");
    	Item item3 = new Item("Java", "is amazing");
    	Item item4 = new Item("Can't think of", "more examples");
    	
    	stToTestFind.add(item);
    	stToTestFind.add(item2);
    	stToTestFind.add(item3);
    	stToTestFind.add(item4);
    	
    	assertEquals(item, stToTestFind.find("life is"));
    	assertEquals(item, stToTestFind.find("good"));
    	assertEquals(item2, stToTestFind.find("Programming"));
    	assertEquals(item2, stToTestFind.find("is fun"));
    	assertEquals(item3, stToTestFind.find("Java"));
    	assertEquals(item3, stToTestFind.find("is amazing"));
    	assertEquals(item4, stToTestFind.find("Can't think of"));
    	assertEquals(item4, stToTestFind.find("more examples"));
    	
    	
    	
    }

    /**
     * Test method for {@link flashCards.StudyList#delete(flashCards.Item)}.
     */
    @Test
    public final void testDelete() {
    	
    	StudyList stToTestFind = new StudyList();
    	
    	Item item = new Item("life is", "good");
    	Item item2 = new Item("Programming", "is fun");
    	Item item3 = new Item("Java", "is amazing");
    	Item item4 = new Item("Can't think of", "more examples");
    	
    	stToTestFind.add(item);
    	stToTestFind.add(item2);
    	stToTestFind.add(item3);
    	stToTestFind.add(item4);
    	
    	stToTestFind.delete(item4);
    	assertEquals(3, stToTestFind.itemList.size());
    	assertFalse(stToTestFind.hasItem(item4));
    	stToTestFind.delete(item3);
    	assertFalse(stToTestFind.hasItem(item3));
    	assertTrue(stToTestFind.hasItem(item2));
    	
    }

    /**
     * Test method for {@link flashCards.StudyList#modify(flashCards.Item, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testModify() {
    	
    	StudyList stToTestModify = new StudyList();
    	
    	Item item = new Item("life is", "good");
    	Item item2 = new Item("Programming", "is fun");
    	Item item3 = new Item("Java", "is amazing");
    	Item item4 = new Item("Can't think of", "more examples");
    	
    	stToTestModify.add(item);
    	stToTestModify.add(item2);
    	stToTestModify.add(item3);
    	stToTestModify.add(item4);
    	
    	stToTestModify.modify(item4, "Joo", "Kim");
    	
    	assertEquals("Joo", stToTestModify.itemList.get(3).getStimulus());
    	assertEquals("Kim", stToTestModify.itemList.get(3).getResponse());
    	
    	stToTestModify.modify(item3, "Python", "is better");
    	assertEquals("Python", stToTestModify.itemList.get(2).getStimulus());
    	assertEquals("is better", stToTestModify.itemList.get(2).getResponse());
    	
    	stToTestModify.modify(item2, "Nikanomome", "is better");
    	assertEquals("Nikanomome", stToTestModify.itemList.get(1).getStimulus());
    	assertEquals("is better", stToTestModify.itemList.get(1).getResponse());
    	
    }
    
    @Test
    public final void testConvertToStringArray() {
    	
    	ArrayList<String> stringFile = new ArrayList<String>
        (Arrays.asList(new String[]{"I want || hoshii desu || 4",
                "I don't want || hoshiku arimasen",
                "now || ima || 2",
                "yes (casual) || ee || 3",
                "at my place || watashi no tokoro de",
                "at your place || anata no tokoro de",
                "you are skilled || jozu desu",
                "I am not skilled. || Jozu ja arimasen. || 0"}));
        
        ArrayList<Item> itemArrayList = new ArrayList<Item>();
        for(String line : stringFile) { 
        	String[] lineParts = line.split(" *\\|\\| *");
        	String stim = lineParts[0];
        	String resp = lineParts[1];
        	stim.trim();
        	resp.trim();
        	if (lineParts.length > 2) {
        		String timesCorrect = lineParts[2];
        	}
        	Item item = new Item(stim, resp);
        	itemArrayList.add(item);
        }
        ArrayList<String> testResultArray = new ArrayList<String>
        (Arrays.asList(new String[]{"I want || hoshii desu",
                "I don't want || hoshiku arimasen",
                "now || ima",
                "yes (casual) || ee",
                "at my place || watashi no tokoro de",
                "at your place || anata no tokoro de",
                "you are skilled || jozu desu",
                "I am not skilled. || Jozu ja arimasen."}));
        
        ArrayList<String> testArray = studyList.convertToStringArray(itemArrayList);
        
        assertEquals(testResultArray, testArray);
        
        assertEquals("I want || hoshii desu", testArray.get(0));
        assertEquals(8, testArray.size());
        assertEquals("I am not skilled. || Jozu ja arimasen.", testArray.get(7));
    
    }
}
