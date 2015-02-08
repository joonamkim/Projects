package squaresort;

import java.util.Comparator;
/**
 * 
 * @author Joo-nam Kim
 *
 */
public class PayGradeComparator implements Comparator<Person>{
	
	/**
	 * compares two Person objects by their pay grades.
	 */
	@Override
	public int compare(Person o1, Person o2) {
		
		return o1.payGrade - o2.payGrade;

	}

}
