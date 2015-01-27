/**
 * 
 */
package flashCards;

import java.io.IOException;
import java.util.ArrayList;

import simpleIO.SimpleIO;

/**
 * @author Joo-nam Kim & Ted Fujimoto
 */
public class StudyList {
	
	ArrayList<Item> itemList = new ArrayList<Item>();
	ArrayList<String> stringFile;
	Item instanceItem; 
	
    public StudyList() { 
        // TODO Auto-generated constructor stub
    	
    }
    
    /**
     * Add item to itemList object. 
     * @param item
     */
    public void add(Item item) {
    	
    	itemList.add(item);
    	
    }
    
    /**
     * Finds an item for user
     * @param stimulusOrResponse
     * @return the found item. 
     */
    public Item find(String stimulusOrResponse) {
    	
    	for (Item item : itemList) { 
    		if (stimulusOrResponse.equals(item.getStimulus()) || stimulusOrResponse.equals(item.getResponse())) {
    			return item; 
    		}
    	}
		throw new IllegalArgumentException();
    }
    /**
     * delete an item from itemList object. 
     * @param item
     */
    public void delete(Item item) {
    	
        itemList.remove(item);
    
    }
    
    /**
     * Modify an item by resetting stimulus and response. 
     * @param item
     * @param newStimulus
     * @param newResponse
     */
    public void modify(Item item, String newStimulus, String newResponse) {
        
    	item.setStimulus(newStimulus);
    	item.setResponse(newResponse);
    		
    }
    /**
     * load a file. 
     * @throws IOException
     */
    public void load() throws IOException {
        
    	stringFile = SimpleIO.load();

		for (String line : stringFile) {
			int timesCorrect;
			String[] lineParts = line.split(" *\\|\\| *");
			String stim = lineParts[0];
			String resp = lineParts[1];
			stim.trim();
			resp.trim();
			if (lineParts.length > 2) {
				timesCorrect = Integer.parseInt(lineParts[2]);
			} else {
				timesCorrect = 0;
			}
			Item item = new Item(stim, resp);
			item.setTimesCorrect(timesCorrect);
			itemList.add(item);
        }
    }
    /**
     * Save item
     * @throws IOException
     */
    public void save() throws IOException {
    	
    	SimpleIO.save(convertToStringArray(itemList)); 
    }
    /**
     * Save As Item
     * @throws IOException
     */
    public void saveAs() throws IOException {
    	
    	SimpleIO.saveAs(convertToStringArray(itemList)); 	
    	
    }
    /**
     * a Helper method to convert an Item ArrayList to a String ArrayList
     * used in saving files!
     * @param items
     * @return a String ArrayList
     */
    public ArrayList<String> convertToStringArray(ArrayList<Item> items) {
    	
    	ArrayList<String> returnAL = new ArrayList<String>();
    	
    	for (Item item : items) {
    		returnAL.add(item.getStimulus()+" || "+item.getResponse());
    	}
    	
		return returnAL;
    }
    /**
     * a Helper method to check if an itemList has a given item
     * @param item
     * @return true if itemList has it, false otherwise. 
     */
    public boolean hasItem(Item item) {
    	
    	for(Item i : itemList) {
    		if (i.equals(item)) {
    			return true;
    		}
    	}
    	return false;
    	
    }
    /**
     * itemList getter
     * @return itemList
     */
    public ArrayList<Item> getItemList(){
		return itemList;
	}
    
    
    
}
