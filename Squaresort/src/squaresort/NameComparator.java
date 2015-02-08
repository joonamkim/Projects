package squaresort;

import java.util.Comparator;

/**
 * 
 * @author Joo-nam Kim
 *
 */
public class NameComparator implements Comparator<Person>{
	
	@Override
	public int compare(Person o1, Person o2) {
		
		assert o1.givenName != null && o2.givenName != null;
		
		int compareGiven = o1.givenName.toLowerCase().compareTo(o2.givenName.toLowerCase()); // lower case them
		int compareSurname = o1.surname.toLowerCase().compareTo(o2.surname.toLowerCase());   // and compare
		
		if (compareSurname < 0) {
			return -1; 
		} 
		else if (compareSurname > 0) {
			return 1; 
		} else { // if same
			return compareGiven;
		}
		
	}
	

}
