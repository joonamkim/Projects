/**
 * 
 */
package flashCards;
import java.util.ArrayList;

import simpleIO.SimpleIO;

/**
 * @author Joo-nam Kim
 */

public class Item {
	
	String stim;
	String resp; 
	int time; 
	/**
	 * Item constructor
	 * @param stimulus
	 * @param response
	 */
    public Item(String stimulus, String response) {
        // TODO Auto-generated constructor stub
    	stim = stimulus;
    	resp = response;
    	time = 0;
    }
    /**
     * stimulus getter
     * @return a stimulus
     */
    public String getStimulus() {
        return stim;
    }
    /**
     * stimulus setter
     * @param stimulus
     */
    public void setStimulus(String stimulus) {
        stim = stimulus;
    }
    /**
     * Response getter
     * @return a response
     */
    public String getResponse() {
        return resp;
    }
    /**
     * Response setter
     * @param response
     */
    public void setResponse(String response) {
        resp = response; 
    	
    }
    /**
     * timesCorrect getter
     * @return timesCorrect
     */
    public int getTimesCorrect() {
    	
        return time;
        
    }
    /**
     * timesCorrect setter
     * @param timesCorrect
     */
    public void setTimesCorrect(int times) {
        
    	time = times; 
    	
    }
}
