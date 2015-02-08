package squaresort;

import java.util.Comparator;
/**
 * 
 * @author Joo-nam Kim
 *
 */
public class EmployeeIdComparator implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		
		return o1.employeeId - o2.employeeId;
	}

}
