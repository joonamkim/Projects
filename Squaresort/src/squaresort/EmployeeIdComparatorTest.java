package squaresort;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Joo-nam Kim
 *
 */
public class EmployeeIdComparatorTest {

	@Test
	public void testCompare() {
		
		Person one = new Person();
		Person two = new Person();
		Person three = new Person();
		Person four = new Person();
		
		EmployeeIdComparator eic = new EmployeeIdComparator();
		assertTrue(eic.compare(one, one) == 0);
		assertTrue(eic.compare(two, three) < 0);
		assertTrue(eic.compare(four, two) > 0);
	}

}
